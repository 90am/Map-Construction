package com.bbn.openmap.corba.CSpecialist;

/**
* com/bbn/openmap/corba/CSpecialist/UGraphicHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class UGraphicHolder implements org.omg.CORBA.portable.Streamable
{
  public com.bbn.openmap.corba.CSpecialist.UGraphic value = null;

  public UGraphicHolder ()
  {
  }

  public UGraphicHolder (com.bbn.openmap.corba.CSpecialist.UGraphic initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.bbn.openmap.corba.CSpecialist.UGraphicHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.bbn.openmap.corba.CSpecialist.UGraphicHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.bbn.openmap.corba.CSpecialist.UGraphicHelper.type ();
  }

}