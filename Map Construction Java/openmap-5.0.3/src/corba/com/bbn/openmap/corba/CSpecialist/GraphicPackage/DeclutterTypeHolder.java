package com.bbn.openmap.corba.CSpecialist.GraphicPackage;

/**
* com/bbn/openmap/corba/CSpecialist/GraphicPackage/DeclutterTypeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class DeclutterTypeHolder implements org.omg.CORBA.portable.Streamable
{
  public com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType value = null;

  public DeclutterTypeHolder ()
  {
  }

  public DeclutterTypeHolder (com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterTypeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterTypeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterTypeHelper.type ();
  }

}
