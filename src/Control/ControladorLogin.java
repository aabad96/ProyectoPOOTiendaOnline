package Control;
/*
 * Clase encargada del control de usuarios.
 * @author Alejandro
 */
import Model.LoginService;
import View.VentanaLogin;

public class ControladorLogin{
	public ControladorLogin(VentanaLogin vista, LoginService servicioLogin){
		this.vistaLogin = vista;
		this.servicioLogin = servicioLogin;
		
	}

	private VentanaLogin vistaLogin;
	private LoginService servicioLogin;
	public void hazLogin(String nick, String pass) {
		if (nick.isEmpty() || pass.isEmpty()){
			vistaLogin.muestraMensajeError("¡Usuario y/o contraseña vacíos!");
		}else{
			servicioLogin.loginUser(nick, pass);
		}
		
	}
	

}
