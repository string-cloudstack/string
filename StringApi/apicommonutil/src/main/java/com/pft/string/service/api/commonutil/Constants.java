package com.pft.string.service.api.commonutil;


public class Constants {  
    public static final String ApplicationContext = "ApplicationContext";			
	public static final String RETURN_SUCCESS="Success";
	public static final String RETURN_FAIL="Fail";
	public static final String RETURN_PASS="Pass";
	
public class ServiceAgentConstatnt 
{

	public static final String SERVICEAGENTPATH="ServiceAgentPath";
	public static final String VIDEOSTREAMINGSERVERHOSTNAME="VideoStreamingServerHostName";
	public static final String VIDEOENCRYPTEDSERVICEPATH="VideoEncryptedServicePath";
	public static final String DOCSTREAMINGSERVERHOSTNAME="DocumentStreamingServerHostName";
	public static final String DOCENCRYPTEDSERVICEPATH="DocumentEncryptedServicePath";
	public static final String ALFRESCOAPITOKEN="ALFRESCO_API_TOKEN";
	public static final String VALIDATEAPITOKEN="APItoken";
	
	
	public static final String WSGETVIDEOUNCPATH="WSGetVideoUNCPath";
	public static final String WSGETASSETINFORMATION="WSGetAssetInformation";
	public static final String WSGETPLAYLIST="WSGetPlayList";
	public static final String WSGETUSERPROFILE="WSGetUserProfile";
	public static final String WSGETUSERSESSION	="WSGetUserSession";
	public static final String WSPINGREQUEST	="WSPingRequest";
	public static final String WSGETCOMMENTS	="WSGetComments";
	public static final String WSGETPASSWORDHASH = "WSGetPasswordHash";
	public static final String WSGETPALFRESCOTICKET = "WSGetAlfrescoTicket";
	public static final String WSLOGOUTREQUEST	="WSLogoutRequest";
	public static final String WSVALIDATEUSERGUIDANDUSERSESSION = "WSValidateUserGuidAndUserSession";
	public static final String SCHEMA_USER_GUID="SchemaUserGuid";
	public static final String BOX_PLATE_FORM="BoxPlateForm";
	public static final String BOXSESSIONCLEANUP_TIMEGAP="BoxSessionCleanUp_TimeGap";
	public static final String USERSESSIONVIDEOURLCLEANUP_TIMEGAP="UserSessionVideoUrlCleanUp_TimeGap";
	public static final String OUTSTANDINGLOGINCHALLENGESCLEANUP_TIMEGAP="OutStandingLoginChallengesCleanUp_TimeGap";
	public static final String WSAUDITSTBACTION	="WSAuditSTBAction";
	public static final String WSGETASSETMETADATA = "WSGetAssetMetadata";
	public static final String WSGETCHILDREN = "WSGetChildren";
	public static final String WSGETWATERMARKDETAILS = "WSGetWaterMarkDetails";
	public static final String WSGETCLIPMETADATA = "WSGetClipMetadata";

	
	public static final String BOXSESSIONCLEANUP_URL="BoxSessionCleanUp_URL";
	public static final String USERSESSIONVIDEOURLCLEANUP_URL="UserSessionVideoUrlCleanUp_URL";
	public static final String OUTSTANDINGLOGINCHALLENGESCLEANUP_URL="OutStandingLoginChallengesCleanUp_URL";
	public static final String DAXAPICONTEXT="DAXAPIContext";
	
	
	public static final String STBPREFERENCEVALUE1 = "STB_Preference_Value_1";
	public static final String STBPREFERENCEVALUE2 = "STB_Preference_Value_2";
	public static final String STBPREFERENCEVALUE3 = "STB_Preference_Value_3";
	public static final String STBPREFERENCEVALUE4 = "STB_Preference_Value_4";
	public static final String STBPREFERENCEVALUE5 = "STB_Preference_Value_5";
	
	public static final String LAPTOPPREFERENCEVALUE1 = "LAPTOP_Preference_Value_1";
	public static final String LAPTOPPREFERENCEVALUE2 = "LAPTOP_Preference_Value_2";
	public static final String LAPTOPPREFERENCEVALUE3 = "LAPTOP_Preference_Value_3";
	public static final String LAPTOPPREFERENCEVALUE4 = "LAPTOP_Preference_Value_4";
	public static final String LAPTOPPREFERENCEVALUE5 = "LAPTOP_Preference_Value_5";
	
