package com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider;


/**
* com/bbn/openmap/layer/rpf/corba/CRpfFrameProvider/CRFPCoverageBoxHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/rpf/corba/CorbaRpfFrameProvider.idl
* Tuesday, November 12, 2013 11:07:44 PM EST
*/

abstract public class CRFPCoverageBoxHelper
{
  private static String  _id = "IDL:CRpfFrameProvider/CRFPCoverageBox:1.0";

  public static void insert (org.omg.CORBA.Any a, com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCoverageBox that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCoverageBox extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [14];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[0] = new org.omg.CORBA.StructMember (
            "nw_lat",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[1] = new org.omg.CORBA.StructMember (
            "nw_lon",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[2] = new org.omg.CORBA.StructMember (
            "se_lat",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[3] = new org.omg.CORBA.StructMember (
            "se_lon",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_double);
          _members0[4] = new org.omg.CORBA.StructMember (
            "subframeLatInterval",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_double);
          _members0[5] = new org.omg.CORBA.StructMember (
            "subframeLonInterval",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[6] = new org.omg.CORBA.StructMember (
            "chartCode",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ushort);
          _members0[7] = new org.omg.CORBA.StructMember (
            "zone",
            _tcOf_members0,
            null);
          _tcOf_members0 = com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.XYPointHelper.type ();
          _members0[8] = new org.omg.CORBA.StructMember (
            "startIndexes",
            _tcOf_members0,
            null);
          _tcOf_members0 = com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.XYPointHelper.type ();
          _members0[9] = new org.omg.CORBA.StructMember (
            "endIndexes",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ushort);
          _members0[10] = new org.omg.CORBA.StructMember (
            "tocNumber",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ushort);
          _members0[11] = new org.omg.CORBA.StructMember (
            "entryNumber",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[12] = new org.omg.CORBA.StructMember (
            "scale",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[13] = new org.omg.CORBA.StructMember (
            "percentCoverage",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCoverageBoxHelper.id (), "CRFPCoverageBox", _members0);
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

  public static com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCoverageBox read (org.omg.CORBA.portable.InputStream istream)
  {
    com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCoverageBox value = new com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCoverageBox ();
    value.nw_lat = istream.read_float ();
    value.nw_lon = istream.read_float ();
    value.se_lat = istream.read_float ();
    value.se_lon = istream.read_float ();
    value.subframeLatInterval = istream.read_double ();
    value.subframeLonInterval = istream.read_double ();
    value.chartCode = istream.read_string ();
    value.zone = istream.read_ushort ();
    value.startIndexes = com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.XYPointHelper.read (istream);
    value.endIndexes = com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.XYPointHelper.read (istream);
    value.tocNumber = istream.read_ushort ();
    value.entryNumber = istream.read_ushort ();
    value.scale = istream.read_float ();
    value.percentCoverage = istream.read_float ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCoverageBox value)
  {
    ostream.write_float (value.nw_lat);
    ostream.write_float (value.nw_lon);
    ostream.write_float (value.se_lat);
    ostream.write_float (value.se_lon);
    ostream.write_double (value.subframeLatInterval);
    ostream.write_double (value.subframeLonInterval);
    ostream.write_string (value.chartCode);
    ostream.write_ushort (value.zone);
    com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.XYPointHelper.write (ostream, value.startIndexes);
    com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.XYPointHelper.write (ostream, value.endIndexes);
    ostream.write_ushort (value.tocNumber);
    ostream.write_ushort (value.entryNumber);
    ostream.write_float (value.scale);
    ostream.write_float (value.percentCoverage);
  }

}
