package com.bbn.openmap.corba.CSpecialist.BitmapPackage;


/**
* com/bbn/openmap/corba/CSpecialist/BitmapPackage/EBitmap.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class EBitmap implements org.omg.CORBA.portable.IDLEntity
{
  public com.bbn.openmap.corba.CSpecialist.GraphicPackage.EGraphic egraphic = null;
  public com.bbn.openmap.corba.CSpecialist.XYPoint p1 = null;
  public com.bbn.openmap.corba.CSpecialist.LLPoint ll1 = null;
  public short width = (short)0;
  public short height = (short)0;
  public short x_hot = (short)0;
  public short y_hot = (short)0;
  public byte bits[] = null;
  public String bmref = null;

  public EBitmap ()
  {
  } // ctor

  public EBitmap (com.bbn.openmap.corba.CSpecialist.GraphicPackage.EGraphic _egraphic, com.bbn.openmap.corba.CSpecialist.XYPoint _p1, com.bbn.openmap.corba.CSpecialist.LLPoint _ll1, short _width, short _height, short _x_hot, short _y_hot, byte[] _bits, String _bmref)
  {
    egraphic = _egraphic;
    p1 = _p1;
    ll1 = _ll1;
    width = _width;
    height = _height;
    x_hot = _x_hot;
    y_hot = _y_hot;
    bits = _bits;
    bmref = _bmref;
  } // ctor

} // class EBitmap