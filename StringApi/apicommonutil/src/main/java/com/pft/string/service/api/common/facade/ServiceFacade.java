package com.pft.string.service.api.common.facade;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.pft.string.service.api.common.bdo.Service;
import com.pft.string.service.api.common.repositories.ServiceRepository;
import com.pft.string.service.framework.business.base.actions.CreateAction;
import com.pft.string.service.framework.business.base.actions.GetAllAction;
import com.pft.string.service.framework.business.base.actions.UpdateAction;

public class ServiceFacade {

	public class CreateService extends CreateAction<Service> {
		public CreateService() {
			super(CreateService.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}
	
	public class UpdateService extends UpdateAction<Service> {
		public UpdateService() {
			super(UpdateService.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}
	
	public class AllServices extends GetAllAction<List<Service>> {
		private Integer isPublic;
		
		public Integer getIsPublic() {
			return isPublic;
		}

		public void setIsPublic(Integer isPublic) {
			this.isPublic = isPublic;
		}

		public AllServices() {
			super(AllServices.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			Criterion searchQuery = null;
			searchQuery = Restrictions.eq("isPublic", getIsPublic());
			DetachedCriteria criteria = DetachedCriteria.forClass(Service.class).add(searchQuery);
			this.setEntity((List<Service>) new ServiceRepository().findAll(criteria));
			
		}	
	}
}