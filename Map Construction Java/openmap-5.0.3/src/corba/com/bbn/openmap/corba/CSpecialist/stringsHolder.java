package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/stringsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:45 PM EST
*/


// ----------------------------------------------------------------------
public final class stringsHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public stringsHolder ()
  {
  }

  public stringsHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.bbn.openmap.corba.CSpecialist.stringsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.bbn.openmap.corba.CSpecialist.stringsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.bbn.openmap.corba.CSpecialist.stringsHelper.type ();
  }

}