/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.vcell.vis.vismesh.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-07-12")
public class MovingBoundaryIndexData implements org.apache.thrift.TBase<MovingBoundaryIndexData, MovingBoundaryIndexData._Fields>, java.io.Serializable, Cloneable, Comparable<MovingBoundaryIndexData> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("MovingBoundaryIndexData");

  private static final org.apache.thrift.protocol.TField DOMAIN_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("domainName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TIME_INDEX_FIELD_DESC = new org.apache.thrift.protocol.TField("timeIndex", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField MOVING_BOUNDARY_SURFACE_INDICES_FIELD_DESC = new org.apache.thrift.protocol.TField("movingBoundarySurfaceIndices", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField MOVING_BOUNDARY_VOLUME_INDICES_FIELD_DESC = new org.apache.thrift.protocol.TField("movingBoundaryVolumeIndices", org.apache.thrift.protocol.TType.LIST, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new MovingBoundaryIndexDataStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new MovingBoundaryIndexDataTupleSchemeFactory();

  public java.lang.String domainName; // required
  public int timeIndex; // required
  public java.util.List<MovingBoundarySurfaceIndex> movingBoundarySurfaceIndices; // optional
  public java.util.List<MovingBoundaryVolumeIndex> movingBoundaryVolumeIndices; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DOMAIN_NAME((short)1, "domainName"),
    TIME_INDEX((short)2, "timeIndex"),
    MOVING_BOUNDARY_SURFACE_INDICES((short)3, "movingBoundarySurfaceIndices"),
    MOVING_BOUNDARY_VOLUME_INDICES((short)4, "movingBoundaryVolumeIndices");

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
        case 1: // DOMAIN_NAME
          return DOMAIN_NAME;
        case 2: // TIME_INDEX
          return TIME_INDEX;
        case 3: // MOVING_BOUNDARY_SURFACE_INDICES
          return MOVING_BOUNDARY_SURFACE_INDICES;
        case 4: // MOVING_BOUNDARY_VOLUME_INDICES
          return MOVING_BOUNDARY_VOLUME_INDICES;
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
  private static final int __TIMEINDEX_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.MOVING_BOUNDARY_SURFACE_INDICES,_Fields.MOVING_BOUNDARY_VOLUME_INDICES};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DOMAIN_NAME, new org.apache.thrift.meta_data.FieldMetaData("domainName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TIME_INDEX, new org.apache.thrift.meta_data.FieldMetaData("timeIndex", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "int")));
    tmpMap.put(_Fields.MOVING_BOUNDARY_SURFACE_INDICES, new org.apache.thrift.meta_data.FieldMetaData("movingBoundarySurfaceIndices", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, MovingBoundarySurfaceIndex.class))));
    tmpMap.put(_Fields.MOVING_BOUNDARY_VOLUME_INDICES, new org.apache.thrift.meta_data.FieldMetaData("movingBoundaryVolumeIndices", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, MovingBoundaryVolumeIndex.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(MovingBoundaryIndexData.class, metaDataMap);
  }

  public MovingBoundaryIndexData() {
  }

  public MovingBoundaryIndexData(
    java.lang.String domainName,
    int timeIndex)
  {
    this();
    this.domainName = domainName;
    this.timeIndex = timeIndex;
    setTimeIndexIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public MovingBoundaryIndexData(MovingBoundaryIndexData other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetDomainName()) {
      this.domainName = other.domainName;
    }
    this.timeIndex = other.timeIndex;
    if (other.isSetMovingBoundarySurfaceIndices()) {
      java.util.List<MovingBoundarySurfaceIndex> __this__movingBoundarySurfaceIndices = new java.util.ArrayList<MovingBoundarySurfaceIndex>(other.movingBoundarySurfaceIndices.size());
      for (MovingBoundarySurfaceIndex other_element : other.movingBoundarySurfaceIndices) {
        __this__movingBoundarySurfaceIndices.add(new MovingBoundarySurfaceIndex(other_element));
      }
      this.movingBoundarySurfaceIndices = __this__movingBoundarySurfaceIndices;
    }
    if (other.isSetMovingBoundaryVolumeIndices()) {
      java.util.List<MovingBoundaryVolumeIndex> __this__movingBoundaryVolumeIndices = new java.util.ArrayList<MovingBoundaryVolumeIndex>(other.movingBoundaryVolumeIndices.size());
      for (MovingBoundaryVolumeIndex other_element : other.movingBoundaryVolumeIndices) {
        __this__movingBoundaryVolumeIndices.add(new MovingBoundaryVolumeIndex(other_element));
      }
      this.movingBoundaryVolumeIndices = __this__movingBoundaryVolumeIndices;
    }
  }

  public MovingBoundaryIndexData deepCopy() {
    return new MovingBoundaryIndexData(this);
  }

  @Override
  public void clear() {
    this.domainName = null;
    setTimeIndexIsSet(false);
    this.timeIndex = 0;
    this.movingBoundarySurfaceIndices = null;
    this.movingBoundaryVolumeIndices = null;
  }

  public java.lang.String getDomainName() {
    return this.domainName;
  }

  public MovingBoundaryIndexData setDomainName(java.lang.String domainName) {
    this.domainName = domainName;
    return this;
  }

  public void unsetDomainName() {
    this.domainName = null;
  }

  /** Returns true if field domainName is set (has been assigned a value) and false otherwise */
  public boolean isSetDomainName() {
    return this.domainName != null;
  }

  public void setDomainNameIsSet(boolean value) {
    if (!value) {
      this.domainName = null;
    }
  }

  public int getTimeIndex() {
    return this.timeIndex;
  }

  public MovingBoundaryIndexData setTimeIndex(int timeIndex) {
    this.timeIndex = timeIndex;
    setTimeIndexIsSet(true);
    return this;
  }

  public void unsetTimeIndex() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TIMEINDEX_ISSET_ID);
  }

  /** Returns true if field timeIndex is set (has been assigned a value) and false otherwise */
  public boolean isSetTimeIndex() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TIMEINDEX_ISSET_ID);
  }

  public void setTimeIndexIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TIMEINDEX_ISSET_ID, value);
  }

  public int getMovingBoundarySurfaceIndicesSize() {
    return (this.movingBoundarySurfaceIndices == null) ? 0 : this.movingBoundarySurfaceIndices.size();
  }

  public java.util.Iterator<MovingBoundarySurfaceIndex> getMovingBoundarySurfaceIndicesIterator() {
    return (this.movingBoundarySurfaceIndices == null) ? null : this.movingBoundarySurfaceIndices.iterator();
  }

  public void addToMovingBoundarySurfaceIndices(MovingBoundarySurfaceIndex elem) {
    if (this.movingBoundarySurfaceIndices == null) {
      this.movingBoundarySurfaceIndices = new java.util.ArrayList<MovingBoundarySurfaceIndex>();
    }
    this.movingBoundarySurfaceIndices.add(elem);
  }

  public java.util.List<MovingBoundarySurfaceIndex> getMovingBoundarySurfaceIndices() {
    return this.movingBoundarySurfaceIndices;
  }

  public MovingBoundaryIndexData setMovingBoundarySurfaceIndices(java.util.List<MovingBoundarySurfaceIndex> movingBoundarySurfaceIndices) {
    this.movingBoundarySurfaceIndices = movingBoundarySurfaceIndices;
    return this;
  }

  public void unsetMovingBoundarySurfaceIndices() {
    this.movingBoundarySurfaceIndices = null;
  }

  /** Returns true if field movingBoundarySurfaceIndices is set (has been assigned a value) and false otherwise */
  public boolean isSetMovingBoundarySurfaceIndices() {
    return this.movingBoundarySurfaceIndices != null;
  }

  public void setMovingBoundarySurfaceIndicesIsSet(boolean value) {
    if (!value) {
      this.movingBoundarySurfaceIndices = null;
    }
  }

  public int getMovingBoundaryVolumeIndicesSize() {
    return (this.movingBoundaryVolumeIndices == null) ? 0 : this.movingBoundaryVolumeIndices.size();
  }

  public java.util.Iterator<MovingBoundaryVolumeIndex> getMovingBoundaryVolumeIndicesIterator() {
    return (this.movingBoundaryVolumeIndices == null) ? null : this.movingBoundaryVolumeIndices.iterator();
  }

  public void addToMovingBoundaryVolumeIndices(MovingBoundaryVolumeIndex elem) {
    if (this.movingBoundaryVolumeIndices == null) {
      this.movingBoundaryVolumeIndices = new java.util.ArrayList<MovingBoundaryVolumeIndex>();
    }
    this.movingBoundaryVolumeIndices.add(elem);
  }

  public java.util.List<MovingBoundaryVolumeIndex> getMovingBoundaryVolumeIndices() {
    return this.movingBoundaryVolumeIndices;
  }

  public MovingBoundaryIndexData setMovingBoundaryVolumeIndices(java.util.List<MovingBoundaryVolumeIndex> movingBoundaryVolumeIndices) {
    this.movingBoundaryVolumeIndices = movingBoundaryVolumeIndices;
    return this;
  }

  public void unsetMovingBoundaryVolumeIndices() {
    this.movingBoundaryVolumeIndices = null;
  }

  /** Returns true if field movingBoundaryVolumeIndices is set (has been assigned a value) and false otherwise */
  public boolean isSetMovingBoundaryVolumeIndices() {
    return this.movingBoundaryVolumeIndices != null;
  }

  public void setMovingBoundaryVolumeIndicesIsSet(boolean value) {
    if (!value) {
      this.movingBoundaryVolumeIndices = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case DOMAIN_NAME:
      if (value == null) {
        unsetDomainName();
      } else {
        setDomainName((java.lang.String)value);
      }
      break;

    case TIME_INDEX:
      if (value == null) {
        unsetTimeIndex();
      } else {
        setTimeIndex((java.lang.Integer)value);
      }
      break;

    case MOVING_BOUNDARY_SURFACE_INDICES:
      if (value == null) {
        unsetMovingBoundarySurfaceIndices();
      } else {
        setMovingBoundarySurfaceIndices((java.util.List<MovingBoundarySurfaceIndex>)value);
      }
      break;

    case MOVING_BOUNDARY_VOLUME_INDICES:
      if (value == null) {
        unsetMovingBoundaryVolumeIndices();
      } else {
        setMovingBoundaryVolumeIndices((java.util.List<MovingBoundaryVolumeIndex>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case DOMAIN_NAME:
      return getDomainName();

    case TIME_INDEX:
      return getTimeIndex();

    case MOVING_BOUNDARY_SURFACE_INDICES:
      return getMovingBoundarySurfaceIndices();

    case MOVING_BOUNDARY_VOLUME_INDICES:
      return getMovingBoundaryVolumeIndices();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case DOMAIN_NAME:
      return isSetDomainName();
    case TIME_INDEX:
      return isSetTimeIndex();
    case MOVING_BOUNDARY_SURFACE_INDICES:
      return isSetMovingBoundarySurfaceIndices();
    case MOVING_BOUNDARY_VOLUME_INDICES:
      return isSetMovingBoundaryVolumeIndices();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof MovingBoundaryIndexData)
      return this.equals((MovingBoundaryIndexData)that);
    return false;
  }

  public boolean equals(MovingBoundaryIndexData that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_domainName = true && this.isSetDomainName();
    boolean that_present_domainName = true && that.isSetDomainName();
    if (this_present_domainName || that_present_domainName) {
      if (!(this_present_domainName && that_present_domainName))
        return false;
      if (!this.domainName.equals(that.domainName))
        return false;
    }

    boolean this_present_timeIndex = true;
    boolean that_present_timeIndex = true;
    if (this_present_timeIndex || that_present_timeIndex) {
      if (!(this_present_timeIndex && that_present_timeIndex))
        return false;
      if (this.timeIndex != that.timeIndex)
        return false;
    }

    boolean this_present_movingBoundarySurfaceIndices = true && this.isSetMovingBoundarySurfaceIndices();
    boolean that_present_movingBoundarySurfaceIndices = true && that.isSetMovingBoundarySurfaceIndices();
    if (this_present_movingBoundarySurfaceIndices || that_present_movingBoundarySurfaceIndices) {
      if (!(this_present_movingBoundarySurfaceIndices && that_present_movingBoundarySurfaceIndices))
        return false;
      if (!this.movingBoundarySurfaceIndices.equals(that.movingBoundarySurfaceIndices))
        return false;
    }

    boolean this_present_movingBoundaryVolumeIndices = true && this.isSetMovingBoundaryVolumeIndices();
    boolean that_present_movingBoundaryVolumeIndices = true && that.isSetMovingBoundaryVolumeIndices();
    if (this_present_movingBoundaryVolumeIndices || that_present_movingBoundaryVolumeIndices) {
      if (!(this_present_movingBoundaryVolumeIndices && that_present_movingBoundaryVolumeIndices))
        return false;
      if (!this.movingBoundaryVolumeIndices.equals(that.movingBoundaryVolumeIndices))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetDomainName()) ? 131071 : 524287);
    if (isSetDomainName())
      hashCode = hashCode * 8191 + domainName.hashCode();

    hashCode = hashCode * 8191 + timeIndex;

    hashCode = hashCode * 8191 + ((isSetMovingBoundarySurfaceIndices()) ? 131071 : 524287);
    if (isSetMovingBoundarySurfaceIndices())
      hashCode = hashCode * 8191 + movingBoundarySurfaceIndices.hashCode();

    hashCode = hashCode * 8191 + ((isSetMovingBoundaryVolumeIndices()) ? 131071 : 524287);
    if (isSetMovingBoundaryVolumeIndices())
      hashCode = hashCode * 8191 + movingBoundaryVolumeIndices.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(MovingBoundaryIndexData other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetDomainName()).compareTo(other.isSetDomainName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDomainName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.domainName, other.domainName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTimeIndex()).compareTo(other.isSetTimeIndex());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimeIndex()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timeIndex, other.timeIndex);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetMovingBoundarySurfaceIndices()).compareTo(other.isSetMovingBoundarySurfaceIndices());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMovingBoundarySurfaceIndices()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.movingBoundarySurfaceIndices, other.movingBoundarySurfaceIndices);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetMovingBoundaryVolumeIndices()).compareTo(other.isSetMovingBoundaryVolumeIndices());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMovingBoundaryVolumeIndices()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.movingBoundaryVolumeIndices, other.movingBoundaryVolumeIndices);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("MovingBoundaryIndexData(");
    boolean first = true;

    sb.append("domainName:");
    if (this.domainName == null) {
      sb.append("null");
    } else {
      sb.append(this.domainName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("timeIndex:");
    sb.append(this.timeIndex);
    first = false;
    if (isSetMovingBoundarySurfaceIndices()) {
      if (!first) sb.append(", ");
      sb.append("movingBoundarySurfaceIndices:");
      if (this.movingBoundarySurfaceIndices == null) {
        sb.append("null");
      } else {
        sb.append(this.movingBoundarySurfaceIndices);
      }
      first = false;
    }
    if (isSetMovingBoundaryVolumeIndices()) {
      if (!first) sb.append(", ");
      sb.append("movingBoundaryVolumeIndices:");
      if (this.movingBoundaryVolumeIndices == null) {
        sb.append("null");
      } else {
        sb.append(this.movingBoundaryVolumeIndices);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (domainName == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'domainName' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'timeIndex' because it's a primitive and you chose the non-beans generator.
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

  private static class MovingBoundaryIndexDataStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public MovingBoundaryIndexDataStandardScheme getScheme() {
      return new MovingBoundaryIndexDataStandardScheme();
    }
  }

  private static class MovingBoundaryIndexDataStandardScheme extends org.apache.thrift.scheme.StandardScheme<MovingBoundaryIndexData> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, MovingBoundaryIndexData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DOMAIN_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.domainName = iprot.readString();
              struct.setDomainNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TIME_INDEX
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.timeIndex = iprot.readI32();
              struct.setTimeIndexIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // MOVING_BOUNDARY_SURFACE_INDICES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list72 = iprot.readListBegin();
                struct.movingBoundarySurfaceIndices = new java.util.ArrayList<MovingBoundarySurfaceIndex>(_list72.size);
                MovingBoundarySurfaceIndex _elem73;
                for (int _i74 = 0; _i74 < _list72.size; ++_i74)
                {
                  _elem73 = new MovingBoundarySurfaceIndex();
                  _elem73.read(iprot);
                  struct.movingBoundarySurfaceIndices.add(_elem73);
                }
                iprot.readListEnd();
              }
              struct.setMovingBoundarySurfaceIndicesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // MOVING_BOUNDARY_VOLUME_INDICES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list75 = iprot.readListBegin();
                struct.movingBoundaryVolumeIndices = new java.util.ArrayList<MovingBoundaryVolumeIndex>(_list75.size);
                MovingBoundaryVolumeIndex _elem76;
                for (int _i77 = 0; _i77 < _list75.size; ++_i77)
                {
                  _elem76 = new MovingBoundaryVolumeIndex();
                  _elem76.read(iprot);
                  struct.movingBoundaryVolumeIndices.add(_elem76);
                }
                iprot.readListEnd();
              }
              struct.setMovingBoundaryVolumeIndicesIsSet(true);
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
      if (!struct.isSetTimeIndex()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'timeIndex' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, MovingBoundaryIndexData struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.domainName != null) {
        oprot.writeFieldBegin(DOMAIN_NAME_FIELD_DESC);
        oprot.writeString(struct.domainName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIME_INDEX_FIELD_DESC);
      oprot.writeI32(struct.timeIndex);
      oprot.writeFieldEnd();
      if (struct.movingBoundarySurfaceIndices != null) {
        if (struct.isSetMovingBoundarySurfaceIndices()) {
          oprot.writeFieldBegin(MOVING_BOUNDARY_SURFACE_INDICES_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.movingBoundarySurfaceIndices.size()));
            for (MovingBoundarySurfaceIndex _iter78 : struct.movingBoundarySurfaceIndices)
            {
              _iter78.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.movingBoundaryVolumeIndices != null) {
        if (struct.isSetMovingBoundaryVolumeIndices()) {
          oprot.writeFieldBegin(MOVING_BOUNDARY_VOLUME_INDICES_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.movingBoundaryVolumeIndices.size()));
            for (MovingBoundaryVolumeIndex _iter79 : struct.movingBoundaryVolumeIndices)
            {
              _iter79.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class MovingBoundaryIndexDataTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public MovingBoundaryIndexDataTupleScheme getScheme() {
      return new MovingBoundaryIndexDataTupleScheme();
    }
  }

  private static class MovingBoundaryIndexDataTupleScheme extends org.apache.thrift.scheme.TupleScheme<MovingBoundaryIndexData> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, MovingBoundaryIndexData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeString(struct.domainName);
      oprot.writeI32(struct.timeIndex);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetMovingBoundarySurfaceIndices()) {
        optionals.set(0);
      }
      if (struct.isSetMovingBoundaryVolumeIndices()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetMovingBoundarySurfaceIndices()) {
        {
          oprot.writeI32(struct.movingBoundarySurfaceIndices.size());
          for (MovingBoundarySurfaceIndex _iter80 : struct.movingBoundarySurfaceIndices)
          {
            _iter80.write(oprot);
          }
        }
      }
      if (struct.isSetMovingBoundaryVolumeIndices()) {
        {
          oprot.writeI32(struct.movingBoundaryVolumeIndices.size());
          for (MovingBoundaryVolumeIndex _iter81 : struct.movingBoundaryVolumeIndices)
          {
            _iter81.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, MovingBoundaryIndexData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.domainName = iprot.readString();
      struct.setDomainNameIsSet(true);
      struct.timeIndex = iprot.readI32();
      struct.setTimeIndexIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list82 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.movingBoundarySurfaceIndices = new java.util.ArrayList<MovingBoundarySurfaceIndex>(_list82.size);
          MovingBoundarySurfaceIndex _elem83;
          for (int _i84 = 0; _i84 < _list82.size; ++_i84)
          {
            _elem83 = new MovingBoundarySurfaceIndex();
            _elem83.read(iprot);
            struct.movingBoundarySurfaceIndices.add(_elem83);
          }
        }
        struct.setMovingBoundarySurfaceIndicesIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list85 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.movingBoundaryVolumeIndices = new java.util.ArrayList<MovingBoundaryVolumeIndex>(_list85.size);
          MovingBoundaryVolumeIndex _elem86;
          for (int _i87 = 0; _i87 < _list85.size; ++_i87)
          {
            _elem86 = new MovingBoundaryVolumeIndex();
            _elem86.read(iprot);
            struct.movingBoundaryVolumeIndices.add(_elem86);
          }
        }
        struct.setMovingBoundaryVolumeIndicesIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

