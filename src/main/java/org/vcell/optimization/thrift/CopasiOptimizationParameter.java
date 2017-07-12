/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.vcell.optimization.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-07-12")
public class CopasiOptimizationParameter implements org.apache.thrift.TBase<CopasiOptimizationParameter, CopasiOptimizationParameter._Fields>, java.io.Serializable, Cloneable, Comparable<CopasiOptimizationParameter> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CopasiOptimizationParameter");

  private static final org.apache.thrift.protocol.TField PARAM_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("paramType", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("value", org.apache.thrift.protocol.TType.DOUBLE, (short)2);
  private static final org.apache.thrift.protocol.TField DATA_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("dataType", org.apache.thrift.protocol.TType.I32, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new CopasiOptimizationParameterStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new CopasiOptimizationParameterTupleSchemeFactory();

  /**
   * 
   * @see OptimizationParameterType
   */
  public OptimizationParameterType paramType; // required
  public double value; // required
  /**
   * 
   * @see OptimizationParameterDataType
   */
  public OptimizationParameterDataType dataType; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see OptimizationParameterType
     */
    PARAM_TYPE((short)1, "paramType"),
    VALUE((short)2, "value"),
    /**
     * 
     * @see OptimizationParameterDataType
     */
    DATA_TYPE((short)3, "dataType");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // PARAM_TYPE
          return PARAM_TYPE;
        case 2: // VALUE
          return VALUE;
        case 3: // DATA_TYPE
          return DATA_TYPE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __VALUE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PARAM_TYPE, new org.apache.thrift.meta_data.FieldMetaData("paramType", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, OptimizationParameterType.class)));
    tmpMap.put(_Fields.VALUE, new org.apache.thrift.meta_data.FieldMetaData("value", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.DATA_TYPE, new org.apache.thrift.meta_data.FieldMetaData("dataType", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, OptimizationParameterDataType.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CopasiOptimizationParameter.class, metaDataMap);
  }

  public CopasiOptimizationParameter() {
  }

  public CopasiOptimizationParameter(
    OptimizationParameterType paramType,
    double value,
    OptimizationParameterDataType dataType)
  {
    this();
    this.paramType = paramType;
    this.value = value;
    setValueIsSet(true);
    this.dataType = dataType;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CopasiOptimizationParameter(CopasiOptimizationParameter other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetParamType()) {
      this.paramType = other.paramType;
    }
    this.value = other.value;
    if (other.isSetDataType()) {
      this.dataType = other.dataType;
    }
  }

  public CopasiOptimizationParameter deepCopy() {
    return new CopasiOptimizationParameter(this);
  }

  @Override
  public void clear() {
    this.paramType = null;
    setValueIsSet(false);
    this.value = 0.0;
    this.dataType = null;
  }

  /**
   * 
   * @see OptimizationParameterType
   */
  public OptimizationParameterType getParamType() {
    return this.paramType;
  }

  /**
   * 
   * @see OptimizationParameterType
   */
  public CopasiOptimizationParameter setParamType(OptimizationParameterType paramType) {
    this.paramType = paramType;
    return this;
  }

  public void unsetParamType() {
    this.paramType = null;
  }

  /** Returns true if field paramType is set (has been assigned a value) and false otherwise */
  public boolean isSetParamType() {
    return this.paramType != null;
  }

  public void setParamTypeIsSet(boolean value) {
    if (!value) {
      this.paramType = null;
    }
  }

  public double getValue() {
    return this.value;
  }

  public CopasiOptimizationParameter setValue(double value) {
    this.value = value;
    setValueIsSet(true);
    return this;
  }

  public void unsetValue() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __VALUE_ISSET_ID);
  }

  /** Returns true if field value is set (has been assigned a value) and false otherwise */
  public boolean isSetValue() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __VALUE_ISSET_ID);
  }

  public void setValueIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __VALUE_ISSET_ID, value);
  }

  /**
   * 
   * @see OptimizationParameterDataType
   */
  public OptimizationParameterDataType getDataType() {
    return this.dataType;
  }

  /**
   * 
   * @see OptimizationParameterDataType
   */
  public CopasiOptimizationParameter setDataType(OptimizationParameterDataType dataType) {
    this.dataType = dataType;
    return this;
  }

  public void unsetDataType() {
    this.dataType = null;
  }

  /** Returns true if field dataType is set (has been assigned a value) and false otherwise */
  public boolean isSetDataType() {
    return this.dataType != null;
  }

  public void setDataTypeIsSet(boolean value) {
    if (!value) {
      this.dataType = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case PARAM_TYPE:
      if (value == null) {
        unsetParamType();
      } else {
        setParamType((OptimizationParameterType)value);
      }
      break;

    case VALUE:
      if (value == null) {
        unsetValue();
      } else {
        setValue((java.lang.Double)value);
      }
      break;

    case DATA_TYPE:
      if (value == null) {
        unsetDataType();
      } else {
        setDataType((OptimizationParameterDataType)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case PARAM_TYPE:
      return getParamType();

    case VALUE:
      return getValue();

    case DATA_TYPE:
      return getDataType();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case PARAM_TYPE:
      return isSetParamType();
    case VALUE:
      return isSetValue();
    case DATA_TYPE:
      return isSetDataType();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof CopasiOptimizationParameter)
      return this.equals((CopasiOptimizationParameter)that);
    return false;
  }

  public boolean equals(CopasiOptimizationParameter that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_paramType = true && this.isSetParamType();
    boolean that_present_paramType = true && that.isSetParamType();
    if (this_present_paramType || that_present_paramType) {
      if (!(this_present_paramType && that_present_paramType))
        return false;
      if (!this.paramType.equals(that.paramType))
        return false;
    }

    boolean this_present_value = true;
    boolean that_present_value = true;
    if (this_present_value || that_present_value) {
      if (!(this_present_value && that_present_value))
        return false;
      if (this.value != that.value)
        return false;
    }

    boolean this_present_dataType = true && this.isSetDataType();
    boolean that_present_dataType = true && that.isSetDataType();
    if (this_present_dataType || that_present_dataType) {
      if (!(this_present_dataType && that_present_dataType))
        return false;
      if (!this.dataType.equals(that.dataType))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetParamType()) ? 131071 : 524287);
    if (isSetParamType())
      hashCode = hashCode * 8191 + paramType.getValue();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(value);

    hashCode = hashCode * 8191 + ((isSetDataType()) ? 131071 : 524287);
    if (isSetDataType())
      hashCode = hashCode * 8191 + dataType.getValue();

    return hashCode;
  }

  @Override
  public int compareTo(CopasiOptimizationParameter other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetParamType()).compareTo(other.isSetParamType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParamType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.paramType, other.paramType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetValue()).compareTo(other.isSetValue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetValue()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.value, other.value);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDataType()).compareTo(other.isSetDataType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDataType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dataType, other.dataType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("CopasiOptimizationParameter(");
    boolean first = true;

    sb.append("paramType:");
    if (this.paramType == null) {
      sb.append("null");
    } else {
      sb.append(this.paramType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("value:");
    sb.append(this.value);
    first = false;
    if (!first) sb.append(", ");
    sb.append("dataType:");
    if (this.dataType == null) {
      sb.append("null");
    } else {
      sb.append(this.dataType);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (paramType == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'paramType' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'value' because it's a primitive and you chose the non-beans generator.
    if (dataType == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'dataType' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class CopasiOptimizationParameterStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public CopasiOptimizationParameterStandardScheme getScheme() {
      return new CopasiOptimizationParameterStandardScheme();
    }
  }

  private static class CopasiOptimizationParameterStandardScheme extends org.apache.thrift.scheme.StandardScheme<CopasiOptimizationParameter> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CopasiOptimizationParameter struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PARAM_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.paramType = org.vcell.optimization.thrift.OptimizationParameterType.findByValue(iprot.readI32());
              struct.setParamTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // VALUE
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.value = iprot.readDouble();
              struct.setValueIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DATA_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.dataType = org.vcell.optimization.thrift.OptimizationParameterDataType.findByValue(iprot.readI32());
              struct.setDataTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetValue()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'value' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, CopasiOptimizationParameter struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.paramType != null) {
        oprot.writeFieldBegin(PARAM_TYPE_FIELD_DESC);
        oprot.writeI32(struct.paramType.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(VALUE_FIELD_DESC);
      oprot.writeDouble(struct.value);
      oprot.writeFieldEnd();
      if (struct.dataType != null) {
        oprot.writeFieldBegin(DATA_TYPE_FIELD_DESC);
        oprot.writeI32(struct.dataType.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CopasiOptimizationParameterTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public CopasiOptimizationParameterTupleScheme getScheme() {
      return new CopasiOptimizationParameterTupleScheme();
    }
  }

  private static class CopasiOptimizationParameterTupleScheme extends org.apache.thrift.scheme.TupleScheme<CopasiOptimizationParameter> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CopasiOptimizationParameter struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI32(struct.paramType.getValue());
      oprot.writeDouble(struct.value);
      oprot.writeI32(struct.dataType.getValue());
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CopasiOptimizationParameter struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.paramType = org.vcell.optimization.thrift.OptimizationParameterType.findByValue(iprot.readI32());
      struct.setParamTypeIsSet(true);
      struct.value = iprot.readDouble();
      struct.setValueIsSet(true);
      struct.dataType = org.vcell.optimization.thrift.OptimizationParameterDataType.findByValue(iprot.readI32());
      struct.setDataTypeIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

