package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/CStippleHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:45 PM EST
*/

abstract public class CStippleHelper
{
  private static String  _id = "IDL:CSpecialist/CStipple:1.0";

  public static void insert (org.omg.CORBA.Any a, com.bbn.openmap.corba.CSpecialist.CStipple that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.bbn.openmap.corba.CSpecialist.CStipple extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (com.bbn.openmap.corba.CSpecialist.CStippleHelper.id (), "CStipple");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static com.bbn.openmap.corba.CSpecialist.CStipple read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CStippleStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.bbn.openmap.corba.CSpecialist.CStipple value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static com.bbn.openmap.corba.CSpecialist.CStipple narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof com.bbn.openmap.corba.CSpecialist.CStipple)
      return (com.bbn.openmap.corba.CSpecialist.CStipple)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      com.bbn.openmap.corba.CSpecialist._CStippleStub stub = new com.bbn.openmap.corba.CSpecialist._CStippleStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static com.bbn.openmap.corba.CSpecialist.CStipple unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof com.bbn.openmap.corba.CSpecialist.CStipple)
      return (com.bbn.openmap.corba.CSpecialist.CStipple)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      com.bbn.openmap.corba.CSpecialist._CStippleStub stub = new com.bbn.openmap.corba.CSpecialist._CStippleStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
