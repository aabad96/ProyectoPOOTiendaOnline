package Model;
/*
 * Clase encargada de contener los permisos de un comprador.
 * @author Alejandro
 */
import Permiso.catalogo.AnadirACarrito;
import Permiso.VerCarrito;
import Permiso.VerCatalogo;
import Permiso.VerCompra;

public class Comprador extends Usuario {

	public Comprador(String nombre,String pass,String nick) {
		super(nombre,nick,pass);
		addPermisoCatalogo(new AnadirACarrito());
		addPermisoGeneral(new VerCarrito());
		addPermisoGeneral(new VerCatalogo());
		addPermisoGeneral(new VerCompra());
	}

}
