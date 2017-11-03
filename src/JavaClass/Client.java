package JavaClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import java.util.HashMap;  
import java.util.Map;  
import org.json.simple.JSONValue;



public class Client {
	String serverURL;	
	static Logger log = Logger.getLogger( 
			 Base.class.getName());
	public Client(){
		
	}
	public Client(String serverURL){
		this.serverURL=serverURL;
	}


	//Showing all Contacts
	public String getContacts(){

		String result="";
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					serverURL+"/ProjectFinal/prj/contacts");
			
			getRequest.addHeader("accept", "application/json");
			
			

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));

			String output;
			log.error("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				result=result+output;
				
				log.error(output);
			}
			httpClient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		return result;
	}


	//adding a new contact
	public void saveContact(String name,String family,long homephone,long cellphone,String email){

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					serverURL+"/ProjectFinal/prj/contacts");

			Map obj=new HashMap();    
			obj.put("name",name);    
			obj.put("family",family); 
			obj.put("homephone",homephone); 
			obj.put("cellphone",cellphone); 
			obj.put("email",email); 
			String jsonText = JSONValue.toJSONString(obj);  
			
			StringEntity input = new StringEntity(jsonText,
					ContentType.APPLICATION_JSON);
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200
					|| response.getStatusLine().getStatusCode() != 401) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));

			String output;
			
			log.error("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				log.error(output);
			}

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}


	//Validating a user
	public String ValidateUser(String username,String password){
		

		String result="";

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					serverURL+"/ProjectFinal/prj/users/validate");


			Map obj=new HashMap();    
			obj.put("username",username);    
			obj.put("password",password);    
			String jsonText = JSONValue.toJSONString(obj);  
			StringEntity input = new StringEntity(jsonText,
					ContentType.APPLICATION_JSON);
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));

			String output;
			log.error("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				result=result+output;
				log.error(output);
			}

			httpClient.getConnectionManager().shutdown();


		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}



	//Registering a new user
	public String RegisterUser(String username,String password){

		String result="";

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
					serverURL+"/ProjectFinal/prj/users/adduser");


			Map obj=new HashMap();    
			obj.put("username",username);    
			obj.put("password",password);    
			String jsonText = JSONValue.toJSONString(obj);  
			StringEntity input = new StringEntity(jsonText,
					ContentType.APPLICATION_JSON);
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
					new InputStreamReader((response.getEntity().getContent())));

			String output;
			log.error("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				result=result+output;
				log.error(output);
				
			}

			httpClient.getConnectionManager().shutdown();


		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
