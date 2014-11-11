package com.pft.string.service.framework.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeepCopy
{
	public static <T> T Copy(T from) throws ClassNotFoundException, IOException
	{
		ObjectOutputStream outStream = null;
        ObjectInputStream inStream = null;
        T copy = null;
		   // deep copy
        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream(); 
        outStream = new ObjectOutputStream(byteOutStream); 
        // serialize and pass the object
        outStream.writeObject(from);   
        outStream.flush();               
        ByteArrayInputStream byteInStream = 
		        new ByteArrayInputStream(byteOutStream.toByteArray()); 
        inStream = new ObjectInputStream(byteInStream);                  
        // return the new object
        copy = (T) inStream.readObject();
		return copy;
		
	}

}
