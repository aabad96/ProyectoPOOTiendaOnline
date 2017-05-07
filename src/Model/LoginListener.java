package Model;
/*
 * Clase encargada de autorizar la entrada al programa con los datos recibidos del usuario.
 * @author Alejandro
 */
import Model.Usuario;

public interface LoginListener {

	public void loginCorrecto(Usuario user);
	public void loginIncorrecto(String error);
}
