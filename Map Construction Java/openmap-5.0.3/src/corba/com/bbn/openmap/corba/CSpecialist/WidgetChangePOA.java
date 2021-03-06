package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/WidgetChangePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:47 PM EST
*/

public abstract class WidgetChangePOA extends org.omg.PortableServer.Servant
 implements com.bbn.openmap.corba.CSpecialist.WidgetChangeOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("ForgetAll", new java.lang.Integer (0));
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

  //it will do a new getPaletteConfig() call)
       case 0:  // CSpecialist/WidgetChange/ForgetAll
       {
         boolean forceRedo = in.read_boolean ();
         this.ForgetAll (forceRedo);
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
    "IDL:CSpecialist/WidgetChange:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public WidgetChange _this() 
  {
    return WidgetChangeHelper.narrow(
    super._this_object());
  }

  public WidgetChange _this(org.omg.CORBA.ORB orb) 
  {
    return WidgetChangeHelper.narrow(
    super._this_object(orb));
  }


} // class WidgetChangePOA
