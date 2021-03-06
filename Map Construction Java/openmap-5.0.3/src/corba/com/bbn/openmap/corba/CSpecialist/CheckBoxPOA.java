package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/CheckBoxPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public abstract class CheckBoxPOA extends org.omg.PortableServer.Servant
 implements com.bbn.openmap.corba.CSpecialist.CheckBoxOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_label", new java.lang.Integer (0));
    _methods.put ("_set_label", new java.lang.Integer (1));
    _methods.put ("_get_buttons", new java.lang.Integer (2));
    _methods.put ("_set_buttons", new java.lang.Integer (3));
    _methods.put ("selected", new java.lang.Integer (4));
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
       case 0:  // CSpecialist/CheckBox/_get_label
       {
         String $result = null;
         $result = this.label ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // CSpecialist/CheckBox/_set_label
       {
         String newLabel = in.read_string ();
         this.label (newLabel);
         out = $rh.createReply();
         break;
       }

       case 2:  // CSpecialist/CheckBox/_get_buttons
       {
         com.bbn.openmap.corba.CSpecialist.CheckButton $result[] = null;
         $result = this.buttons ();
         out = $rh.createReply();
         com.bbn.openmap.corba.CSpecialist.CheckButtonsHelper.write (out, $result);
         break;
       }

       case 3:  // CSpecialist/CheckBox/_set_buttons
       {
         com.bbn.openmap.corba.CSpecialist.CheckButton newButtons[] = com.bbn.openmap.corba.CSpecialist.CheckButtonsHelper.read (in);
         this.buttons (newButtons);
         out = $rh.createReply();
         break;
       }

       case 4:  // CSpecialist/CheckBox/selected
       {
         String box_label = in.read_string ();
         com.bbn.openmap.corba.CSpecialist.CheckButton button = com.bbn.openmap.corba.CSpecialist.CheckButtonHelper.read (in);
         String uniqueID = in.read_string ();
         this.selected (box_label, button, uniqueID);
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
    "IDL:CSpecialist/CheckBox:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public CheckBox _this() 
  {
    return CheckBoxHelper.narrow(
    super._this_object());
  }

  public CheckBox _this(org.omg.CORBA.ORB orb) 
  {
    return CheckBoxHelper.narrow(
    super._this_object(orb));
  }


} // class CheckBoxPOA
