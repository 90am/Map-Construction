package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/CheckButton.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class CheckButton implements org.omg.CORBA.portable.IDLEntity
{
  public String button_label = null;
  public boolean checked = false;

  public CheckButton ()
  {
  } // ctor

  public CheckButton (String _button_label, boolean _checked)
  {
    button_label = _button_label;
    checked = _checked;
  } // ctor

} // class CheckButton