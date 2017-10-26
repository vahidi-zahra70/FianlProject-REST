package HibernateManager;
import java.sql.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBManager {
	
	Configuration cfg=null;
	SessionFactory factory=null;
	
	public DBManager() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	
     		cfg= new Configuration(); 
			cfg.configure("hibernate.cfg.xml");
			factory=cfg.buildSessionFactory();
	}
	
	public Configuration getconn() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	return cfg;
	
	}
	
	public SessionFactory  getfactory() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return factory;
		
		}
}

