package Permiso.catalogo;
/*
 * Clase encargada de añadirACarrito.
 * @author Alejandro
 */
public class AnadirACarrito implements PermisoCatalogo {

    @Override
    public void procesa(VentanaPermisosCatalogo ventanaPermisos) {
        ventanaPermisos.activarAnadirProductoCarrito();
    }

    
}
