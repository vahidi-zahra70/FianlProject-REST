package JavaClass;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import HibernateManager.ContactManager;


import java.util.List;
import java.util.Set;




public class Base {
	public static String encrypt(String source) {
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
	static Logger log = Logger.getLogger( 
			Base.class.getName());
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Configuration	cfg=new Configuration();  
		cfg.configure("hibernate.cfg.xml");
		SessionFactory	factory=cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();


		//		Feature f1=new Feature("See contact");
		//		Feature f2=new Feature("Add contact");
		//		Feature f3=new Feature("Delete contact");
		//		Feature f4=new Feature("Update contact");
		//		Feature f5=new Feature("Search contact");
		//		Feature f6=new Feature("Delete User");
		//		Feature f7=new Feature("Update User");
		//		Feature f9=new Feature("See event");
		//		
		//		Set<Feature> featuresRole1=new HashSet<Feature>();
		//		featuresRole1.add(f1);
		//		featuresRole1.add(f2);
		//		featuresRole1.add(f3);
		//		featuresRole1.add(f4);
		//		featuresRole1.add(f5);
		//		featuresRole1.add(f6);
		//		featuresRole1.add(f7);
		//		featuresRole1.add(f9);
		//		Role r1=new Role(1,"seniormanager",featuresRole1);
		//
		//		Set<Feature> featuresRole2=new HashSet<Feature>();
		//		featuresRole2.add(f1);
		//		featuresRole2.add(f2);
		//		featuresRole2.add(f3);
		//		featuresRole2.add(f4);
		//		featuresRole2.add(f5);
		//		featuresRole2.add(f9);
		//		Role r2=new Role(2,"manager",featuresRole2);
		//		
		//		Set<Feature> featuresRole3=new HashSet<Feature>();
		//		featuresRole3.add(f1);
		//		featuresRole3.add(f2);
		//		featuresRole3.add(f5);
		//		Role r3=new Role(3,"member",featuresRole3);
		//
		//		Set<Feature> featuresRole4=new HashSet<Feature>();
		//		featuresRole4.add(f1);
		//		Role r4=new Role(4,"guest",featuresRole4);
		//
		//		
		//
		//		User u=new User("n.nasher",encrypt("12345"),r1);
		//		User u1=new User("z.vahidi",encrypt("12346"),r2);
		//		User u2=new User("a.aslani",encrypt("12347"),r3);
		//		User u3=new User("m.ansari",encrypt("12348"),r3);
		//		User u4=new User("f.amiri",encrypt("12349"),r3);
		//		User u5=new User("guest",r4);
		//		session.save(u);
		//		session.save(u1);
		//		session.save(u2);
		//		session.save(u3);
		//		session.save(u4);
		//		session.save(u5);
		//		Contact c1=new Contact("contact1","family1",2177075502L,9194895293L,"contact1@yahoo.com");
		//		Contact c2=new Contact("contact2","family2",2188075502L,9194895693L,"contact2@yahoo.com");
		//		session.save(c1);
		//		session.save(c2);
		//		Date date=new Date();
		//		Event ee=new Event(u1,"kkkk", date);
		//		session.save(ee);
		//		tx.commit();
		//		ContactManager TT=new ContactManager();
		//		TT.searchContact("ali", 888);
		//		ArrayList<Event> drogs=new ArrayList<Event>();
		//		String hql = "FROM Event";
		//		Query query = session.createQuery(hql);
		//		List results =  query.list();
		//		Iterator itr = results.iterator();
		//		while (itr.hasNext()) {
		//			Event emp = (Event) itr.next();
		//			drogs.add(emp);
		//			System.out.println(emp.getDescription());
		//		}
		//tx.commit();


		User user=session.get(User.class,"z.vahidi");
		if(user!=null && user.getPassword().equals(encrypt("12346"))){
			System.out.println("oooooooooooo");
			Role role=user.getRole();
			System.out.println(role.getId());
			tx.commit();

		}




	}

}
