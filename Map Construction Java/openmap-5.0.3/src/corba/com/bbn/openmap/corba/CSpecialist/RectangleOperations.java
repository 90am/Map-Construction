package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/RectangleOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public interface RectangleOperations  extends com.bbn.openmap.corba.CSpecialist.GraphicOperations
{
  com.bbn.openmap.corba.CSpecialist.XYPoint p1 ();
  void p1 (com.bbn.openmap.corba.CSpecialist.XYPoint newP1);
  com.bbn.openmap.corba.CSpecialist.XYPoint p2 ();
  void p2 (com.bbn.openmap.corba.CSpecialist.XYPoint newP2);
  com.bbn.openmap.corba.CSpecialist.LLPoint ll1 ();
  void ll1 (com.bbn.openmap.corba.CSpecialist.LLPoint newLl1);
  com.bbn.openmap.corba.CSpecialist.LLPoint ll2 ();
  void ll2 (com.bbn.openmap.corba.CSpecialist.LLPoint newLl2);
  com.bbn.openmap.corba.CSpecialist.RectanglePackage.ERectangle fill ();
} // interface RectangleOperations
