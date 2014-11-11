package com.pft.string.service.framework.business.base.actions;
import com.pft.string.service.framework.business.annotations.Transaction;
import com.pft.string.service.framework.core.types.Extendable;
import com.pft.string.service.framework.data.repositories.Repository;

@Transaction(Supports=false)
public class GetByIdAction<T> extends EntityAction<T>
{
	private long id;
    private Class entityInfo;
	public GetByIdAction(Class classInfo,Class entityInfo) {
		super(GetByIdAction.class);
		this.entityInfo = entityInfo;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	protected void OnExecute() throws Exception 
	{
		if(id != 0)
			{
			setEntity(new Repository<T>(entityInfo).getEnity(id));
			T entity = getEntity();
			if(entity != null && entity instanceof  Extendable )
			{
				((Extendable)(entity)).LoadExtensionData();
			}
		
	}
	}

	

}
