package org.model;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import org.springframework.data.neo4j.annotation.GraphId;
/**
 * provides shared behaviour and attributes to derive entity classes from
 * @author ben
 * @since 29.06.2013
 */
@MappedSuperclass
public class AbstractEntity  {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GraphId 
	Long id;
	/**
	 * Returns the identifier of the entity.
	 * 
	 * @return the id
	 */
	public Long getId(){
		return id;
	}
	/* 
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
			return false;
		}

		AbstractEntity that = (AbstractEntity) obj;

		return this.id.equals(that.getId());
	}
	/* 
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}
}
