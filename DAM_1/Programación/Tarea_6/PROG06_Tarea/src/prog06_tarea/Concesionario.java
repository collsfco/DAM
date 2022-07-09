package prog06_tarea;

/**
 * @author FColls
 *
 */
public class Concesionario {

    String marca;
    String matricula;
    int kmsCoche;
    String propietario, dniPropietario;
    int contVehiculos;
    int indiceArray = 0;
    boolean buscaMatricula = false;
    float precio = 0;
    String descripcion;
    //Array para almacenar los vehiculos, número de vehículos a gurdar 50.
    private String[] arrayConcesionario = new String[50];
    //Objeto de la Clase vehículo.
    Vehiculo objVehiculo = new Vehiculo();

    //Constructor de la Clase.
    public Concesionario() {
        marca = null;
        kmsCoche = 0;
        matricula = null;
        propietario = null;
        precio = 0;
        descripcion = null;
        contVehiculos = 0;
    }

    //Métodos getter y setter, para acceder y modificar los atributos.
    // Marca del vehículo.
    public void setMarca(String marca) {
        this.marca = marca;
    }

    // Matrícula del vehículo.
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    // Descripción del vehículo.
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Nombre del propietario del vehículo.
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    // Kilometros del vehículo.
    public void setKms(int kms) {
        kmsCoche = kms;
    }

    // Precio del vehículo.
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    // DNI del propietario del vehículo.
    public void setDniPropietario(String dniPropietario) {
        this.dniPropietario = dniPropietario;
    }

//Metodo que crea el vehiculo y lo almacena en el consecionario.
    public int insertarVehiculo() {

        boolean matriculaOK = false;
        int codInsertarVehiculo = 0;

        if (contVehiculos <= arrayConcesionario.length - 1) { //Se agrega longitud -1 para que no de error al superar la longitud del array.

            for (int i = 0; i < contVehiculos; i++) { // Usamos como limite del for el contVehiculos de la clase
                //consecionario que es el encargador de llevar el registros de los coches creados.
                objVehiculo.setMatricula(matricula); //Primero establecemos el valor de la matricula para realizar la comparación con los vehículos creados.
                matriculaOK = arrayConcesionario[i].contains("Matricula=" + matricula + ",");
            }
            if (matriculaOK) {
                codInsertarVehiculo = -2;
            } else {
                objVehiculo.setMarca(marca);
                objVehiculo.setKms(kmsCoche);
                objVehiculo.setMatricula(matricula);
                objVehiculo.setPropietario(propietario);
                objVehiculo.setDniPropietario(dniPropietario);
                objVehiculo.setPrecio(precio);
                objVehiculo.setDescripcion(descripcion);
                arrayConcesionario[contVehiculos] = objVehiculo.toString();
                contVehiculos++;
                codInsertarVehiculo = 0;
            }
        } else {

            codInsertarVehiculo = -1;
        }

        return codInsertarVehiculo;

    }

    /*
    listaVehículos: Lista por pantalla los datos de todos los vehículos del concesionario.
     **/
    
    //Devolver string para recorrer for en principal
    
    /*
    public String[] listarVehiculo() {

        return arrayConcesionario;
    }
     */
    public void listarVehiculo() {

        for (int i = 0; i < contVehiculos; i++) {
            System.out.println(arrayConcesionario[i]);
        }

    }

    /*
    buscaVehículo: Recibe como parámetro una matrícula, buscar el vehículo en el concesionario 
    y devuelve una cadena con los datos del vehículo o null si el vehículo buscado no existe.
     */
    public String buscaVehiculo(String matricula) {
        boolean matriculaOK = false;
        String datosVehiculo = null;

        for (int i = 0; i < contVehiculos; i++) { // Usamos como limite del for el contVehiculos de la clase
            //consecionario que es el encargador de llevar el registros de los coches creados.
            matriculaOK = arrayConcesionario[i].contains("Matricula=" + matricula + ","); //Si el array contiene la matricula, matriculaOk ==true.
            if (matriculaOK) {

                int vinicial = (arrayConcesionario[indiceArray].indexOf("Marca="));//Buscar valor inicial del array
                int vfinal = (arrayConcesionario[indiceArray].indexOf("€")); //Buscar valor final del array

                datosVehiculo = arrayConcesionario[indiceArray].substring(vinicial, vfinal + 1);// Obtenemos el numero de kms actual.

            }
        }
        return datosVehiculo;
    }

    /*
    actualizaKms: Recibe por parámetro una matrícula y un número de kilómetros,
    busca el vehículo con la cuya matrícula coincida y actualiza sus kilómetros. 
    Devuelve true si se hizo con éxito y false en caso contrario.
     */
    public boolean actualizaKms(String nuevoKm, String matricula) {

        boolean kmsActualizados = false; //Devuelve true, si se actualizaron los Kms.

        for (int i = 0; i < contVehiculos; i++) { // Usamos como limite del for el contVehiculos de la clase
            //consecionario que es el encargador de llevar el registros de los coches creados.
            buscaMatricula = arrayConcesionario[i].contains("Matricula=" + matricula + ",");

            if (buscaMatricula) {

                indiceArray = i; //Indice del array.
                int vinicial = (arrayConcesionario[indiceArray].indexOf("Kms="));//Buscar valor inicial del array
                int vfinal = (arrayConcesionario[indiceArray].indexOf("}")); //Buscar valor final del array

                String kmAnterior = arrayConcesionario[indiceArray].substring(vinicial + 4, vfinal);// Obtenemos el numero de kms actual.
                String arrayActualizado = arrayConcesionario[indiceArray].replace(kmAnterior, nuevoKm); //Reemplazamos los kms.
                arrayConcesionario[indiceArray] = arrayActualizado; //Asignamos el string modificado al array en la posición original.
                //System.out.println(arrayActualizado);
                kmsActualizados = true;  //Si actulizamos los Kms, retorna true.

            }
        }

        return kmsActualizados;

    }

}
