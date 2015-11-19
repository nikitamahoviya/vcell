package org.vcell.vis.vtk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.vcell.vis.chombo.ChomboMembraneVarData;
import org.vcell.vis.chombo.ChomboMeshData;
import org.vcell.vis.mapping.CartesianMeshVtkFileWriter.CartesianMeshIndices;
import org.vcell.vis.mapping.ChomboVtkFileWriter.SimpleChomboCellIndices;
import org.vcell.vis.mapping.ChomboVtkFileWriter.SimpleChomboVisMembraneIndex;
import org.vcell.vis.vismesh.ChomboCellIndices;
import org.vcell.vis.vismesh.ChomboVisMembraneIndex;
import org.vcell.vis.vismesh.VisDataset.VisDomain;
import org.vcell.vis.vismesh.VisIrregularPolyhedron;
import org.vcell.vis.vismesh.VisIrregularPolyhedron.PolyhedronFace;
import org.vcell.vis.vismesh.VisLine;
import org.vcell.vis.vismesh.VisMesh;
import org.vcell.vis.vismesh.VisMeshData;
import org.vcell.vis.vismesh.VisPoint;
import org.vcell.vis.vismesh.VisPolygon;
import org.vcell.vis.vismesh.VisPolyhedron;
import org.vcell.vis.vismesh.VisSurfaceTriangle;
import org.vcell.vis.vismesh.VisTetrahedron;
import org.vcell.vis.vismesh.VisVoxel;

import vtk.vtkCell;
import vtk.vtkCellData;
import vtk.vtkDelaunay3D;
import vtk.vtkDoubleArray;
import vtk.vtkFieldData;
import vtk.vtkFloatArray;
import vtk.vtkGeometryFilter;
import vtk.vtkIdList;
import vtk.vtkIdTypeArray;
import vtk.vtkLine;
import vtk.vtkNativeLibrary;
import vtk.vtkObjectBase;
import vtk.vtkPointData;
import vtk.vtkPoints;
import vtk.vtkPolyData;
import vtk.vtkPolygon;
import vtk.vtkPolyhedron;
import vtk.vtkQuad;
import vtk.vtkTetra;
import vtk.vtkTriangle;
import vtk.vtkUnstructuredGrid;
import vtk.vtkUnstructuredGridGeometryFilter;
import vtk.vtkUnstructuredGridReader;
import vtk.vtkUnstructuredGridWriter;
import vtk.vtkVoxel;
import vtk.vtkWindowedSincPolyDataFilter;
import vtk.vtkXMLFileReadTester;
import vtk.vtkXMLUnstructuredGridReader;
import vtk.vtkXMLUnstructuredGridWriter;
//import vtk.vtkXdmfWriter;
import cbit.vcell.resource.NativeLib;

public class VtkGridUtils {
	
	public static final Logger LG = Logger.getLogger(VtkGridUtils.class);

	// Load VTK library and print which library was not properly loaded
	static {
		try {
			//in case loader thread not complete
			NativeLib.VTK.load();
		}catch (Exception e){
			LG.warn("exception loading VTK NativeLib",e);
		}
		vtkNativeLibrary.DisableOutputWindow(null);
		vtkObjectBase.JAVA_OBJECT_MANAGER.getAutoGarbageCollector().SetAutoGarbageCollection(true);
	}
	
	private VtkGridUtils(){
		
	}

