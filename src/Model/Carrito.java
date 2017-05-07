package Model;
/*
 * Clase encargada de contener los productos una vez que el usuario los ha añadido al carrito.
 * @author Alejandro
 */
import java.util.*;

public class Carrito{
  
    private List<ElementoCarrito> productos = new ArrayList<>();

    public void put(ElementoCarrito elementoCarrito) {
        if(productos.contains(elementoCarrito))
        {
            ElementoCarrito antiguo = productos.get(productos.indexOf(elementoCarrito));
            antiguo.addUnidades(elementoCarrito.getUnidades());
        }
        else
        {
            productos.add(elementoCarrito);
        }
    }

    public List<ElementoCarrito> getProductos() {
        List<ElementoCarrito> copia = new ArrayList<>();
        copia.addAll(productos);
        return copia;
    }

    public void reset() {
        productos.clear();
    }

    public boolean isVacio() {
        return productos.isEmpty();
    }
}