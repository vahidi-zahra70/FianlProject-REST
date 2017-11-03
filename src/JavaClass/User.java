
package JavaClass;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="user")
public  class User {


	@Id
	@Column(name="username" ,updatable = false, nullable = false)	
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="password")
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	@ManyToOne(cascade=CascadeType.ALL)
	private Role role;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	@OneToMany(mappedBy = "user") 
	private Set<Event> events = new HashSet<Event>(); 
	public Set<Event> getEvents() {
		return events;
	}
	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public User(String username,String password, Role role){

		this.username=username;
		this.password=password;
		this.role=role;	
	}


	public User(String username, Role role){

		this.username=username;
		this.role=role;	
	}
	public User(String username){

		this.username=username;

	}
	public User(){

	}



}
