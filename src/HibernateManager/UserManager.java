package HibernateManager;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import JavaClass.Contact;
import JavaClass.Event;
import JavaClass.Role;
import JavaClass.User;







public class UserManager {

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
	
	//Show all Users
	public ArrayList<User> showAllUsers(String ip,Map<String, User> userDB) throws SQLException{
		boolean is_exist=false;
		User user = null;
		Session session = factory.openSession();
		Transaction tx = null;
		ArrayList<User> users=new ArrayList<User>();
		try{
			tx = session.beginTransaction();
//			for(Map.Entry<String,User> me : userDB.entrySet()){
//				if(me.getKey().equals(ip)){
//					user=me.getValue();
//					is_exist=true;
//				}
//			}
//			if(is_exist){
//				User userMember=session.get(User.class,user.getUsername());
//				Date date=new Date();
//				Event ee=new Event(userMember,"show all contacts", date);
//				session.save(ee);
//			}
//			else{
//				User userguest=session.get(User.class,"guest");
//				Date date=new Date();
//				Event ee=new Event(userguest,"show all contacts", date);
//				session.save(ee);
//			}
			
			
			String hql = "FROM User";
			System.out.println("kkkkkkkkkkkk");
					Query query = session.createQuery(hql);
					List results =  query.list();
					Iterator itr = results.iterator();
					while (itr.hasNext()) {
						User emp = (User) itr.next();
						users.add(emp);
					}
			System.out.println("jjjjjjjjj");
//			Criteria criteria = session.createCriteria(User.class);
//			System.out.println("mmmmmmmmmmmm");
//			List users2 = criteria.list();
//			Iterator itr = users2.iterator();
//			while (itr.hasNext()) {
//				User emp = (User) itr.next();
//				users.add(emp);
//			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return users;
	}
	
	
	//Show all Events
	public ArrayList<Event> showAllEvents(String ip,Map<String, User> userDB) throws SQLException{
		boolean is_exist=false;
		User user = null;
		Session session = factory.openSession();
		Transaction tx = null;
		ArrayList<Event> events=new ArrayList<Event>();
		try{
			tx = session.beginTransaction();
//			for(Map.Entry<String,User> me : userDB.entrySet()){
//				if(me.getKey().equals(ip)){
//					user=me.getValue();
//					is_exist=true;
//				}
//			}
//			if(is_exist){
//				User userMember=session.get(User.class,user.getUsername());
//				Date date=new Date();
//				Event ee=new Event(userMember,"show all contacts", date);
//				session.save(ee);
//			}
//			else{
//				User userguest=session.get(User.class,"guest");
//				Date date=new Date();
//				Event ee=new Event(userguest,"show all contacts", date);
//				session.save(ee);
//			}
			
			
//			String hql = "FROM Event";
//			System.out.println("kkkkkkkkkkkk");
//					Query query = session.createQuery(hql);
//					List results =  query.list();
//					Iterator itr = results.iterator();
//					while (itr.hasNext()) {
//						User emp = (User) itr.next();
//						users.add(emp);
//					}
			System.out.println("lllllllllllllll");
			Criteria criteria = session.createCriteria(Event.class);
			System.out.println("mmmmmmmmmmmmmm");
			List events2 = criteria.list();
			Iterator itr = events2.iterator();
			while (itr.hasNext()) {
				Event emp = (Event) itr.next();
				events.add(emp);
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return events;
	}
	
	
	
	//Show all Events//based especial dates
	public ArrayList<Event> showAllEventsDate(String date1,String date2,String ip,Map<String, User> userDB) throws SQLException{
		boolean is_exist=false;
		User user = null;
		Session session = factory.openSession();
		Transaction tx = null;
		ArrayList<Event> events=new ArrayList<Event>();
		try{
			tx = session.beginTransaction();
//			for(Map.Entry<String,User> me : userDB.entrySet()){
//				if(me.getKey().equals(ip)){
//					user=me.getValue();
//					is_exist=true;
//				}
//			}
//			if(is_exist){
//				User userMember=session.get(User.class,user.getUsername());
//				Date date=new Date();
//				Event ee=new Event(userMember,"show all contacts", date);
//				session.save(ee);
//			}
//			else{
//				User userguest=session.get(User.class,"guest");
//				Date date=new Date();
//				Event ee=new Event(userguest,"show all contacts", date);
//				session.save(ee);
//			}
			
			
//			String hql = "FROM Event";
//			System.out.println("kkkkkkkkkkkk");
//					Query query = session.createQuery(hql);
//					List results =  query.list();
//					Iterator itr = results.iterator();
//					while (itr.hasNext()) {
//						User emp = (User) itr.next();
//						users.add(emp);
//					}
			System.out.println(date1);
			System.out.println(date2);
			String hql = "FROM  Event E 	WHERE E.date >= '" +date1+ "' AND E.date< '"+date2+"'";
			Query query = session.createQuery(hql);
			List results =  query.list();
			Iterator itr = results.iterator();
			System.out.println(results.size());
			
			while (itr.hasNext()) {
				Event emp = (Event) itr.next();
				events.add(emp);
				
				
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return events;
	}
	
	

	//inserting a new User
	public boolean insertUser(User CC){
		boolean is_exist=false;
		Session session = factory.openSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			if(session.get(User.class, CC.getUsername())==null){
				CC.setPassword( encrypt(CC.getPassword()));
				Role role=session.get(Role.class,3);
				CC.setRole(role);
				session.save(CC); 
				System.out.println("successfully saved"); 
				is_exist=true;
				Date date=new Date();
				Event ee=new Event(CC,"successful register", date);
				session.save(ee);

			}
			else{
				User userguest=session.get(User.class,"guest");
				Date date=new Date();
				Event ee=new Event(userguest,"failed register", date);
				session.save(ee);

			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return is_exist;

	}

	//Validating a user
	public int validation_of_user(User CC){
		int roleID=-1;
		Role role;
		Session session = factory.openSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			User user=session.get(User.class,CC.getUsername());

			if(user!=null && user.getPassword().equals(encrypt(CC.getPassword()))){
				role=user.getRole();
				roleID=role.getId();
				Date date=new Date();
				Event ee=new Event(user,"successful login", date);
				session.save(ee);


			}
			else{
				User userguest=session.get(User.class,"guest");
				Date date=new Date();
				Event ee=new Event(userguest,"failed login", date);
				session.save(ee);
				System.out.println("Failed");

			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return roleID;


	}
//	//Changing the user's roles
	public boolean changeRole(User t) throws SQLException{

		boolean is_exist=false;
		Session session = factory.openSession();
		Transaction tx = null;
		User user;
		try{
			tx = session.beginTransaction();
			user=new User();
			String username=t.getUsername();
			user=session.get(User.class, username);

			if(	user!=null){
				System.out.println("ppppppppp");
				user.setRole(t.getRole());
				System.out.println(t.getRole().getId());
				session.update(user);
				is_exist=true;
				System.out.println("successfully update"); 
				

			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return is_exist;
	}

	
	//Changing the user's roles
//		public boolean changeRole(String username,String role) throws SQLException{
//
//			boolean is_exist=false;
//			Session session = factory.openSession();
//			Transaction tx = null;
//			User user;
//			Role changerole;
//			try{
//				tx = session.beginTransaction();
//				user=new User();
//				int roleNumber = 0;
//				user=session.get(User.class, username);
//				if(role.equals("member")){
//					roleNumber=3;
//				}
//				else if(role.equals("manager")){
//					roleNumber=2;
//					
//				}
//				changerole=session.get(Role.class, roleNumber);
//				if(	user!=null){
//					user.setRole(changerole);
//					session.update(user);
//					is_exist=true;
//					System.out.println("successfully update"); 
//
//				}
//				tx.commit();
//			}catch (HibernateException e) {
//				if (tx!=null) tx.rollback();
//				e.printStackTrace(); 
//			}finally {
//				session.close(); 
//			}
//			return is_exist;
//		}

	//delete one user
	public boolean deleteOneUser(String username) throws SQLException{

		boolean is_exist=false;
		Session session = factory.openSession();
		Transaction tx = null;
		User user;
		try{
			tx = session.beginTransaction();
			user=new User();
			user=	session.get(User.class, username);

			if(	user!=null){

				session.delete(user); 
				is_exist=true;
				System.out.println("successfully deleted");
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return is_exist;
	}

	public  String encrypt(String source) {
		String md5 = null;
		try {
			MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
			mdEnc.update(source.getBytes(), 0, source.length());
			md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
		} catch (Exception ex) {
			return null;
		}
		return md5;
	}


	//Junit
	//Validating a user for Junit
	public boolean JUnitvalidationof_user(User CC){
		boolean is_exist=false;
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			User user=session.get(User.class,CC.getUsername());
			if(user!=null ){
				is_exist=true;
			}

			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return is_exist;
	}




}
