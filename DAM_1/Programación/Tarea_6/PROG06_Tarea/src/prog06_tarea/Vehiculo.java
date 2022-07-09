
package prog06_tarea;

/**
 *
 * @author FColls
 */
public class Vehiculo {
    
    /*Atributos necesarios para crear un nuevo vehículo*/
    private String marca, matricula, descripcion, nombrePropietario, dniPropietario;
    private int numeroKms;
    private float precio;
    

    /*Metodo constructor de la clase Vehiculo.
    Se inicializan los atributos declarados*/
    public Vehiculo(){
        marca=null;
        matricula=null;
        descripcion=null;
        nombrePropietario=null;
        numeroKms=0;
        precio=0;
        dniPropietario=null;
    }


    //Métodos getter y setter, para acceder y modificar los atributos.
    
    // Marca del vehículo.
    public void setMarca (String marca){
        this.marca=marca;
    }
    public String getMarca(){
        return marca;
    }
    // Matrícula del vehículo.
    public void setMatricula(String matricula){
        this.matricula=matricula;
    }
    public String getMatricula(){
        return matricula;
    }
    // Descripción del vehículo.
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public String getDescripcion(){
        return descripcion;
    }
    // Nombre del propietario del vehículo.
    public void setPropietario(String propietario){
        this.nombrePropietario=propietario;
    }
    public String getPropietario(){
        return nombrePropietario;
    }
    // Kilometros del vehículo.
    public void setKms(int kms){
        numeroKms=kms;
    }
    public int getKms(){
        return numeroKms;
    }
    // Precio del vehículo.
    public void setPrecio(float precio){
        this.precio=precio;
    }
    public float getPrecio(){
        return precio;
    }
      
    // DNI del propietario del vehículo.
    public void setDniPropietario(String dniPropietario){
        this.dniPropietario=dniPropietario;
    }
    public String getDni(){
        return dniPropietario;
    }

    @Override
    public String toString() {
        return "Vehiculo{"+"Marca=" + marca + ", Matricula=" + matricula + ", Precio=" + precio +"€"+
                ", Descripción=" +descripcion+", Propietario=" + nombrePropietario + 
                ", DNI_Propietario=" + dniPropietario +", NúmeroKms=" + numeroKms + '}';
    }
    
    
      
}
