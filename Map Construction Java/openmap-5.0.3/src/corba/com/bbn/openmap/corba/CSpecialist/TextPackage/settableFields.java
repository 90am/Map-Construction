package com.bbn.openmap.corba.CSpecialist.TextPackage;


/**
* com/bbn/openmap/corba/CSpecialist/TextPackage/settableFields.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public class settableFields implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 5;
  private static com.bbn.openmap.corba.CSpecialist.TextPackage.settableFields[] __array = new com.bbn.openmap.corba.CSpecialist.TextPackage.settableFields [__size];

  public static final int _TF_p1 = 0;
  public static final com.bbn.openmap.corba.CSpecialist.TextPackage.settableFields TF_p1 = new com.bbn.openmap.corba.CSpecialist.TextPackage.settableFields(_TF_p1);
  public static final int _TF_ll1 = 1;
  public static final com.bbn.openmap.corba.CSpecialist.TextPackage.settableFields TF_ll1 = new com.bbn.openmap.corba.CSpecialist.TextPackage.settableFields(_TF_ll1);
  public static final int _TF_data = 2;
  public static final com.bbn.openmap.corba.CSpecialist.TextPackage.settableFields TF_data = new com.bbn.openmap.corba.CSpecialist.TextPackage.settableFields(_TF_data);
  public static final int _TF_font = 3;
  public static final com.bbn.openmap.corba.CSpecialist.TextPackage.settableFields TF_font = new com.bbn.openmap.corba.CSpecialist.TextPackage.settableFields(_TF_font);
  public static final int _TF_justify = 4;
  public static final com.bbn.openmap.corba.CSpecialist.TextPackage.settableFields TF_justify = new com.bbn.openmap.corba.CSpecialist.TextPackage.settableFields(_TF_justify);

  public int value ()
  {
    return __value;
  }

  public static com.bbn.openmap.corba.CSpecialist.TextPackage.settableFields from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected settableFields (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class settableFields
