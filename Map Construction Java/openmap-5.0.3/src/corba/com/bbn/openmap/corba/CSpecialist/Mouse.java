package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/Mouse.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class Mouse implements org.omg.CORBA.portable.IDLEntity
{
  public com.bbn.openmap.corba.CSpecialist.XYPoint point = null;
  public com.bbn.openmap.corba.CSpecialist.LLPoint llpoint = null;
  public short mousebutton = (short)0;
  public boolean press = false;

  //true for press, false for release
  public com.bbn.openmap.corba.CSpecialist.key_modifiers modifiers = null;

  public Mouse ()
  {
  } // ctor

  public Mouse (com.bbn.openmap.corba.CSpecialist.XYPoint _point, com.bbn.openmap.corba.CSpecialist.LLPoint _llpoint, short _mousebutton, boolean _press, com.bbn.openmap.corba.CSpecialist.key_modifiers _modifiers)
  {
    point = _point;
    llpoint = _llpoint;
    mousebutton = _mousebutton;
    press = _press;
    modifiers = _modifiers;
  } // ctor

} // class Mouse