package Model;
/*
 * Clase encargada de contener los permisos de un Administrador.
 * @author Alejandro
 */
import Permiso.VerCatalogo;
import Permiso.catalogo.AnadirNuevoProducto;
import Permiso.catalogo.BorrarProducto;
import Permiso.catalogo.ModificarProducto;

public class Admin extends Usuario {
	
	public Admin(String nombre, String pass, String nick) {
		super(nombre,nick,pass);
		addPermisoCatalogo(new AnadirNuevoProducto());
		addPermisoCatalogo(new BorrarProducto());
		addPermisoCatalogo(new ModificarProducto());
		addPermisoGeneral(new VerCatalogo());
	}

}
