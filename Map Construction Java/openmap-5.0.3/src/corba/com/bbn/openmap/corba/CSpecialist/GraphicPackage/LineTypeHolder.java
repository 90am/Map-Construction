package com.bbn.openmap.corba.CSpecialist.GraphicPackage;

/**
* com/bbn/openmap/corba/CSpecialist/GraphicPackage/LineTypeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:45 PM EST
*/

public final class LineTypeHolder implements org.omg.CORBA.portable.Streamable
{
  public com.bbn.openmap.corba.CSpecialist.GraphicPackage.LineType value = null;

  public LineTypeHolder ()
  {
  }

  public LineTypeHolder (com.bbn.openmap.corba.CSpecialist.GraphicPackage.LineType initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.bbn.openmap.corba.CSpecialist.GraphicPackage.LineTypeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.bbn.openmap.corba.CSpecialist.GraphicPackage.LineTypeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.bbn.openmap.corba.CSpecialist.GraphicPackage.LineTypeHelper.type ();
  }

}
