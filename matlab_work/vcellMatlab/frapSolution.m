classdef frapSolution < handle
    %FRAPSOLUTION Summary of this class goes here
    %   Detailed explanation goes here
    
    properties
        % problem to solve
        problem           % object that holds parameters and geometry
        
        % discretization
        xarray            % x coordinate of each mesh element, number of non-zero mask points 1*n
        yarray            % y coordinate of each mesh element, number of non-zero mask points 1*n
        A_matrix          % sparse laplacian matrix, cell region indexing
        numMeshPoints     % number of non-zero mask points
        
        % smallest N eigenvalues/eigenvectors
        eigenVectors      % V matrix (each column is an eigenvector?) non-zero mask points n * m (numOfEigenValues)
        eigenValues       % vector (not diagonal D matrix) numOfEigenValues * 1
        
        % initial total fluorescence in mesh indices
        initialFluorescence % number of non-zero mask points n*1
        
        % solution
        solutionTimes     % vector of times (including 0.0)
        solutionValues    % 2D dense matrix (numTimes x 2*numMeshPoints)
                          %   each solution vector is [Mobile Immobile]'
                          
        % solution projections of M+I = fluorescence for each eigenvector.
        projection        % numTimes rows X numEigenvalues columns
    end
    
    methods
        %
        % function solutionM = getSolutionM(this)
        % function solutionI = getSolutionI(this)
        % function solutionT = getSolutionT(this)
        % function eigenvector = getEigenvector(this, index)
        % function eigenvector = getEigenvalue(this, index)
        % function numEigenvalues = getNumEigenvalues(this)
        %
        % function animateSolution(this, var, framePauseTime)
        % function plotInitialConditions(this)
        % function plotSolution(this,var,timeIndex)
        % function plotMesh(this, var, figure1, figure2, bSymmetric)
        %
        % function computeEigenvalues(this, numSmallEigenvalues)
        % function animateEigenfunctions(this, framePauseTime)
        %
        % function solve(this)
        % function yp = frapPdeRHS_local(this, t, y)
        % function init = getGaussianBleachPattern(this, bleachCenterX, bleachCenterY, bleachRadiusFactor)
        % function buildMatrix(this)
        % 
        %
        function solutionM = getSolutionM(this)
            solutionM = this.solutionValues(:,1:this.numMeshPoints);
        end
        
        function solutionI = getSolutionI(this)
            solutionI = this.solutionValues(:,this.numMeshPoints+1:2*this.numMeshPoints);
        end
        
        function solutionT = getSolutionT(this)
            solutionT = this.getSolutionM() + this.getSolutionI();
        end
        
        function eigenvector = getEigenvector(this, index)
            eigenvector = this.eigenVectors(:,index);
        end
        
        function eigenvalue = getEigenvalue(this, index)
            eigenvalue = this.eigenValues(index);
        end
        
        function numEigenvalues = getNumEigenvalues(this)
            numEigenvalues = length(this.eigenValues);
        end
        
        function animateSolution(this, var, framePauseTime)
            numTimePoints = length(this.solutionTimes);
             for ii = 1:numTimePoints
                this.plotSolution(var,ii);
                if (framePauseTime == 0)
                    pause();
                else
                    pause(framePauseTime);
                end
            end
        end

        function plotInitialConditions(this)
            plotMesh(this,this.initialFluorescence,1,3,false);
        end
        
        function plotSolution(this,var,timeIndex)
            plotMesh(this,var(timeIndex,:),1,3,false);
        end
        
        function projectFluorescence(this)
            T = this.getSolutionT();   % numTimes x numMeshpoints
            this.projection = T*this.eigenVectors;
        end
        
        function projectExpFluorescence(this)
            this.projection = this.problem.expFluorescence * this.eigenVectors;
        end
        
        function plotMesh(this, var, figure1, figure2, bSymmetric)
            mask = this.problem.mask;
            numX = this.problem.numX;
            numY = this.problem.numY;
            mesh = zeros(numX,numY);
            for maskj=1:numY
                for maski=1:numX
                    if (mask(maski,maskj)>0)
                        mesh(maski,maskj) = var(mask(maski,maskj));
                    end
                end
            end
            meshmin = min(min(mesh));
            meshmax = max(max(mesh));
            if (bSymmetric == 1)
                maxAbsValue = max(abs(meshmin),abs(meshmax));
                normmesh = 100 + 100*mesh/maxAbsValue;
            else
                normmesh = (mesh-meshmin)/(meshmax-meshmin)*200;
            end
            figure(figure1)
            imagesc(normmesh);
            figure(figure2)
            surfc(normmesh);
            if (bSymmetric == 1)
                axis([0 this.problem.numY 0 this.problem.numX 0 200]);
            end
            figure(figure1)
        end
        
        function computeEigenvalues(this, numSmallEigenvalues)
            display(sprintf('\nstarting eigs() %d smallest', numSmallEigenvalues));    
            tic();
            [V,D] = eigs(this.A_matrix,numSmallEigenvalues,'sm');
            this.eigenVectors = V;
            this.eigenValues = spdiags(D);%Extract and create sparse band and diagonal matrices
            toc();
            display(sprintf('done with eigs() %d smallest', numSmallEigenvalues));
        end
        
        function animateEigenfunctions(this, framePauseTime)
            numEigenvalues = length(this.eigenValues);
             for ii = 1:numEigenvalues
                plotMesh(this, this.getEigenvector(ii), 4, 5, true);
                if (framePauseTime == 0)
                    pause();
                else
                    pause(framePauseTime);
                end
            end
        end

        function solve(this)
            display(sprintf('\nstarting solve()'));    
            tic();
            Kon = this.problem.Kon;
            Koff = this.problem.Koff;
            y_init = [Koff/(Kon+Koff)*this.initialFluorescence ; Kon/(Koff+Kon)*this.initialFluorescence];
            options = odeset('RelTol',1e-5,'AbsTol',1e-6);
            [this.solutionTimes, this.solutionValues] = ode15s(@(t,y) this.frapPdeRHS_local(t,y),this.solutionTimes,y_init,options);
            toc();
            display(sprintf('done with solve()'));
        end
        
        function yp = frapPdeRHS_local(this, t, y)
            %ode15s(@frapPdeRHS);
            % y = [M I]
            D = this.problem.D;
            Kon = this.problem.Kon;
            Koff = this.problem.Koff;
            A_laplacian = this.A_matrix; 
            n=length(y)/2;
            yp = zeros(size(y));
            yp(n+1:2*n) = Kon*y(1:n)-Koff*y(n+1:2*n);
            yp(1:n) = -yp(n+1:2*n);  % - kon*y(1:n) + koff*y(n+1:2*n)
            yp(1:n) = yp(1:n) + D*A_laplacian*y(1:n);
        end
        
        function init = getGaussianBleachPattern(this, bleachCenterX, bleachCenterY, bleachRadiusFactor)
            % use ERF (error function) for a Circle convolved with a Gaussian
            x = this.xarray-bleachCenterX;  % center at (0.7,0.7)
            y = this.yarray-bleachCenterY;  % center at (0.7,0.7)
            r_squared = x.*x+y.*y;
            init = erf((r_squared'*bleachRadiusFactor));
        end
        
        function buildMatrix(this)
            numX = this.problem.numX;
            numY = this.problem.numY;
            mask = this.problem.mask;
            iarray = 0 * (1:5*numX*numY); % initialized with a little extra room
            jarray = 0 * (1:5*numX*numY); % initialized with a little extra room
            sarray = 0 * (1:5*numX*numY); % initialized with a little extra room
            this.xarray = 0 * (1:numX*numY); % initialized with a little extra room
            this.yarray = 0 * (1:numX*numY); % initialized with a little extra room
            elementIndex = 1;
            numOffDiagonals_x = 0;
            numOffDiagonals_y = 0;
            indexDiag = 0;
            D = this.problem.D;
            h_x = this.problem.domainSizeX/(this.problem.numX-1);
            lambda_x = D/h_x/h_x;
            h_y = this.problem.domainSizeY/(this.problem.numY-1);
            lambda_y = D/h_y/h_y;
            for j=1:numY
                for i=1:numX
                    if (mask(i,j)>0)
                        indexDiag = indexDiag+1;
                        if mask(i,j) ~= indexDiag
                            display('indexing error');
                        end
                        % count number of nonzeros
                        numOffDiagonals_x = 0; % diagonal
                        numOffDiagonals_y = 0; % diagonal
                        if (j>1) && mask(i,j-1)>0 
                            numOffDiagonals_y = numOffDiagonals_y + 1;   
                        end;
                        if (j<numY) && mask(i,j+1)>0 
                            numOffDiagonals_y = numOffDiagonals_y + 1;   
                        end;
                        if (i>1)  && mask(i-1,j)>0
                            numOffDiagonals_x = numOffDiagonals_x + 1;   
                        end;
                        if (i<numX)  && mask(i+1,j)>0
                            numOffDiagonals_x = numOffDiagonals_x + 1;   
                        end;

                        % minus Y
                        if (j>1)  && mask(i,j-1)>0
                            iarray(elementIndex) = indexDiag;
                            jarray(elementIndex) = mask(i,j-1); %indexDiag - numX;
                            sarray(elementIndex) = lambda_y;
                            elementIndex = elementIndex + 1;
                        end

                        % minus X
                        if  i>1 && mask(i-1,j)>0
                            iarray(elementIndex) = indexDiag;
                            jarray(elementIndex) = mask(i-1,j); %indexDiag - 1;
                            sarray(elementIndex) = lambda_x;
                            elementIndex = elementIndex + 1;
                        end

                        % Diagonal
                        iarray(elementIndex) = indexDiag;
                        jarray(elementIndex) = indexDiag;
                        sarray(elementIndex) = - numOffDiagonals_x * lambda_x - numOffDiagonals_y * lambda_y;
                        this.xarray(indexDiag) = (i-1)*h_x;
                        this.yarray(indexDiag) = (j-1)*h_y;
                        elementIndex = elementIndex + 1;

                        % positive X
                        if (i<numX) && mask(i+1,j)>0
                            iarray(elementIndex) = indexDiag;
                            jarray(elementIndex) = mask(i+1,j); %indexDiag + 1;
                            sarray(elementIndex) = lambda_x;
                            elementIndex = elementIndex + 1;
                        end

                        % positive Y
                        if (j<numY) && mask(i,j+1)>0
                            iarray(elementIndex) = indexDiag;
                            jarray(elementIndex) = mask(i,j+1); %indexDiag + numX;
                            sarray(elementIndex) = lambda_y;
                            elementIndex = elementIndex + 1;
                        end
            %         else
            %            % Diagonal
            %             iarray(elementIndex) = indexDiag;
            %             jarray(elementIndex) = indexDiag;
            %             sarray(elementIndex) = 1;S
            %             elementIndex = elementIndex + 1;
                    end
                end
            end
            this.numMeshPoints = indexDiag;
            iarray = iarray(1:elementIndex-1);
            jarray = jarray(1:elementIndex-1);
            sarray = sarray(1:elementIndex-1);
            this.xarray = this.xarray(1:this.numMeshPoints);
            this.yarray = this.yarray(1:this.numMeshPoints);
            this.A_matrix = sparse(iarray,jarray,sarray);

        end
    end
    
end

