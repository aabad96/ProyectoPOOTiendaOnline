package Permiso;
/*
 * Clase encargada de mostrar Carrito.
 * @author Alejandro
 */
public class VerCarrito implements PermisoGeneral {

	@Override
	public void procesa(VentanaPermisosGeneral ventanaPermisos) {
		ventanaPermisos.activarVerCarrito();
	}

	

}
