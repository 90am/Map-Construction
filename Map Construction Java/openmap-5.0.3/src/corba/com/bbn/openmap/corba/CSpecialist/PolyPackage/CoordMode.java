package com.bbn.openmap.corba.CSpecialist.PolyPackage;


/**
* com/bbn/openmap/corba/CSpecialist/PolyPackage/CoordMode.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public class CoordMode implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 2;
  private static com.bbn.openmap.corba.CSpecialist.PolyPackage.CoordMode[] __array = new com.bbn.openmap.corba.CSpecialist.PolyPackage.CoordMode [__size];

  public static final int _CModeOrigin = 0;
  public static final com.bbn.openmap.corba.CSpecialist.PolyPackage.CoordMode CModeOrigin = new com.bbn.openmap.corba.CSpecialist.PolyPackage.CoordMode(_CModeOrigin);
  public static final int _CModePrevious = 1;
  public static final com.bbn.openmap.corba.CSpecialist.PolyPackage.CoordMode CModePrevious = new com.bbn.openmap.corba.CSpecialist.PolyPackage.CoordMode(_CModePrevious);

  public int value ()
  {
    return __value;
  }

  public static com.bbn.openmap.corba.CSpecialist.PolyPackage.CoordMode from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected CoordMode (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class CoordMode