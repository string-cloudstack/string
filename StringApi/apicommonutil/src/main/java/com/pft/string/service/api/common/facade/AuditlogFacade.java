package com.pft.string.service.api.common.facade;

import com.pft.string.service.api.common.bdo.Auditlog;
import com.pft.string.service.framework.business.base.actions.CreateAction;

public class AuditlogFacade {

	public class CreateAuditlog extends CreateAction<Auditlog> {
		public CreateAuditlog() {
			super(CreateAuditlog.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}
}