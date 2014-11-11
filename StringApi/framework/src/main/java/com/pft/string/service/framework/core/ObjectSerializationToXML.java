package com.pft.string.service.framework.core;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
 
public class ObjectSerializationToXML {
 
    /**
     * This method saves (serializes) any java bean object into xml file
     */
    public void serializeObjectToXML(String xmlFileLocation,
            Object objectToSerialize) throws Exception {
        FileOutputStream os = new FileOutputStream(xmlFileLocation);
        XMLEncoder encoder = new XMLEncoder(os);
        encoder.writeObject(objectToSerialize);
        encoder.close();
    }
    
    public String serializeObjectToXML(Object objectToSerialize) throws Exception {
    	 ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        XMLEncoder encoder = new XMLEncoder(outStream);
        encoder.writeObject(objectToSerialize);
        encoder.close();
        String xmlString =  outStream.toString();
        return xmlString;
    }
 
    /**
     * Reads Java Bean Object From XML File
     */
    public Object deserializeXMLToObject(String xmlFileLocation)
            throws Exception {
        FileInputStream outStream = new FileInputStream(xmlFileLocation);
        XMLDecoder decoder = new XMLDecoder(outStream);
        Object deSerializedObject = decoder.readObject();
        decoder.close();
 
        return deSerializedObject;
    }
    
    public Object deserializeXMLStringToObject(String xmlString)
            throws Exception {
    	ByteArrayInputStream inStream = new ByteArrayInputStream(xmlString.getBytes());
    	XMLDecoder decoder = new XMLDecoder(inStream);
        Object deSerializedObject = decoder.readObject();
        decoder.close();
 
        return deSerializedObject;
    }
 

}