	static vtkUnstructuredGrid getVolumeVtkGrid(VisDomain visDomain) throws IOException{
		
		vtkPoints vtkpoints = new vtkPoints();
		VisMesh vMesh = visDomain.getVisMesh();
		for (VisPoint visPoint : vMesh.getPoints()) {
		    vtkpoints.InsertNextPoint(visPoint.x,visPoint.y,visPoint.z);
		}

		vtkUnstructuredGrid vtkgrid = new vtkUnstructuredGrid();
		vtkgrid.Allocate(vMesh.getPoints().size(), vMesh.getPoints().size());
		vtkgrid.SetPoints(vtkpoints);

		int quadType = new vtkQuad().GetCellType();
		//int lineType = new vtkLine().GetCellType();
		int polygonType = new vtkPolygon().GetCellType();
		int triangleType = new vtkTriangle().GetCellType();
		int polyhedronType = new vtkPolyhedron().GetCellType();
		int voxelType = new vtkVoxel().GetCellType();
		int tetraType = new vtkTetra().GetCellType();

		for (VisPolygon visPolygon : vMesh.getPolygons()) {
		    vtkIdList pts = new vtkIdList();
		    int[] polygonPoints = visPolygon.getPointIndices();
		    for (int p : polygonPoints){
		        pts.InsertNextId(p);
		    }

		    int numPoints = polygonPoints.length;
		    if (numPoints == 4){
		        vtkgrid.InsertNextCell(quadType,pts);
		    }else if (numPoints == 3){
		        vtkgrid.InsertNextCell(triangleType,pts);
		    }else{
		        vtkgrid.InsertNextCell(polygonType,pts);
		    }
		}
		boolean bInitializedFaces = false;
		for (VisPolyhedron visPolyhedron : vMesh.getPolyhedra()) {
			if (visPolyhedron instanceof VisVoxel){
				VisVoxel voxel = (VisVoxel)visPolyhedron;
			    vtkIdList pts = new vtkIdList();
			    int[] polyhedronPoints = voxel.getPointIndices();
			    for (int p : polyhedronPoints){
			        pts.InsertNextId(p);
			    }
		        vtkgrid.InsertNextCell(voxelType,pts);
			}else if (visPolyhedron instanceof VisTetrahedron){
				VisTetrahedron visTet = (VisTetrahedron)visPolyhedron;
			    vtkIdList pts = new vtkIdList();
			    int[] tetPoints = visTet.getPointIndices();
			    for (int p : tetPoints){
			        pts.InsertNextId(p);
			    }
		        vtkgrid.InsertNextCell(tetraType,pts);
			}else if (visPolyhedron instanceof VisIrregularPolyhedron){
				VisIrregularPolyhedron irregPolyhedron = (VisIrregularPolyhedron)visPolyhedron;
			    vtkIdList faceStreamList = new vtkIdList();
			    int[] faceStream = irregPolyhedron.getVtkFaceStream();
			    for (int p : faceStream){
			        faceStreamList.InsertNextId(p);
			    }
			    if (!bInitializedFaces && vtkgrid.GetNumberOfCells()>0){
				    vtkgrid.InitializeFacesRepresentation(vtkgrid.GetNumberOfCells());
				}
				bInitializedFaces = true;
				vtkgrid.InsertNextCell(polyhedronType, faceStreamList);
			}else{
				throw new RuntimeException("unsupported polyhedron type: "+visPolyhedron.getClass().getName());
			}
		}
		
		vtkgrid.BuildLinks();
		
		int numCells = vtkgrid.GetCells().GetNumberOfCells();

		vtkCellData cellData = vtkgrid.GetCellData();
		VisMeshData meshData = visDomain.getVisMeshData();
		for (String varName : meshData.getVarNames()){
			double[] data = meshData.getData(varName);
		    vtkDoubleArray cellScalars1 = new vtkDoubleArray();
		    cellScalars1.SetNumberOfComponents(1);
		    cellScalars1.SetNumberOfTuples(numCells);
		    cellScalars1.SetName(varName);
//		    System.out.println("saving var "+varName);
		    cellScalars1.SetJavaArray(data);
			cellData.AddArray(cellScalars1);
		}
		
		vtkFieldData fieldData = vtkgrid.GetFieldData();
		vtkFloatArray timeValue = new vtkFloatArray();
		timeValue.SetNumberOfComponents(1);
		timeValue.SetNumberOfTuples(1);
		timeValue.SetName("TIME");
	    float time = (float)visDomain.getVisMeshData().getTime();
	    float timeArray[] = new float[] { time };
//		System.out.println("setting time "+time);
		timeValue.SetJavaArray(timeArray);
		fieldData.AddArray(timeValue);
		
//		vtkgrid.Squeeze();

		return vtkgrid;
	}

	private static void writeLegacy(vtkUnstructuredGrid vtkgrid, String filename, boolean bASCII){
		vtkUnstructuredGridWriter writer = new vtkUnstructuredGridWriter();
		if (bASCII){
			writer.SetFileTypeToASCII();
		}else{
			writer.SetFileTypeToBinary();
		}
		
		writer.SetInputDataObject(vtkgrid);
		writer.SetFileName(filename);
		writer.Update();
		long length = new File(filename).length();
		if (LG.isInfoEnabled()) {
			LG.info("saved to legacy file: "+filename+" with "+((bASCII)?"ASCII":"Binary")+" data encoding, length="+length+" bytes");
		}
	}
	
