package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/UpdateGraphicSeqHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

abstract public class UpdateGraphicSeqHelper
{
  private static String  _id = "IDL:CSpecialist/UpdateGraphicSeq:1.0";

  public static void insert (org.omg.CORBA.Any a, com.bbn.openmap.corba.CSpecialist.UpdateGraphic[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.bbn.openmap.corba.CSpecialist.UpdateGraphic[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = com.bbn.openmap.corba.CSpecialist.UpdateGraphicHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (com.bbn.openmap.corba.CSpecialist.UpdateGraphicSeqHelper.id (), "UpdateGraphicSeq", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static com.bbn.openmap.corba.CSpecialist.UpdateGraphic[] read (org.omg.CORBA.portable.InputStream istream)
  {
    com.bbn.openmap.corba.CSpecialist.UpdateGraphic value[] = null;
    int _len0 = istream.read_long ();
    value = new com.bbn.openmap.corba.CSpecialist.UpdateGraphic[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = com.bbn.openmap.corba.CSpecialist.UpdateGraphicHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.bbn.openmap.corba.CSpecialist.UpdateGraphic[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      com.bbn.openmap.corba.CSpecialist.UpdateGraphicHelper.write (ostream, value[_i0]);
  }

}
