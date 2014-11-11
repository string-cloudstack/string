package com.pft.string.service.api.bl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;

import com.pft.string.service.api.common.bdo.Assets;
import com.pft.string.service.api.common.bdo.LoginCredentials;
import com.pft.string.service.api.common.bdo.PasswordChange;
import com.pft.string.service.api.common.bdo.Service;
import com.pft.string.service.api.common.bdo.ServiceRegistry;
import com.pft.string.service.api.common.bdo.UserSession;
import com.pft.string.service.api.common.bdo.Users;
import com.pft.string.service.api.common.facade.AssetFacade;
import com.pft.string.service.api.common.facade.AssetFacade.CreateAssets;
import com.pft.string.service.api.common.facade.AssetFacade.DeleteAssets;
import com.pft.string.service.api.common.facade.AssetFacade.GetAllAssets;
import com.pft.string.service.api.common.facade.AssetFacade.UpdateAsset;
import com.pft.string.service.api.common.facade.ServiceFacade;
import com.pft.string.service.api.common.facade.ServiceFacade.AllServices;
import com.pft.string.service.api.common.facade.ServiceFacade.CreateService;
import com.pft.string.service.api.common.facade.ServiceFacade.UpdateService;
import com.pft.string.service.api.common.facade.ServiceRegistryFacade;
import com.pft.string.service.api.common.facade.ServiceRegistryFacade.CreateServiceRegistry;
import com.pft.string.service.api.common.facade.ServiceRegistryFacade.UpdateServiceRegistry;
import com.pft.string.service.api.common.facade.UserSessionFacade;
import com.pft.string.service.api.common.facade.UserSessionFacade.CreateUserSession;
import com.pft.string.service.api.common.facade.UserSessionFacade.CurrentSession;
import com.pft.string.service.api.common.facade.UserSessionFacade.DeleteSession;
import com.pft.string.service.api.common.facade.UserSessionFacade.UpdateUserSession;
import com.pft.string.service.api.common.facade.UserSessionFacade.UserSessionByToken;
import com.pft.string.service.api.common.facade.UsersFacade;
import com.pft.string.service.api.common.facade.UsersFacade.CreateUsers;
import com.pft.string.service.api.common.facade.UsersFacade.UpdateUsers;
import com.pft.string.service.api.common.facade.UsersFacade.UserDetails;
import com.pft.string.service.api.common.facade.UsersFacade.UserDetailsByName;
import com.pft.string.service.api.commonutil.PropertyLoader;
import com.pft.string.service.api.commonutil.Utils;
import com.pft.string.service.framework.core.FaultMessage;
import com.pft.string.service.framework.core.types.ErrorLevel;
import com.pft.string.service.framework.core.types.ServiceResponse;
import com.pft.string.service.framework.logproperties.LogFactory;

public class StringAPIAdapter {

	private Pattern pattern;
	private Matcher matcher;

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	public StringAPIAdapter() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	private static final Logger LOGGER = LogFactory
			.getLogger(StringAPIAdapter.class);

	public Properties getAPIProperties() {
		return PropertyLoader.getInstance().getApiProperties();
	}

	/* Asset Related actions and queries */

	public ServiceResponse addAsset(Assets asset) {

		ServiceResponse response = new ServiceResponse();

		// Need to implement for user session all methods

		if (Utils.isStringEmpty(asset.getAssetHash()) && Utils.isStringEmpty(asset.getUserSession())) {
			if (isSessionValid(asset.getUserSession())) {
				CreateAssets createAssets = new AssetFacade().new CreateAssets();
				createAssets.setEntity(asset);
				try {
					createAssets.Execute();
					response.setResult(createAssets.getEntity().getAssetHash());
				} catch (ConstraintViolationException e) {
					e.printStackTrace();
					response.setResult(null);
					response.setErrorMessage(new FaultMessage(500,ErrorLevel.MEDIUM,"Asset and serviceToken combination is duplicated", "", ""));
				}catch (Exception e1) {
					e1.printStackTrace();
					response.setResult(null);
					response.setErrorMessage(new FaultMessage(500,ErrorLevel.MEDIUM,"Problem while access the service ", "", ""));
				}
				
			} else {
				response.setResult(null);
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
						"UserSession is invalid", "", ""));
			}
		} else {
			response.setResult(null);
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
					"getAssetHash is empty please correct the input values",
					"", ""));
		}
		return response;
	}

