package View;

import javax.swing.*;

import Control.ControladorProducto;
import Model.Producto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaProducto extends JFrame {

    private Producto productoAEditar;
    private ControladorProducto controlador;

    private JTextField nombre;
    private JTextField descripcion;
    private JTextField precio;
    private JTextField stock;

    public VentanaProducto(Producto productoAEditar)
    {
        this.productoAEditar = productoAEditar;

    }

    public VentanaProducto()
    {
    }

    public void addControlador(ControladorProducto controlador)
    {
        this.controlador = controlador;
    }

    public void iniciaVista()
    {
        setSize(500,300);
        setTitle("Crear/Editar producto");
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(4,2));
        add(panel,BorderLayout.CENTER);
        JPanel botonesCierre = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton guardarCambios = new JButton("Guardar cambios");

        panel.add(new JLabel("Nombre"));
        nombre = new JTextField();
        panel.add(nombre);

        panel.add(new JLabel("Descripción"));
        descripcion = new JTextField();
        panel.add(descripcion);

        panel.add(new JLabel("Precio"));
        precio = new JTextField();
        panel.add(precio);

        panel.add(new JLabel("Stock"));
        stock = new JTextField();
        panel.add(stock);

        if(productoAEditar != null)
        configurarFormulario();


        guardarCambios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.onGuardarClick();
                cerrarVentana();
            }
        });

        JButton cancelar = new JButton("Cancelar");

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentana();
            }
        });

        botonesCierre.add(guardarCambios);
        botonesCierre.add(cancelar);
        add(botonesCierre,BorderLayout.SOUTH);

        setVisible(true);
    }

    private void configurarFormulario() {
        nombre.setText(productoAEditar.getNombre());
        descripcion.setText(productoAEditar.getdescripcion());
        precio.setText(String.valueOf(productoAEditar.getPrecio()));
        stock.setText(String.valueOf(productoAEditar.getStock()));

        nombre.setEnabled(false);
    }

    private void cerrarVentana()
    {
       setVisible(false);
       dispose();
    }

    public String getCampoNombre() {
        return nombre.getText();
    }

    public String getCampoDescripcion() {
        return descripcion.getText();
    }

    public String getCampoPrecio() {
        return precio.getText();
    }

    public String getCampoStock() {
        return stock.getText();
    }
}