	static void writeXML(vtkUnstructuredGrid vtkgrid, String filename, boolean bASCII){
		vtkXMLUnstructuredGridWriter writer = new vtkXMLUnstructuredGridWriter();
		if (bASCII){
			writer.SetDataModeToAscii();
		}else{
			writer.SetCompressorTypeToNone();
			writer.SetDataModeToBinary();
		}
//		vtkEnSightWriter writer2 = new vtkEnSightWriter();
//		writer2.SetInputDataObject(id0, id1)
		
		writer.SetInputDataObject(vtkgrid);
		writer.SetFileName(filename);
		writer.Update();
		long length = new File(filename).length();
		if (LG.isInfoEnabled()) {
			LG.info("saved to XML file: "+filename+" with "+((bASCII)?"ASCII":"Binary")+" data encoding, length="+length+" bytes");
		}
	}
	
//	public void writeXDMF(vtkUnstructuredGrid vtkgrid, String filename){
//		vtkXdmfWriter writer = new vtkXdmfWriter();
//		writer.SetInputDataObject(vtkgrid);
//		writer.SetFileName(filename);
//		writer.Update();
//		long length = new File(filename).length();
//		System.out.println("saved to XDMF file: "+filename+", length="+length+" bytes");
//	}
//	
	static vtkUnstructuredGrid read(String filename){
		File file = new File(filename);
		if (!file.exists()){
			throw new RuntimeException("unstructured mesh file "+filename+" not found");
		}
		vtkXMLFileReadTester tester = new vtkXMLFileReadTester();
		tester.SetFileName(filename);
		vtkUnstructuredGrid vtkgrid = null;
		
		if (tester.TestReadFile() == 1){
			vtkXMLUnstructuredGridReader reader = new vtkXMLUnstructuredGridReader();
			reader.SetFileName(filename);
			reader.Update();
			vtkgrid = reader.GetOutput();
			if (LG.isInfoEnabled()) {
				LG.info("read from XML file "+filename+", of type "+tester.GetFileDataType());
			}
		}else{
			vtkUnstructuredGridReader reader = new vtkUnstructuredGridReader();
			reader.SetFileName(filename);
			reader.Update();
			vtkgrid = reader.GetOutput();
			if (LG.isInfoEnabled()) {
				LG.info("read from legacy file "+filename);
			}
		}
		//vtkgrid.BuildLinks();
		return vtkgrid;
	}

	private static void write(vtkUnstructuredGrid vtkgrid, String filename) {
		writeXML(vtkgrid,filename,false);		// default
		//writeXDMF(vtkgrid, filename);
//		writeXML(vtkgrid,filename,true);
//		writeLegacy(vtkgrid,filename,false);
//		writeLegacy(vtkgrid,filename,true);
	}

	public static VisTetrahedron[] createTetrahedra(VisIrregularPolyhedron clippedPolyhedron, VisMesh visMesh){
			vtkPolyData vtkpolydata = new vtkPolyData();
			vtkPoints vtkpoints = new vtkPoints();
			int polygonType = new vtkPolygon().GetCellType();
			int[] uniquePointIndices = clippedPolyhedron.getPointIndices();
			for (int point : uniquePointIndices){
				VisPoint visPoint = visMesh.getPoints().get(point);
				vtkpoints.InsertNextPoint(visPoint.x, visPoint.y, visPoint.z);
			}
			vtkpolydata.Allocate(100, 100);
			vtkpolydata.SetPoints(vtkpoints);
			
			for (PolyhedronFace face : clippedPolyhedron.getFaces()){
				vtkIdList faceIdList = new vtkIdList();
				for (int visPointIndex : face.getVertices()){
					int vtkpointid = -1;
					for (int i=0;i<uniquePointIndices.length;i++){
						if (uniquePointIndices[i] == visPointIndex){
							vtkpointid = i;
						}
					}
					faceIdList.InsertNextId(vtkpointid);
				}
				vtkpolydata.InsertNextCell(polygonType, faceIdList);
			}
			
			vtkDelaunay3D delaunayFilter = new vtkDelaunay3D();
			delaunayFilter.SetInputData(vtkpolydata);
			delaunayFilter.Update();
			delaunayFilter.SetAlpha(0.1);
			vtkUnstructuredGrid vtkgrid2 = delaunayFilter.GetOutput();
			delaunayFilter.Delete();
			ArrayList<VisTetrahedron> visTets = new ArrayList<VisTetrahedron>();
			int numTets = vtkgrid2.GetNumberOfCells();
			if (numTets<1){
				LG.debug("found no tets");
			}
	//		System.out.println("numFaces = "+vtkpolydata.GetNumberOfCells()+", numTets = "+numTets);
			for (int cellIndex=0; cellIndex<numTets; cellIndex++){
				vtkCell cell = vtkgrid2.GetCell(cellIndex);
				if (cell instanceof vtkTetra){
					vtkTetra vtkTet = (vtkTetra)cell;
					vtkIdList tetPointIds = vtkTet.GetPointIds();
					//
					// translate from vtkgrid pointids to visMesh point ids
					//
					int numPoints = tetPointIds.GetNumberOfIds();
					int[] visPointIds = new int[numPoints];
					for (int p=0; p<numPoints; p++){
						visPointIds[p] = uniquePointIndices[tetPointIds.GetId(p)];
					}
					VisTetrahedron visTet = new VisTetrahedron(visPointIds, clippedPolyhedron.getLevel(), clippedPolyhedron.getBoxNumber(), clippedPolyhedron.getBoxIndex(), clippedPolyhedron.getFraction(), clippedPolyhedron.getRegionIndex());
					visTets.add(visTet);
				}else{
					if (LG.isEnabledFor(Level.WARN)) {
						LG.warn("ChomboMeshMapping.createTetrahedra(): expecting a tet, found a "+cell.GetClassName());
					}
				}
			}
			return visTets.toArray(new VisTetrahedron[0]);
		}

