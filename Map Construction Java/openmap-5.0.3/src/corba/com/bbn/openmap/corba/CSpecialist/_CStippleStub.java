package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/_CStippleStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:45 PM EST
*/

public class _CStippleStub extends org.omg.CORBA.portable.ObjectImpl implements com.bbn.openmap.corba.CSpecialist.CStipple
{

  public short height ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_height", true);
                $in = _invoke ($out);
                short $result = $in.read_ushort ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return height (        );
            } finally {
                _releaseReply ($in);
            }
  } // height

  public short width ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_width", true);
                $in = _invoke ($out);
                short $result = $in.read_ushort ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return width (        );
            } finally {
                _releaseReply ($in);
            }
  } // width

  public byte[] data ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("_get_data", true);
                $in = _invoke ($out);
                byte $result[] = com.bbn.openmap.corba.CSpecialist.binarydataHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return data (        );
            } finally {
                _releaseReply ($in);
            }
  } // data

  public com.bbn.openmap.corba.CSpecialist.CStipplePackage.EStipple fill ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("fill", true);
                $in = _invoke ($out);
                com.bbn.openmap.corba.CSpecialist.CStipplePackage.EStipple $result = com.bbn.openmap.corba.CSpecialist.CStipplePackage.EStippleHelper.read ($in);
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
    "IDL:CSpecialist/CStipple:1.0"};

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
} // class _CStippleStub
