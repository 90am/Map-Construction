package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/_CompStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/


// ----------------------------------------------------------------------
public class _CompStub extends org.omg.CORBA.portable.ObjectImpl implements com.bbn.openmap.corba.CSpecialist.Comp
{

  public com.bbn.openmap.corba.CSpecialist.ActionUnion[] sendGesture (com.bbn.openmap.corba.CSpecialist.MouseEvent gesture, String uniqueID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sendGesture", true);
                com.bbn.openmap.corba.CSpecialist.MouseEventHelper.write ($out, gesture);
                $out.write_string (uniqueID);
                $in = _invoke ($out);
                com.bbn.openmap.corba.CSpecialist.ActionUnion $result[] = com.bbn.openmap.corba.CSpecialist.ActionSeqHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return sendGesture (gesture, uniqueID        );
            } finally {
                _releaseReply ($in);
            }
  } // sendGesture

  public String cID ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_cID", true);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return cID (        );
            } finally {
                _releaseReply ($in);
            }
  } // cID

  public com.bbn.openmap.corba.CSpecialist.EComp fill ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("fill", true);
                $in = _invoke ($out);
                com.bbn.openmap.corba.CSpecialist.EComp $result = com.bbn.openmap.corba.CSpecialist.ECompHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return fill (        );
            } finally {
                _releaseReply ($in);
            }
  } // fill

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:CSpecialist/Comp:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _CompStub
