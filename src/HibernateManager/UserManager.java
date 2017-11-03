package HibernateManager;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
	//Changing the user's roles
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
				user.setRole(t.getRole());
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


	//	//show all Drugs
	//	public Object [][] ShowDrogs( ) throws SQLException {
	//		Object[][] data = null;
	//		Session session = factory.openSession();
	//		Transaction tx = null;
	//		ArrayList<Drog> drogs=new ArrayList<Drog>();
	//		try{
	//			tx = session.beginTransaction();
	//			Criteria criteria = session.createCriteria(Drog.class);
	//			List tickets2 = criteria.list();
	//			Iterator itr = tickets2.iterator();
	//			while (itr.hasNext()) {
	//				Drog emp = (Drog) itr.next();
	//				drogs.add(emp);
	//			}
	//			tx.commit();
	//
	//			data =new Object[drogs.size()][4];
	//			for(int i=0;i<drogs.size();i++){
	//				data[i][0]=drogs.get(i).getId();
	//				data[i][1]=drogs.get(i).getName();
	//				data[i][2]=drogs.get(i).getInventory();
	//				data[i][3]=drogs.get(i).getPrice();
	//
	//			}
	//
	//		}catch (HibernateException e) {
	//			if (tx!=null) tx.rollback();
	//			e.printStackTrace(); 
	//		}finally {
	//			session.close(); 
	//		}
	//		return data;
	//	}
	//
	//	//show all Critical Drugs
	//	public Object [][] ShowCriticalDrogs( ) throws SQLException {
	//		Object[][] data = null;
	//		Session session = factory.openSession();
	//		Transaction tx = null;
	//		ArrayList<Drog> drogs=new ArrayList<Drog>();
	//		try{
	//			tx = session.beginTransaction();
	//			String hql = "FROM Drog E WHERE E.inventory < 10";
	//			Query query = session.createQuery(hql);
	//			List results =  query.list();
	//			Iterator itr = results.iterator();
	//			while (itr.hasNext()) {
	//				Drog emp = (Drog) itr.next();
	//				drogs.add(emp);
	//				System.out.println(emp.getName());
	//			}
	//			tx.commit();
	//
	//			data =new Object[drogs.size()][4];
	//			for(int i=0;i<drogs.size();i++){
	//				data[i][0]=drogs.get(i).getId();
	//				data[i][1]=drogs.get(i).getName();
	//				data[i][2]=drogs.get(i).getInventory();
	//				data[i][3]=drogs.get(i).getPrice();
	//
	//			}
	//
	//		}catch (HibernateException e) {
	//			if (tx!=null) tx.rollback();
	//			e.printStackTrace(); 
	//		}finally {
	//			session.close(); 
	//		}
	//		return data;
	//	}	
	//	//delete one drug
	//	public boolean deleteOneInsurance(int id) throws SQLException{
	//
	//		boolean is_exist=false;
	//		Session session = factory.openSession();
	//		Transaction tx = null;
	//		Insurance ticket;
	//		try{
	//			tx = session.beginTransaction();
	//			ticket=new Insurance();
	//			ticket=	session.get(Insurance.class, id);
	//
	//			if(	ticket!=null){
	//
	//				session.delete(ticket); 
	//				is_exist=true;
	//				System.out.println("successfully deleted");
	//			}
	//			tx.commit();
	//		}catch (HibernateException e) {
	//			if (tx!=null) tx.rollback();
	//			e.printStackTrace(); 
	//		}finally {
	//			session.close(); 
	//		}
	//		return is_exist;
	//	}
	//
	//	//update the price for a  Drug
	//	public boolean updateOneDrug(Drog t) throws SQLException{
	//
	//		boolean is_exist=false;
	//		Session session = factory.openSession();
	//		Transaction tx = null;
	//		Drog insurance;
	//		try{
	//			tx = session.beginTransaction();
	//			insurance=new Drog();
	//			int id=t.getId();
	//			insurance=	session.get(Drog.class, id);
	//
	//			if(	insurance!=null){
	//				insurance.setPrice(t.getPrice());
	//				session.update(insurance);
	//				is_exist=true;
	//				System.out.println("successfully update"); 
	//
	//			}
	//			tx.commit();
	//		}catch (HibernateException e) {
	//			if (tx!=null) tx.rollback();
	//			e.printStackTrace(); 
	//		}finally {
	//			session.close(); 
	//		}
	//		return is_exist;
	//	}
	//
	//
	//	//update the inventory for a  Drug
	//	public boolean updateInventoryDrug(Integer DrugID,int quantity) throws SQLException{
	//
	//		boolean is_exist=false;
	//		Session session = factory.openSession();
	//		Transaction tx = null;
	//		Drog insurance;
	//		try{
	//			tx = session.beginTransaction();
	//			insurance=new Drog();
	//
	//			insurance=	session.get(Drog.class, DrugID);
	//
	//			if(	insurance!=null){
	//				if(insurance.getInventory()-quantity>=0){
	//					insurance.setInventory(insurance.getInventory()-quantity);
	//					session.update(insurance);
	//					is_exist=true;
	//					System.out.println("successfully update"); 
	//				}
	//			}
	//			tx.commit();
	//		}catch (HibernateException e) {
	//			if (tx!=null) tx.rollback();
	//			e.printStackTrace(); 
	//		}finally {
	//			session.close(); 
	//		}
	//		return is_exist;
	//	}
	//
	//
	//
	//

}
