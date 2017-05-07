package Model;
/*
 * Clase encargada de comprobar si un usuario es valido o invalido.
 * @author Alejandro
 */
import java.util.ArrayList;
import java.util.List;

public class LoginService {
	public LoginService(RepositorioUsuarios repo){
		this.repo = repo;
		this.listeners = new ArrayList<>();
		}
	public void addListener(LoginListener l){
		listeners.add(l);
	}
	private List<LoginListener> listeners;
	RepositorioUsuarios repo ;
	public void loginUser(String nick,String pass){
		List<Usuario> usuarioSistema = repo.getUsuarios();
		boolean encontrado = false;
		int contador = 0;
		Usuario usuarioActual = null; 
		while(!encontrado && contador < usuarioSistema.size() ){
			usuarioActual = usuarioSistema.get(contador);
			if(usuarioActual.getNick().equals(nick) && usuarioActual.getPass().equals(pass)){
				encontrado = true;
			}else contador++;
		}if (encontrado){
			for(int j=0;j < listeners.size();j++){
				listeners.get(j).loginCorrecto(usuarioActual);
			}
		}else{
			for(int j=0;j < listeners.size();j++){
				listeners.get(j).loginIncorrecto("¡Usuario y/o contraseña incorrecto!");
	
			}
		}

	}

}
