package com.pft.string.service.framework.data.interceptors;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import com.pft.string.service.framework.core.ApplicationContext;
import com.pft.string.service.framework.core.RequestContext;
import com.pft.string.service.framework.core.types.Auditable;

public class AuditInterceptor extends EmptyInterceptor {

	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		// do nothing
	}

	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		ApplicationContext context = RequestContext
				.getCurrentApplicationContext();
		if (entity instanceof Auditable) {
			for (int i = 0; i < propertyNames.length; i++) {
				if ("createdOn".equals(propertyNames[i])) {
					if (currentState[i] == null)
						currentState[i] = new Date();
				}

				if ("createdBy".equals(propertyNames[i])) {
					if (currentState[i] == null)
						currentState[i] = context.getUserId();
				}

				if ("modifiedOn".equals(propertyNames[i])) {
					currentState[i] = new Date();

				}
				if ("modifiedBy".equals(propertyNames[i])) {
					currentState[i] = context.getUserId();

				}

			}
		}
		return false;
	}

	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {

		return false;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		ApplicationContext context = RequestContext
				.getCurrentApplicationContext();
		if ( entity instanceof Auditable ) {

		 for ( int i=0; i<propertyNames.length; i++ ) {
		 if ( "createdOn".equals( propertyNames[i] ) ) {
		 state[i] = new Date();
		
		 }
		 if ( "createdBy".equals( propertyNames[i] ) ) {
		 state[i] =context.getUserId();
		
		 }
		
		 }
		 }

		return false;
	}

	public void afterTransactionCompletion(Transaction tx) {

	}

}