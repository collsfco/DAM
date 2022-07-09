package PROG05_Ejerc1;

import PROG05_Ejerc1_util.DNI; //Importamos la clase DNI para realizar la validación del DNI.
import java.time.LocalDate;    //Importamos LocalDate para crear la fecha de matriculación.
import java.util.Scanner;      //Importamos Scanner para recoger los datos introducidos por teclado.

/**
 *
 * @author FColls
 *
 */
public class Principal {
    
    /*
    Creamos el método creaVehiculo, el cual es el encargado de guardar los datos del
    vehículo. Además, con este método validamos los datos introducidos por el usuario,
    luego de validar los datos se envian a la clase Vehiculo a traves de los setters.
    */
     //<-Se le pasa al metodo de la clase el objeto instaciado al cual se quiere usar/llamar
    public static void creaVehiculo(Vehiculo nuevoVehiculo){
   
        Scanner read = new Scanner(System.in); //Creamos el objeto read, que será el encargado de 
                                               //leer los datos introducidos por teclado.
        
        /*Atributos necesarios para crear un nuevo vehículo*/
        String marca, matricula, descripcion, dniProp=null, propietario;
        int kmsCoche=0,dia, mes, anyo;
        float precio;
        boolean dniOk=true, fechaOk=true, kmsOk=true; //Booleanos creados para romper ciclo While cuando los datos sean correctos.
        LocalDate fechaM = null;
        String nifLeido;
        System.out.println(">>> Marca del vehículo:"); 
        marca=read.nextLine();
        
        System.out.println(">>> Descripcion del vehículo:"); 
        descripcion=read.nextLine();
        
        System.out.println(">>> Matrícula del vehículo:"); 
        matricula=read.nextLine();
        
        System.out.println(">>> Kms del vehículo:"); 
        while (kmsOk){
            kmsCoche=read.nextInt();
            read.nextLine(); //Después de leer un entero lee una nueva línea para evitar errores de salto de línea.
            if (kmsCoche>0){
                kmsOk=false;
            }
            else{
                System.out.println("--Ingrese una cantidad de Kms mayor a 0");
            }
        
        }
 
        System.out.println(">>> Precio del vehículo:"); 
        precio=read.nextFloat();
        
        System.out.println(">>> Fecha de matriculación del vehículo dd/mm/aa:"); 
       
        while (fechaOk){
            //Excepción creada por si se introduce mal la fecha, por ejemplo si en mes se escribe un número mayor a 12. 
            try{  
                System.out.println("--Día");
                dia=read.nextInt();
                read.nextLine();
                System.out.println("--Mes");
                mes=read.nextInt();
                read.nextLine();
                System.out.println("--Año");
                anyo=read.nextInt();
                read.nextLine();
                //Comprobamos que la fecha introducida es anterior a la fecha actual.
                fechaOk=LocalDate.now().isBefore(LocalDate.of(anyo, mes, dia));
                if (fechaOk){
                    System.out.println("--La fecha debe ser anterior a la fecha actual.");
                }
                else{
                    fechaM = LocalDate.of(anyo, mes, dia);
                }   //Con LocalDate creamos la fecha de matriculación del coche.
            }
            
            catch (Exception e){ //La declaración catch, permite definir un bloque de código a ejecutar, si ocurre un error en el bloque try.
                System.out.println("--El formato de fecha es incorrecto."); 
                System.out.println("--Día 1-31 / Mes 1-12 / Año XXXX\n");
            }
        }
       
        
        System.out.println(">>> DNI del propietario del Vehículo (ej:000000000X):"); 
        System.out.println("--La letra debe ser en Mayúsculas");
        
        while (dniOk){ //Repite el ciclo hasta que Dni sea correcto.
            try{//Si el Dni introducido es incorrecto crea un error, por eso es necesario crear una excepción.
                DNI dniPropietario = new DNI(); 
                nifLeido=read.nextLine();
                dniPropietario.establecer(nifLeido);
                System.out.println("--DNI correcto: "+ dniPropietario.obtenerNIF());
                dniProp=dniPropietario.obtenerNIF();
                dniOk=false;
            }
     
            catch (Exception e){ //La declaración catch, permite definir un bloque de código a ejecutar, si ocurre un error en el bloque try.
            System.out.println("--Ingrese un número de DNI valido");  
            } 
        }
        System.out.println(">>> Nombre del propietario del vehículo:");    
        propietario=read.nextLine();
        
        //Enviamos los datos correspondientes a la clase Vehiculo a traves de los setters.
        nuevoVehiculo.setMarca(marca);
        nuevoVehiculo.setMatricula(matricula);
        nuevoVehiculo.setDescripcion(descripcion);
        nuevoVehiculo.setPropietario(propietario);
        nuevoVehiculo.setKms(kmsCoche);
        nuevoVehiculo.setPrecio(precio);
        nuevoVehiculo.setFechaMatri(fechaM);
        nuevoVehiculo.setDniPropietario(dniProp);
    }
    
