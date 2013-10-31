package localidad;


import javax.jdo.annotations.IdentityType;


import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.util.TitleBuffer;



@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Queries({
@javax.jdo.annotations.Query(name="listado_localidades", language="JDQL",
							value="SELECT  FROM dom.localidad.Localidad WHERE  ACTIVO==:true")})
@ObjectType("LOCALIDAD")



public class Localidad {

	public String title(){
	final TitleBuffer buf = new TitleBuffer();
	        buf.append(getNombreLocalidad()
	        		);        
	        return buf.toString();
	}	
	
	// {{ Nombre de localidad
	private String nombreLocalidad;
	@javax.jdo.annotations.Column(allowsNull = "false")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence="1")
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}
	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}
	// }}
	
	// {{ Campo Activo
	private boolean activo=true;
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Hidden
	@DescribedAs("Activo")
	@MemberOrder(sequence = "2")
   	public boolean getActivo() {
   		return activo; 
   	}    	
	public void setActivo(boolean activo){ 
		this.activo=activo; 	
	}	 
	public void remove() { 
		setActivo(false); 	 
	}
	// }}
	
	// {{ OwnedBy (property)	
	private String ownedBy;
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Hidden 
	public String getOwnedBy() {
	    return ownedBy;	
	}
	public void setOwnedBy(final String ownedBy){
	    this.ownedBy = ownedBy;	
	}	
	// }}
		
}
