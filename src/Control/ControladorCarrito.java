package Control;
/*
 * Clase encargada del control del carrito.
 * @author Alejandro
 */
import Model.Tienda;
import Model.Usuario;
import View.VentanaCarrito;


public class ControladorCarrito {

    private final Tienda tienda;
    private final VentanaCarrito ventanaCarrito;

    public ControladorCarrito(Tienda tienda, VentanaCarrito ventanaCarrito)
    {
        this.tienda = tienda;
        this.ventanaCarrito = ventanaCarrito;
    }

    public void onRealizarCompraClick(Usuario usuario)
    {
        tienda.facturarCompra(usuario);
    }
}
