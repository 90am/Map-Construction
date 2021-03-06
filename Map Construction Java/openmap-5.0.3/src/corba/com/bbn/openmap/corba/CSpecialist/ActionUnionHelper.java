package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/ActionUnionHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

abstract public class ActionUnionHelper
{
  private static String  _id = "IDL:CSpecialist/ActionUnion:1.0";

  public static void insert (org.omg.CORBA.Any a, com.bbn.openmap.corba.CSpecialist.ActionUnion that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.bbn.openmap.corba.CSpecialist.ActionUnion extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      org.omg.CORBA.TypeCode _disTypeCode0;
      _disTypeCode0 = com.bbn.openmap.corba.CSpecialist.ActionTypeHelper.type ();
      org.omg.CORBA.UnionMember[] _members0 = new org.omg.CORBA.UnionMember [5];
      org.omg.CORBA.TypeCode _tcOf_members0;
      org.omg.CORBA.Any _anyOf_members0;

      // Branch for ginfo (case label UpdateGraphics)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      com.bbn.openmap.corba.CSpecialist.ActionTypeHelper.insert (_anyOf_members0, com.bbn.openmap.corba.CSpecialist.ActionType.UpdateGraphics);
      _tcOf_members0 = com.bbn.openmap.corba.CSpecialist.UpdateRecordHelper.type ();
      _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
      _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (com.bbn.openmap.corba.CSpecialist.GUpdateHelper.id (), "GUpdate", _tcOf_members0);
      _members0[0] = new org.omg.CORBA.UnionMember (
        "ginfo",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for itext (case label InfoText)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      com.bbn.openmap.corba.CSpecialist.ActionTypeHelper.insert (_anyOf_members0, com.bbn.openmap.corba.CSpecialist.ActionType.InfoText);
      _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
      _members0[1] = new org.omg.CORBA.UnionMember (
        "itext",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for ptext (case label PlainText)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      com.bbn.openmap.corba.CSpecialist.ActionTypeHelper.insert (_anyOf_members0, com.bbn.openmap.corba.CSpecialist.ActionType.PlainText);
      _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
      _members0[2] = new org.omg.CORBA.UnionMember (
        "ptext",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for htext (case label HTMLText)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      com.bbn.openmap.corba.CSpecialist.ActionTypeHelper.insert (_anyOf_members0, com.bbn.openmap.corba.CSpecialist.ActionType.HTMLText);
      _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
      _members0[3] = new org.omg.CORBA.UnionMember (
        "htext",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for url (case label URL)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      com.bbn.openmap.corba.CSpecialist.ActionTypeHelper.insert (_anyOf_members0, com.bbn.openmap.corba.CSpecialist.ActionType.URL);
      _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
      _members0[4] = new org.omg.CORBA.UnionMember (
        "url",
        _anyOf_members0,
        _tcOf_members0,
        null);
      __typeCode = org.omg.CORBA.ORB.init ().create_union_tc (com.bbn.openmap.corba.CSpecialist.ActionUnionHelper.id (), "ActionUnion", _disTypeCode0, _members0);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static com.bbn.openmap.corba.CSpecialist.ActionUnion read (org.omg.CORBA.portable.InputStream istream)
  {
    com.bbn.openmap.corba.CSpecialist.ActionUnion value = new com.bbn.openmap.corba.CSpecialist.ActionUnion ();
    com.bbn.openmap.corba.CSpecialist.ActionType _dis0 = null;
    _dis0 = com.bbn.openmap.corba.CSpecialist.ActionTypeHelper.read (istream);
    switch (_dis0.value ())
    {
      case com.bbn.openmap.corba.CSpecialist.ActionType._UpdateGraphics:
        com.bbn.openmap.corba.CSpecialist.UpdateRecord _ginfo[] = null;
        _ginfo = com.bbn.openmap.corba.CSpecialist.GUpdateHelper.read (istream);
        value.ginfo (_ginfo);
        break;
      case com.bbn.openmap.corba.CSpecialist.ActionType._InfoText:
        String _itext = null;
        _itext = istream.read_string ();
        value.itext (_itext);
        break;
      case com.bbn.openmap.corba.CSpecialist.ActionType._PlainText:
        String _ptext = null;
        _ptext = istream.read_string ();
        value.ptext (_ptext);
        break;
      case com.bbn.openmap.corba.CSpecialist.ActionType._HTMLText:
        String _htext = null;
        _htext = istream.read_string ();
        value.htext (_htext);
        break;
      case com.bbn.openmap.corba.CSpecialist.ActionType._URL:
        String _url = null;
        _url = istream.read_string ();
        value.url (_url);
        break;
      default:
        value._default( _dis0 ) ;
        break;
    }
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.bbn.openmap.corba.CSpecialist.ActionUnion value)
  {
    com.bbn.openmap.corba.CSpecialist.ActionTypeHelper.write (ostream, value.discriminator ());
    switch (value.discriminator ().value ())
    {
      case com.bbn.openmap.corba.CSpecialist.ActionType._UpdateGraphics:
        com.bbn.openmap.corba.CSpecialist.GUpdateHelper.write (ostream, value.ginfo ());
        break;
      case com.bbn.openmap.corba.CSpecialist.ActionType._InfoText:
        ostream.write_string (value.itext ());
        break;
      case com.bbn.openmap.corba.CSpecialist.ActionType._PlainText:
        ostream.write_string (value.ptext ());
        break;
      case com.bbn.openmap.corba.CSpecialist.ActionType._HTMLText:
        ostream.write_string (value.htext ());
        break;
      case com.bbn.openmap.corba.CSpecialist.ActionType._URL:
        ostream.write_string (value.url ());
        break;
    }
  }

}
