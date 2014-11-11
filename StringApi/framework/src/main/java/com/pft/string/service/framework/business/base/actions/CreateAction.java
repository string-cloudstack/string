package com.pft.string.service.framework.business.base.actions;

import com.pft.string.service.framework.business.annotations.Transaction;
import com.pft.string.service.framework.data.repositories.Repository;

@Transaction(Supports=true)
public class CreateAction<T> extends EntityAction<T>
{

	public CreateAction(Class classInfo) {
		super(CreateAction.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void OnExecute() throws Exception 
	{
		if(getEntity() != null)
		new Repository<T>(getEntity().getClass()).add(getEntity());
		
	}

}
