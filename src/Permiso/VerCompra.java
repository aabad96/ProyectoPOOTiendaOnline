package Permiso;
/*
 * Clase encargada de verCarrito.
 * @author Alejandro
 */
public class VerCompra implements PermisoGeneral {

	@Override
	public void procesa(VentanaPermisosGeneral ventanaPermisos) {
		ventanaPermisos.activarVerCompra();
	}

	
}
