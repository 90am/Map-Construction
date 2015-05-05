package com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider;


/**
* com/bbn/openmap/layer/rpf/corba/CRpfFrameProvider/ServerHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/rpf/corba/CorbaRpfFrameProvider.idl
* Tuesday, November 12, 2013 11:07:44 PM EST
*/


//------------------------------------------------------------
abstract public class ServerHelper
{
  private static String  _id = "IDL:CRpfFrameProvider/Server:1.0";

  public static void insert (org.omg.CORBA.Any a, com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.Server that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.Server extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.ServerHelper.id (), "Server");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.Server read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ServerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.Server value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.Server narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.Server)
      return (com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.Server)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider._ServerStub stub = new com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider._ServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.Server unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.Server)
      return (com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.Server)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider._ServerStub stub = new com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider._ServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
