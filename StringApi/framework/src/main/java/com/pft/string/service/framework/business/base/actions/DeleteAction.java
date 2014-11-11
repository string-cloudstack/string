package com.pft.string.service.framework.business.base.actions;

import com.pft.string.service.framework.business.annotations.Transaction;
import com.pft.string.service.framework.business.base.entities.Entity;
import com.pft.string.service.framework.data.repositories.Repository;

@Transaction(Supports=true)
public class DeleteAction<T> extends EntityAction<T>
{

	public DeleteAction(Class classInfo) {
		super(DeleteAction.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void OnExecute() throws Exception 
	{
		if(getEntity() != null)
		{
			Entity entity = (Entity) getEntity();
			entity.setIsDeleted(true);
			new Repository<T>(getEntity().getClass()).save(getEntity());
		}
		
	}

}
