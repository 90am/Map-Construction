package com.bbn.openmap.corba.CSpecialist.LinePackage;


/**
* com/bbn/openmap/corba/CSpecialist/LinePackage/settableFieldsHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

abstract public class settableFieldsHelper
{
  private static String  _id = "IDL:CSpecialist/Line/settableFields:1.0";

  public static void insert (org.omg.CORBA.Any a, com.bbn.openmap.corba.CSpecialist.LinePackage.settableFields that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.bbn.openmap.corba.CSpecialist.LinePackage.settableFields extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (com.bbn.openmap.corba.CSpecialist.LinePackage.settableFieldsHelper.id (), "settableFields", new String[] { "LF_p1", "LF_p2", "LF_ll1", "LF_ll2"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static com.bbn.openmap.corba.CSpecialist.LinePackage.settableFields read (org.omg.CORBA.portable.InputStream istream)
  {
    return com.bbn.openmap.corba.CSpecialist.LinePackage.settableFields.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.bbn.openmap.corba.CSpecialist.LinePackage.settableFields value)
  {
    ostream.write_long (value.value ());
  }

}