    public static void main(String[] args) throws Exception {
        
        boolean exitP =true;
        int opcion;
        int kmActual, kmNuevo;
        Vehiculo nuevoVehiculo= new Vehiculo(); //Creamos el objeto nuevoVehiculo.
              
        while (exitP){//Menú de opciones.
            System.out.println("\n###############################");
            System.out.println("<<<< Gestión de Vehículos >>>>");
            System.out.println("###############################");
            
            System.out.println("[1] Nuevo Vehículo.           #");
            System.out.println("[2] Ver Matrícula             #");
            System.out.println("[3] Ver Número de Kilómetros  #");
            System.out.println("[4] Actualizar Kilómetros     #");
            System.out.println("[5] Ver años de antigüedad    #");
            System.out.println("[6] Mostrar propietario       #");
            System.out.println("[7] Mostrar descripción       #");
            System.out.println("[8] Mostrar Precio            #");
            System.out.println("[9] Salir                     #");
            System.out.println("###############################");
            System.out.println(">> Elige una opción...");
            
            Scanner read = new Scanner(System.in);
            opcion=read.nextInt();
            read.nextLine();
           
            switch (opcion){
                
                case 1: //Nuevo Vehiculo.
                    System.out.println("\n#######################");
                    System.out.println("<<<< Nuevo Vehículo>>>>");
                    System.out.println("#######################");
                    creaVehiculo(nuevoVehiculo); //Llamamos al metodo creaVehiculo.
                    break;
                /*
                    Los casos del 2 al 8 cuentan con un if/else que nos permite controlar
                    según una serie de condiciones que si el vehiculo aún no ha sido creado
                    no podamos ver los datos de dichas opciones. 
                    
                    Con metodos getter accedemos a los datos solicitados, y cuando es necesario modificar algo
                    lo realizamos a traves de los setter.
                */    
                case 2: //Ver Matrícula.
                    if (nuevoVehiculo.getMatricula()!= null){
                        System.out.println("\n>>> Matrícula del vehículo= "+nuevoVehiculo.getMatricula());
                    }
                    else{
                        System.out.println("Es necesario registrar el vehículo en la opción [1].");
                    }
                    break;
                    
                case 3: //Ver Número de Kilómetros.
                    if (nuevoVehiculo.getKms()>0){
                        System.out.println("\n>>> Número de Kilometros actual= "+nuevoVehiculo.getKms()+"Kms");
                    }
                    else{
                        System.out.println("Es necesario registrar el vehículo en la opción [1].");
                    }
                    break;
                case 4: //Actualizar Kilómetros.
                    if (nuevoVehiculo.getKms()>0){
                        boolean kmActualizado=true;
                        while (kmActualizado){
                            System.out.println(">>> Indique Kms actuales= ");
                            kmNuevo=read.nextInt();
                            read.nextLine();
                            kmActual=nuevoVehiculo.getKms();
                            if (kmActual> kmNuevo){
                                System.out.println(">>> Los Km no pueden ser inferiores a los actuales");
                            }
                            else{
                                nuevoVehiculo.setKms(kmNuevo);
                                kmActualizado=false;
                            }
                        }
                    }
                    else{
                        System.out.println("Es necesario registrar el vehículo en la opción [1].");
                    }  
                    break;
                    
                case 5: //Ver años de antigüedad.
                    if (nuevoVehiculo.getKms()>0){
                        System.out.println("\n>>> Años de antigüedad= "+ nuevoVehiculo.get_Anios());
                        
                    }
                    else{
                        System.out.println("Es necesario registrar el vehículo en la opción [1].");
                    }
                    break;
                    
                case 6: //Mostrar propietario.
                    if (nuevoVehiculo.getDni()!=null){
                        System.out.println("\n>>> Propietario del Vehículo= "+ nuevoVehiculo.getPropietario());
                        System.out.println(">>> DNI= "+ nuevoVehiculo.getDni());
                    }
                    else{
                        System.out.println("Es necesario registrar el vehículo en la opción [1].");
                    }
                    break;
                case 7: //Mostrar descripción.
                    if (nuevoVehiculo.getDescripcion()!=null){
                        System.out.println("\n>>> Descripción= "+ nuevoVehiculo.getDescripcion());
                        System.out.println(">>> Matrícula del vehículo= "+nuevoVehiculo.getMatricula());
                        System.out.println(">>> Número de Kilometros actuales= "+nuevoVehiculo.getKms());
                    }
                    else{
                        System.out.println("Es necesario registrar el vehiculo en la opción [1].");
                    }
                    break;
                case 8: //Mostrar Precio.
                    if (nuevoVehiculo.getKms()>0){
                    System.out.println("\n>>> Precio del Vehículo= "+ nuevoVehiculo.getPrecio()+ "€");
                    }
                    else{
                        System.out.println("Es necesario registrar el vehículo en la opción [1].");
                    }
                    break;
                case 9: //Salir.
                    exitP=false;
                    break;
            }
     
        }
       }
    }
