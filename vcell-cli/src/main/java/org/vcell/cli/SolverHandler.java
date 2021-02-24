package org.vcell.cli;

import cbit.util.xml.VCLogger;
import cbit.vcell.biomodel.BioModel;
import cbit.vcell.messaging.server.SimulationTask;
import cbit.vcell.solver.*;
import cbit.vcell.solver.ode.AbstractJavaSolver;
import cbit.vcell.solver.ode.ODESolver;
import cbit.vcell.solver.ode.ODESolverResultSet;
import cbit.vcell.solver.server.Solver;
import cbit.vcell.solver.server.SolverFactory;
import cbit.vcell.solver.server.SolverStatus;
import cbit.vcell.solver.stoch.GibsonSolver;
import cbit.vcell.solver.stoch.HybridSolver;
import cbit.vcell.solvers.AbstractCompiledSolver;
import cbit.vcell.xml.ExternalDocInfo;
import cbit.vcell.xml.XmlHelper;
import org.jlibsedml.Libsedml;
import org.jlibsedml.SedML;
import org.jlibsedml.Task;
import org.jlibsedml.UniformTimeCourse;
import org.vcell.cli.vcml.VCMLHandler;
import org.vcell.sbml.vcell.SBMLImportException;
import org.vcell.sbml.vcell.SBMLImporter;
import org.vcell.util.document.VCDocument;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SolverHandler {

    private static void sanityCheck(VCDocument doc) throws Exception {
        if (doc == null) {
            throw new Exception("Imported VCDocument is null.");
        }
        String docName = doc.getName();
        if (docName == null || docName.isEmpty()) {
            throw new Exception("The name of the imported VCDocument is null or empty.");
        }
        if (!(doc instanceof BioModel)) {
            throw new Exception("The imported VCDocument '" + docName + "' is not a BioModel.");
        }
        BioModel bioModel = (BioModel) doc;
        if (bioModel.getSimulationContext(0) == null) {
            throw new Exception("The imported VCDocument '" + docName + "' has no Application");
        }
        if (bioModel.getSimulation(0) == null) {
            throw new Exception("The imported VCDocument '" + docName + "' has no Simulation");
        }
    }

    public HashMap<String, ODESolverResultSet> simulateAllSedmlTasks(ExternalDocInfo externalDocInfo, SedML sedml, File outputDir, String sedmlName) throws Exception {
        // create the VCDocument(s) (bioModel(s) + application(s) + simulation(s)), do sanity checks
        cbit.util.xml.VCLogger sedmlImportLogger = new LocalLogger();
        List<VCDocument> docs = null;
        // Key String is SEDML Task ID
        HashMap<String, ODESolverResultSet> resultsHash = new LinkedHashMap<String, ODESolverResultSet>();
        String docName = null;
        BioModel bioModel = null;
        Simulation[] sims = null;
        String outDirRoot = outputDir.toString().substring(0, outputDir.toString().lastIndexOf(System.getProperty("file.separator")));
        VCDocument singleDoc = null;
        try {
            docs = XmlHelper.sedmlToBioModel(sedmlImportLogger, externalDocInfo, sedml, null);
        } catch (Exception e) {
            System.err.println("Unable to Parse SED-ML into Bio-Model, failed with err: " + e.getMessage());
            throw e;
        }
//        try {
//            File vcmlPath = new File("/Users/akhilteja/projects/virtualCell/vcell/sample_omex_files/_00_omex_test_cvode.vcml");
//            singleDoc = VCMLHandler.convertVcmlToVcDocument(vcmlPath);
//        } catch (Exception e) {
//            System.err.println("Unable to Parse SED-ML into Bio-Model, failed with err: " + e.getMessage());
//            throw e;
//        }

        for (VCDocument doc : docs) {
            try {
                sanityCheck(singleDoc);
            } catch (Exception e) {
                e.printStackTrace(System.err);
//                continue;
            }
        assert singleDoc != null;
        docName = singleDoc.getName();
            bioModel = (BioModel) singleDoc;
            sims = bioModel.getSimulations();
            for (Simulation sim : sims) {
                sim = new TempSimulation(sim, false);
                SolverTaskDescription std = sim.getSolverTaskDescription();
                SolverDescription sd = std.getSolverDescription();
                String kisao = sd.getKisao();
                SimulationJob simJob = new SimulationJob(sim, 0, null);
                SimulationTask simTask = new SimulationTask(simJob, 0);
                Solver solver = SolverFactory.createSolver(outputDir, simTask, false);
                ODESolverResultSet odeSolverResultSet = null;
                try {
                    if (solver instanceof AbstractCompiledSolver) {
                        ((AbstractCompiledSolver) solver).runSolver();
                        if (solver instanceof ODESolver) {
                            odeSolverResultSet = ((ODESolver) solver).getODESolverResultSet();
                        } else if (solver instanceof GibsonSolver) {
                            odeSolverResultSet = ((GibsonSolver) solver).getStochSolverResultSet();
                        } else if (solver instanceof HybridSolver) {
                            odeSolverResultSet = ((HybridSolver) solver).getHybridSolverResultSet();
                        } else {
                            System.err.println("Solver results are not compatible with CSV format");
                        }
                    } else if (solver instanceof AbstractJavaSolver) {
                        ((AbstractJavaSolver) solver).runSolver();
                        odeSolverResultSet = ((ODESolver) solver).getODESolverResultSet();
                        // must interpolate data for uniform time course which is not supported natively by the Java solvers
                        Task task = (Task) sedml.getTaskWithId(sim.getImportedTaskID());
                        assert task != null;
                        org.jlibsedml.Simulation sedmlSim = sedml.getSimulation(task.getSimulationReference());
                        if (sedmlSim instanceof UniformTimeCourse) {
                            odeSolverResultSet = CLIUtils.interpolate(odeSolverResultSet, (UniformTimeCourse) sedmlSim);
                        }
                    } else {
                        // this should actually never happen...
                        throw new Exception("Unexpected solver: " + kisao + " " + solver);
                    }
                    if (solver.getSolverStatus().getStatus() == SolverStatus.SOLVER_FINISHED) {
                        System.out.println("Succesful execution: Model '" + docName + "' Task '" + sim.getDescription() + "'.");

                        CLIUtils.updateTaskStatusYml(sedmlName, sim.getDescription(), CLIUtils.Status.SUCCEEDED, outDirRoot);

                        CLIUtils.drawBreakLine("-", 100);
                    } else {
                        System.err.println("Solver status: " + solver.getSolverStatus().getStatus());
                        System.err.println("Solver message: " + solver.getSolverStatus().getSimulationMessage().getDisplayMessage());
                        throw new Exception();
                    }
                    CLIUtils.finalStatusUpdate( CLIUtils.Status.SUCCEEDED, outDirRoot);
                } catch (Exception e) {
                    System.err.println("Failed execution: Model '" + docName + "' Task '" + sim.getDescription() + "'.");
                    CLIUtils.updateTaskStatusYml(sedmlName, sim.getDescription(), CLIUtils.Status.FAILED, outDirRoot);
                    CLIUtils.finalStatusUpdate( CLIUtils.Status.FAILED, outDirRoot);
                    if (e.getMessage() != null) {
                        // something else than failure caught by solver instance during execution
                        System.err.println(e.getMessage());
                    }
                    CLIUtils.drawBreakLine("-", 100);
                }
                if(odeSolverResultSet != null) {
                    resultsHash.put(sim.getImportedTaskID(), odeSolverResultSet);
                }

                CLIUtils.removeIntermediarySimFiles(outputDir);

            }
        }
        return resultsHash;
    }

    public HashMap<String, ODESolverResultSet> simulateAllVcmlTasks(File vcmlPath, File outputDir, String vcmlName) throws Exception {
        // create the VCDocument(s) (bioModel(s) + application(s) + simulation(s)), do sanity checks
        cbit.util.xml.VCLogger sedmlImportLogger = new LocalLogger();
        List<VCDocument> docs = null;
        // Key String is SEDML Task ID
        HashMap<String, ODESolverResultSet> resultsHash = new LinkedHashMap<String, ODESolverResultSet>();
        String docName = null;
        BioModel bioModel = null;
        Simulation[] sims = null;
        String outDirRoot = outputDir.toString().substring(0, outputDir.toString().lastIndexOf(System.getProperty("file.separator")));
        VCDocument singleDoc = null;
//        try {
//            docs = XmlHelper.sedmlToBioModel(sedmlImportLogger, externalDocInfo, sedml, null);
//        } catch (Exception e) {
//            System.err.println("Unable to Parse SED-ML into Bio-Model, failed with err: " + e.getMessage());
//            throw e;
//        }
        try {
            //File vcmlPath = new File("/Users/akhilteja/projects/virtualCell/vcell/sample_omex_files/_00_omex_test_cvode.vcml");
            singleDoc = VCMLHandler.convertVcmlToVcDocument(vcmlPath);
        } catch (Exception e) {
            System.err.println("Unable to Parse SED-ML into Bio-Model, failed with err: " + e.getMessage());
            throw e;
        }

//        for (VCDocument doc : docs) {
        try {
            sanityCheck(singleDoc);
        } catch (Exception e) {
            e.printStackTrace(System.err);
//                continue;
        }
        assert singleDoc != null;
        docName = singleDoc.getName();
        bioModel = (BioModel) singleDoc;
        sims = bioModel.getSimulations();
        for (Simulation sim : sims) {
            sim = new TempSimulation(sim, false);
            SolverTaskDescription std = sim.getSolverTaskDescription();
            SolverDescription sd = std.getSolverDescription();
            String kisao = sd.getKisao();
            SimulationJob simJob = new SimulationJob(sim, 0, null);
            SimulationTask simTask = new SimulationTask(simJob, 0);
            Solver solver = SolverFactory.createSolver(outputDir, simTask, false);
            ODESolverResultSet odeSolverResultSet = null;
            try {
                if (solver instanceof AbstractCompiledSolver) {
                    ((AbstractCompiledSolver) solver).runSolver();
                    if (solver instanceof ODESolver) {
                        odeSolverResultSet = ((ODESolver) solver).getODESolverResultSet();
                    } else if (solver instanceof GibsonSolver) {
                        odeSolverResultSet = ((GibsonSolver) solver).getStochSolverResultSet();
                    } else if (solver instanceof HybridSolver) {
                        odeSolverResultSet = ((HybridSolver) solver).getHybridSolverResultSet();
                    } else {
                        System.err.println("Solver results are not compatible with CSV format");
                    }
//                } else if (solver instanceof AbstractJavaSolver) {
//                    ((AbstractJavaSolver) solver).runSolver();
//                    odeSolverResultSet = ((ODESolver) solver).getODESolverResultSet();
//                    // must interpolate data for uniform time course which is not supported natively by the Java solvers
//                    Task task = (Task) sedml.getTaskWithId(sim.getImportedTaskID());
//                    assert task != null;
//                    org.jlibsedml.Simulation sedmlSim = sedml.getSimulation(task.getSimulationReference());
//                    if (sedmlSim instanceof UniformTimeCourse) {
//                        odeSolverResultSet = CLIUtils.interpolate(odeSolverResultSet, (UniformTimeCourse) sedmlSim);
//                    }
                } else {
                    // this should actually never happen...
                    throw new Exception("Unexpected solver: " + kisao + " " + solver);
                }
                if (solver.getSolverStatus().getStatus() == SolverStatus.SOLVER_FINISHED) {
                    System.out.println("Succesful execution: Model '" + docName + "' Task '" + sim.getDescription() + "'.");

                    //CLIUtils.updateTaskStatusYml(sedmlName, sim.getDescription(), CLIUtils.Status.SUCCEEDED, outDirRoot);

                    CLIUtils.drawBreakLine("-", 100);
                } else {
                    System.err.println("Solver status: " + solver.getSolverStatus().getStatus());
                    System.err.println("Solver message: " + solver.getSolverStatus().getSimulationMessage().getDisplayMessage());
                    throw new Exception();
                }
                //CLIUtils.finalStatusUpdate( CLIUtils.Status.SUCCEEDED, outDirRoot);
            } catch (Exception e) {
                System.err.println("Failed execution: Model '" + docName + "' Task '" + sim.getDescription() + "'.");
                //CLIUtils.updateTaskStatusYml(sedmlName, sim.getDescription(), CLIUtils.Status.FAILED, outDirRoot);
                //CLIUtils.finalStatusUpdate( CLIUtils.Status.FAILED, outDirRoot);
                if (e.getMessage() != null) {
                    // something else than failure caught by solver instance during execution
                    System.err.println(e.getMessage());
                }
                CLIUtils.drawBreakLine("-", 100);
            }
            if(odeSolverResultSet != null) {
                resultsHash.put(vcmlName, odeSolverResultSet);
            }

            CLIUtils.removeIntermediarySimFiles(outputDir);

        }
//        }
        return resultsHash;
    }

    ;

    // TODO: Complete this logger and use it for whole CLI
    private static class LocalLogger extends VCLogger {
        @Override
        public void sendMessage(Priority p, ErrorType et, String message) throws Exception {
            System.out.println("LOGGER: msgLevel=" + p + ", msgType=" + et + ", " + message);
            if (p == VCLogger.Priority.HighPriority) {
                SBMLImportException.Category cat = SBMLImportException.Category.UNSPECIFIED;
                if (message.contains(SBMLImporter.RESERVED_SPATIAL)) {
                    cat = SBMLImportException.Category.RESERVED_SPATIAL;
                }
                throw new SBMLImportException(message, cat);
            }
        }

        public void sendAllMessages() {
        }

        public boolean hasMessages() {
            return false;
        }
    }
}