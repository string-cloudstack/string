package com.pft.string.service.api.common.repositories;

import com.pft.string.service.api.common.bdo.Auditlog;
import com.pft.string.service.framework.data.repositories.Repository;

public class AuditlogRepository extends Repository<Auditlog> {

	public AuditlogRepository() {
		super(Auditlog.class);
	}
}
