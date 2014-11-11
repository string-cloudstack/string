package com.pft.string.service.api.common.repositories;

import com.pft.string.service.api.common.bdo.Assets;
import com.pft.string.service.api.common.bdo.Auditlog;
import com.pft.string.service.framework.data.repositories.Repository;

public class AssetsRepository extends Repository<Assets> {

	public AssetsRepository() {
		super(Assets.class);
	}
}
