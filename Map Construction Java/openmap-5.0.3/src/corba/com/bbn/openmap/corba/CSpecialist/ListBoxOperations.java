package com.bbn.openmap.corba.CSpecialist;


/**
* com/bbn/openmap/corba/CSpecialist/ListBoxOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./src/corba/com/bbn/openmap/layer/specialist/Specialist.idl
* Tuesday, November 12, 2013 11:07:46 PM EST
*/

public interface ListBoxOperations 
{
  String label ();
  void label (String newLabel);
  String[] contents ();
  void contents (String[] newContents);
  String highlighted_item ();
  void highlighted_item (String newHighlighted_item);
  void selected (String box_label, String selected_item, String uniqueID);
} // interface ListBoxOperations