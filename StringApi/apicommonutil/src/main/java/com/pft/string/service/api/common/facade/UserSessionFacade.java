package com.pft.string.service.api.common.facade;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.pft.string.service.api.common.bdo.UserSession;
import com.pft.string.service.api.common.repositories.UserSessionRepository;
import com.pft.string.service.framework.business.base.actions.CreateAction;
import com.pft.string.service.framework.business.base.actions.DeleteAction;
import com.pft.string.service.framework.business.base.actions.EntityAction;
import com.pft.string.service.framework.business.base.actions.UpdateAction;
import com.pft.string.service.framework.data.repositories.Repository;

public class UserSessionFacade {

	public class CreateUserSession extends CreateAction<UserSession> {
		public CreateUserSession() {
			super(CreateUserSession.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}

	public class UpdateUserSession extends UpdateAction<UserSession> {
		public UpdateUserSession() {
			super(UpdateUserSession.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			super.OnExecute();
		}
	}

	public class CurrentSession extends EntityAction<UserSession> {
		private String session;

		public void setSession(String session) {
			this.session = session;
		}

		public String getSession() {
			return session;
		}

		public CurrentSession() {
			super(CurrentSession.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			Criterion searchQuery = null;
			searchQuery = Restrictions.eq("userSession", getSession());
			DetachedCriteria criteria = DetachedCriteria.forClass(
					UserSession.class).add(searchQuery);
			this.setEntity((UserSession) new UserSessionRepository()
					.findOne(criteria));
		}
	}

	public class DeleteSession extends DeleteAction<UserSession> {
		public DeleteSession() {
			super(DeleteSession.class);
		}
		@Override
		protected void OnExecute() throws Exception {		
				new Repository<UserSession>(getEntity().getClass()).delete(getEntity());
		}
	}
	
	public class UserSessionByToken extends EntityAction<UserSession> {
		private String userSession;
		
		public String getUserSession() {
			return userSession;
		}

		public void setUserSession(String userSession) {
			this.userSession = userSession;
		}

		public UserSessionByToken() {
			super(UserSessionByToken.class);
		}

		@Override
		protected void OnExecute() throws Exception {
			Criterion searchQuery = null;
			searchQuery = Restrictions.eq("userSession", getUserSession());
			DetachedCriteria criteria = DetachedCriteria.forClass(UserSession.class).add(searchQuery);
			this.setEntity((UserSession) new UserSessionRepository().findOne(criteria));

		}
	}
}
