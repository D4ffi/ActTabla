// Estos son los bellos imports
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

public class Tabla extends JFrame implements ActionListener{

    // Metodo main
    public static void main(String[] args) throws Exception {
        new Tabla();
    }

    // Paso 0: Dejar un espacio para las variables globales
    private JTextField nombreText;
    private JTextField apellidoText;
    private JTextField telefonoText;
    private JTextField correoText;

    private JButton agregar = new JButton("Agregar"); // Este boton es global por que lo reutilizaremos para el metodo editar
    private JTable table;

    ModeloTabla modelo = new ModeloTabla(); // Esto es global porque lo usaremos en mas metodos

    // Paso 1: Crear el constructor
    public Tabla() {
        // Paso 2: Configurar el JFrame
        this.setTitle("Tabla De Alumnos"); // Titulo de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la ventana
        this.setLocationRelativeTo(null); // Centrar la ventana
        gui();
    }

    // Paso 3: Crear el metodo para formatear el frame
    private void gui(){
        // Paso 4: Declarar los componentes

        // Necesitamos declarar algun tipo de layout para poder acomodar los componentes, usaremos el GridBagLayout y GridBagConstraints
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        this.setLayout(layout); // Aplicando el layout al frame

        JLabel nombre = new JLabel("Nombre");
        JLabel apellido = new JLabel("Apellido");
        JLabel telefono = new JLabel("Telefono");
        JLabel correo = new JLabel("Correo");

        // Los JTextField seran varaibles globales pk los usaremos en distintos metodos
        nombreText = new JTextField(10);
        apellidoText = new JTextField(10);
        telefonoText = new JTextField(10);
        correoText = new JTextField(15);

        // Botones
        JButton eliminar = new JButton("Eliminar");
        JButton editar = new JButton("Editar");

        // Tabla
        table = new JTable();
        table.setModel(modelo);

        // Paso 5: Agregar los componentes al frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(nombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(apellido, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        this.add(telefono, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        this.add(correo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(nombreText, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(apellidoText, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        this.add(telefonoText, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        this.add(correoText, gbc);

        gbc.gridx = 4;
        gbc.gridy = 1;
        this.add(agregar, gbc);

        gbc.gridx = 5;
        gbc.gridy = 1;
        this.add(eliminar, gbc);

        gbc.gridx = 6;
        gbc.gridy = 1;
        this.add(editar, gbc);

        // Agregando la tabla al frame, necesitamos un JScrollPane para poder agregar la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(scrollPane, gbc);

        agregar.addActionListener(this);
        eliminar.addActionListener(this);
        editar.addActionListener(this);

        // Paso 6: Hacer visible el frame y paquetizarlo
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Paso 12: Crear un if para revisar que boton fue presionado
        if (e.getActionCommand().equals("Agregar")){
            agregarAlumno();
        }
        if (e.getActionCommand().equals("Eliminar")){
            eliminarAlumno();
        }
        if (e.getActionCommand().equals("Editar")){
            editarAlumno();
        }
        if (e.getActionCommand().equals("Guardar")){
            guardar();
        }
    }

    // Paso 7: Crear los metodos para agregar, eliminar y editar alumnos mas un metodo para limpiar los JTextField y otro para revisar si estan vacios

    private void limpiar(){
        nombreText.setText("");
        apellidoText.setText("");
        telefonoText.setText("");
        correoText.setText("");
    }

    private boolean revisar(){
        if(nombreText.getText().isEmpty() || apellidoText.getText().isEmpty() || telefonoText.getText().isEmpty() || correoText.getText().isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    private void agregarAlumno(){
        // Paso 8: Revisar si los JTextField estan vacios
        if (revisar()) {
            JOptionPane.showMessageDialog(this, "No puedes dejar campos vacios");
            return;
        } else {
            // Revisar que el campo telefono solo contenga numeros y que tenga 10 digitos
            try {
                Long.parseLong(telefonoText.getText());
                if (telefonoText.getText().length() != 10) {
                    JOptionPane.showMessageDialog(this, "El telefono debe tener 10 digitos");
                    return;
                }
                // De paso usaremos estre try catch para revisar que el correo contenga un @ y un .
                if (!correoText.getText().contains("@") || !correoText.getText().contains(".")) {
                    JOptionPane.showMessageDialog(this, "El correo no es valido");
                    return;
                }
                // Paso 9: Agregar el alumno al modelo de la tabla
                modelo.agregarAlumno(new Alumno(nombreText.getText(), apellidoText.getText(), telefonoText.getText(), correoText.getText()));
                // Paso 10: Actualizaremos la tabla
                modelo.fireTableDataChanged();
                // Paso 11: Limpiar los JTextField
                limpiar();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El telefono solo puede contener numeros");
                return;
            }
    }
}
 
    // Paso 13: Crear los metodos para eliminar, editar y guardar alumnos
    private void eliminarAlumno(){
        // Seleccionamos el alumno que queremos eliminar y sino hay ninguno seleccionado mandamos un mensaje
        try {
            modelo.eliminarAlumno(table.getSelectedRow());
            modelo.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No hay ningun alumno seleccionado");
        }

    }

    private void editarAlumno(){
        // Seleccionamos el alumno que queremos editar y sino hay ninguno seleccionado mandamos un mensaje
        try {
            Alumno alumno = modelo.getAlumno(table.getSelectedRow());
            nombreText.setText(alumno.getNombre());
            apellidoText.setText(alumno.getApellido());
            telefonoText.setText(alumno.getTelefono());
            correoText.setText(alumno.getCorreo());
            agregar.setText("Guardar");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No hay ningun alumno seleccionado");
        }
    }

    private void guardar(){
        // Seleccionamos el alumno que queremos editar y sino hay ninguno seleccionado mandamos un mensaje
        try {
            Alumno alumno = modelo.getAlumno(table.getSelectedRow());
            alumno.setNombre(nombreText.getText());
            alumno.setApellido(apellidoText.getText());
            alumno.setTelefono(telefonoText.getText());
            alumno.setCorreo(correoText.getText());
            modelo.editarAlumno(table.getSelectedRow(), alumno);
            modelo.fireTableDataChanged();
            limpiar();
            agregar.setText("Agregar");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No hay ningun alumno seleccionado");
        }
    }
}