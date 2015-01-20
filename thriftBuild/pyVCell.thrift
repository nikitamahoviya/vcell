namespace java cbit.vcell.client.pyvcellproxy
namespace py pyvcell

typedef i32 int
typedef i64 long

enum ExportType {
   VTU
}

enum ModelType {
   BIOMODEL
   MATHMODEL
}

typedef int TimeIndex
typedef double TimePoint 
typedef string DomainName
typedef string FilePath

typedef list<TimePoint> TimePoints

struct VariableInfo{
   1: required string variableName;
   2: optional string domainName;
   3: optional string variableDomainType;
   4: optional string unitsLabel;
}

typedef list<VariableInfo> VariableList

struct User{
   1: required string userKey;
   2: optional string userName; 
}

struct SimulationDataSetRef {
   1: required string simId;
   2: optional string simName;
   3: optional VariableList variableList;
   4: optional string modelId;
}

struct ModelRef {
   1: required string modelId;
   2: optional string modelName;
   3: optional User user;
   4: optional ModelType modelType;
}

typedef list<ModelRef> ModelRefList

typedef list<SimulationDataSetRef> SimulationDataSetRefList

struct ExportRequestSpec {
   1: required ExportType exportType;
   2: required string SimID;
   3: required VariableList variables;
   4: required int startTime;
   5: required int endTime;
}

exception ExportException {
   1: string message;
}

exception DataAccessException {
   1: string message;
}

exception DatabaseFileException {
   1: string message;
}

exception PlotException {
   1: string message;
}

#service VisitProxy {

#   void openDatabase(1:string databaseName) throws (1:DatabaseFileException databaseFileException);
#   void closeDatabase() throws (1:DatabaseFileException databaseFileException);
#   void drawPlot(1:string variableName) throws (1:PlotException plotException);
#   oneway void clearPlots() 
#}

service VCellProxy {

    FilePath exportRequest(1:ExportRequestSpec exportRequestSpec) throws (1:ExportException exportException);
    FilePath exportAllRequest(1:SimulationDataSetRef simulationDataSetRef) throws (1:ExportException exportException);
    FilePath getDataSetFileOfDomainAtTimeIndex(1:SimulationDataSetRef simulationDataSetRef, 2:DomainName domainName, 3:TimeIndex timeIndex) throws (1:DataAccessException dataAccessException)
    FilePath getDataSetFileOfDomainAtTimePoint(1:SimulationDataSetRef simulationDataSetRef, 2:DomainName domainName, 3:TimePoint timePoint) throws (1:DataAccessException dataAccessException)
    SimulationDataSetRefList getSimsFromOpenModels() throws (1: DataAccessException dataAccessException);
    SimulationDataSetRefList getSimsFromModel(1: ModelRef modelRef) throws (1: DataAccessException dataAccessException);
    TimeIndex getEndTimeIndex(1: SimulationDataSetRef simulationDataSetRef) throws (1:DataAccessException dataAccessException);
    TimePoints getTimePoints(1: SimulationDataSetRef simulationDataSetRef) throws (1:DataAccessException dataAccessException);    
    VariableList getVariableList(1: SimulationDataSetRef simulationDataSetRef) throws (1: DataAccessException dataAccessException);
    ModelRefList getBioModels() throws (1: DataAccessException dataAccessException);
    ModelRefList getMathModels() throws (1: DataAccessException dataAccessException);
    User getUser() throws (1: DataAccessException dataAccessException);
}
