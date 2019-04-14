/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package bigdata.java.schema;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-4-29")
public class PersonProperty implements org.apache.thrift.TBase<PersonProperty, PersonProperty._Fields>, java.io.Serializable, Cloneable, Comparable<PersonProperty> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PersonProperty");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField PROPERTY_FIELD_DESC = new org.apache.thrift.protocol.TField("property", org.apache.thrift.protocol.TType.STRUCT, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new PersonPropertyStandardSchemeFactory());
    schemes.put(TupleScheme.class, new PersonPropertyTupleSchemeFactory());
  }

  private PersonID id; // required
  private PersonPropertyValue property; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    PROPERTY((short)2, "property");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // PROPERTY
          return PROPERTY;
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
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, PersonID.class)));
    tmpMap.put(_Fields.PROPERTY, new org.apache.thrift.meta_data.FieldMetaData("property", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, PersonPropertyValue.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PersonProperty.class, metaDataMap);
  }

  public PersonProperty() {
  }

  public PersonProperty(
    PersonID id,
    PersonPropertyValue property)
  {
    this();
    this.id = id;
    this.property = property;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PersonProperty(PersonProperty other) {
    if (other.is_set_id()) {
      this.id = new PersonID(other.id);
    }
    if (other.is_set_property()) {
      this.property = new PersonPropertyValue(other.property);
    }
  }

  public PersonProperty deepCopy() {
    return new PersonProperty(this);
  }

  @Override
  public void clear() {
    this.id = null;
    this.property = null;
  }

  public PersonID get_id() {
    return this.id;
  }

  public void set_id(PersonID id) {
    this.id = id;
  }

  public void unset_id() {
    this.id = null;
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean is_set_id() {
    return this.id != null;
  }

  public void set_id_isSet(boolean value) {
    if (!value) {
      this.id = null;
    }
  }

  public PersonPropertyValue get_property() {
    return this.property;
  }

  public void set_property(PersonPropertyValue property) {
    this.property = property;
  }

  public void unset_property() {
    this.property = null;
  }

  /** Returns true if field property is set (has been assigned a value) and false otherwise */
  public boolean is_set_property() {
    return this.property != null;
  }

  public void set_property_isSet(boolean value) {
    if (!value) {
      this.property = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unset_id();
      } else {
        set_id((PersonID)value);
      }
      break;

    case PROPERTY:
      if (value == null) {
        unset_property();
      } else {
        set_property((PersonPropertyValue)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return get_id();

    case PROPERTY:
      return get_property();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return is_set_id();
    case PROPERTY:
      return is_set_property();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof PersonProperty)
      return this.equals((PersonProperty)that);
    return false;
  }

  public boolean equals(PersonProperty that) {
    if (that == null)
      return false;

    boolean this_present_id = true && this.is_set_id();
    boolean that_present_id = true && that.is_set_id();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (!this.id.equals(that.id))
        return false;
    }

    boolean this_present_property = true && this.is_set_property();
    boolean that_present_property = true && that.is_set_property();
    if (this_present_property || that_present_property) {
      if (!(this_present_property && that_present_property))
        return false;
      if (!this.property.equals(that.property))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_id = true && (is_set_id());
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_property = true && (is_set_property());
    list.add(present_property);
    if (present_property)
      list.add(property);

    return list.hashCode();
  }

  @Override
  public int compareTo(PersonProperty other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(is_set_id()).compareTo(other.is_set_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_property()).compareTo(other.is_set_property());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_property()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.property, other.property);
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
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("PersonProperty(");
    boolean first = true;

    sb.append("id:");
    if (this.id == null) {
      sb.append("null");
    } else {
      sb.append(this.id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("property:");
    if (this.property == null) {
      sb.append("null");
    } else {
      sb.append(this.property);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!is_set_id()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'id' is unset! Struct:" + toString());
    }

    if (!is_set_property()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'property' is unset! Struct:" + toString());
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

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PersonPropertyStandardSchemeFactory implements SchemeFactory {
    public PersonPropertyStandardScheme getScheme() {
      return new PersonPropertyStandardScheme();
    }
  }

  private static class PersonPropertyStandardScheme extends StandardScheme<PersonProperty> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PersonProperty struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.id = new PersonID();
              struct.id.read(iprot);
              struct.set_id_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PROPERTY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.property = new PersonPropertyValue();
              struct.property.read(iprot);
              struct.set_property_isSet(true);
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
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, PersonProperty struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.id != null) {
        oprot.writeFieldBegin(ID_FIELD_DESC);
        struct.id.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.property != null) {
        oprot.writeFieldBegin(PROPERTY_FIELD_DESC);
        struct.property.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PersonPropertyTupleSchemeFactory implements SchemeFactory {
    public PersonPropertyTupleScheme getScheme() {
      return new PersonPropertyTupleScheme();
    }
  }

  private static class PersonPropertyTupleScheme extends TupleScheme<PersonProperty> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PersonProperty struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      struct.id.write(oprot);
      struct.property.write(oprot);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PersonProperty struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.id = new PersonID();
      struct.id.read(iprot);
      struct.set_id_isSet(true);
      struct.property = new PersonPropertyValue();
      struct.property.read(iprot);
      struct.set_property_isSet(true);
    }
  }

}

