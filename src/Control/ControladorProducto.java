package Control;
/*
 * Clase encargada de controlar las funciones de los productos.
 * @author Alejandro
 */
import Model.Catalogo;
import Model.Producto;
import View.VentanaProducto;

public class ControladorProducto {

	private VentanaProducto ventanaCatalogo;
	private Catalogo catalogo;

	public ControladorProducto(VentanaProducto vista, Catalogo catalogo)
    {
        this.catalogo = catalogo;
        this.ventanaCatalogo = vista;
    }

    public void onGuardarClick()
    {
        String nuevoNombre = ventanaCatalogo.getCampoNombre();
        String nuevaDescripcion = ventanaCatalogo.getCampoDescripcion();
        double nuevoPrecio = Double.parseDouble(ventanaCatalogo.getCampoPrecio());
        int nuevoStock = Integer.parseInt(ventanaCatalogo.getCampoStock());
        Producto nuevoProducto = new Producto(nuevoNombre,nuevaDescripcion,nuevoPrecio,nuevoStock);
        catalogo.guardarProducto(nuevoProducto);
    }
    }
