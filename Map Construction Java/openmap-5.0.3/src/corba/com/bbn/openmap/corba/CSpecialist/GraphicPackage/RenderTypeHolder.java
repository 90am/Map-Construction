package com.bbn.openmap.corba.CSpecialist.GraphicPackage;

/**
* com/bbn/openmap/corba/CSpecialist/GraphicPackage/RenderTypeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:45 PM EST
*/

public final class RenderTypeHolder implements org.omg.CORBA.portable.Streamable
{
  public com.bbn.openmap.corba.CSpecialist.GraphicPackage.RenderType value = null;

  public RenderTypeHolder ()
  {
  }

  public RenderTypeHolder (com.bbn.openmap.corba.CSpecialist.GraphicPackage.RenderType initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.bbn.openmap.corba.CSpecialist.GraphicPackage.RenderTypeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.bbn.openmap.corba.CSpecialist.GraphicPackage.RenderTypeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.bbn.openmap.corba.CSpecialist.GraphicPackage.RenderTypeHelper.type ();
  }

}
