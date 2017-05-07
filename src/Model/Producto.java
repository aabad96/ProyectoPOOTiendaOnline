package Model;
/*
 * Clase encargada de gestionar los productos de nuestra tienda
 * @author Alejandro
 */
public class Producto {

    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;


    public Producto(String nombre, String descripcion, double precio, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }



    public String getNombre() {
        return nombre;
    }
    
    public String getdescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }
    public String toString(){
    	return nombre + " | " + descripcion + " | " + precio + " | " + stock;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() == Producto.class)
        {
            Producto p = (Producto) obj;
            return this.nombre.equals(p.getNombre());
        }
        return false;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}