/*	private Assets converttoAssetBDO(Assets asset) {
		Assets objAssets = new Assets();
		objAssets.setAssetHash(asset.getAssetHash());
		objAssets.setDescription(asset.getDescription());
		objAssets.setMetadata(asset.getMetadata());
		objAssets.setUrl(asset.getUrl());
		Set<Service> services = new HashSet<Service>();
		for (Service sr : asset.getServices()) {
			sr.setAssetHash(objAssets);
			services.add(sr);
		}
		objAssets.setServices(services);
		return objAssets;

	}*/

	public ServiceResponse getAllServicesByAssetHash(Assets asset) {
		Set<String> serviceTokens=new HashSet<String>();
		ServiceResponse response = new ServiceResponse();
		ServiceResponse serviceResponse = new ServiceResponse();
		if (Utils.isStringEmpty(asset.getAssetHash()) && Utils.isStringEmpty(asset.getUserSession())) {
			if (isSessionValid(asset.getUserSession())) {
				GetAllAssets getAllAssets= new AssetFacade().new GetAllAssets();
				getAllAssets.setAssetHash(asset.getAssetHash());
				try {
					getAllAssets.Execute();
					List<Assets> assets = getAllAssets.getEntity();
					for(Assets temp:assets){
						serviceTokens.add(temp.getServiceToken());
					}
					
					Service service =new Service();
					service.setIsPublic(1);
					service.setUserSession(asset.getUserSession());
					serviceResponse= getServices(service);
					List<Service> allServices=(List<Service>) serviceResponse.getResult();
					
					Set<Service> assetServices=new HashSet<Service>();
					
					for(Service temp1:allServices){
						 if(serviceTokens.contains(temp1.getServiceToken()))
						 assetServices.add(temp1);
					}
					response.setResult(assetServices);
					
				}catch(Exception e){
					response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"Error in fetching in services", "", ""));
				}
			}else{
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserSession is invalid", "", ""));
			}
		}else {
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH, "AssetHash and usersession should not be empty","", ""));
		}
		return response;
	}

	
	public ServiceResponse getServices(Service service) throws Exception {
		ServiceResponse response = new ServiceResponse();
		if (isSessionValid(service.getUserSession())) {
			AllServices allServices = new ServiceFacade().new AllServices();
			allServices.setIsPublic(service.getIsPublic());
			try{
				allServices.Execute();
				List<Service> services = allServices.getEntity();
				response.setResult(services);
			}catch(Exception e){
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"Error in fetching in services", "", ""));
			}
			
		}else
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserSession is invalid", "", ""));
		return response;
	}
	
	public ServiceResponse getAsset(Assets asset) throws Exception {
		ServiceResponse response = new ServiceResponse();
		if (isSessionValid(asset.getUserSession())) {
			GetAllAssets allAssets= new AssetFacade().new GetAllAssets();
			allAssets.setAssetHash(asset.getAssetHash());
			try{
				allAssets.Execute();
				List<Assets> assets= allAssets.getEntity();
				Set<String> servicesSet=new HashSet<String>();
				for(Assets obj:assets){
					servicesSet.add(obj.getServiceToken());
				}
				Set resultSet= new HashSet();
				Assets resultAsset=assets.get(0);
				resultAsset.setServiceToken("");
				resultSet.add(resultAsset);
				resultSet.add(servicesSet);
				response.setResult(resultSet);
			}catch(Exception e){
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"Error in fetching in Asset", "", ""));
			}
			
		}else
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserSession is invalid", "", ""));
		return response;
	}	
	

	public ServiceResponse deleteAsset(Assets asset) {
		ServiceResponse response = new ServiceResponse();
	if(isSessionValid(asset.getUserSession())){	
		if (Utils.isStringEmpty(asset.getAssetHash())) {
			DeleteAssets deleteAsset = new AssetFacade().new DeleteAssets();
			deleteAsset.setEntity(asset);
			try {
				deleteAsset.Execute();
				response.setResult("true");
			} catch (Exception e) {
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"Delete asset failed", "", ""));
			}
		} else
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"AssetId cannot be null ", "", ""));
	}else
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserSession is invalid", "", ""));
		return response;
	}

	public ServiceResponse updateAsset(Assets asset) {
		ServiceResponse response = new ServiceResponse();
		if(isSessionValid(asset.getUserSession())){
			if (Utils.isStringEmpty(asset.getAssetHash())) {
				UpdateAsset updateAsset = new AssetFacade().new UpdateAsset();
				updateAsset.setEntity(asset);
				try {
					updateAsset.Execute();
					response.setResult("true");
				} catch (Exception e) {
					response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"Update Asset is failed", "", ""));
				}
			} else
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"AssetHash cannot be null", "", ""));
		}
		else{
			response.setResult(null);
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserSession is invalid", "", ""));
		}
		
		return response;
	}

	
	public ServiceResponse addService(Service service) {

		ServiceResponse response = new ServiceResponse();
		CreateService createService = new ServiceFacade().new CreateService();

		if (Utils.isStringEmpty(service.getServiceName()) && Utils.isStringEmpty(service.getUserSession())) {
			
			if (isSessionValid(service.getUserSession())) {
				//generating serviceToken with 64 bit
				SecureRandom random = new SecureRandom();
				String serviceToken = new BigInteger(320, random).toString(32);
				service.setServiceToken(serviceToken);
				createService.setEntity(service);
			try {
				createService.Execute();
				response.setResult(createService.getEntity());
			} catch (Exception e) {
				e.printStackTrace();
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
						"getService  is empty please correct the input values",
						e.getMessage(), ""));
			}
		}else{
			response.setResult(null);
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserSession is invalid", "", ""));			
		}
			}
			else {
			response.setResult(null);
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
					"getServiceName is empty please correct the input values",
					"", ""));
		}
		return response;
	}

	public ServiceResponse updateService(Service service) {

		ServiceResponse response = new ServiceResponse();
		UpdateService updateService = new ServiceFacade().new UpdateService();

		if (Utils.isStringEmpty(service.getServiceName()) && Utils.isStringEmpty(service.getUserSession())) {
			
			if(isSessionValid(service.getUserSession())){
			updateService.setEntity(service);
			try {
				updateService.Execute();
				response.setResult(updateService.getEntity());
			} catch (Exception e) {
				e.printStackTrace();
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
						"getService  is empty please correct the input values", e.getMessage(), ""));
			}
		}else{
			response.setResult(null);
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserSession is invalid", "", ""));
		}
			
		} else {
			response.setResult(null);
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
					"getServiceName is empty please correct the input values",
					"", ""));
		}
		return response;
	}

	public ServiceResponse addServiceRegistry(ServiceRegistry serviceRegistry) {
		ServiceResponse response = new ServiceResponse();
		CreateServiceRegistry createServiceRegistry = new ServiceRegistryFacade().new CreateServiceRegistry();
		createServiceRegistry.setEntity(serviceRegistry);
		if (Utils.isStringEmpty(serviceRegistry.getServiceName())
				&& Utils.isStringEmpty(serviceRegistry.getServiceToken())&& Utils.isStringEmpty(serviceRegistry.getUserSession()) ) {
			if(isSessionValid(serviceRegistry.getUserSession())){
			try {
				createServiceRegistry.Execute();
				response.setResult(createServiceRegistry.getEntity()
						.getServiceName());
			} catch (Exception e) {
				e.printStackTrace();
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
						"Service registration is failed to process", "", ""));
			}
			}else{
				response.setResult(null);
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserSession is invalid", "", ""));
			}
		} else
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
					"Service name and service token cannot be empty", "", ""));
		return response;
	}

	public ServiceResponse updateServiceRegistry(ServiceRegistry serviceRegistry) {
		ServiceResponse response = new ServiceResponse();
		UpdateServiceRegistry updateServiceRegistry = new ServiceRegistryFacade().new UpdateServiceRegistry();
		updateServiceRegistry.setEntity(serviceRegistry);
		if (Utils.isStringEmpty(serviceRegistry.getServiceName())
				&& Utils.isStringEmpty(serviceRegistry.getServiceToken()) && Utils.isStringEmpty(serviceRegistry.getUserSession())) {
			if(isSessionValid(serviceRegistry.getUserSession())){
			try {
				updateServiceRegistry.Execute();
				response.setResult(updateServiceRegistry.getEntity()
						.getServiceName());
			} catch (Exception e) {
				e.printStackTrace();
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
						"Service registration is failed to process", "", ""));
			}
			}else{
				response.setResult(null);
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserSession is invalid", "", ""));
			}
		} else
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
					"Service name and service token cannot be empty", "", ""));
		return response;
	}

	/* User related action and queries */

	public ServiceResponse addUsers(Users users) {
		ServiceResponse response = new ServiceResponse();
		CreateUsers createUsers = new UsersFacade().new CreateUsers();
		matcher = pattern.matcher(users.getPassword());
		if (Utils.isStringEmpty(users.getUsername())
				&& Utils.isStringEmpty(users.getPassword()) && Utils.isStringEmpty(users.getUserSession())) {
			if(isSessionValid(users.getUserSession())){
			try {
				if (matcher.matches()) {
					//generating serviceToken with 64 bit
					SecureRandom random = new SecureRandom();
					String userGuid =  new BigInteger(320, random).toString(32);
					users.setUserGuid(userGuid);
					createUsers.setEntity(users);
					createUsers.Execute();
					response.setResult("User added successfully");	
				}else{
					response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"password should contain 1 Upper 1 Lower and symbol", "", ""));
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"User registration is failed to proceed", "", ""));
			}
			}
			else{
				response.setResult(null);
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserSession is invalid", "", ""));
			}
			} else
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"Username and password fields cannot be empty", "", ""));
		return response;
	}

	public ServiceResponse updateUser(Users users) {
		ServiceResponse response = new ServiceResponse();
		UpdateUsers updateUsers = new UsersFacade().new UpdateUsers();
		updateUsers.setEntity(users);
		if (Utils.isStringEmpty(users.getUsername())
				&& Utils.isStringEmpty(users.getPassword()) && Utils.isStringEmpty(users.getUserSession())) {
			if(isSessionValid(users.getUserSession())){
				try {
					updateUsers.Execute();
					response.setResult("Pass");
				} catch (Exception e) {
					e.printStackTrace();
					response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
							"User upgradation is failed to proceed", "", ""));
				}
			}
			else{
				response.setResult(null);
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserSession is invalid", "", ""));
			}
				
		} else
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
					"Username and password fields cannot be empty", "", ""));
		return response;
	}

	public ServiceResponse changePassword(PasswordChange cpwd) {
		
		ServiceResponse response = new ServiceResponse();
		UserDetailsByName userDetails = new UsersFacade().new UserDetailsByName();

		matcher = pattern.matcher(cpwd.getNewPassword());
		if (Utils.isStringEmpty(cpwd.getUsername())
				&& Utils.isStringEmpty(cpwd.getOldPassword()) && Utils.isStringEmpty(cpwd.getUserSession())) {
			if(isSessionValid(cpwd.getUserSession())){
				if (matcher.matches()) {
					userDetails.setUsername(cpwd.getUsername());
					try {
						userDetails.Execute();
						Users user = userDetails.getEntity();
						response.setResult(user);
						if (user.getPassword().equals(cpwd.getOldPassword())) {
							if (cpwd.getNewPassword().equals(
									cpwd.getReEnterPassword())) {
								if (!cpwd.getNewPassword().equals(user.getOldPassword()) && !cpwd.getNewPassword().equals(user.getPassword())) {
									user.setOldPassword(cpwd.getOldPassword());
									user.setPassword(cpwd.getNewPassword());
									updateUser(user);
								} else
									response.setErrorMessage(new FaultMessage(500,
											ErrorLevel.HIGH,
											"This password already used.. enter new one", "", ""));
							} else
								response.setErrorMessage(new FaultMessage(500,
										ErrorLevel.HIGH,
										"Re-entered password is not match", "", ""));
						} else
							response.setErrorMessage(new FaultMessage(500,
									ErrorLevel.HIGH, "password is not correct", "",
									""));
					} catch (Exception e) {
						e.printStackTrace();
						response.setErrorMessage(new FaultMessage(500,
								ErrorLevel.HIGH, "user not got", "", ""));
					}

				} else
					response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
							"password should contain 1 Upper 1 Lower and symbol",
							"", ""));
			}else{
				response.setResult(null);
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserSession is invalid", "", ""));
			}
			
		} else
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,
					"username and password cannot be empty", "", ""));

		return response;
	}

	public ServiceResponse deleteUser(Users users) {
		ServiceResponse response = new ServiceResponse();
		UpdateUsers updateUsers = new UsersFacade().new UpdateUsers();
		UserDetails userDetails=new UsersFacade().new UserDetails();
		updateUsers.setEntity(users);
		if (Utils.isStringEmpty(String.valueOf(users.isIsAdmin()))) {
			if(isSessionValid(users.getUserSession())){
				if(users.isIsAdmin()==true){
					try {
						updateUsers.Execute();
						response.setResult("User deleted successfully..");
					} catch (Exception e) {
						e.printStackTrace();
						response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH, "User deletion is failed to proceed", "", ""));
					}					
				}else{
					response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"You don't have admin privilige", "", ""));
				}
			}else{
				response.setResult(null);
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserSession is invalid", "", ""));
			}
		} else
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"UserGuid,isAdmin and isDeleted fields are mandatory", "", ""));
		return response;
	}

	public ServiceResponse login(LoginCredentials loginCredentials) {
		ServiceResponse response = new ServiceResponse();
		if (Utils.isStringEmpty(loginCredentials.getUsername()) && Utils.isStringEmpty(loginCredentials.getPassword())) {
			UserDetailsByName userDetails = new UsersFacade().new UserDetailsByName();
			userDetails.setUsername(loginCredentials.getUsername());
			try {
				userDetails.Execute();
				if (userDetails.getEntity().getPassword().compareTo(loginCredentials.getPassword()) == 0) {
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date time = sdf.parse(sdf.format(cal.getTime()));
					SecureRandom random = new SecureRandom();
					UserSession userSession = new UserSession(userDetails.getEntity().getId(),
							new BigInteger(160, random).toString(32), userDetails.getUsername(), userDetails.getEntity().getUserGuid(), time);
					CreateUserSession createUserSession = new UserSessionFacade().new CreateUserSession();
					createUserSession.setEntity(userSession);
					createUserSession.Execute();
					response.setResult(userSession.getUserSession());
				}

			} catch (Exception e) {
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH, "User not available", "", ""));
			}
		} else
			response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH, "username and password cannot be null ", "", ""));
		return response;
	}

	public boolean isSessionValid(String sessionToken) {
		CurrentSession currentSession = new UserSessionFacade().new CurrentSession();
		boolean status = true;
		if (Utils.isStringEmpty(sessionToken)) {
			currentSession.setSession(sessionToken);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currentTime = null;
			Date activeTime = null;
			try {
				currentSession.Execute();
				UserSession userSession = currentSession.getEntity();
				currentTime = sdf.parse(sdf.format(cal.getTime()));
				activeTime = sdf.parse(sdf.format(userSession.getSessionTime()));
				long timeGap = currentTime.getTime() - activeTime.getTime();
				long minutesGap = timeGap / (60 * 1000) % 60;
				if (minutesGap > 30) {
					DeleteSession expiredSession = new UserSessionFacade().new DeleteSession();
					expiredSession.setEntity(userSession);
					expiredSession.Execute();
					status = false;
				} else {
					UpdateUserSession updateUserSession = new UserSessionFacade().new UpdateUserSession();
					userSession.setSessionTime(currentTime);
					updateUserSession.setEntity(userSession);
					updateUserSession.Execute();
				}
			} catch (Exception e) {
				return false;
			}
		} else
			status = false;
		return status;
	}
	
	
	public ServiceResponse logout(UserSession sessionObject) {	
		ServiceResponse response = new ServiceResponse();
		DeleteSession deleteSession= new UserSessionFacade().new DeleteSession();
		UserSessionByToken userSessionByToken = new UserSessionFacade().new UserSessionByToken();
		userSessionByToken.setUserSession(sessionObject.getUserSession());
		try {
			   userSessionByToken.Execute();
			   UserSession userSession = userSessionByToken.getEntity();
			   if(userSession!=null){
				   deleteSession.setEntity(userSession);
				   deleteSession.Execute();
				   response.setResult("User Logged out Successfully");
			   }else
				   response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"Invalid user token.", "", ""));
			   
			} catch (Exception e) {
				e.printStackTrace();
				response.setErrorMessage(new FaultMessage(500, ErrorLevel.HIGH,"User is not logged out", "", ""));
			}
		return response;
	}
}