	private static vtkUnstructuredGrid smoothUnstructuredGridSurface(vtkUnstructuredGrid grid){
		
		vtkUnstructuredGridGeometryFilter ugGeometryFilter = new vtkUnstructuredGridGeometryFilter();
		ugGeometryFilter.PassThroughPointIdsOn();
		ugGeometryFilter.MergingOff();
		ugGeometryFilter.SetInputData(grid);
		ugGeometryFilter.Update();
		vtkUnstructuredGrid surfaceUnstructuredGrid = ugGeometryFilter.GetOutput();
		String originalPointsIdsName = ugGeometryFilter.GetOriginalPointIdsName();
		
		{
		vtkCellData cellData = surfaceUnstructuredGrid.GetCellData();
		int numCellArrays = cellData.GetNumberOfArrays();
		for (int i=0;i<numCellArrays;i++){
			String cellArrayName = cellData.GetArrayName(i);
			if (LG.isDebugEnabled()) {
				LG.debug("CellArray("+i+") \""+cellArrayName+"\"");
			}
		}
		vtkPointData pointData = surfaceUnstructuredGrid.GetPointData();
		int numPointArrays = pointData.GetNumberOfArrays();
		for (int i=0;i<numPointArrays;i++){
			String pointArrayName = pointData.GetArrayName(i);
			if (LG.isDebugEnabled()) {
				LG.debug("PointArray("+i+") \""+pointArrayName+"\"");
			}
		}
		}
		
		vtkGeometryFilter geometryFilter = new vtkGeometryFilter();
		geometryFilter.SetInputData(surfaceUnstructuredGrid);
		geometryFilter.Update();
		vtkPolyData polyData = geometryFilter.GetOutput();
		
		vtkWindowedSincPolyDataFilter filter = new vtkWindowedSincPolyDataFilter();
		filter.SetInputData(polyData);
		filter.SetNumberOfIterations(15);
		filter.BoundarySmoothingOff();
		filter.FeatureEdgeSmoothingOff();
		filter.SetFeatureAngle(120.0);
		filter.SetPassBand(0.001);
		filter.NonManifoldSmoothingOff();
		filter.NormalizeCoordinatesOn();
		filter.Update();
		
		vtkPolyData smoothedPolydata = filter.GetOutput();
		
		vtkPoints smoothedPoints = smoothedPolydata.GetPoints();
		
		vtkPointData smoothedPointData = smoothedPolydata.GetPointData();
		vtkIdTypeArray pointIdsArray = (vtkIdTypeArray)smoothedPointData.GetArray(originalPointsIdsName);
		int pointsIdsArraySize = pointIdsArray.GetSize();
		vtkPoints origPoints = grid.GetPoints();
		for (int i=0;i<pointsIdsArraySize;i++){
			int pointId = pointIdsArray.GetValue(i);
			double[] smoothedPoint = smoothedPoints.GetPoint(i);
			origPoints.SetPoint(pointId, smoothedPoint);
		}
	
		return grid;
	}

