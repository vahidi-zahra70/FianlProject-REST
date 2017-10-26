package JavaClass;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import HibernateManager.ContactManager;
import HibernateManager.UserManager;



@Path("/users")  
public class RESTUser{

	static Logger log = Logger.getLogger( 
			RESTUser.class.getName());


	private static Map<String, User> userDB =
			new ConcurrentHashMap<String, User>();

	public static Map<String, User> getUserDB() {
		return userDB;
	}

	public static void setUserDB(Map<String, User> userDB) {
		RESTUser.userDB = userDB;
	}

	//validating a user
	@Path("/validate")  
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validateUser(User t,@Context HttpServletRequest request) throws SQLException {
		String result;
		UserManager TT=new UserManager();
		int output=TT.validation_of_user(t);
		if(output==-1){
			result="not exist";
		}
		else{
			result="OK "+output;
			String ip = request.getRemoteAddr();
			userDB.put(ip, t);
		}
		log.error(result);
		return Response.status(200).entity(result).build();
	}

	//adding a new user
	@Path("/adduser")  
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User t) throws SQLException {
		String result;
		UserManager TT=new UserManager();
		if(TT.insertUser(t)){
			result="Saved successfully";
		}
		else{
			result="NOT Saved";
		}
		log.error(result);
		return Response.status(200).entity(result).build();
	}


	//Delete one user
	@DELETE
	@Path("/{username}")
	public Response deleteOneTickets(@PathParam("username") String username) throws SQLException{
		UserManager TT=new UserManager();
		String result;
		if(TT.deleteOneUser(username)){
			result = "The user with username "+username+" deleted successfully.";
			System.out.println(result);
		}
		else{
			result = "The user with username "+username+" which you want to delete does not exist.";
			System.out.println(result);
		}
		return Response.status(200).entity(result).build();
	}


	//update one user
	@PUT
	@Path("/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response  UpdateTicket(@PathParam("username") String username,User t) throws NumberFormatException, SQLException{
		t.setUsername(username);
		UserManager TT=new UserManager();
		String result;
		if(TT.changeRole(t)){
			result = "The user with username "+username+" updated successfully.";
			System.out.println(result);
		}
		else{
			result = "The user with username "+username+" which you want to update does'nt exist.";
			System.out.println(result);
		}
		return Response.status(200).entity(result).build();	
	}

}

