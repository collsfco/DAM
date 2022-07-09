package prog08_tarea2;

import java.util.TreeSet;
/**
 * @author FColls
 *
 */
public class Concesionario {
     
    /**
     * La estructura seleccioanda para almacenar la informacion de los cohes es 
     * un TreeSet, ya que su principal caractaristica es que es una estrucutura 
     * ordenada y no permite elementos duplicados, por lo que sobreescribiendo
     * los metodos compareTo y equal ya nos aseguramos de que la lista este
     * ordenada y no permite duplicados. En este caso se ordenaran en base a la 
     * matricula de cada vehiculo y tambin se comprueba la matricula para evitar
     * insertar un vehiculo con matricula repetida.
     */
    TreeSet<Vehiculo> almacenCoches; //Declaramos un TreeSet llamado almacenCoches
    
    //Constructor de la Clase.
    public Concesionario(){
        almacenCoches = new TreeSet<Vehiculo>();
    }
   
    /**
     * insertarVehiculo: Metodo que crea el vehiculo y lo almacena en el consecionario.
     * @param marca Marca del vehículo. 
     * @param matricula Matrícula del vehículo.
     * @param descripcion Descripción del vehículo.
     * @param nombrePropietario Nombre del propietario del vehículo.
     * @param numeroKms Kilometros del vehículo.
     * @param precio Precio del vehículo.
     * @param dniPropietario DNI del propietario del vehículo.
     * @return Devuelve un 0 si se inserta el objeto, Devuelve un -2 si no se inserta el objeto.
     */
    public int insertarVehiculo(String marca, String matricula, String descripcion, String nombrePropietario,
                          int numeroKms, float precio, String dniPropietario) {
        
        //Metodo add devuelve true si se inserta correctamente.
        //Se realiza la comprobación mediante la sobreescritura del metodo equals() en la clase vehiculo.
        if (almacenCoches.add(new Vehiculo(marca, matricula, descripcion, nombrePropietario, numeroKms, precio, dniPropietario))) {
            return 0; 
        } else {
           return -2; //Existe un coche con esa matricula
        }  
    }
    
    /**
     * listarVehiculo: Al llamar a este metodo devuelve la información de todos los vehiculos
     * almacenados en el consecionario.
     */
    public void listarVehiculo() {

        for (Vehiculo listar : almacenCoches) {
            System.out.println("Matrícula: "+listar.getMatricula() + ", Propietario: " + listar.getPropietario()+
                    ", Marca: "+ listar.getMarca()+", Descripción: "+ listar.getDescripcion()+
                    ", Precio: "+ listar.getPrecio()+"€, Kilometros: "+ listar.getKms()+"Kms" );
            System.out.println("--------------------------------------------------------"
                                + "--------------------------------------------------------");
        }
    }
   
    /**
     * buscaVehículo: Recibe como parámetro una matrícula, buscar el vehículo en el concesionario 
     * y devuelve una cadena con los datos del vehículo o null si el vehículo buscado no existe.
     * @param matricula Matrícula del vehículo que queremos buscar.
     * @return Si existe el vehículo devuelve unos datos, si no existe devuelve null.
     */
    public String buscaVehiculo(String matricula) {

        for (Vehiculo buscar : almacenCoches) {
            if (buscar.getMatricula().equals(matricula)) {

                return "Marca: "+buscar.getMarca()+", Matrícula: " + buscar.getMatricula() + 
                        ", Precio: " + buscar.getPrecio()+"€" ;
            }
        }

        return null;
    }
   
    /**
     * actualizaKms: Recibe por parámetro una matrícula y un número de kilómetros,
     * busca un vehículo por su matrícula y si lo consigue actualiza sus kilómetros.
     * @param nuevoKm kilometros que queremos asignar al vehículo.
     * @param matricula Matrícula del vehículo que queremos buscar.
     * @return Devuelve true si se hizo con éxito y false en caso contrario.
     */
    public boolean actualizaKms(int nuevoKm, String matricula) {

        for (Vehiculo buscar : almacenCoches) {
            if (buscar.getMatricula().equals(matricula)) {
                buscar.setKms(nuevoKm);
                return true;
            }
        }
        return false;
    }
    
    /**
     * eliminarVehiculo: Recibe por parámetro una matrícula de un vehículo y 
     * si lo consigue elimina el vehículo.
     * @param matricula Matrícula del vehículo que queremos eliminar
     * @return  Devuelve true si se hizo con éxito y false en caso contrario.
     */
     public boolean eliminarVehiculo(String matricula) {

        for (Vehiculo buscar : almacenCoches) {
            if (buscar.getMatricula().equals(matricula)) {
                almacenCoches.remove(buscar);
                return true;
            }
        }
        return false;
    }
}
