package Control;
/*
 * Clase encargada de controlar las funciones de verCarrito,verCatalogo, y verCompra.
 * @author Alejandro
 */
import Model.RepositorioProductos;
import View.VentanaPrincipal;

public class ControladorPrincipal {

    private VentanaPrincipal ventanaPrincipal;
    private RepositorioProductos productos;
    public ControladorPrincipal(VentanaPrincipal ventanaPrincipal,RepositorioProductos productos) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.productos = productos;
    }

    public void onVerCarritoClick() {
        ventanaPrincipal.muestraVentanaCarrito();
        
    }

    public void onVerCatalogoClick() {
        ventanaPrincipal.addCatalogoPanel();
    }

    public void onVerCompraClick() {
        ventanaPrincipal.muestraVentanaCompra();
    }
}
