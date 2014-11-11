package com.pft.string.service.framework.data.persistence;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pft.string.service.framework.core.ApplicationContext;
import com.pft.string.service.framework.core.Constants;
import com.pft.string.service.framework.core.RequestContext;

public class UnitOfWork
{
	public static void start(ApplicationContext context) throws Exception
	{
		try
		{
		
		  SessionFactory factory= null;
		  
		  Session session = null;
		  
		  if(context.getFactoryKey() == null  )
		  factory = PersistencyService.getCurrentSessionFactory();
		  else
			  factory = PersistencyService.getCurrentSessionFactory(context.getFactoryKey());
		  if(context.getMultiTenancy() == null || context.getMultiTenancy().equals("false"))
			  session = factory.openSession();  
		  else
			  session  = factory.withOptions().tenantIdentifier(context.getTenantId().toString()).openSession();
			  
		  RequestContext.initialize();
		  RequestContext.setAttribute(Constants.CurrentSessionKey,session);
		  RequestContext.setAttribute(Constants.ApplicationContext,context);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw ex;
		}
		  
	}
	
	public static void start() throws Exception
    {
        try
        {
            SessionFactory factory = null;
            Session session = null;
            RequestContext.initialize();
            ApplicationContext context = new ApplicationContext(null, null, null, null);
            RequestContext.setAttribute("ApplicationContext", context);
            factory = PersistencyService.getCurrentSessionFactory();
            session = factory.openSession();
            RequestContext.setAttribute("CurrentSession", session);
            String AuditRequestID= RandomKeyGeneration(128);
            RequestContext.setAttribute("AuditRequestID", AuditRequestID);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
    }
	
	
	public static void end()
	{
		Session session = RequestContext.getAttribute(Constants.CurrentSessionKey);
		session.close();
		RequestContext.cleanup();
		
	}
	public static String RandomKeyGeneration(int length) throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		SecretKey sk;
		kg.init(length);
		sk = kg.generateKey();
		return asHex(sk.getEncoded());
	}
	public static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}


}
