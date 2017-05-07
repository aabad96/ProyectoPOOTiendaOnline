package Permiso.catalogo;

public class AnadirNuevoProducto implements PermisoCatalogo {

    @Override
    public void procesa(VentanaPermisosCatalogo ventanaPermisos) {
        ventanaPermisos.activarAnadirProductos();
    }

   
}
