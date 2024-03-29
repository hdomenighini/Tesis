package marca;

import java.util.List;

import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;

import org.apache.isis.applib.AbstractFactoryAndRepository;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;

//import autos.Auto;

@Named("Marca")
public class MarcaServicio extends AbstractFactoryAndRepository {

	// {{ Marcas
	@MemberOrder(sequence = "1")
	// Carga de Marca
	public Marca CargarMarca(@Named("Marca") String marca) {
		final boolean activo = true;
		final String ownedBy = currentUserName();
		return laMarca(marca, activo, ownedBy);
	}

	// }}

	// {{
	@Hidden
	// for use by fixtures
	public Marca laMarca(String marca, boolean activo, String userName) {
		final Marca aux = newTransientInstance(Marca.class);
		aux.setNombre(marca);
		aux.setActivo(activo);
		aux.setOwnedBy(userName);

		persist(aux);
		return aux;
	}

	// }}
	@MemberOrder(sequence = "2")
	// Listado de Marca
	public List<Marca> ListadoMarcas() {
		return allMatches(Marca.class, new Predicate<Marca>() {
			@Override
			public boolean apply(final Marca t) {
				return t.getActivo();
			}
		});
	}

	// }}

	/*
	 * // {{ Listado de Autos filtrado por Marcas
	 * 
	 * public List<Auto> listadoAutosPorMarca(final Marca lista) { return
	 * allMatches(Auto.class, new Filter<Auto>() {
	 * 
	 * @Override public boolean accept(Auto t){ return
	 * lista.equals(t.getMarca())&& t.getActivo(); } }); } // }}
	 * 
	 * // {{
	 * 
	 * @Hidden public List<Marca> autoComplete(final String marcas) { return
	 * allMatches(Marca.class, new Filter<Marca>() {
	 * 
	 * @Override public boolean accept(final Marca t) { return t.getActivo() &&
	 * t.getNombre().contains(marcas) ; } }); } // }}
	 */

	// {{ Helpers
	protected boolean ownedByCurrentUser(final Marca t) {
		return Objects.equal(t.getOwnedBy(), currentUserName());
	}

	protected String currentUserName() {
		return getContainer().getUser().getName();
	}
	// }}
}
