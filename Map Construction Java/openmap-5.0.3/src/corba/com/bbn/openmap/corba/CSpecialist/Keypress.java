package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/Keypress.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class Keypress implements org.omg.CORBA.portable.IDLEntity
{
  public com.bbn.openmap.corba.CSpecialist.XYPoint point = null;

  //LLPoint llpoint;  implement for consistency?
  public char key = (char)0;
  public com.bbn.openmap.corba.CSpecialist.key_modifiers modifiers = null;

  public Keypress ()
  {
  } // ctor

  public Keypress (com.bbn.openmap.corba.CSpecialist.XYPoint _point, char _key, com.bbn.openmap.corba.CSpecialist.key_modifiers _modifiers)
  {
    point = _point;
    key = _key;
    modifiers = _modifiers;
  } // ctor

} // class Keypress
