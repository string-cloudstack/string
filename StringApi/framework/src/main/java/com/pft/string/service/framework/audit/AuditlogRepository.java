package com.pft.string.service.framework.audit;
import com.pft.string.service.framework.data.repositories.Repository;

public class AuditlogRepository extends Repository<Audit> {

	public AuditlogRepository() {
		super(Audit.class);
	}
}
