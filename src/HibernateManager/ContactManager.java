
package HibernateManager;


import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import JavaClass.Contact;
import JavaClass.Event;
import JavaClass.Feature;
import JavaClass.User;










public class ContactManager {

	Configuration cfg=null;
	SessionFactory factory=null;

	{ try{
		DBManager DD=new DBManager();
		cfg=DD.getconn();
		factory=DD.getfactory();
	}
	catch (Exception e) {

		e.printStackTrace(); 
	}

	}


	//delete one Contact
	public String deleteOneContact(int Contactid,String ip,Map<String, User> userDB) throws SQLException{

		boolean is_allow=false;
		boolean is_guest=true;
		String DeleteContactState = null;
		User user = null;
		User finaluser = null;
		Session session = factory.openSession();
		Transaction tx = null;
		Contact contact;
		try{
			tx = session.beginTransaction();

			for(Map.Entry<String,User> me : userDB.entrySet()){
				if(me.getKey().equals(ip)){
					is_guest=false;
					user=me.getValue();
					finaluser=session.get(User.class,user.getUsername());
					Set<Feature> features=finaluser.getRole().getFeatures();
					Feature ff=new Feature("Delete contact");
					if(features.contains(ff)){
						is_allow=true;
					}
					break;
				}
			}

			if(is_allow){
				contact=new Contact();
				contact=session.get(Contact.class, Contactid);

				if(	contact!=null){
					session.delete(contact); 
					System.out.println("successfully deleted");
					DeleteContactState="successfully deleted";
				}
				else{
					DeleteContactState="The contact does not exist";
				}

				Date date=new Date();
				Event ee=new Event(finaluser,"successful deleting a contact", date);
				session.save(ee);
			}

			else{

				DeleteContactState="Not allow";
				if(is_guest){
					User userguest=session.get(User.class,"guest");
					Date date=new Date();
					Event ee=new Event(userguest,"illegal attempt deleting a contact", date);
					session.save(ee);
				}
				else{
					Date date=new Date();
					Event ee=new Event(finaluser,"illegal attempt deleting a contact", date);
					session.save(ee);
				}
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return DeleteContactState;
	}


	//update one Contact
	public String updateOneContact(Contact t,String ip,Map<String, User> userDB) throws SQLException{

		boolean is_allow=false;
		boolean is_guest=true;
		String UpdateContactState = null;
		User user = null;
		User finaluser = null;
		Session session = factory.openSession();
		Transaction tx = null;
		Contact contact;
		try{
			tx = session.beginTransaction();

			for(Map.Entry<String,User> me : userDB.entrySet()){
				if(me.getKey().equals(ip)){
					is_guest=false;
					user=me.getValue();
					finaluser=session.get(User.class,user.getUsername());
					Set<Feature> features=finaluser.getRole().getFeatures();
					Feature ff=new Feature("Update contact");
					if(features.contains(ff)){
						System.out.println(features.contains(ff));
						is_allow=true;
					}
					break;
				}
			}



			if(is_allow){
				contact=new Contact();
				int id=t.getId();
				contact=session.get(Contact.class, id);

				if(	contact!=null){

					contact.setName(t.getName());
					contact.setFamily(t.getFamily());
					contact.setHomephone(t.getHomephone());
					contact.setCellphone(t.getCellphone());
					contact.setEmail(t.getEmail());
					session.update(contact);

					System.out.println("successfully update"); 
					UpdateContactState="successfully update";
				}
				else{
					System.out.println("The contact does not exist"); 
					UpdateContactState="The contact does not exist";
				}
				Date date=new Date();
				Event ee=new Event(finaluser,"successful editing a contact", date);
				session.save(ee);
			}
			
			else{

				UpdateContactState="Not allow";
				if(is_guest){
					User userguest=session.get(User.class,"guest");
					Date date=new Date();
					Event ee=new Event(userguest,"illegal attempt editing a contact", date);
					session.save(ee);
				}
				else{
					Date date=new Date();
					Event ee=new Event(finaluser,"illegal attempt editing a contact", date);
					session.save(ee);
				}
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return UpdateContactState;
	}



	//Show all Contacts
	public ArrayList<Contact> showAllContacts(String ip,Map<String, User> userDB) throws SQLException{
		boolean is_exist=false;
		User user = null;
		Session session = factory.openSession();
		Transaction tx = null;
		ArrayList<Contact> contacts=new ArrayList<Contact>();
		try{
			tx = session.beginTransaction();
			for(Map.Entry<String,User> me : userDB.entrySet()){
				if(me.getKey().equals(ip)){
					user=me.getValue();
					is_exist=true;
				}
			}
			if(is_exist){
				User userMember=session.get(User.class,user.getUsername());
				Date date=new Date();
				Event ee=new Event(userMember,"show all contacts", date);
				session.save(ee);
			}
			else{
				User userguest=session.get(User.class,"guest");
				Date date=new Date();
				Event ee=new Event(userguest,"show all contacts", date);
				session.save(ee);
			}
			Criteria criteria = session.createCriteria(Contact.class);
			List contacts2 = criteria.list();
			Iterator itr = contacts2.iterator();
			while (itr.hasNext()) {
				Contact emp = (Contact) itr.next();
				contacts.add(emp);
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return contacts;
	}

	//inserting a new Contact
	public boolean insertContact(Contact CC,String ip,Map<String, User> userDB){
		boolean is_allow=false;
		User user = null;
		Session session = factory.openSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			for(Map.Entry<String,User> me : userDB.entrySet()){
				if(me.getKey().equals(ip)){
					user=me.getValue();
					is_allow=true;
					break;
				}
			}
			if(is_allow){
				session.save(CC); 
				System.out.println("successfully saved"); 
				User userMember=session.get(User.class,user.getUsername());
				Date date=new Date();
				Event ee=new Event(userMember,"successful adding a contact", date);
				session.save(ee);

			}
			else{
				User userguest=session.get(User.class,"guest");
				Date date=new Date();
				Event ee=new Event(userguest,"illegal attempt adding a contact", date);
				session.save(ee);
			}
			tx.commit();

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return is_allow;
	}


	//show one contact
	public Contact showOneContact(int id) throws SQLException{

		Session session = factory.openSession();
		Transaction tx = null;
		Contact contact=null;
		try{
			tx = session.beginTransaction();
			contact=new Contact();
			contact=session.get(Contact.class, id); 
			System.out.println("successfully show"); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return contact;
	}

	//search contacts
	public ArrayList<Contact> searchContact(String name,int phone) throws SQLException{

		Session session = factory.openSession();
		Transaction tx = null;

		ArrayList<Contact> contacts=new ArrayList<Contact>();
		try{
			tx = session.beginTransaction();
			String hql = "FROM Contact E WHERE E.name like '"+name+"%' and E.cellphone like '"+phone+"%'";
			Query query = session.createQuery(hql);
			List results =  query.list();
			Iterator itr = results.iterator();
			System.out.println(results.size());
			System.out.println("llllllllllll");
			while (itr.hasNext()) {
				Contact emp = (Contact) itr.next();
				contacts.add(emp);
				System.out.println(emp.getName());
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return contacts;
	}
	//Converting the json to data for table
	public Object [][] ConvertJsonToContact(String input) throws SQLException {
		Object[][] data = null;

		ArrayList<Contact> list;
		list=(ArrayList<Contact>) JsonToContact(input);
		data =new Object[list.size()][6];
		for(int i=0;i<list.size();i++){
			data[i][0]=list.get(i).getId();
			data[i][1]=list.get(i).getName();
			data[i][2]=list.get(i).getFamily();
			data[i][3]=list.get(i).getHomephone();
			data[i][4]=list.get(i).getCellphone();
			data[i][5]=list.get(i).getEmail();

		}
		return data;
	}	


	//converting json to object
	public  List JsonToContact(String input){
		Gson gson=new Gson();
		java.lang.reflect.Type type = new TypeToken<List<Contact>>(){}.getType();
		List<Contact> list =gson.fromJson(input,type);
		return list;
	}


}
