package com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider;

/**
* com/bbn/openmap/layer/rpf/corba/CRpfFrameProvider/CRFPViewAttributesHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/rpf/corba/CorbaRpfFrameProvider.idl
* Tuesday, November 12, 2013 11:07:44 PM EST
*/

public final class CRFPViewAttributesHolder implements org.omg.CORBA.portable.Streamable
{
  public com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPViewAttributes value = null;

  public CRFPViewAttributesHolder ()
  {
  }

  public CRFPViewAttributesHolder (com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPViewAttributes initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPViewAttributesHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPViewAttributesHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPViewAttributesHelper.type ();
  }

}
