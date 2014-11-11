package com.pft.string.service.api.common.repositories;

import com.pft.string.service.api.common.bdo.UserSession;
import com.pft.string.service.framework.data.repositories.Repository;

public class UserSessionRepository extends Repository<UserSession> {

	public UserSessionRepository() {
		super(UserSession.class);
	}
}