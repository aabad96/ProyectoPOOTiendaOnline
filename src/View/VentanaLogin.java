package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Model.LoginListener;
import Model.Catalogo;
import Model.RepositorioProductos;
import Model.Tienda;
import Control.ControladorLogin;
import Control.ControladorPrincipal;
import View.VentanaPrincipal;
import Model.Usuario;

public class VentanaLogin extends JFrame implements LoginListener {

	private final RepositorioProductos repositorioProductos;
	private JTextField userText;
	private JPasswordField passText;
	private Tienda tienda;
	private ControladorLogin controlador;
	private Catalogo catalogo;

	public VentanaLogin(RepositorioProductos repositorioProductos, Catalogo catalogo,Tienda tienda)
	{
		this.repositorioProductos = repositorioProductos;
		this.catalogo = catalogo;
		this.tienda = tienda;
	}
	
	public void iniciarVistaLogin(){
		setTitle("Login"); //Creamos una ventana, que tenga Login como nombre en la cabezera
		setSize(400,200);
		setMinimumSize(new Dimension(400,200));
		crearLogin();
		setVisible(true);
	}

	private void crearLogin() {
		JPanel login = new JPanel();
		login.setBorder(BorderFactory.createTitledBorder("Login"));
		GridBagConstraints c = new GridBagConstraints();

		login.setLayout(new GridBagLayout());

		getContentPane().add(login);

		JLabel userLabel = new JLabel("Usuario: "); //Crea la etiqueta de texto que muesta usuario
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userLabel.setFont(userLabel.getFont ().deriveFont (15.0f));
		userLabel.setVerticalAlignment(SwingConstants.CENTER);
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		login.add(userLabel,c);

	    userText = new JTextField(20); // Crea un campo de texto vacio 
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.gridx = 1;
		c.weightx = 1;
		c.gridy = 0;
		login.add(userText,c);

		JLabel passwordLabel = new JLabel("Contraseña: "); //Crea una etiqueta con caracteres especiales para no poder ver el texto
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setVerticalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(passwordLabel.getFont ().deriveFont (15.0f));
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		login.add(passwordLabel,c);

		passText = new JPasswordField(20);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		login.add(passText,c);

		JButton loginButton = new JButton("Entrar"); //Creamos un boton que nos da acceso al programa
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		login.add(loginButton,c);
		loginButton.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			String nick = userText.getText();
			String pass = String.valueOf(passText.getPassword());
			controlador.hazLogin(nick,pass);
		}
			
	});
	}

	public void muestraMensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this,mensaje);
		
	}

	private void cerrarVentana()
	{
		setVisible(false);
		dispose();
	}

	@Override
	public void loginCorrecto(Usuario user) {
		System.out.println(user);
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(user,catalogo,tienda);
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(ventanaPrincipal,repositorioProductos);
        ventanaPrincipal.addControlador(controladorPrincipal);
        ventanaPrincipal.iniciarVista();
        cerrarVentana();
	}

	@Override
	public void loginIncorrecto(String error) {
		JOptionPane.showMessageDialog(this, error);
	}

	public void addController(ControladorLogin cl) {
		this.controlador = cl;
		
	}

}