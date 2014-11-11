package com.pft.string.service.api.common.repositories;

import com.pft.string.service.api.common.bdo.Users;
import com.pft.string.service.framework.data.repositories.Repository;

public class UserRepository extends Repository<Users> {

	public UserRepository() {
		super(Users.class);
	}
}
