package com.bbn.openmap.corba.CSpecialist.PolyPackage;


/**
* com/bbn/openmap/corba/CSpecialist/PolyPackage/EPoly.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class EPoly implements org.omg.CORBA.portable.IDLEntity
{
  public com.bbn.openmap.corba.CSpecialist.GraphicPackage.EGraphic egraphic = null;
  public com.bbn.openmap.corba.CSpecialist.LLPoint ll1 = null;
  public com.bbn.openmap.corba.CSpecialist.PolyPackage.CoordMode cMode = null;
  public com.bbn.openmap.corba.CSpecialist.XYPoint xypoints[] = null;
  public com.bbn.openmap.corba.CSpecialist.LLPoint llpoints[] = null;

  public EPoly ()
  {
  } // ctor

  public EPoly (com.bbn.openmap.corba.CSpecialist.GraphicPackage.EGraphic _egraphic, com.bbn.openmap.corba.CSpecialist.LLPoint _ll1, com.bbn.openmap.corba.CSpecialist.PolyPackage.CoordMode _cMode, com.bbn.openmap.corba.CSpecialist.XYPoint[] _xypoints, com.bbn.openmap.corba.CSpecialist.LLPoint[] _llpoints)
  {
    egraphic = _egraphic;
    ll1 = _ll1;
    cMode = _cMode;
    xypoints = _xypoints;
    llpoints = _llpoints;
  } // ctor

} // class EPoly
