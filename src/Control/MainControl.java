package Control;
/*
 * Clase que contiene el MAIN.
 * @author Alejandro
 */
import java.io.File;

import Model.Catalogo;
import Model.RepositorioProductos;
import Control.ControladorLogin;
import Model.LoginService;
import Model.RepositorioUsuarios;
import Model.Tienda;
import View.VentanaLogin;



public class MainControl {

	public static void main(String args[]) {
		System.out.println("Iniciando Tienda Online de Alejandro Abad Martínez...");

		RepositorioProductos repositorioProductos = new RepositorioProductos(new File("Producto.txt"));
		Catalogo catalogo = new Catalogo(repositorioProductos);
		Tienda tienda = new Tienda();
		VentanaLogin vista = new VentanaLogin(repositorioProductos, catalogo, tienda);

		// Creo el controlador pasando la ventana
		
		File fuenteUsuarios = new File("Usuarios.txt");
		RepositorioUsuarios repo = new RepositorioUsuarios(fuenteUsuarios);
		LoginService loginService = new LoginService(repo);
		ControladorLogin cl = new ControladorLogin(vista, loginService);
		loginService.addListener(vista);
		
		// Le Asociamos el controlador a la ventana

		vista.addController(cl);

		vista.iniciarVistaLogin();
	}

}