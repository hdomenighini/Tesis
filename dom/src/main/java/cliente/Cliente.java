package cliente;



import javax.jdo.annotations.IdentityType;

import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.util.TitleBuffer;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;



@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "listado_cliente", language = "JDQL", value = "SELECT * FROM dom.cliente.Cliente WHERE activo== :true ") })
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "VERSION")
@ObjectType("Cliente")
@AutoComplete(repository = ClienteServicio.class, action = "autoComplete")
@Bookmarkable
public class Cliente {

	public static enum TipoId {
		CUIL, CUIT;
	}	
	
	@Named("Cliente")
	// {{ Identification on the UI
	public String title() {
		final TitleBuffer buf = new TitleBuffer();
		buf.append(getNumeroIdent());
		return buf.toString();
	}
	// }}

	// {{ OwnedBy (property)
	private String ownedBy;
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Hidden
	public String getOwnedBy() {
		return ownedBy;
	}
	public void setOwnedBy(final String ownedBy) {
		this.ownedBy = ownedBy;
	}
	// }}

	// {{ Nombre
	private String nombre;
	@javax.jdo.annotations.Column(allowsNull = "false")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "1")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	// }}

	// {{ Apellido
	private String apellido;
	@javax.jdo.annotations.Column(allowsNull = "false")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "2")
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	// }}

	// {{ Tipo de Identificacion Tributaria
	private TipoId tipo;
	@javax.jdo.annotations.Column(allowsNull = "false")
	@DescribedAs("Señala el tipo de documento")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "3")
	public TipoId getTipoId() {
		return tipo;
	}
	public void setTipoId(TipoId tipo) {
		this.tipo = tipo;
	}
	// }}

	// {{ Numero de Identificacion Tributaria
	private String numeroIdent;
	@javax.jdo.annotations.Column(allowsNull = "false")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "4")
	public String getNumeroIdent() {
		return numeroIdent;
	}
	public void setNumeroIdent(String numeroId) {
		this.numeroIdent = numeroId;
	}
	// }}
	
	// {{ Numero de telefono
	private int numeroTel;
	@javax.jdo.annotations.Column(allowsNull = "false")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "5")
	public int getNumeroTel() {
		return numeroTel;
	}
	public void setNumeroTel(int numeroTel) {
		this.numeroTel = numeroTel;
	}
	// }}	
	
	// {{ Correo electronico
	private String mail;
	@javax.jdo.annotations.Column(allowsNull = "false")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "6")
	public String getEmail() {
		return mail;
	}
	public void setEmail(String mail) {
		this.mail = mail;
	}
	// }}	
	
		
	// {{ Activo
	private boolean activo;
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Hidden
	@MemberOrder(sequence = "9")
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	// }}

	// {{ Remove (action)
	public void remove() {
		setActivo(false);
	}
	// }}

	// {{ Predicates

		public static class Predicates {

			public static Predicate<Cliente> thoseOwnedBy(final String currentUser) {
				return new Predicate<Cliente>() {
					@Override
					public boolean apply(final Cliente cliente) {
						return Objects.equal(cliente.getOwnedBy(), currentUser);

					}
				};
			}

			public static Predicate<Cliente> thoseWithSimilarDescription(
					final String numeroIdent) {
				return new Predicate<Cliente>() {
					@Override
					public boolean apply(final Cliente t) {
						return t.getNumeroIdent().contains(numeroIdent);
					}
				};
			}
		}

		// }}
	
	// {{ injected: DomainObjectContainer
	private DomainObjectContainer container;

	protected DomainObjectContainer getContainer() {
		return container;
	}
	public void setDomainObjectContainer(final DomainObjectContainer container) {
		this.container = container;
	}
	// }}

}