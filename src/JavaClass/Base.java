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


//		User u=new User("n.nasher",encrypt("12345"),1);
//		User u1=new User("z.vahidi",encrypt("12346"),2);
//		User u2=new User("a.aslani",encrypt("12347"),3);
//		User u3=new User("m.ansari",encrypt("12348"),3);
//		User u4=new User("f.amiri",encrypt("12349"),3);
//		User u5=new User("guest",4);
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
		
		ContactManager TT=new ContactManager();
		TT.searchContact("ali", 888);
		tx.commit();
		
		
//		log.info("Hello2");
//		log.info("Hello3");
		
		
		
//		User user=session.get(User.class,"z.vahidi");
//		if(user!=null && user.getPassword().equals(encrypt("12347"))){
//		System.out.println("oooooooooooo");
//		
//		}

		
		

	}

}
