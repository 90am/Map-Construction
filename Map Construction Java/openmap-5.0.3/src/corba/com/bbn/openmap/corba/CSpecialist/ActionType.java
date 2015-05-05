package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/ActionType.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public class ActionType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 6;
  private static com.bbn.openmap.corba.CSpecialist.ActionType[] __array = new com.bbn.openmap.corba.CSpecialist.ActionType [__size];

  public static final int _UpdateGraphics = 0;
  public static final com.bbn.openmap.corba.CSpecialist.ActionType UpdateGraphics = new com.bbn.openmap.corba.CSpecialist.ActionType(_UpdateGraphics);
  public static final int _UpdatePalette = 1;
  public static final com.bbn.openmap.corba.CSpecialist.ActionType UpdatePalette = new com.bbn.openmap.corba.CSpecialist.ActionType(_UpdatePalette);
  public static final int _InfoText = 2;
  public static final com.bbn.openmap.corba.CSpecialist.ActionType InfoText = new com.bbn.openmap.corba.CSpecialist.ActionType(_InfoText);
  public static final int _PlainText = 3;
  public static final com.bbn.openmap.corba.CSpecialist.ActionType PlainText = new com.bbn.openmap.corba.CSpecialist.ActionType(_PlainText);
  public static final int _HTMLText = 4;
  public static final com.bbn.openmap.corba.CSpecialist.ActionType HTMLText = new com.bbn.openmap.corba.CSpecialist.ActionType(_HTMLText);
  public static final int _URL = 5;
  public static final com.bbn.openmap.corba.CSpecialist.ActionType URL = new com.bbn.openmap.corba.CSpecialist.ActionType(_URL);

  public int value ()
  {
    return __value;
  }

  public static com.bbn.openmap.corba.CSpecialist.ActionType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected ActionType (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class ActionType