package com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider;


/**
* com/bbn/openmap/layer/rpf/corba/CRpfFrameProvider/ServerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/rpf/corba/CorbaRpfFrameProvider.idl
* Tuesday, November 12, 2013 11:07:44 PM EST
*/


//------------------------------------------------------------
public abstract class ServerPOA extends org.omg.PortableServer.Servant
 implements com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.ServerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("setViewAttributes", new java.lang.Integer (0));
    _methods.put ("getCoverage", new java.lang.Integer (1));
    _methods.put ("getCatalogCoverage", new java.lang.Integer (2));
    _methods.put ("getSubframeData", new java.lang.Integer (3));
    _methods.put ("getRawSubframeData", new java.lang.Integer (4));
    _methods.put ("getSubframeAttributes", new java.lang.Integer (5));
    _methods.put ("signoff", new java.lang.Integer (6));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // CRpfFrameProvider/Server/setViewAttributes
       {
         com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPViewAttributes va = com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPViewAttributesHelper.read (in);
         String uniqueID = in.read_string ();
         this.setViewAttributes (va, uniqueID);
         out = $rh.createReply();
         break;
       }

       case 1:  // CRpfFrameProvider/Server/getCoverage
       {
         float ullat = in.read_float ();
         float ullon = in.read_float ();
         float lrlat = in.read_float ();
         float lrlon = in.read_float ();
         com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCADRGProjection p = com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCADRGProjectionHelper.read (in);
         String uniqueID = in.read_string ();
         com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCoverageBox $result[] = null;
         $result = this.getCoverage (ullat, ullon, lrlat, lrlon, p, uniqueID);
         out = $rh.createReply();
         com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCoverageBoxSeqHelper.write (out, $result);
         break;
       }

       case 2:  // CRpfFrameProvider/Server/getCatalogCoverage
       {
         float ullat = in.read_float ();
         float ullon = in.read_float ();
         float lrlat = in.read_float ();
         float lrlon = in.read_float ();
         com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCADRGProjection p = com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCADRGProjectionHelper.read (in);
         String chartSeriesCode = in.read_string ();
         String uniqueID = in.read_string ();
         com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCoverageBox $result[] = null;
         $result = this.getCatalogCoverage (ullat, ullon, lrlat, lrlon, p, chartSeriesCode, uniqueID);
         out = $rh.createReply();
         com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.CRFPCoverageBoxSeqHelper.write (out, $result);
         break;
       }

       case 3:  // CRpfFrameProvider/Server/getSubframeData
       {
         short tocNumber = in.read_ushort ();
         short entryNumber = in.read_ushort ();
         short x = in.read_short ();
         short y = in.read_short ();
         float jpegQuality = in.read_float ();
         String uniqueID = in.read_string ();
         byte $result[] = null;
         $result = this.getSubframeData (tocNumber, entryNumber, x, y, jpegQuality, uniqueID);
         out = $rh.createReply();
         com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.dataHelper.write (out, $result);
         break;
       }

       case 4:  // CRpfFrameProvider/Server/getRawSubframeData
       {
         short tocNumber = in.read_ushort ();
         short entryNumber = in.read_ushort ();
         short x = in.read_short ();
         short y = in.read_short ();
         String uniqueID = in.read_string ();
         com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.RawImage $result = null;
         $result = this.getRawSubframeData (tocNumber, entryNumber, x, y, uniqueID);
         out = $rh.createReply();
         com.bbn.openmap.layer.rpf.corba.CRpfFrameProvider.RawImageHelper.write (out, $result);
         break;
       }

       case 5:  // CRpfFrameProvider/Server/getSubframeAttributes
       {
         short tocNumber = in.read_ushort ();
         short entryNumber = in.read_ushort ();
         short x = in.read_short ();
         short y = in.read_short ();
         String uniqueID = in.read_string ();
         String $result = null;
         $result = this.getSubframeAttributes (tocNumber, entryNumber, x, y, uniqueID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 6:  // CRpfFrameProvider/Server/signoff
       {
         String uniqueID = in.read_string ();
         this.signoff (uniqueID);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:CRpfFrameProvider/Server:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Server _this() 
  {
    return ServerHelper.narrow(
    super._this_object());
  }

  public Server _this(org.omg.CORBA.ORB orb) 
  {
    return ServerHelper.narrow(
    super._this_object(orb));
  }


} // class ServerPOA
