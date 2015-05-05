package com.bbn.openmap.corba.CSpecialist.GraphicPackage;


/**
* com/bbn/openmap/corba/CSpecialist/GraphicPackage/settableFieldsHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/


//  decluttering, timing issue
abstract public class settableFieldsHelper
{
  private static String  _id = "IDL:CSpecialist/Graphic/settableFields:1.0";

  public static void insert (org.omg.CORBA.Any a, com.bbn.openmap.corba.CSpecialist.GraphicPackage.settableFields that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.bbn.openmap.corba.CSpecialist.GraphicPackage.settableFields extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (com.bbn.openmap.corba.CSpecialist.GraphicPackage.settableFieldsHelper.id (), "settableFields", new String[] { "GF_object", "GF_lType", "GF_rType", "GF_color", "GF_fillColor", "GF_lineWidth", "GF_stipple", "GF_fillStipple"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static com.bbn.openmap.corba.CSpecialist.GraphicPackage.settableFields read (org.omg.CORBA.portable.InputStream istream)
  {
    return com.bbn.openmap.corba.CSpecialist.GraphicPackage.settableFields.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.bbn.openmap.corba.CSpecialist.GraphicPackage.settableFields value)
  {
    ostream.write_long (value.value ());
  }

}
