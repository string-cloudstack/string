package com.pft.string.service.api.common.facade;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.pft.string.service.api.common.bdo.Users;
import com.pft.string.service.api.common.repositories.UserRepository;
import com.pft.string.service.framework.business.base.actions.CreateAction;
import com.pft.string.service.framework.business.base.actions.DeleteAction;
import com.pft.string.service.framework.business.base.actions.EntityAction;
import com.pft.string.service.framework.business.base.actions.GetByIdAction;
import com.pft.string.service.framework.business.base.actions.UpdateAction;

public class UsersFacade {

	public class CreateUsers extends CreateAction<Users> {
		public CreateUsers() {
			super(CreateUsers.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}

	public class UpdateUsers extends UpdateAction<Users> {
		public UpdateUsers() {
			super(UpdateUsers.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}
	
	public class GetUserById extends GetByIdAction<Users> {
		public GetUserById() {
			super(GetUserById.class, GetUserById.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}
	
	
	
	public class DeleteUsers extends DeleteAction<Users> {
		public DeleteUsers() {
			super(DeleteUsers.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}

	public class UserDetails extends EntityAction<Users> {
		private String userGUID;

		public void setUserGUID(String userGUID) {
			this.userGUID = userGUID;
		}

		public String getUserGUID() {
			return userGUID;
		}

		public UserDetails() {
			super(UserDetails.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			Criterion searchQuery = null;
			searchQuery = Restrictions.eq("userGuid", getUserGUID());
			DetachedCriteria criteria = DetachedCriteria.forClass(Users.class).add(searchQuery);
			this.setEntity((Users) new UserRepository().findOne(criteria));

		}
		
	}
	
	public class UserDetailsByName extends EntityAction<Users> {
		private String username;
		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public UserDetailsByName() {
			super(UserDetailsByName.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			Criterion searchQuery = null;
			searchQuery = Restrictions.eq("username", getUsername());
			DetachedCriteria criteria = DetachedCriteria.forClass(Users.class).add(searchQuery);
			this.setEntity((Users) new UserRepository().findOne(criteria));

		}
	}
}