package Model;
/*
 * Clase encargada de contener el int de numero de unidades del producto comprado.
 * @author Alejandro
 */

public class ElementoCarrito {

    private Producto producto;
    private int unidades;


    public ElementoCarrito(Producto producto, int unidades) {
        this.producto = producto;
        this.unidades = unidades;
    }

    public void setUnidades(int unidades)
    {
        this.unidades = unidades;
    }

    public void addUnidades(int unidades)
    {
        this.unidades+=unidades;
    }

    public int getUnidades() {
        return unidades;
    }

    public Producto getProducto() {
        return producto;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj.getClass() == ElementoCarrito.class)
        {
            ElementoCarrito el = (ElementoCarrito) obj;
            return el.getProducto().equals(this.getProducto());
        }
        return false;
    }
}
