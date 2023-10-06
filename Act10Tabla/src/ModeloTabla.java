
// Vamos a extender de AbstractTableModel para poder crear un modelo de tabla
    // Vamos tambien a implementar los metodos de AbstractTableModel

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ModeloTabla extends AbstractTableModel{

    // Crearemos un Vector de Alumnos para poder guardar los datos de los alumnos
    private Vector<Alumno> alumnos;

    // Crearemos un constructor para inicializar el Vector
    public ModeloTabla(){
        alumnos = new Vector<Alumno>();
        fireTableDataChanged();
    }

    // Crearemos un metodo para agregar un alumno al Vector
    public void agregarAlumno(Alumno alumno){
        alumnos.add(alumno);
    }

    // Crearemos un metodo para eliminar un alumno del Vector
    public void eliminarAlumno(int index){
        try {
            alumnos.remove(index);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No hay ningun alumno seleccionado");
        }
    }

    // Crearemos un metodo para obtener un alumno del Vector
    public Alumno getAlumno(int index){
        return alumnos.get(index);
    }

    // Crearemos un metodo para editar un alumno del Vector
    public void editarAlumno(int index, Alumno alumno){
            alumnos.set(index, alumno);

    }

    @Override
    public int getRowCount() {
        return alumnos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return alumnos.get(rowIndex).getNombre();
            case 1:
                return alumnos.get(rowIndex).getApellido();
            case 2:
                return alumnos.get(rowIndex).getTelefono();
            case 3:
                return alumnos.get(rowIndex).getCorreo();
            default: 
                return null;
        }
    }
    
}
