package com.bbn.openmap.plugin.corbaImage.corbaImageServer;

/**
* com/bbn/openmap/plugin/corbaImage/corbaImageServer/ServerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/plugin/corbaImage/CorbaImageServer.idl
* Tuesday, November 12, 2013 11:07:48 PM EST
*/


//------------------------------------------------------------
public final class ServerHolder implements org.omg.CORBA.portable.Streamable
{
  public com.bbn.openmap.plugin.corbaImage.corbaImageServer.Server value = null;

  public ServerHolder ()
  {
  }

  public ServerHolder (com.bbn.openmap.plugin.corbaImage.corbaImageServer.Server initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.bbn.openmap.plugin.corbaImage.corbaImageServer.ServerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.bbn.openmap.plugin.corbaImage.corbaImageServer.ServerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.bbn.openmap.plugin.corbaImage.corbaImageServer.ServerHelper.type ();
  }

}
