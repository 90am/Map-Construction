package com.bbn.openmap.corba.CSpecialist.LinePackage;

/**
* com/bbn/openmap/corba/CSpecialist/LinePackage/ELineHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class ELineHolder implements org.omg.CORBA.portable.Streamable
{
  public com.bbn.openmap.corba.CSpecialist.LinePackage.ELine value = null;

  public ELineHolder ()
  {
  }

  public ELineHolder (com.bbn.openmap.corba.CSpecialist.LinePackage.ELine initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.bbn.openmap.corba.CSpecialist.LinePackage.ELineHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.bbn.openmap.corba.CSpecialist.LinePackage.ELineHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.bbn.openmap.corba.CSpecialist.LinePackage.ELineHelper.type ();
  }

}