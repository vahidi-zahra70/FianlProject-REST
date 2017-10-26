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



@Path("/contacts")  
public class RESTContact{
	static Logger log = Logger.getLogger( 
			RESTContact.class.getName());


	private static Map<String, User> userDB;
	{
		userDB=RESTUser.getUserDB();
	}


	//adding a new contact
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addContact(Contact t,@Context HttpServletRequest request) throws SQLException {
		String ip = request.getRemoteAddr();
		String result;

		ContactManager TT=new ContactManager();
		if(TT.insertContact(t,ip,userDB)){
			result="Saved successfully";
		}
		else {
			result="You are not allowed to insert a new contact";
		}
		log.error(result);
		return Response.status(200).entity(result).build();
	}



	//show all contacts
	@GET
	@Produces( MediaType.APPLICATION_JSON)
	public  ArrayList<Contact>   showAllContacts( @Context HttpServletRequest request) throws SQLException{
		String ip = request.getRemoteAddr();
		ContactManager TT=new ContactManager();
		return TT.showAllContacts( ip,userDB);
	}


	//Delete one contact
	@DELETE
	@Path("/{id}")
	public Response deleteOneTickets(@PathParam("id") String id) throws SQLException{
		ContactManager TT=new ContactManager();
		String result;
		if(TT.deleteOneContact(Integer.parseInt(id))){
			result = "The contact with id "+id+" deleted successfully.";
			System.out.println(result);
		}
		else{
			result = "The contact with id "+id+" which you want to delete does not exist.";
			System.out.println(result);
		}
		return Response.status(200).entity(result).build();
	}

	//show one contact
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)  
	public Contact  selectTicket(@PathParam("id") String id) throws NumberFormatException, SQLException{
		ContactManager TT=new ContactManager();
		return  TT.showOneContact(Integer.parseInt(id));
	}

	//update one contact
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response  UpdateTicket(@PathParam("id") String id,Contact t) throws NumberFormatException, SQLException{
		t.setId(Integer.parseInt(id));
		ContactManager TT=new ContactManager();
		String result;
		if(TT.updateOneContact(t)){
			result = "The contact with id "+id+" updated successfully.";
			System.out.println(result);
		}
		else{
			result = "The contact with id "+id+" which you want to update does'nt exist.";
			System.out.println(result);
		}
		return Response.status(200).entity(result).build();	
	}

	//search a contact
	@GET
	@Path("/{name}/{phone}")
	@Produces(MediaType.APPLICATION_JSON)  
	public ArrayList<Contact>  searchContact(@PathParam("name") String name
			,@PathParam("phone") String phone) throws NumberFormatException, SQLException{
		ContactManager TT=new ContactManager();
		return  TT.searchContact(name, Integer.parseInt(phone));
	}


}

