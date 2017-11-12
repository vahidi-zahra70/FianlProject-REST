package JavaClass;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name="role")
public class Role {
	
	@Id
	@Column(name="id" ,updatable = false, nullable = false)	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="position")
	private String position;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	//@NotFound( action=NotFoundAction.IGNORE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="role_feature", joinColumns={ @JoinColumn(name="role_id")},
	inverseJoinColumns={ @JoinColumn(name="feature_id")})
	
	private Set<Feature> features=new HashSet<Feature>();
	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	public Role(){
		
	}
	public Role(int id,String position){
		this.id=id;
		this.position=position;
		
	}
	public Role(int id,String position,Set<Feature> features){
		this.id=id;
		this.position=position;
		this.features=features;
	}

}
