package cbit.image;

/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import java.io.*;

public class ByteOrder
{
   public static final ByteOrder PC = new ByteOrder("II");
   public static final ByteOrder Unix = new ByteOrder("MM");
   public static final ByteOrder Undefined = new ByteOrder("undefined");
   private String orderStr = null;
private ByteOrder(String orderStr) {
	this.orderStr = orderStr;
}   
/**
 * This method was created in VisualAge.
 * @return boolean
 * @param obj java.lang.Object
 */
public boolean equals(Object obj) {
	if (obj instanceof ByteOrder){
		ByteOrder bo = (ByteOrder)obj;
		if (bo.toString().equals(toString())){
			return true;
		}
	}
	return false;
}
/**
 * This method was created by a SmartGuide.
 * @return cbit.image.ByteOrder
 * @param orderStr java.lang.String
 */
public static ByteOrder fromString(String str) {
	if (str.equals(PC.orderStr)){
		return PC;
	}else if (str.equals(Unix.orderStr)){
		return Unix;
	}else{
		return Undefined;
	}	
}
/**
 * This method was created by a SmartGuide.
 * @return java.lang.String
 */
public String toString() {
	return orderStr;
}
}
