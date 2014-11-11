package com.pft.string.service.api.common.repositories;

import com.pft.string.service.api.common.bdo.Service;
import com.pft.string.service.framework.data.repositories.Repository;

public class ServiceRepository extends Repository<Service> {

	public ServiceRepository() {
		super(Service.class);
	}
}
