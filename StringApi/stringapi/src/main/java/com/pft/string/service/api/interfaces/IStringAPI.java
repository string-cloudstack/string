package com.pft.string.service.api.interfaces;

import com.pft.string.service.api.common.bdo.Assets;
import com.pft.string.service.api.common.bdo.PasswordChange;
import com.pft.string.service.api.common.bdo.QueryRegistryReq;
import com.pft.string.service.api.common.bdo.Service;
import com.pft.string.service.api.common.bdo.ServiceRegistry;
import com.pft.string.service.api.common.bdo.Users;
import com.pft.string.service.api.common.bdo.LoginCredentials;
import com.pft.string.service.framework.core.types.ServiceResponse;
import com.pft.string.service.api.common.bdo.UserSession;

public interface IStringAPI {

	public ServiceResponse addAssets(Assets asset) throws Exception;
	public ServiceResponse deleteAsset(Assets asset) throws Exception;
	public ServiceResponse updateAsset(Assets asset) throws Exception;
	public ServiceResponse changePassword(PasswordChange cpwd) throws Exception;
	public ServiceResponse addService(Service service) throws Exception;
	public ServiceResponse addUsers(Users users) throws Exception;
	public ServiceResponse addServiceRegistry(ServiceRegistry serviceRegistry) throws Exception;
	public ServiceResponse getAllServicesByAssetHash(Assets asset) throws Exception ;
	public ServiceResponse deleteUser(Users users) throws Exception;
	public ServiceResponse updateService(Service service) throws Exception;
	public ServiceResponse updateServiceRegistry(ServiceRegistry serviceRegistry) throws Exception;
	public ServiceResponse updateUsers(Users users) throws Exception;
	public ServiceResponse login(LoginCredentials loginCredentials) throws Exception;
	public ServiceResponse logout(UserSession sessionObject) throws Exception;
	public ServiceResponse getServices(Service service) throws Exception ;
	public ServiceResponse getAsset(Assets asset) throws Exception;
	
}
