package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/U2525SymbolOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public interface U2525SymbolOperations  extends com.bbn.openmap.corba.CSpecialist.GraphicOperations
{
  com.bbn.openmap.corba.CSpecialist.XYPoint p1 ();
  void p1 (com.bbn.openmap.corba.CSpecialist.XYPoint newP1);
  com.bbn.openmap.corba.CSpecialist.LLPoint ll1 ();
  void ll1 (com.bbn.openmap.corba.CSpecialist.LLPoint newLl1);
  String symbol ();
  void symbol (String newSymbol);
  char confirmed ();
  void confirmed (char newConfirmed);
  char reduced ();
  void reduced (char newReduced);
  boolean is_hq ();
  void is_hq (boolean newIs_hq);

  // Headquarters mark display
  String movement ();

  // Headquarters mark display
  void movement (String newMovement);
  String left2 ();
  void left2 (String newLeft2);
  String left4 ();
  void left4 (String newLeft4);
  String right2 ();
  void right2 (String newRight2);
  String right3 ();
  void right3 (String newRight3);
  String right4 ();
  void right4 (String newRight4);
  String bottom1 ();
  void bottom1 (String newBottom1);
  short nom_size ();
  void nom_size (short newNom_size);

  // nominal size is in pixels
  short min_size ();

  // nominal size is in pixels
  void min_size (short newMin_size);

  // minimal size is in pixels
  short max_size ();

  // minimal size is in pixels
  void max_size (short newMax_size);

  // maximum size is in pixels
  int scale ();

  // maximum size is in pixels
  void scale (int newScale);

  // scale at which size is nom_size
  float rotate ();

  // scale at which size is nom_size
  void rotate (float newRotate);
  com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.E2525Symbol fill ();
} // interface U2525SymbolOperations
