package View;
/*
 * Clase encargada de crear la ventana del carrito.
 * @author Alejandro
 */
import Model.*;

import javax.swing.*;

import Control.ControladorCarrito;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class VentanaCarrito extends JFrame implements Tienda.ResultadoCompraListener {

    private final Usuario usuario;
    private ControladorCarrito controlador;

    public VentanaCarrito(Usuario usuario)
    {
        this.usuario = usuario;
    }

    public void addControlador(ControladorCarrito controlador)
    {
        this.controlador = controlador;
    }

    public void iniciaVista()
    {
        setTitle("Carrito de compra");
        setSize(400,400);
        setLayout(new BorderLayout());

        JPanel botonCompra = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton compra = new JButton("Realizar compra");
        botonCompra.add(compra);

        compra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.onRealizarCompraClick(usuario);
            }
        });

        Carrito productosCarrito = usuario.getCarrito();

        List<ElementoCarrito> elementoCarritos = productosCarrito.getProductos();
        Object[][] items = new Object[elementoCarritos.size()][3];
        String[] columnas = {"Producto","Uds.","Precio"};

        ElementoCarrito elementoCarrito;
        for(int i=0;i<elementoCarritos.size();i++)
        {
            elementoCarrito = elementoCarritos.get(i);
            int uds = elementoCarrito.getUnidades();
            items[i][0] = elementoCarrito.getProducto().getNombre();
            items[i][1] = uds;
            items[i][2] = elementoCarrito.getProducto().getPrecio() * uds;
        }

        JTable table = new JTable(items, columnas);
        table.setEnabled(false);
        JScrollPane pane = new JScrollPane(table);
        add(pane,BorderLayout.CENTER);
        add(botonCompra,BorderLayout.SOUTH);

        setVisible(true);
    }

    private void cierraVentana()
    {
        setVisible(false);
        dispose();
    }

    @Override
    public void onCompraExito(String msg) {
        JOptionPane.showMessageDialog(this,msg);
        cierraVentana();
    }

    @Override
    public void onCompraError(String error) {
        JOptionPane.showMessageDialog(this,error,"Error compra",JOptionPane.WARNING_MESSAGE);
        cierraVentana();
    }
}
