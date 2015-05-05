package com.bbn.openmap.corba.CSpecialist.GraphicPackage;


/**
* com/bbn/openmap/corba/CSpecialist/GraphicPackage/EGraphic.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public final class EGraphic implements org.omg.CORBA.portable.IDLEntity
{
  public com.bbn.openmap.corba.CSpecialist.Graphic graph = null;
  public com.bbn.openmap.corba.CSpecialist.EComp obj = null;
  public String gID = null;
  public com.bbn.openmap.corba.CSpecialist.GraphicPackage.LineType lType = null;
  public com.bbn.openmap.corba.CSpecialist.GraphicPackage.GraphicType gType = null;
  public com.bbn.openmap.corba.CSpecialist.GraphicPackage.RenderType rType = null;
  public com.bbn.openmap.corba.CSpecialist.CColorPackage.EColor color = null;
  public com.bbn.openmap.corba.CSpecialist.CColorPackage.EColor fillColor = null;
  public short lineWidth = (short)0;
  public com.bbn.openmap.corba.CSpecialist.CStipplePackage.EStipple stipple = null;
  public com.bbn.openmap.corba.CSpecialist.CStipplePackage.EStipple fillStipple = null;
  public com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType dcType = null;

  public EGraphic ()
  {
  } // ctor

  public EGraphic (com.bbn.openmap.corba.CSpecialist.Graphic _graph, com.bbn.openmap.corba.CSpecialist.EComp _obj, String _gID, com.bbn.openmap.corba.CSpecialist.GraphicPackage.LineType _lType, com.bbn.openmap.corba.CSpecialist.GraphicPackage.GraphicType _gType, com.bbn.openmap.corba.CSpecialist.GraphicPackage.RenderType _rType, com.bbn.openmap.corba.CSpecialist.CColorPackage.EColor _color, com.bbn.openmap.corba.CSpecialist.CColorPackage.EColor _fillColor, short _lineWidth, com.bbn.openmap.corba.CSpecialist.CStipplePackage.EStipple _stipple, com.bbn.openmap.corba.CSpecialist.CStipplePackage.EStipple _fillStipple, com.bbn.openmap.corba.CSpecialist.GraphicPackage.DeclutterType _dcType)
  {
    graph = _graph;
    obj = _obj;
    gID = _gID;
    lType = _lType;
    gType = _gType;
    rType = _rType;
    color = _color;
    fillColor = _fillColor;
    lineWidth = _lineWidth;
    stipple = _stipple;
    fillStipple = _fillStipple;
    dcType = _dcType;
  } // ctor

} // class EGraphic