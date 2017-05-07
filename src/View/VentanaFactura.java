package View;

import Model.ElementoCarrito;
import Model.Factura;
import Model.Producto;
import Model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class VentanaFactura extends JFrame {

    private final Usuario usuario;

    public VentanaFactura(Usuario usuario)
    {
        this.usuario = usuario;
    }


    public void iniciaVista()
    {
        setTitle("Compra realizada");
        setSize(400,400);
        setLayout(new BorderLayout());

        JPanel totalCompra = new JPanel(new FlowLayout(FlowLayout.CENTER));
        totalCompra.setFont(totalCompra.getFont().deriveFont(45f));
        JLabel compra = new JLabel("TOTAL COMPRA: "+ usuario.getFactura().getTotal());
        totalCompra.add(compra);

        Factura factura = usuario.getFactura();

        List<ElementoCarrito> elementosCarrito = factura.getProductos();
        Object[][] items = new Object[elementosCarrito.size()][3];
        String[] columnas = {"Producto",
                "Uds.","Precio"};

        ElementoCarrito elementoCarrito;
        for(int i=0;i<elementosCarrito.size();i++)
        {
            elementoCarrito = elementosCarrito.get(i);
            int uds = elementoCarrito.getUnidades();
            items[i][0] = elementoCarrito.getProducto().getNombre();
            items[i][1] = uds;
            items[i][2] = elementoCarrito.getProducto().getPrecio() * uds;
        }

        JTable table = new JTable(items, columnas);
        JScrollPane pane = new JScrollPane(table);
        add(pane,BorderLayout.CENTER);
        add(totalCompra,BorderLayout.SOUTH);
        setVisible(true);
    }
}
