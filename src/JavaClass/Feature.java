package JavaClass;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="feature")
public class Feature {
	@Id
	@Column(name="namefeature" ,updatable = false, nullable = false)
	private String namefeature;

	public String getNamefeature() {
		return namefeature;
	}
	public void setNamefeature(String namefeature) {
		this.namefeature = namefeature;
	}

	public Feature(){

	}
	public Feature(String namefeature ){
		this.namefeature=namefeature;

	}
	
	public int hashCode(){
		int result = 0; 
		result = 31*result + (namefeature !=null ? namefeature.hashCode() : 0);
		return result;
	} 
	
	public boolean equals(Object other){ 
		if(this == other) return true;
		if(other == null || (this.getClass() != other.getClass())){
			return false;
			}
		Feature guest = (Feature) other; 
		return (this.namefeature != null && namefeature.equals(guest.namefeature));
		}


}
