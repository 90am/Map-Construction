package com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider;


/**
* com/bbn/openmap/layer/rpf/corba/CRpfFrameProvider/RawImage.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/rpf/corba/CorbaRpfFrameProvider.idl
* Tuesday, November 12, 2013 11:07:44 PM EST
*/

public final class RawImage implements org.omg.CORBA.portable.IDLEntity
{
  public int colortable[] = null;
  public byte imagedata[] = null;

  public RawImage ()
  {
  } // ctor

  public RawImage (int[] _colortable, byte[] _imagedata)
  {
    colortable = _colortable;
    imagedata = _imagedata;
  } // ctor

} // class RawImage