package Control;
/*
 * Clase encargada de actualizar, eliminar y comprar productos.
 * @author Alejandro
 */
import Model.Catalogo;
import Model.Producto;
import Model.Tienda;
import Model.Usuario;
import View.VentanaCatalogo;

public class ControladorCatalogo {
    private final Usuario usuario;
    private VentanaCatalogo ventanaCatalogo;
    private Catalogo catalogo;
    private Tienda tienda;

    public ControladorCatalogo(Usuario usuario,VentanaCatalogo vista, Catalogo catalogo,Tienda tienda)
    {
        this.usuario = usuario;
        this.catalogo = catalogo;
        this.tienda = tienda;
        this.ventanaCatalogo = vista;
    }

    public void solicitaActualizacionProductosCatalogo()
    {
        catalogo.solicitaProductos();
    }


    public void onEliminarProductoClick(Producto productoSeleccionado) {
        catalogo.eliminarProducto(productoSeleccionado);
    }

    public void onCompraProducto(Producto productoSeleccionado, int unidades) {
        tienda.compraProducto(usuario,productoSeleccionado,unidades);
    }
}
