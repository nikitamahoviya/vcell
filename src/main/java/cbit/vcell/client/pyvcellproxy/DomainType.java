/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package cbit.vcell.client.pyvcellproxy;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum DomainType implements org.apache.thrift.TEnum {
  VOLUME(0),
  MEMBRANE(1);

  private final int value;

  private DomainType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static DomainType findByValue(int value) { 
    switch (value) {
      case 0:
        return VOLUME;
      case 1:
        return MEMBRANE;
      default:
        return null;
    }
  }
}
