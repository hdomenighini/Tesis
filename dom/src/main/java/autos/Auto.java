package autos;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.AutoComplete;

import org.apache.isis.applib.annotation.DescribedAs;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;

import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.util.TitleBuffer;

import org.apache.isis.applib.annotation.Named;

import categoria.Categoria;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;

import marca.Marca;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "listado_autos", language = "JDOQL", value = "SELECT FROM autos.Auto WHERE activo==true "),
		@javax.jdo.annotations.Query(name = "todo_complete", language = "JDOQL", value = "SELECT "
				+ "FROM dom.autos.Auto "
				+ "WHERE ownedBy == :ownedBy "
				+ "&& activo == true"),
		@javax.jdo.annotations.Query(name = "findAutos", language = "JDOQL", value = "SELECT FROM dom.autos.Auto WHERE patente == :patente ") })
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "VERSION")
@ObjectType("AUTO")
@AutoComplete(repository = AutoServicio.class, action = "autoComplete")
@Bookmarkable
public class Auto {

	public static enum TipoCombustible {
		NAFTA, DIESEL;
	}

	public static enum Estado {
		ALQUILADO, LIBRE, AVERIADO;
	}

	public static enum Seguro {
		LA_SEGUNDA, MAPFRE, LA_PATRONAL, LA_CAJA, ZURICH;
	}

	@Named("Dominio")
	// {{ Identification on the UI
	public String title() {
		final TitleBuffer buf = new TitleBuffer();
		buf.append(getPatente());
		return buf.toString();
	}

	// }}

	// {{ {{ OwnedBy (property)
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

	// {{ Patente
	private String patente;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@DescribedAs("El dominio del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	// words, spaces and selected punctuation
	@MemberOrder(sequence = "1")
	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	// }}

	// {{ Marca
	@Persistent
	private Marca marca;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@DescribedAs("La marca del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "2")
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(final Marca marca) {
		this.marca = marca;
	}

	// }}

	// {{ Modelo
	private String modelo;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@DescribedAs("El modelo del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "3")
	public String getModelo() {
		return modelo;
	}

	public void setModelo(final String modelo) {
		this.modelo = modelo;
	}

	// }}

	// {{ Año
	private int ano;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@DescribedAs("El año del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "4")
	public int getAno() {
		return ano;
	}

	public void setAno(final int ano) {
		this.ano = ano;
	}

	// }}

	// {{ Categoria
	@Persistent
	private Categoria categoria;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@DescribedAs("La categoria del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "5")
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(final Categoria categoria) {
		this.categoria = categoria;
	}

	// }}

	// {{ Color
	private String color;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@DescribedAs("El color del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "5")
	public String getColor() {
		return color;
	}

	public void setColor(final String color) {
		this.color = color;
	}

	// }}

	// {{ Kilometraje
	private int kms;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@DescribedAs("El kilometraje del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "6")
	public int getKilometraje() {
		return kms;
	}

	public void setKilometraje(final int kms) {
		this.kms = kms;
	}

	// }}

	// {{ Capacidad del Baul
	private int baul;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@DescribedAs("La capacidad del baul del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "7")
	public int getCapacidadBaul() {
		return baul;
	}

	public void setCapacidadBaul(final int baul) {
		this.baul = baul;
	}

	// }}

	// {{ Tipo de Combustible
	private TipoCombustible combustible;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@DescribedAs("El tipo de combustible del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "8")
	public TipoCombustible getTipoCombustible() {
		return combustible;
	}

	public void setTipoCombustible(TipoCombustible combustible) {
		this.combustible = combustible;
	}

	// }}

	/*
	 * // {{ Estado de Alquiler private Estado estado;
	 * 
	 * @javax.jdo.annotations.Column(allowsNull="false")
	 * 
	 * @DescribedAs("Señala el estado actual del vehiculo.")
	 * 
	 * @RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	 * 
	 * @MemberOrder(sequence="9") public Estado getEstado(){ return estado; }
	 * public void setEstado(Estado estado) { this.estado=estado; } // }}
	 */

	// {{ Fecha de Compra del vehiculo
	private Date fechaCompra;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@DescribedAs("Señala la fecha de compra del vehiculo.")
	@MemberOrder(sequence = "9")
	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(final Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public void clearFechaCompra() {
		setFechaCompra(null);
	}

	// }}

	// {{ Seguro del vehiculo
	private Seguro seguro;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@DescribedAs("Señala el seguro del vehiculo.")
	@RegEx(validation = "\\w[@&:\\-\\,\\.\\+ \\w]*")
	@MemberOrder(sequence = "10")
	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	// }}

	// {{ Campo Activo
	private boolean activo;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@Hidden
	@DescribedAs("Activo")
	@MemberOrder(sequence = "12")
	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	// }}

	/*// {{ Remove
	public void remove() {
		setActivo(false);
	}*/

	// }}

	// {{ Predicates

	public static class Predicates {

		public static Predicate<Auto> thoseOwnedBy(final String currentUser) {
			return new Predicate<Auto>() {
				@Override
				public boolean apply(final Auto auto) {
					return Objects.equal(auto.getOwnedBy(), currentUser);

				}
			};
		}

		public static Predicate<Auto> thoseWithSimilarDescription(
				final String patente) {
			return new Predicate<Auto>() {
				@Override
				public boolean apply(final Auto t) {
					return t.getPatente().contains(patente);
				}
			};
		}
	}

	// }}
	
	// {{ Inyectar Servicio
	private AutoServicio autoServicio;

	public void injectarServicio(final AutoServicio autoServicio) {

		this.autoServicio = autoServicio;
	}

	// }}
	
public List<Auto> sonActivos(){
   		
   		return autoServicio.listadoActivos();
   	}   	
   	//}}
   	   	
   	//{{ Remove
   	@Named("Borrar")
   	@Bulk
   	public List<Auto> remove(){
   		setActivo(false);
   		
   		return autoServicio.listadoActivos();
   	}   	
   	//}}

	// {{ injected: DomainObjectContainer
	private DomainObjectContainer container;

	public void injectDomainObjectContainer(
			final DomainObjectContainer container) {
		this.container = container;
	}
	// }}
}
