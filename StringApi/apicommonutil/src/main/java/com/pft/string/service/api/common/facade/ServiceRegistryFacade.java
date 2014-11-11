package com.pft.string.service.api.common.facade;

import com.pft.string.service.api.common.bdo.ServiceRegistry;
import com.pft.string.service.framework.business.base.actions.CreateAction;
import com.pft.string.service.framework.business.base.actions.UpdateAction;

public class ServiceRegistryFacade {

	public class CreateServiceRegistry extends CreateAction<ServiceRegistry> {
		public CreateServiceRegistry() {
			super(CreateServiceRegistry.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}
	
	public class UpdateServiceRegistry extends UpdateAction<ServiceRegistry> {
		public UpdateServiceRegistry() {
			super(UpdateServiceRegistry.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}
}