package com.bbn.openmap.corba.CSpecialist.ForceArrowPackage;


/**
* com/bbn/openmap/corba/CSpecialist/ForceArrowPackage/EForceArrow.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class EForceArrow implements org.omg.CORBA.portable.IDLEntity
{
  public com.bbn.openmap.corba.CSpecialist.GraphicPackage.EGraphic egraphic = null;
  public com.bbn.openmap.corba.CSpecialist.XYPoint p1 = null;
  public com.bbn.openmap.corba.CSpecialist.XYPoint p2 = null;
  public com.bbn.openmap.corba.CSpecialist.XYPoint p3 = null;
  public com.bbn.openmap.corba.CSpecialist.LLPoint ll1 = null;
  public com.bbn.openmap.corba.CSpecialist.LLPoint ll2 = null;
  public com.bbn.openmap.corba.CSpecialist.LLPoint ll3 = null;
  public com.bbn.openmap.corba.CSpecialist.LLPoint offset = null;

  public EForceArrow ()
  {
  } // ctor

  public EForceArrow (com.bbn.openmap.corba.CSpecialist.GraphicPackage.EGraphic _egraphic, com.bbn.openmap.corba.CSpecialist.XYPoint _p1, com.bbn.openmap.corba.CSpecialist.XYPoint _p2, com.bbn.openmap.corba.CSpecialist.XYPoint _p3, com.bbn.openmap.corba.CSpecialist.LLPoint _ll1, com.bbn.openmap.corba.CSpecialist.LLPoint _ll2, com.bbn.openmap.corba.CSpecialist.LLPoint _ll3, com.bbn.openmap.corba.CSpecialist.LLPoint _offset)
  {
    egraphic = _egraphic;
    p1 = _p1;
    p2 = _p2;
    p3 = _p3;
    ll1 = _ll1;
    ll2 = _ll2;
    ll3 = _ll3;
    offset = _offset;
  } // ctor

} // class EForceArrow
