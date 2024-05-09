package gm.tienda_libros.vista;

import gm.tienda_libros.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;


@Component
public class LibroForm extends JFrame {
    LibroServicio libroServicio;
    private JPanel panel;
    private DefaultTableModel tablaModeloLibros;
    private JTable tablaLibros;
    private JTextField libroTexto;
    private JTextField autorTexto;
    private JTextField precioTexto;
    private JTextField existenciasTexto;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton eliminarButton;

    @Autowired
    public LibroForm(LibroServicio libroServicio){
        this.libroServicio = libroServicio;
        iniciarForma();

        agregarButton.addActionListener(e -> {

        });
    }

    private void iniciarForma(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla=toolkit.getScreenSize();
        int x= (tamanioPantalla.width-getWidth()/2);
        int y= (tamanioPantalla.height-getHeight()/2);
        setLocation(x,y);
    }

   private void createUIComponents() {
       this.tablaModeloLibros = new DefaultTableModel(0, 5);
       String[] cabeceros = {"Id", "Libro", "Autor", "Precio", "Existencias"};
       this.tablaModeloLibros.setColumnIdentifiers(cabeceros);
       this.tablaLibros = new JTable(tablaModeloLibros);
       listarLibros();
   }

   private void listarLibros(){
        tablaModeloLibros.setRowCount(0);

        var libros=libroServicio.listarLibros();
        libros.forEach((libro)->{
            Object[] renglonLibro={
                libro.getIdLibro(),
                libro.getNombreLibro(),
                libro.getAutor(),
                    libro.getPrecio(),
                    libro.getExistencias()
            };
            this.tablaModeloLibros.addRow(renglonLibro);
        });
   }

}