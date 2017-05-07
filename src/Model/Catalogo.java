
package Model;
/*
 * Clase encargada de contener los productos.
 * @author Alejandro
 */
import java.util.ArrayList;
import java.util.List;

public class Catalogo {

    public void addListener(Listener listener)
    {
        listeners.add(listener);
    }

    public void guardarProducto(Producto producto) {
        repositorioProductos.replace(producto);
        notificarCambios();
    }

    public void eliminarProducto(Producto productoSeleccionado) {
        repositorioProductos.remove(productoSeleccionado);
        notificarCambios();
    }

    public interface Listener
    {
        void onProductosCargados(List<Producto> productos);
    }

    RepositorioProductos repositorioProductos;

    private List<Listener> listeners;

    public Catalogo(RepositorioProductos repositorioProductos)
    {
        this.repositorioProductos = repositorioProductos;
        this.listeners = new ArrayList<>();
    }

    private void notificarCambios()
    {
        List<Producto> productos = repositorioProductos.getProductos();
        for(Listener listener : listeners)
        {
            listener.onProductosCargados(productos);
        }
    }

    public void solicitaProductos()
    {
       notificarCambios();
    }

}
