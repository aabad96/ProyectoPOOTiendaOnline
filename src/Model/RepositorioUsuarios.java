package Model;
/*
 * Clase encargada de coger los usuarios desde un txt y guardarlos en "cache".
 * @author Alejandro
 */
import Model.Admin;
import Model.Comprador;
import Model.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios {

	private File fuenteUsuarios;
	List<Usuario> cacheUsuarios;

	public RepositorioUsuarios(File fuenteUsuarios) {
		this.fuenteUsuarios = fuenteUsuarios;
		this.cacheUsuarios = new ArrayList<>();
	}

	public List<Usuario> getUsuarios() {
		if (!cacheUsuarios.isEmpty()) {
			return cacheUsuarios;
		}
		List<Usuario> usuarios = new ArrayList<>();

		try {
			BufferedReader flujoLectura = new BufferedReader(new FileReader(fuenteUsuarios));
			String linea = flujoLectura.readLine();

			while (linea != null) {
				String[] productoParseado = linea.split("\\|");
				if (productoParseado.length > 3) {
					String nombre = productoParseado[0];
					String nick = productoParseado[1];
					String pass = productoParseado[2];
					String rol = productoParseado[3];
					Usuario nuevoUsuario = parseaUsuario(nombre,nick,pass,rol);
					if(nuevoUsuario != null)
					{
						usuarios.add(nuevoUsuario);
						cacheUsuarios.add(nuevoUsuario);
					}
					else
					{
						System.err.println("Usuario con rol desconocido");
					}
					linea = flujoLectura.readLine();
				}
			}
			flujoLectura.close();

		} catch (IOException e) {
			System.err.println("Error procesando el fichero.");

		}
		return usuarios;
	}

	private Usuario parseaUsuario(String nombre, String nick, String pass,String rol)
	{
		if("comprador".equalsIgnoreCase(rol))
		{
			return new Comprador(nombre,pass,nick);
		}
		else if("admin".equalsIgnoreCase(rol))
		{
			return new Admin(nombre,pass,nick);
		}
		else
		{
			return null;
		}
	}
}
