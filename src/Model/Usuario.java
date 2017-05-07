package Model;

/*
 * Clase encargada de contener los atributos comunes de admin y comprador.
 * @author Alejandro
 */
import Permiso.catalogo.PermisoCatalogo;
import Permiso.PermisoGeneral;

import java.util.HashSet;
import java.util.Set;

public abstract class Usuario {
	
	 	private String nombre;
	    private String pass;
	    private Set<PermisoCatalogo> permisosCatalogo;
	    private Set<PermisoGeneral> permisosGenerales;

	private String nick;
	    private Carrito carrito;
	private Factura factura;

	public Usuario(String nombre, String nick, String pass) {
	    	
	        this.nombre = nombre;
	        this.pass = pass;
	        this.nick = nick;
	        this.permisosCatalogo = new HashSet<>();//no permite elementos duplicados(producto no cambia nombre)
		this.permisosGenerales = new HashSet<>();
		this.carrito = new Carrito();
	    }

	    public String getNombre() {
	        return nombre;
	    }
	    
	    public String getPass() {
	        return pass;
	    }

	    public Set<PermisoGeneral> getPermisos() {
	        return permisosGenerales;
	    }

	    public String getNick() {
	        return nick;
	    }

	@Override
	public String toString() {
		return nombre + " | " + nick + " | " + "*****";
	}

	public void addProductoACarrito(Producto producto, int uds) {
		carrito.put(new ElementoCarrito(producto,uds));
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public boolean hasFactura() {
		return factura != null;
	}

	public Factura getFactura() {
		return factura;
	}

	public void vaciarCarrito() {
		carrito.reset();
	}

	public void addPermisoGeneral(PermisoGeneral permisoGeneral)
	{
		this.permisosGenerales.add(permisoGeneral);
	}

	public void addPermisoCatalogo(PermisoCatalogo permisoCatalogo)
	{
		this.permisosCatalogo.add(permisoCatalogo);
	}

	public Set<PermisoCatalogo> getPermisosCatalogo() {
		return permisosCatalogo;
	}
}
