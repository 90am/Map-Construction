package com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage;

/**
* com/bbn/openmap/corba/CSpecialist/U2525SymbolPackage/E2525SymbolHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class E2525SymbolHolder implements org.omg.CORBA.portable.Streamable
{
  public com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.E2525Symbol value = null;

  public E2525SymbolHolder ()
  {
  }

  public E2525SymbolHolder (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.E2525Symbol initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.E2525SymbolHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.E2525SymbolHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.E2525SymbolHelper.type ();
  }

}
