package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/ListBoxPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public abstract class ListBoxPOA extends org.omg.PortableServer.Servant
 implements com.bbn.openmap.corba.CSpecialist.ListBoxOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_label", new java.lang.Integer (0));
    _methods.put ("_set_label", new java.lang.Integer (1));
    _methods.put ("_get_contents", new java.lang.Integer (2));
    _methods.put ("_set_contents", new java.lang.Integer (3));
    _methods.put ("_get_highlighted_item", new java.lang.Integer (4));
    _methods.put ("_set_highlighted_item", new java.lang.Integer (5));
    _methods.put ("selected", new java.lang.Integer (6));
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
       case 0:  // CSpecialist/ListBox/_get_label
       {
         String $result = null;
         $result = this.label ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // CSpecialist/ListBox/_set_label
       {
         String newLabel = in.read_string ();
         this.label (newLabel);
         out = $rh.createReply();
         break;
       }

       case 2:  // CSpecialist/ListBox/_get_contents
       {
         String $result[] = null;
         $result = this.contents ();
         out = $rh.createReply();
         com.bbn.openmap.corba.CSpecialist.stringsHelper.write (out, $result);
         break;
       }

       case 3:  // CSpecialist/ListBox/_set_contents
       {
         String newContents[] = com.bbn.openmap.corba.CSpecialist.stringsHelper.read (in);
         this.contents (newContents);
         out = $rh.createReply();
         break;
       }

       case 4:  // CSpecialist/ListBox/_get_highlighted_item
       {
         String $result = null;
         $result = this.highlighted_item ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 5:  // CSpecialist/ListBox/_set_highlighted_item
       {
         String newHighlighted_item = in.read_string ();
         this.highlighted_item (newHighlighted_item);
         out = $rh.createReply();
         break;
       }

       case 6:  // CSpecialist/ListBox/selected
       {
         String box_label = in.read_string ();
         String selected_item = in.read_string ();
         String uniqueID = in.read_string ();
         this.selected (box_label, selected_item, uniqueID);
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
    "IDL:CSpecialist/ListBox:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ListBox _this() 
  {
    return ListBoxHelper.narrow(
    super._this_object());
  }

  public ListBox _this(org.omg.CORBA.ORB orb) 
  {
    return ListBoxHelper.narrow(
    super._this_object(orb));
  }


} // class ListBoxPOA
