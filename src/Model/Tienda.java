package Model;
/*
 * Clase encargada de las funciones de tienda (comprar/facturar).
 * @author Alejandro
 */
import Model.Carrito;
import Model.Producto;
import Model.Factura;
import Model.Usuario;
import View.VentanaCarrito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tienda {

    public void addListener(CarritoListener listener) {
        carritoListeners.add(listener);
    }

    public void addListener(ResultadoCompraListener listener) {
        compraListeners.add(listener);
    }

    public interface CarritoListener
    {
        void onAnadidoACarritoConExito(Producto producto, int unidades);

        void onError(String error);
    }

    private List<CarritoListener> carritoListeners = new ArrayList<>();
    private List<ResultadoCompraListener> compraListeners = new ArrayList<>();

    public void compraProducto(Usuario usuario, Producto producto, int uds)
    {
        if(producto.getStock() >= uds)
        {
            producto.setStock(producto.getStock() - uds);
            usuario.addProductoACarrito(producto,uds);
            notificaCarritoExito(producto,uds);
        }
        else
        {
            notificaErrorCarrito("No hay stock suficiente del producto solicitado");
        }
    }

    public void facturarCompra(Usuario usuario)
    {
        Carrito carrito = usuario.getCarrito();
        if(carrito.isVacio())
        {
            notificaErrorCompra("El carrito está vacio");
        }
        else
        {
            double totalCompra = 0;
            List<ElementoCarrito> elementos = carrito.getProductos();
            Producto productoActual;
            for(ElementoCarrito elementoCarrito : elementos)
            {
                productoActual = elementoCarrito.getProducto();
                int uds = elementoCarrito.getUnidades();
                totalCompra += productoActual.getPrecio() * uds;
            }
            usuario.setFactura(new Factura(elementos,totalCompra));
            usuario.vaciarCarrito();
            notificaExitoCompra();
        }
    }

    private void notificaErrorCompra(String error) {
        for(ResultadoCompraListener listener : compraListeners)
        {
            listener.onCompraError(error);
        }
    }

    private void notificaExitoCompra() {
        for(ResultadoCompraListener listener : compraListeners)
        {
            listener.onCompraExito("¡Compra realizada con éxito!");
        }
    }

    private void notificaErrorCarrito(String s) {
        for(CarritoListener listener : carritoListeners)
        {
            listener.onError(s);
        }
    }

    private void notificaCarritoExito(Producto producto, int uds) {
        for(CarritoListener listener : carritoListeners)
        {
            listener.onAnadidoACarritoConExito(producto,uds);
        }
    }

    public interface ResultadoCompraListener {
        void onCompraExito(String s);

        void onCompraError(String error);
    }
}
