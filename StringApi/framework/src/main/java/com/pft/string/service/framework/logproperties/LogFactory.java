package com.pft.string.service.framework.logproperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFactory
{
	public static Logger getLogger(Class classInfo)
	{
		Logger logger = LoggerFactory.getLogger(classInfo);
		return logger;
	}
}
