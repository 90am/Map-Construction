package com.bbn.openmap.corba.CSpecialist.PolyPackage;


/**
* com/bbn/openmap/corba/CSpecialist/PolyPackage/PF_update.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class PF_update implements org.omg.CORBA.portable.IDLEntity
{
  private com.bbn.openmap.corba.CSpecialist.LLPoint ___ll1;
  private com.bbn.openmap.corba.CSpecialist.PolyPackage.CoordMode ___cMode;
  private com.bbn.openmap.corba.CSpecialist.XYPoint[] ___xypoints;
  private com.bbn.openmap.corba.CSpecialist.LLPoint[] ___llpoints;
  private com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields __discriminator;
  private boolean __uninitialized = true;

  public PF_update ()
  {
  }

  public com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields discriminator ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    return __discriminator;
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
    __discriminator = com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields.PF_ll1;
    ___ll1 = value;
    __uninitialized = false;
  }

  public void ll1 (com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields discriminator, com.bbn.openmap.corba.CSpecialist.LLPoint value)
  {
    verifyll1 (discriminator);
    __discriminator = discriminator;
    ___ll1 = value;
    __uninitialized = false;
  }

  private void verifyll1 (com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields.PF_ll1)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public com.bbn.openmap.corba.CSpecialist.PolyPackage.CoordMode cMode ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifycMode (__discriminator);
    return ___cMode;
  }

  public void cMode (com.bbn.openmap.corba.CSpecialist.PolyPackage.CoordMode value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields.PF_cMode;
    ___cMode = value;
    __uninitialized = false;
  }

  public void cMode (com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields discriminator, com.bbn.openmap.corba.CSpecialist.PolyPackage.CoordMode value)
  {
    verifycMode (discriminator);
    __discriminator = discriminator;
    ___cMode = value;
    __uninitialized = false;
  }

  private void verifycMode (com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields.PF_cMode)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public com.bbn.openmap.corba.CSpecialist.XYPoint[] xypoints ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyxypoints (__discriminator);
    return ___xypoints;
  }

  public void xypoints (com.bbn.openmap.corba.CSpecialist.XYPoint[] value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields.PF_xypoints;
    ___xypoints = value;
    __uninitialized = false;
  }

  public void xypoints (com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields discriminator, com.bbn.openmap.corba.CSpecialist.XYPoint[] value)
  {
    verifyxypoints (discriminator);
    __discriminator = discriminator;
    ___xypoints = value;
    __uninitialized = false;
  }

  private void verifyxypoints (com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields.PF_xypoints)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public com.bbn.openmap.corba.CSpecialist.LLPoint[] llpoints ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyllpoints (__discriminator);
    return ___llpoints;
  }

  public void llpoints (com.bbn.openmap.corba.CSpecialist.LLPoint[] value)
  {
    __discriminator = com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields.PF_llpoints;
    ___llpoints = value;
    __uninitialized = false;
  }

  public void llpoints (com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields discriminator, com.bbn.openmap.corba.CSpecialist.LLPoint[] value)
  {
    verifyllpoints (discriminator);
    __discriminator = discriminator;
    ___llpoints = value;
    __uninitialized = false;
  }

  private void verifyllpoints (com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields discriminator)
  {
    if (discriminator != com.bbn.openmap.corba.CSpecialist.PolyPackage.settableFields.PF_llpoints)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

} // class PF_update