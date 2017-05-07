package View;

import Control.ControladorCarrito;
import Control.ControladorCatalogo;
import Control.ControladorPrincipal;
import Model.Catalogo;
import Model.*;
import Permiso.PermisoGeneral;
import Permiso.VentanaPermisosGeneral;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame implements  VentanaPermisosGeneral {

    private final Usuario user;
    private final Catalogo catalogo;
    private final Tienda tienda;
    private JToolBar menuBar;
    private ControladorPrincipal controlador;
    private JPanel panel;

    public VentanaPrincipal(Usuario user,Catalogo catalogo,Tienda tienda) {
        this.user = user;
        this.tienda = tienda;
        this.catalogo = catalogo;
    }

    public void iniciarVista()
    {
        setSize(800, 600);
        setTitle("Ventana Principal");
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel jLabel = new JLabel("Seleccione una opción del menú");
        jLabel.setFont(jLabel.getFont().deriveFont(40f));
        jLabel.setVerticalAlignment(SwingConstants.CENTER);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(jLabel,BorderLayout.CENTER);
        setContentPane(panel);
        setVisible(true);
        //Crea la barra de men�
        menuBar = new JToolBar();
        add(menuBar,BorderLayout.NORTH);
        configurarSegunPermisos(user);
    }

    private void configurarSegunPermisos(Usuario user) {
        for(PermisoGeneral permiso : user.getPermisos())
        {
            permiso.procesa(this);
        }
    }

    @Override
    public void activarVerCarrito() {
        JButton submenuAnadirProductos = new JButton("Ver carrito");
        submenuAnadirProductos.setFont(submenuAnadirProductos.getFont().deriveFont(15f));
        menuBar.add(submenuAnadirProductos);
        submenuAnadirProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.onVerCarritoClick();
            }
        });
    }

    @Override
    public void activarVerCatalogo() {
        JButton submenuAnadirProductos = new JButton("Ver catálogo");
        submenuAnadirProductos.setFont(submenuAnadirProductos.getFont().deriveFont(15f));
        menuBar.add(submenuAnadirProductos);
        submenuAnadirProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.onVerCatalogoClick();
            }
        });
    }

    @Override
    public void activarVerCompra() {
        JButton submenuAnadirProductos = new JButton("Ver compra");
        submenuAnadirProductos.setFont(submenuAnadirProductos.getFont().deriveFont(15f));
        menuBar.add(submenuAnadirProductos);
        submenuAnadirProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.onVerCompraClick();
            }
        });
    }


    public void addControlador(ControladorPrincipal controladorPrincipal) {
        this.controlador = controladorPrincipal;
    }

    public void addCatalogoPanel() {
        VentanaCatalogo vista = new VentanaCatalogo(user,catalogo);
        ControladorCatalogo controlador = new ControladorCatalogo(user,vista,catalogo,tienda);
        panel.add(vista, BorderLayout.CENTER);
        vista.addControlador(controlador);
        catalogo.addListener(vista);
        tienda.addListener(vista);
        vista.iniciaVista();
        panel.revalidate();
    }

    public void muestraVentanaCarrito() {
        VentanaCarrito carrito = new VentanaCarrito(user);
        ControladorCarrito controladorCarrito = new ControladorCarrito(tienda, carrito);
        carrito.addControlador(controladorCarrito);
        tienda.addListener(carrito);
        carrito.iniciaVista();
    }

    public void muestraVentanaCompra() {
        if(user.hasFactura())
        {
            VentanaFactura factura = new VentanaFactura(user);
            factura.iniciaVista();
        }
        else
        {
            JOptionPane.showMessageDialog(this,"¡Debe primero pasar por caja!");
        }
    }
}
