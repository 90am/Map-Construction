package com.bbn.openmap.corba.CSpecialist.UnitSymbolPackage;

/**
* com/bbn/openmap/corba/CSpecialist/UnitSymbolPackage/USF_updateHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class USF_updateHolder implements org.omg.CORBA.portable.Streamable
{
  public com.bbn.openmap.corba.CSpecialist.UnitSymbolPackage.USF_update value = null;

  public USF_updateHolder ()
  {
  }

  public USF_updateHolder (com.bbn.openmap.corba.CSpecialist.UnitSymbolPackage.USF_update initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.bbn.openmap.corba.CSpecialist.UnitSymbolPackage.USF_updateHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.bbn.openmap.corba.CSpecialist.UnitSymbolPackage.USF_updateHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.bbn.openmap.corba.CSpecialist.UnitSymbolPackage.USF_updateHelper.type ();
  }

}