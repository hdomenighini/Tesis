package localidad;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import com.google.common.base.Predicate;

import com.google.common.base.Objects;

@Named("Localidad")
public class LocalidadServicio extends AbstractFactoryAndRepository {

	// {{ Carga de localidad
	@MemberOrder(sequence = "1")
	public Localidad CargaDeLocalidad(@Named("Localidad") String localidad) {
		final boolean activo = true;
		final String ownedBy = currentUserName();
		return laLocalidad(localidad, activo, ownedBy);
	}

	// }}

	// {{
	@Hidden
	// for use by fixtures
	public Localidad laLocalidad(String localidad, boolean activo,
			String userName) {
		final Localidad aux = newTransientInstance(Localidad.class);
		aux.setNombreLocalidad(localidad);
		aux.setActivo(activo);
		aux.setOwnedBy(userName);
		persist(aux);
		return aux;
	}

	// {{ Listado de Localidades
	@ActionSemantics(Of.SAFE)
	@MemberOrder(sequence = "2")
	public List<Localidad> ListaDeLocalidades() {
		List<Localidad> items = doComplete();
		if (items.isEmpty()) {
			getContainer().informUser("No hay Ciudades activos :-(");
		}
		return items;
	}

	protected List<Localidad> doComplete() {
		return allMatches(Localidad.class, new Predicate<Localidad>() {
			@Override
			public boolean apply(final Localidad t) {
				return t.getActivo();
			}
		});
	}

	// }}

	// {{ Helpers
	protected String currentUserName() {
		return getContainer().getUser().getName();
	}

	protected boolean ownedByCurrentUser(final Localidad t) {
		return Objects.equal(t.getOwnedBy(), currentUserName());
	}
	// }}

}
