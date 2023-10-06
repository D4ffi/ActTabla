public class Alumno {
    // Esta clase va a representar a un alumno
    // Necesitamos 4 atributos: nombre, apellido, telefono y correo
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

    // Crearemos un constructor para inicializar los atributos
    Alumno(String nombre, String apellido, String telefono, String correo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
    }




    // Crearemos los metodos para obtener y establecer los atributos
    // Para obtener los atributos usaremos los metodos get
    // Para establecer los atributos usaremos los metodos set

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono(){
        return telefono;
    }

    public String getCorreo(){
        return correo;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }


    
}
