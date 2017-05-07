package Model;
/*
 * Clase encargada de contener lo que se ha generado una vez comprados los productos.
 * @author Alejandro
 */
import java.util.List;

public class Factura {


    private final double totalCompra;
    private final List<ElementoCarrito> productos;

    public Factura(List<ElementoCarrito> productos, double totalCompra) {
        this.productos = productos;
        this.totalCompra = totalCompra;
    }

    public double getTotal() {
        return totalCompra;
    }

    public List<ElementoCarrito> getProductos() {
        return productos;
    }
}