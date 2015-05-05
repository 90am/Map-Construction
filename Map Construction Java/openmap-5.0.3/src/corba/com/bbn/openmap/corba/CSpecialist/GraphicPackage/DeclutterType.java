package com.bbn.openmap.corba.CSpecialist.GraphicPackage;


/**
* com/bbn/openmap/corba/CSpecialist/GraphicPackage/DeclutterType.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public class DeclutterType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 4;
  private static com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType[] __array = new com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType [__size];

  public static final int _DC_None = 0;
  public static final com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType DC_None = new com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType(_DC_None);
  public static final int _DC_Space = 1;
  public static final com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType DC_Space = new com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType(_DC_Space);
  public static final int _DC_Move = 2;
  public static final com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType DC_Move = new com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType(_DC_Move);
  public static final int _DC_Line = 3;
  public static final com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType DC_Line = new com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType(_DC_Line);

  public int value ()
  {
    return __value;
  }

  public static com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected DeclutterType (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class DeclutterType
