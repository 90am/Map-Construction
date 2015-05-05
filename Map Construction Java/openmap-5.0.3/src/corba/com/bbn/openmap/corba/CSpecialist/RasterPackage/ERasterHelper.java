package com.bbn.openmap.corba.CSpecialist.RasterPackage;


/**
* com/bbn/openmap/corba/CSpecialist/RasterPackage/ERasterHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

abstract public class ERasterHelper
{
  private static String  _id = "IDL:CSpecialist/Raster/ERaster:1.0";

  public static void insert (org.omg.CORBA.Any a, com.bbn.openmap.corba.CSpecialist.RasterPackage.ERaster that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.bbn.openmap.corba.CSpecialist.RasterPackage.ERaster extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [11];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = com.bbn.openmap.corba.CSpecialist.GraphicPackage.EGraphicHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "egraphic",
            _tcOf_members0,
            null);
          _tcOf_members0 = com.bbn.openmap.corba.CSpecialist.XYPointHelper.type ();
          _members0[1] = new org.omg.CORBA.StructMember (
            "p1",
            _tcOf_members0,
            null);
          _tcOf_members0 = com.bbn.openmap.corba.CSpecialist.LLPointHelper.type ();
          _members0[2] = new org.omg.CORBA.StructMember (
            "ll1",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (com.bbn.openmap.corba.CSpecialist.pixeldataHelper.id (), "pixeldata", _tcOf_members0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "pixels",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ushort);
          _members0[4] = new org.omg.CORBA.StructMember (
            "width",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ushort);
          _members0[5] = new org.omg.CORBA.StructMember (
            "height",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ushort);
          _members0[6] = new org.omg.CORBA.StructMember (
            "x_hot",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ushort);
          _members0[7] = new org.omg.CORBA.StructMember (
            "y_hot",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ushort);
          _members0[8] = new org.omg.CORBA.StructMember (
            "colorsTotal",
            _tcOf_members0,
            null);
          _tcOf_members0 = com.bbn.openmap.corba.CSpecialist.CTEntryHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (com.bbn.openmap.corba.CSpecialist.colorTableHelper.id (), "colorTable", _tcOf_members0);
          _members0[9] = new org.omg.CORBA.StructMember (
            "ct",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ushort);
          _members0[10] = new org.omg.CORBA.StructMember (
            "transparent",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (com.bbn.openmap.corba.CSpecialist.RasterPackage.ERasterHelper.id (), "ERaster", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static com.bbn.openmap.corba.CSpecialist.RasterPackage.ERaster read (org.omg.CORBA.portable.InputStream istream)
  {
    com.bbn.openmap.corba.CSpecialist.RasterPackage.ERaster value = new com.bbn.openmap.corba.CSpecialist.RasterPackage.ERaster ();
    value.egraphic = com.bbn.openmap.corba.CSpecialist.GraphicPackage.EGraphicHelper.read (istream);
    value.p1 = com.bbn.openmap.corba.CSpecialist.XYPointHelper.read (istream);
    value.ll1 = com.bbn.openmap.corba.CSpecialist.LLPointHelper.read (istream);
    value.pixels = com.bbn.openmap.corba.CSpecialist.pixeldataHelper.read (istream);
    value.width = istream.read_ushort ();
    value.height = istream.read_ushort ();
    value.x_hot = istream.read_ushort ();
    value.y_hot = istream.read_ushort ();
    value.colorsTotal = istream.read_ushort ();
    value.ct = com.bbn.openmap.corba.CSpecialist.colorTableHelper.read (istream);
    value.transparent = istream.read_ushort ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.bbn.openmap.corba.CSpecialist.RasterPackage.ERaster value)
  {
    com.bbn.openmap.corba.CSpecialist.GraphicPackage.EGraphicHelper.write (ostream, value.egraphic);
    com.bbn.openmap.corba.CSpecialist.XYPointHelper.write (ostream, value.p1);
    com.bbn.openmap.corba.CSpecialist.LLPointHelper.write (ostream, value.ll1);
    com.bbn.openmap.corba.CSpecialist.pixeldataHelper.write (ostream, value.pixels);
    ostream.write_ushort (value.width);
    ostream.write_ushort (value.height);
    ostream.write_ushort (value.x_hot);
    ostream.write_ushort (value.y_hot);
    ostream.write_ushort (value.colorsTotal);
    com.bbn.openmap.corba.CSpecialist.colorTableHelper.write (ostream, value.ct);
    ostream.write_ushort (value.transparent);
  }

}
