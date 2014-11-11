package com.pft.string.service.api.resources;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pft.string.service.api.bl.StringAPIAdapter;
import com.pft.string.service.api.common.bdo.Assets;
import com.pft.string.service.api.common.bdo.LoginCredentials;
import com.pft.string.service.api.common.bdo.PasswordChange;
import com.pft.string.service.api.common.bdo.Service;
import com.pft.string.service.api.common.bdo.ServiceRegistry;
import com.pft.string.service.api.common.bdo.UserSession;
import com.pft.string.service.api.common.bdo.Users;
import com.pft.string.service.api.interfaces.IStringAPI;
import com.pft.string.service.framework.core.types.ServiceResponse;


@Path("/Service")
public class StringAPIServices implements IStringAPI {

	@Override
	@POST()
	@Path("/AddAsset")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceResponse addAssets(Assets asset) throws Exception {
		StringAPIAdapter obj=new  StringAPIAdapter();
		return obj.addAsset(asset);
	}
	
	@Override
	@POST()
	@Path("/DeleteAsset")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceResponse deleteAsset(Assets asset) throws Exception{
		StringAPIAdapter obj=new StringAPIAdapter();
		return obj.deleteAsset(asset);
	}

	@Override
	@POST()
	@Path("/UpdateAsset")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceResponse updateAsset(Assets asset) throws Exception{
		StringAPIAdapter obj=new StringAPIAdapter();
		return obj.updateAsset(asset);
	}
	
	
	@POST()
	@Path("/AddService")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ServiceResponse addService(Service service) throws Exception {
		StringAPIAdapter obj=new  StringAPIAdapter();
		return obj.addService(service);
	}

	@POST()
	@Path("/UpdateService")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ServiceResponse updateService(Service service) throws Exception {
		StringAPIAdapter obj=new  StringAPIAdapter();
		return obj.updateService(service);
	}
	
	@POST()
	@Path("/AddUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ServiceResponse addUsers(Users users) throws Exception {
		StringAPIAdapter obj=new  StringAPIAdapter();
		return obj.addUsers(users);
	}
	
	@POST()
	@Path("/UpdateUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ServiceResponse updateUsers(Users users) throws Exception {
		StringAPIAdapter obj=new StringAPIAdapter();
		return obj.updateUser(users);		
	}
	
	@POST()
	@Path("/DeleteUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ServiceResponse deleteUser(Users users) throws Exception {
		StringAPIAdapter obj=new StringAPIAdapter();
		return obj.deleteUser(users);		
	}

	
	@POST()
	@Path("/AddServiceRegistry")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ServiceResponse addServiceRegistry(ServiceRegistry serviceRegistry)
			throws Exception {
		StringAPIAdapter obj=new  StringAPIAdapter();
		return obj.addServiceRegistry(serviceRegistry);
	}
	
	@POST()
	@Path("/UpdateServiceRegistry")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ServiceResponse updateServiceRegistry(ServiceRegistry serviceRegistry)
			throws Exception {
		StringAPIAdapter obj=new  StringAPIAdapter();
		return obj.updateServiceRegistry(serviceRegistry);
	}
	
	//need to implement still
	@POST()
	@Path("/GetAllServicesByAssetHash")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ServiceResponse getAllServicesByAssetHash(Assets asset) throws Exception {
		StringAPIAdapter obj=new  StringAPIAdapter();
		return obj.getAllServicesByAssetHash(asset);
	}
	
	@POST()
	@Path("/GetServices")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ServiceResponse getServices(Service service) throws Exception {
		StringAPIAdapter obj=new StringAPIAdapter();
		return obj.getServices(service);
	}
	
	@POST()
	@Path("/GetAsset")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ServiceResponse getAsset(Assets asset) throws Exception {
		StringAPIAdapter obj=new StringAPIAdapter();
		return obj.getAsset(asset);
	}

	@POST()
	@Path("/ChangePassword")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ServiceResponse changePassword(PasswordChange cpwd) throws Exception {
		StringAPIAdapter obj=new  StringAPIAdapter();
		return obj.changePassword(cpwd);
	}

	@POST()
	@Path("/Login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ServiceResponse login(LoginCredentials loginCredentials) throws Exception {
		StringAPIAdapter obj=new  StringAPIAdapter();
		return obj.login(loginCredentials);
	}
	
	@POST()
	@Path("/Logout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public ServiceResponse logout(UserSession sessionObject) throws Exception {
		StringAPIAdapter obj=new  StringAPIAdapter();
		return obj.logout(sessionObject);
	}

	
}
	
