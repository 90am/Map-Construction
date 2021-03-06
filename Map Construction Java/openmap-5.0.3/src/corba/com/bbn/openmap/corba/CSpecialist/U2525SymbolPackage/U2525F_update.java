package com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage;


/**
* com/bbn/openmap/corba/CSpecialist/U2525SymbolPackage/U2525F_update.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class U2525F_update implements org.omg.CORBA.portable.IDLEntity
{
  private com.bbn.openmap.corba.CSpecialist.XYPoint ___p1;
  private com.bbn.openmap.corba.CSpecialist.LLPoint ___ll1;
  private String ___symbol;
  private char ___confirmed;
  private char ___reduced;
  private String ___movement;
  private String ___left2;
  private String ___left4;
  private String ___right2;
  private String ___right3;
  private String ___right4;
  private String ___bottom1;
  private short ___nom_size;
  private short ___min_size;
  private short ___max_size;
  private int ___scale;
  private boolean ___is_hq;
  private float ___rotate;
  private com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields __discriminator;
  private boolean __uninitialized = true;

  public U2525F_update ()
  {
  }

  public com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    return __discriminator;
  }

  public com.bbn.openmap.corba.CSpecialist.XYPoint p1 ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyp1 (__discriminator);
    return ___p1;
  }

  public void p1 (com.bbn.openmap.corba.CSpecialist.XYPoint value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_p1;
    ___p1 = value;
    __uninitialized = false;
  }

  public void p1 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, com.bbn.openmap.corba.CSpecialist.XYPoint value)
  {
    verifyp1 (discriminator);
    __discriminator = discriminator;
    ___p1 = value;
    __uninitialized = false;
  }

  private void verifyp1 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_p1)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public com.bbn.openmap.corba.CSpecialist.LLPoint ll1 ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyll1 (__discriminator);
    return ___ll1;
  }

  public void ll1 (com.bbn.openmap.corba.CSpecialist.LLPoint value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_ll1;
    ___ll1 = value;
    __uninitialized = false;
  }

  public void ll1 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, com.bbn.openmap.corba.CSpecialist.LLPoint value)
  {
    verifyll1 (discriminator);
    __discriminator = discriminator;
    ___ll1 = value;
    __uninitialized = false;
  }

  private void verifyll1 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_ll1)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public String symbol ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifysymbol (__discriminator);
    return ___symbol;
  }

  public void symbol (String value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_symbol;
    ___symbol = value;
    __uninitialized = false;
  }

  public void symbol (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, String value)
  {
    verifysymbol (discriminator);
    __discriminator = discriminator;
    ___symbol = value;
    __uninitialized = false;
  }

  private void verifysymbol (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_symbol)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public char confirmed ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyconfirmed (__discriminator);
    return ___confirmed;
  }

  public void confirmed (char value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_confirmed;
    ___confirmed = value;
    __uninitialized = false;
  }

  public void confirmed (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, char value)
  {
    verifyconfirmed (discriminator);
    __discriminator = discriminator;
    ___confirmed = value;
    __uninitialized = false;
  }

  private void verifyconfirmed (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_confirmed)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public char reduced ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyreduced (__discriminator);
    return ___reduced;
  }

  public void reduced (char value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_reduced;
    ___reduced = value;
    __uninitialized = false;
  }

  public void reduced (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, char value)
  {
    verifyreduced (discriminator);
    __discriminator = discriminator;
    ___reduced = value;
    __uninitialized = false;
  }

  private void verifyreduced (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_reduced)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public String movement ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifymovement (__discriminator);
    return ___movement;
  }

  public void movement (String value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_movement;
    ___movement = value;
    __uninitialized = false;
  }

  public void movement (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, String value)
  {
    verifymovement (discriminator);
    __discriminator = discriminator;
    ___movement = value;
    __uninitialized = false;
  }

  private void verifymovement (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_movement)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public String left2 ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyleft2 (__discriminator);
    return ___left2;
  }

  public void left2 (String value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_left2;
    ___left2 = value;
    __uninitialized = false;
  }

  public void left2 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, String value)
  {
    verifyleft2 (discriminator);
    __discriminator = discriminator;
    ___left2 = value;
    __uninitialized = false;
  }

  private void verifyleft2 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_left2)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public String left4 ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyleft4 (__discriminator);
    return ___left4;
  }

  public void left4 (String value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_left4;
    ___left4 = value;
    __uninitialized = false;
  }

  public void left4 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, String value)
  {
    verifyleft4 (discriminator);
    __discriminator = discriminator;
    ___left4 = value;
    __uninitialized = false;
  }

  private void verifyleft4 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_left4)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public String right2 ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyright2 (__discriminator);
    return ___right2;
  }

  public void right2 (String value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_right2;
    ___right2 = value;
    __uninitialized = false;
  }

  public void right2 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, String value)
  {
    verifyright2 (discriminator);
    __discriminator = discriminator;
    ___right2 = value;
    __uninitialized = false;
  }

  private void verifyright2 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_right2)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public String right3 ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyright3 (__discriminator);
    return ___right3;
  }

  public void right3 (String value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_right3;
    ___right3 = value;
    __uninitialized = false;
  }

  public void right3 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, String value)
  {
    verifyright3 (discriminator);
    __discriminator = discriminator;
    ___right3 = value;
    __uninitialized = false;
  }

  private void verifyright3 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_right3)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public String right4 ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyright4 (__discriminator);
    return ___right4;
  }

  public void right4 (String value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_right4;
    ___right4 = value;
    __uninitialized = false;
  }

  public void right4 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, String value)
  {
    verifyright4 (discriminator);
    __discriminator = discriminator;
    ___right4 = value;
    __uninitialized = false;
  }

  private void verifyright4 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_right4)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public String bottom1 ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifybottom1 (__discriminator);
    return ___bottom1;
  }

  public void bottom1 (String value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_bottom1;
    ___bottom1 = value;
    __uninitialized = false;
  }

  public void bottom1 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, String value)
  {
    verifybottom1 (discriminator);
    __discriminator = discriminator;
    ___bottom1 = value;
    __uninitialized = false;
  }

  private void verifybottom1 (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_bottom1)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public short nom_size ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifynom_size (__discriminator);
    return ___nom_size;
  }

  public void nom_size (short value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_nom_size;
    ___nom_size = value;
    __uninitialized = false;
  }

  public void nom_size (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, short value)
  {
    verifynom_size (discriminator);
    __discriminator = discriminator;
    ___nom_size = value;
    __uninitialized = false;
  }

  private void verifynom_size (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_nom_size)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public short min_size ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifymin_size (__discriminator);
    return ___min_size;
  }

  public void min_size (short value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_min_size;
    ___min_size = value;
    __uninitialized = false;
  }

  public void min_size (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, short value)
  {
    verifymin_size (discriminator);
    __discriminator = discriminator;
    ___min_size = value;
    __uninitialized = false;
  }

  private void verifymin_size (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_min_size)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public short max_size ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifymax_size (__discriminator);
    return ___max_size;
  }

  public void max_size (short value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_max_size;
    ___max_size = value;
    __uninitialized = false;
  }

  public void max_size (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, short value)
  {
    verifymax_size (discriminator);
    __discriminator = discriminator;
    ___max_size = value;
    __uninitialized = false;
  }

  private void verifymax_size (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_max_size)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public int scale ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyscale (__discriminator);
    return ___scale;
  }

  public void scale (int value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_scale;
    ___scale = value;
    __uninitialized = false;
  }

  public void scale (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, int value)
  {
    verifyscale (discriminator);
    __discriminator = discriminator;
    ___scale = value;
    __uninitialized = false;
  }

  private void verifyscale (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_scale)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public boolean is_hq ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyis_hq (__discriminator);
    return ___is_hq;
  }

  public void is_hq (boolean value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_is_hq;
    ___is_hq = value;
    __uninitialized = false;
  }

  public void is_hq (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, boolean value)
  {
    verifyis_hq (discriminator);
    __discriminator = discriminator;
    ___is_hq = value;
    __uninitialized = false;
  }

  private void verifyis_hq (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_is_hq)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public float rotate ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyrotate (__discriminator);
    return ___rotate;
  }

  public void rotate (float value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_rotate;
    ___rotate = value;
    __uninitialized = false;
  }

  public void rotate (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator, float value)
  {
    verifyrotate (discriminator);
    __discriminator = discriminator;
    ___rotate = value;
    __uninitialized = false;
  }

  private void verifyrotate (com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.U2525SymbolPackage.settableFields.U2525F_rotate)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

} // class U2525F_update
