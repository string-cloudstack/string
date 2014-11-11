package com.pft.string.service.framework.business.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Transaction
{
	 boolean Supports();
}
