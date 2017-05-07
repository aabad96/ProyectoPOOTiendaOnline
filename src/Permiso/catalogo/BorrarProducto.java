package Permiso.catalogo;
public class BorrarProducto implements PermisoCatalogo {

	@Override
	public void procesa(VentanaPermisosCatalogo ventanaPermisos) {
		ventanaPermisos.activarBorrarProducto();
	}

}