	private static vtkUnstructuredGrid getMembraneVtkGrid(VisMesh visMesh, ChomboMeshData chomboMeshData) {
		vtkPoints vtkpoints = new vtkPoints();
		List<VisPoint> surfacePoints = visMesh.getSurfacePoints();
		for (VisPoint visPoint : surfacePoints) {
		    vtkpoints.InsertNextPoint(visPoint.x,visPoint.y,visPoint.z);
		}

		vtkUnstructuredGrid vtkgrid = new vtkUnstructuredGrid();
		vtkgrid.Allocate(surfacePoints.size(), surfacePoints.size());
		vtkgrid.SetPoints(vtkpoints);

		if (visMesh.getDimension() == 2)
		{
			int lineType = new vtkLine().GetCellType();
		
			for (VisLine line : visMesh.getLines()) 
			{
				vtkIdList pts = new vtkIdList();
				pts.InsertNextId(line.getP1());
				pts.InsertNextId(line.getP2());
			  vtkgrid.InsertNextCell(lineType, pts);
			}
		}
		else
		{
			int triangleType = new vtkTriangle().GetCellType();
			for (VisSurfaceTriangle surfaceTriangle : visMesh.getSurfaceTriangles())
			{
				vtkIdList pts = new vtkIdList();
				for (int pi : surfaceTriangle.getPointIndices())
				{
					pts.InsertNextId(pi);
				}
				// each triangle is a cell
				vtkgrid.InsertNextCell(triangleType, pts);
			}
		}
		
		vtkgrid.BuildLinks();
		
		int numCells = vtkgrid.GetCells().GetNumberOfCells();
		vtkCellData cellData = vtkgrid.GetCellData();
		for (ChomboMembraneVarData vcsol : chomboMeshData.getMembraneVarData())
		{
			double[] data = vcsol.getRawChomboData();
			if (visMesh.getDimension() == 3)
			{
				// reconstruct data based on triangles
				double[] tridata = new double[numCells];
				int cnt = 0;
				for (VisSurfaceTriangle surfaceTriangle : visMesh.getSurfaceTriangles())
				{
					int memIndex = surfaceTriangle.getChomboIndex();
					tridata[cnt ++] = data[memIndex];
				}
				data = tridata;
			}
			
		    vtkDoubleArray cellScalars1 = new vtkDoubleArray();
		    cellScalars1.SetNumberOfComponents(1);
		    cellScalars1.SetNumberOfTuples(numCells);
		    cellScalars1.SetName(vcsol.getName());
		    cellScalars1.SetJavaArray(data);
			cellData.AddArray(cellScalars1);
		}
		
		vtkFieldData fieldData = vtkgrid.GetFieldData();
		vtkFloatArray timeValue = new vtkFloatArray();
		timeValue.SetNumberOfComponents(1);
		timeValue.SetNumberOfTuples(1);
		timeValue.SetName("TIME");
	    float time = (float)chomboMeshData.getTime();
	    float timeArray[] = new float[] { time };
		timeValue.SetJavaArray(timeArray);
		fieldData.AddArray(timeValue);
		
		return vtkgrid;
	}

	public static void writeSmoothedVtkGrid(VisDomain visDomain, File file) throws IOException {
		vtk.vtkUnstructuredGrid vtkgrid = getVolumeVtkGrid(visDomain);
		try {
			vtk.vtkUnstructuredGrid vtkgridSmoothed = VtkGridUtils.smoothUnstructuredGridSurface(vtkgrid);
			write(vtkgridSmoothed, file.getPath());
			//vtkgridSmoothed.Delete();	// is needed for garbage collection?   //superfluous with prior delete according to runtime errors with this uncommented?
		}finally{
			vtkgrid.Delete(); 			// is needed for garbage collection?
		}
	}
	
	public static CartesianMeshIndices readCartesianMeshIndices(File vtuMeshFile, String globalIndexVarName, String regionIdVarName){
		vtkUnstructuredGrid vtkUnstructuredGrid = read(vtuMeshFile.getAbsolutePath());
		vtkUnstructuredGrid.BuildLinks();

		int numberOfArrays = vtkUnstructuredGrid.GetCellData().GetNumberOfArrays();
		for (int i=0;i<numberOfArrays;i++){
			String arrayName = vtkUnstructuredGrid.GetCellData().GetArrayName(i);
			System.out.println("Array("+i+") named \""+arrayName+"\"");
		}
		vtkDoubleArray globalIndexArray = (vtkDoubleArray)vtkUnstructuredGrid.GetCellData().GetArray(globalIndexVarName);
		double[] globalIndexValues = globalIndexArray.GetJavaArray();
		int maxGlobalIndex = 0;
		for (double globalIndex : globalIndexValues){
			maxGlobalIndex = Math.max((int)globalIndex,maxGlobalIndex);
		}
		
		vtkDoubleArray regionIndexArray = (vtkDoubleArray)vtkUnstructuredGrid.GetCellData().GetArray(regionIdVarName);
		double[] regionIndexValues = regionIndexArray.GetJavaArray();
		int maxRegionIndex = 0;
		for (double regionIndex : regionIndexValues){
			maxRegionIndex = Math.max((int)regionIndex,maxRegionIndex);
		}
		int numCells = vtkUnstructuredGrid.GetCells().GetNumberOfCells();
		
		CartesianMeshIndices cartesianMeshIndices = new CartesianMeshIndices(numCells, regionIndexValues, globalIndexValues);
		return cartesianMeshIndices;
	}

