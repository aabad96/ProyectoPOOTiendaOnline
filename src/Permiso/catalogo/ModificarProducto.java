package Permiso.catalogo;


public class ModificarProducto implements PermisoCatalogo {

	@Override
	public void procesa(VentanaPermisosCatalogo ventanaPermisos) {
		ventanaPermisos.activarModificacionProductos();
	}

}
