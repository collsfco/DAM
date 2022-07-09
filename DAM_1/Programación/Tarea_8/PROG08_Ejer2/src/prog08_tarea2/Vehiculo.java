
package prog08_tarea2;

import java.util.Objects;

/**
 *
 * @author FColls
 */
//Implementamos la interfaz Comparable para ordenar los elementos dentro del TreeSet.
public class Vehiculo implements Comparable <Vehiculo> {
    
    /*Atributos necesarios para crear un nuevo vehículo*/
    private String marca, matricula, descripcion, nombrePropietario, dniPropietario;
    private int numeroKms;
    private float precio;
    

    /*Metodo constructor de la clase Vehiculo.
    Se inicializan los atributos declarados*/
    
    public Vehiculo(String marca,String matricula,String descripcion, String nombrePropietario,
                    int numeroKms, float precio, String dniPropietario){
        this.marca=marca;
        this.matricula=matricula;
        this.descripcion=descripcion;
        this.nombrePropietario=nombrePropietario;
        this.numeroKms=numeroKms;
        this.precio=precio;
        this.dniPropietario=dniPropietario;
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
    
    /**
    **Sobreescribimos el metodo compareTo de la interfaz comparable,
    **le indicamos que compare la matricula de los coches para ordenarlas
    **de menor a mayor.
    */
    @Override
    public int compareTo(Vehiculo o) {
        return this.matricula.compareTo(o.getMatricula());
    }
    
    /**
    **Sobreescribimos el metodo equals, al cual le indicamos que diferencie los
    **objetos por matricula, con esto evitamos insertar objetos repetidos en el 
    **TreeSet.
    */
    @Override
    public boolean equals(Object obj){ 
        
        if(obj instanceof Vehiculo ){ //Comprobamos que el objeto perteneca a la clase Vehiculo.
            Vehiculo otro=(Vehiculo) obj; //Convertimos obj a tipo Vehiculo con un cast.
            if (this.matricula==otro.matricula) {
                return true; 
            }
            else return false;
        }
        else return false;
    }
    /**
    **Sobreescribimos el metodo hashCode(),ya que al sobreescribir equals() nos
    **recomienda sobreescribir este meteodo. 
    */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.matricula);
        return hash;
    }
   
 
}
