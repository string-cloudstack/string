package com.pft.string.service.framework.business.base.actions;

import com.pft.string.service.framework.business.annotations.Transaction;
import com.pft.string.service.framework.data.repositories.Repository;

@Transaction(Supports=true)
public class UpdateAction<T> extends EntityAction<T>
{

	public UpdateAction(Class classInfo) {
		super(UpdateAction.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void OnExecute() throws Exception 
	{
		if(getEntity() != null)
			new Repository<T>(getEntity().getClass()).save(getEntity());
	}

}
