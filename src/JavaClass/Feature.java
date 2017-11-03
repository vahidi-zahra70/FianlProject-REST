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


}