		public static final String SUPERUSERNAME = "SuperUserName";
		public static final String SUPERUSERPWD = "SuperUserPWD";
		public static final String DEFAULT_ROOTFOLDER = "Default_RootFolder";
	}
	public class ErrorDescriptionConstent 
	{
	public static final String PLEASE_ENSURE_THE_INPUT_VALUES = "Please ensure these input values are valid";	
	public static final String INVALID_OBJECTGUID="Object Does Not exist";
	public static final String WEB_SCRIPT_NOT_FOUND="Alfresco Web Script Status 404 - Not Found";
	public static final String INVALID_TOKEN="Invalid Token";
	public static final String INVALID_PLATFORM="Invalid Client Software Platform";
	public static final String INVALID_SOFTWAREVERSION="Invalid Client Software Version";
	public static final String INVALID_DIVICE="Invalid device";
	public static final String USER_NOTEXIST="User Does Not exist";
	public static final String DEVICE_GUID_NULL = "deviceGUID is null";
	public static final String SOFTWARE_VERSION_NULL = "softwareVersion is null";
	public static final String INVALID_HARDWARE = "Box is not found or box is inactive";
	public static final String BOXSESSION_FAILED = "Create box session failed";
	public static final String INVALID_BOXSESSION = "Box session is invalid or not active";
	public static final String NO_ACTIVE_USERS = "There is no active users associated to the box";
	public static final String DEVICE_SESSION_NULL = "Device session is null";
	public static final String USER_SESSION_NULL = "User session is null";
	public static final String INVALID_DEVICE_SESSION_OR_INVALID_USER_SESSION = "Invalid device session or invalid user session";
	public static final String INVALID_USER = "Invalid box user";
	public static final String USER_GUID_NULL = "UserGuid is null";
	public static final String STB_CONFIG_PROPERTIES_FILE_EXCEPTION = "STB Configurations file reading failed.";
	public static final String INVALID_USERSESSION="Invalid userSession";
	public static final String INVALID_APITOKEN="Invalid API token";
	public static final String CHALLEGNE_RESPONSE_NULL_OR_EMPTY = "Challenge response is null or empty";
	public static final String OUTSTANDING_LOGIN_CHALLENGE_NOT_FOUND = "Login challenge not found.";
	public static final String INVALID_PASSWORD = "Password is invalid";
	public static final String USER_GUID_INVALID = "Invalid userGuid";
	public static final String INVALID_PLATEFORM = "Invalid platform no information available";
	public static final String INPUT_VALUES_CHECK = "Please ensure Input values are correct and in valid format";
	public static final String USER_SESSION_EMPTY = "User session is empty";
	public static final String DEVICE_SESSION_EMPTY = "Device session is empty";
	public static final String INVALID_USER_GUID_USER_SESSION = "Please check UserGUID. UserSession was not associated with this UserGUID";
	public static final String DEVICE_GUID_EMPTY = "deviceGUID is empty";
	public static final String SOFTWARE_VERSION_EMPTY = "Software version is empty";
	public static final String USER_GUID_EMPTY = "UserGUID is empty";
	public static final String DEFAULT_PREFERENCES_NULL = "Default preferences are empty";
	public static final String INVALID_OSTYPE = "Invalid OSType";
	public static final String INVALID_DATEFORMAT = "incrementalDate Should be in '2014-05-05T06:21:13.384-0700' this format";
	public static final String INVALID_RELEASETYPE = "Invalid release type";
	public static final String DEVICE_KEY_ISNULL = "Device hash Key is null or empty";
	public static final String PLAYBACK_KEY_ISNULL = "Playback hash Key is null or empty";
	public static final String VALIDATION_KEY_ISNULL = "Validation hash Key is null or empty";
	public static final String VERSION_REGISERED_SUCCESSFULLY = "Build version is registered successfully ";
	public static final String CLIPS_EMPTY = "Clips are empty for this playlist";
	public static final String INVALID_PLAYLIST_OR_USER_PERNISSION = "Invalid playlist object or User doesnt have permission";
public static final String ALFRESCO_INTERNAL_ERROR = "Unable to retrieve the data from server";


	}

	public enum OSType {
		LINUX(1), MAC(2), WINDOWS(3);
		private int value;

		private OSType(int value) {
			this.value = value;
		}

		public int getIdForOSType() {
			return value;
		}

		public static boolean contains(String osTypeValue) {

			for (OSType enumOSType : OSType.values()) {

				if (enumOSType.toString().equalsIgnoreCase(osTypeValue)) {
					return true;
				}
			}

			return false;
		}
	}

	public enum ReleaseType {
		MAJOR, MINOR, PATCH;
		public static boolean contains(String releaseType) {
			for (ReleaseType enumReleaseType : ReleaseType.values()) {

				if (enumReleaseType.toString().equalsIgnoreCase(releaseType)) {
					return true;
				}
			}

			return false;
		}
	}

}