package com.bbn.openmap.corba.CSpecialist.CirclePackage;


/**
* com/bbn/openmap/corba/CSpecialist/CirclePackage/CF_updateHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

abstract public class CF_updateHelper
{
  private static String  _id = "IDL:CSpecialist/Circle/CF_update:1.0";

  public static void insert (org.omg.CORBA.Any a, com.bbn.openmap.corba.CSpecialist.CirclePackage.CF_update that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.bbn.openmap.corba.CSpecialist.CirclePackage.CF_update extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      org.omg.CORBA.TypeCode _disTypeCode0;
      _disTypeCode0 = com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFieldsHelper.type ();
      org.omg.CORBA.UnionMember[] _members0 = new org.omg.CORBA.UnionMember [6];
      org.omg.CORBA.TypeCode _tcOf_members0;
      org.omg.CORBA.Any _anyOf_members0;

      // Branch for p1 (case label CF_p1)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFieldsHelper.insert (_anyOf_members0, com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields.CF_p1);
      _tcOf_members0 = com.bbn.openmap.corba.CSpecialist.XYPointHelper.type ();
      _members0[0] = new org.omg.CORBA.UnionMember (
        "p1",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for ll1 (case label CF_ll1)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFieldsHelper.insert (_anyOf_members0, com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields.CF_ll1);
      _tcOf_members0 = com.bbn.openmap.corba.CSpecialist.LLPointHelper.type ();
      _members0[1] = new org.omg.CORBA.UnionMember (
        "ll1",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for major (case label CF_major)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFieldsHelper.insert (_anyOf_members0, com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields.CF_major);
      _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
      _members0[2] = new org.omg.CORBA.UnionMember (
        "major",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for minor (case label CF_minor)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFieldsHelper.insert (_anyOf_members0, com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields.CF_minor);
      _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
      _members0[3] = new org.omg.CORBA.UnionMember (
        "minor",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for width (case label CF_width)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFieldsHelper.insert (_anyOf_members0, com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields.CF_width);
      _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ushort);
      _members0[4] = new org.omg.CORBA.UnionMember (
        "width",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for height (case label CF_height)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFieldsHelper.insert (_anyOf_members0, com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields.CF_height);
      _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_ushort);
      _members0[5] = new org.omg.CORBA.UnionMember (
        "height",
        _anyOf_members0,
        _tcOf_members0,
        null);
      __typeCode = org.omg.CORBA.ORB.init ().create_union_tc (com.bbn.openmap.corba.CSpecialist.CirclePackage.CF_updateHelper.id (), "CF_update", _disTypeCode0, _members0);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static com.bbn.openmap.corba.CSpecialist.CirclePackage.CF_update read (org.omg.CORBA.portable.InputStream istream)
  {
    com.bbn.openmap.corba.CSpecialist.CirclePackage.CF_update value = new com.bbn.openmap.corba.CSpecialist.CirclePackage.CF_update ();
    com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields _dis0 = null;
    _dis0 = com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFieldsHelper.read (istream);
    switch (_dis0.value ())
    {
      case com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields._CF_p1:
        com.bbn.openmap.corba.CSpecialist.XYPoint _p1 = null;
        _p1 = com.bbn.openmap.corba.CSpecialist.XYPointHelper.read (istream);
        value.p1 (_p1);
        break;
      case com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields._CF_ll1:
        com.bbn.openmap.corba.CSpecialist.LLPoint _ll1 = null;
        _ll1 = com.bbn.openmap.corba.CSpecialist.LLPointHelper.read (istream);
        value.ll1 (_ll1);
        break;
      case com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields._CF_major:
        float _major = (float)0;
        _major = istream.read_float ();
        value.major (_major);
        break;
      case com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields._CF_minor:
        float _minor = (float)0;
        _minor = istream.read_float ();
        value.minor (_minor);
        break;
      case com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields._CF_width:
        short _width = (short)0;
        _width = istream.read_ushort ();
        value.width (_width);
        break;
      case com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields._CF_height:
        short _height = (short)0;
        _height = istream.read_ushort ();
        value.height (_height);
        break;
    }
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.bbn.openmap.corba.CSpecialist.CirclePackage.CF_update value)
  {
    com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFieldsHelper.write (ostream, value.discriminator ());
    switch (value.discriminator ().value ())
    {
      case com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields._CF_p1:
        com.bbn.openmap.corba.CSpecialist.XYPointHelper.write (ostream, value.p1 ());
        break;
      case com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields._CF_ll1:
        com.bbn.openmap.corba.CSpecialist.LLPointHelper.write (ostream, value.ll1 ());
        break;
      case com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields._CF_major:
        ostream.write_float (value.major ());
        break;
      case com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields._CF_minor:
        ostream.write_float (value.minor ());
        break;
      case com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields._CF_width:
        ostream.write_ushort (value.width ());
        break;
      case com.bbn.openmap.corba.CSpecialist.CirclePackage.settableFields._CF_height:
        ostream.write_ushort (value.height ());
        break;
    }
  }

}