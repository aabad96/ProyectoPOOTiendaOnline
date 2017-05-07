package View;
/*
 * Clase encargada de crear la ventana del catalogo.
 * @author Alejandro
 */
import Control.ControladorCatalogo;
import Control.ControladorProducto;
import Model.Catalogo;
import Model.Producto;
import View.VentanaProducto;
import Model.Tienda;
import Model.Usuario;
import Permiso.catalogo.PermisoCatalogo;
import Permiso.catalogo.VentanaPermisosCatalogo;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaCatalogo extends JPanel implements ListSelectionListener,Catalogo.Listener,
        Tienda.CarritoListener, VentanaPermisosCatalogo {

    private final Catalogo catalogo;
    private final Usuario usuario;
    private ControladorCatalogo controlador;
    private JList<Producto> lista;
    DefaultListModel<Producto> listModel;
    JPanel opcionesUsuario;

    public VentanaCatalogo(Usuario usuario, Catalogo catalogo)
    {
        this.catalogo = catalogo;
        this.usuario = usuario;
    }

    public void addControlador(ControladorCatalogo controlador)
    {
        this.controlador = controlador;
    }

    public void iniciaVista()
    {
        setLayout(new BorderLayout());
        listModel = new DefaultListModel<>();
        lista = new JList<>(listModel);
        lista.setFont(lista.getFont().deriveFont(22.0f));
        lista.setBorder(BorderFactory.createTitledBorder("Lista productos"));
        lista.addListSelectionListener(this);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(lista);
        opcionesUsuario = new JPanel();
        JLabel titulo = new JLabel("Opciones disponibles");
        titulo.setFont(titulo.getFont().deriveFont(15f));
        titulo.setVerticalAlignment(SwingConstants.CENTER);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        opcionesUsuario.add(titulo);
        opcionesUsuario.setLayout(new BoxLayout(opcionesUsuario,BoxLayout.Y_AXIS));
        add(opcionesUsuario,BorderLayout.WEST);
        add(scrollPane,BorderLayout.CENTER);
        controlador.solicitaActualizacionProductosCatalogo();
        configurarSegunPermisos(usuario);

    }

    private void configurarSegunPermisos(Usuario user) {
        for(PermisoCatalogo permiso : user.getPermisosCatalogo())
        {
            permiso.procesa(this);
        }
    }


    private Producto getProductoSeleccionado()
    {
        int indexSeleccionado = lista.getSelectedIndex();
        return indexSeleccionado != -1 ? listModel.elementAt(indexSeleccionado) : null;
        //antes de que el usuario toque cualquier elemento de la tabla, se da un valor-1 que devuelve NULL
        
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        int indexSeleccionado = lista.getSelectedIndex();
        if(indexSeleccionado >= 0) {
            Producto productoSeleccionado = listModel.elementAt(indexSeleccionado);
            System.out.println(productoSeleccionado);
        }
    }

    @Override
    public void onProductosCargados(List<Producto> productos) {
        listModel = new DefaultListModel<>();
        for(Producto producto : productos)
        {
            listModel.addElement(producto);
        }
        lista.setModel(listModel);
    }

    @Override
    public void onAnadidoACarritoConExito(Producto producto, int unidades) {
        System.out.println("Añadid@s "+unidades+" "+producto.getNombre()+" al carrito");
        controlador.solicitaActualizacionProductosCatalogo();
    }

    @Override
    public void onError(String error) {
        JOptionPane.showMessageDialog(this,error,"Error compra",JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void activarAnadirProductos() {
        JButton eliminar =new JButton("Añadir nuevo producto");
        opcionesUsuario.add(eliminar);
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaProducto producto = new VentanaProducto();
                ControladorProducto controladorProducto = new ControladorProducto(producto,catalogo);
                producto.addControlador(controladorProducto);
                producto.iniciaVista();
            }
        });
    }

    @Override
    public void activarBorrarProducto() {
        JButton eliminar =new JButton("Eliminar producto");
        opcionesUsuario.add(eliminar);
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getProductoSeleccionado() != null)
                {
                    controlador.onEliminarProductoClick(getProductoSeleccionado());
                }
            }
        });
    }

    @Override
    public void activarModificacionProductos() {
        JButton modificar =new JButton("Modificar producto");
        opcionesUsuario.add(modificar);
        modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getProductoSeleccionado() != null)
                {
                    VentanaProducto producto = new VentanaProducto(getProductoSeleccionado());
                    ControladorProducto controladorProducto = new ControladorProducto(producto, catalogo);
                    producto.addControlador(controladorProducto);
                    producto.iniciaVista();
                }
            }
        });
    }

    @Override
    public void activarAnadirProductoCarrito() {
        JButton modificar =new JButton("Añadir a carrito");
        opcionesUsuario.add(modificar);
        modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto productoSeleccionado = getProductoSeleccionado();
                if(productoSeleccionado != null)
                {
                    int unidades = Integer.parseInt(
                            JOptionPane.showInputDialog("Introduzca el número de unidades"));
                    controlador.onCompraProducto(productoSeleccionado,unidades);
                }
            }
        });
    }
}