	public static void writeVolumeVtkGrid(VisDomain visDomain, File volumeMeshFile) throws IOException {
		vtkUnstructuredGrid vtkgrid = getVolumeVtkGrid(visDomain);
		write(vtkgrid, volumeMeshFile.getPath());
		vtkgrid.Delete(); // if needed for garbage collection
	}

	public static void writeMembraneVtkGrid(VisMesh visMesh, ChomboMeshData chomboMeshData, File memfile) {
		vtkUnstructuredGrid vtkgrid = getMembraneVtkGrid(visMesh, chomboMeshData);
		write(vtkgrid, memfile.getPath());
		vtkgrid.Delete();
	}

	public static List<ChomboCellIndices> getChomboVolumeCellIndices(File meshFile){
		vtkUnstructuredGrid usGrid = read(meshFile.getPath());

		vtkDoubleArray boxLevelArray = (vtkDoubleArray)usGrid.GetCellData().GetArray(ChomboMeshData.BUILTIN_VAR_BOXLEVEL);
		double[] boxLevelData = boxLevelArray.GetJavaArray();
		vtkDoubleArray boxNumberArray = (vtkDoubleArray)usGrid.GetCellData().GetArray(ChomboMeshData.BUILTIN_VAR_BOXNUMBER);
		double[] boxNumberData = boxNumberArray.GetJavaArray();
		vtkDoubleArray boxIndexArray = (vtkDoubleArray)usGrid.GetCellData().GetArray(ChomboMeshData.BUILTIN_VAR_BOXINDEX);
		double[] boxIndexData = boxIndexArray.GetJavaArray();

		ArrayList<ChomboCellIndices> chomboCellIndices = new ArrayList<ChomboCellIndices>();
		for (int i=0;i<boxLevelData.length;i++){
			chomboCellIndices.add(new SimpleChomboCellIndices((int)boxLevelData[i],(int)boxNumberData[i],(int)boxIndexData[i]));
		}
		usGrid.Delete();
		return chomboCellIndices;
	}
	
	public static List<ChomboVisMembraneIndex> getChomboMembraneCellIndices(File meshFile){
		vtkUnstructuredGrid usGrid = read(meshFile.getPath());

		vtkCellData cellData = usGrid.GetCellData();
		vtkDoubleArray membraneIndexArray = (vtkDoubleArray)cellData.GetArray(ChomboMeshData.BUILTIN_VAR_MEMBRANE_INDEX);
		double[] membraneIndexData = membraneIndexArray.GetJavaArray();

		ArrayList<ChomboVisMembraneIndex> chomboMembraneIndices = new ArrayList<ChomboVisMembraneIndex>();
		for (int i=0;i<membraneIndexData.length;i++){
			chomboMembraneIndices.add(new SimpleChomboVisMembraneIndex((int)membraneIndexData[i]));
		}
		usGrid.Delete();
		return chomboMembraneIndices;
	}
	
	public static void writeDataArrayToNewVtkFile(File emptyMeshFile, String varName, double[] data, File newMeshFile) throws IOException{
		vtkUnstructuredGrid vtkgrid = null;
		try {
			vtkgrid = read(emptyMeshFile.getCanonicalPath());
			vtkgrid.BuildLinks();
			
			//
			// add cell data array to the empty mesh for this variable
			//
			vtkDoubleArray dataArray = new vtkDoubleArray();
			dataArray.SetName(varName);
			dataArray.SetJavaArray(data);
			vtkCellData cellData = vtkgrid.GetCellData();
			cellData.AddArray(dataArray);
			
			//
			// write mesh and data to the file for that domain and time
			//
			write(vtkgrid, newMeshFile.getAbsolutePath());
		} finally {
			if (vtkgrid != null){
				vtkgrid.Delete();
			}
		}
	}

	
}
