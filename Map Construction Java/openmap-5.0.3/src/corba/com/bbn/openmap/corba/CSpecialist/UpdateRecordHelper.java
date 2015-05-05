package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/UpdateRecordHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

abstract public class UpdateRecordHelper
{
  private static String  _id = "IDL:CSpecialist/UpdateRecord:1.0";

  public static void insert (org.omg.CORBA.Any a, com.bbn.openmap.corba.CSpecialist.UpdateRecord that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.bbn.openmap.corba.CSpecialist.UpdateRecord extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "gID",
            _tcOf_members0,
            null);
          _tcOf_members0 = com.bbn.openmap.corba.CSpecialist.UpdateGraphicHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (com.bbn.openmap.corba.CSpecialist.UpdateGraphicSeqHelper.id (), "UpdateGraphicSeq", _tcOf_members0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "objectUpdates",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (com.bbn.openmap.corba.CSpecialist.UpdateRecordHelper.id (), "UpdateRecord", _members0);
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

  public static com.bbn.openmap.corba.CSpecialist.UpdateRecord read (org.omg.CORBA.portable.InputStream istream)
  {
    com.bbn.openmap.corba.CSpecialist.UpdateRecord value = new com.bbn.openmap.corba.CSpecialist.UpdateRecord ();
    value.gID = istream.read_string ();
    value.objectUpdates = com.bbn.openmap.corba.CSpecialist.UpdateGraphicSeqHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.bbn.openmap.corba.CSpecialist.UpdateRecord value)
  {
    ostream.write_string (value.gID);
    com.bbn.openmap.corba.CSpecialist.UpdateGraphicSeqHelper.write (ostream, value.objectUpdates);
  }

}
