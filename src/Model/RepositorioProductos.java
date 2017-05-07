package Model;
/*
 * Clase encargada de coger los productos desde un txt y guardarlos en "cache".
 * @author Alejandro
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioProductos {

	private File fuenteProductos;
	private List<Producto> cache;

	
public RepositorioProductos(File fuenteProductos) {
		this.fuenteProductos = fuenteProductos;
		this.cache = new ArrayList<>();
	}


public List<Producto> getProductos(){
	if(!cache.isEmpty())
	{
		return cache;
	}
	List<Producto> productos = new ArrayList<>();
	
	try
	{
		BufferedReader flujoLectura = new BufferedReader(new FileReader(fuenteProductos));
		String linea = flujoLectura.readLine();
		
		while(linea!=null) {
			String[] productoParseado = linea.split("\\|");
			if(productoParseado.length > 4) {
				String nombre = productoParseado[0];
				String descripcion = productoParseado[1];
				try
				{
					double precio = Double.parseDouble(productoParseado[2]);
					int stock = Integer.parseInt(productoParseado[3]);
					Producto nuevoProducto = new Producto(nombre, descripcion,precio,stock);
					productos.add(nuevoProducto);
					cache.add(nuevoProducto);
				}
				catch(NumberFormatException e)
				{
					System.err.println("Error durante la conversión de este producto.");
				}
				linea = flujoLectura.readLine();

			} 

		}	flujoLectura.close();
		
	}catch(IOException e){
		System.err.println("Error procesando el fichero.");
		
	}
	return productos;
}

	public void replace(Producto producto) {
		List<Producto> productos = getProductos();
		productos.remove(producto);
		productos.add(producto);
	}

	public void remove(Producto producto) {
		List<Producto> productos = getProductos();
		productos.remove(producto);
	}
}
