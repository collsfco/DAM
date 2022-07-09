/*
 *Crea un proyecto en Netbeans denominado PROG02_Ejerc1 
 *con una clase clase y método main y declara e inicializa 
 *una variable para almacenar cada uno de los siguientes valores. 
 *Trata de utilizar el tipo de datos de mas se ajuste a los datos. 
 *Justifica tu elección.

**Valor máximo no modificable: 5000.
**Si el nuevo empleado tiene carnet de conducir o no.
**Un mes del año en formato numérido y como cadena.
**El nombre y apellidos de una persona.
**Sexo: con dos valores posibles 'V' o 'M'.
**Milisegundos transcurridos desde el 01/01/1970 hasta nuestros días.
**Saldo de una cuenta bancaria.
**Distancia en kms desde la Tierra a Júpiter.
 */
package prog02_ejerc1;

/**
 *
 * @author FColls
 */
public class PROG02_Ejerc1 {

    /**
     */
    // Variable de tipo "enumerado" con 2 posibles valores V/M.
    public enum sexo {V,M};
    
    public static void main(String[] args) {
        
        //**Valor máximo no modificable: 5000**, al no ser modificable
        //se inicia con la palabra final y del tipo int porque 5000
        //entra dentro del rango de las variables int. 
        final int maximo = 5000;
        
        //**Si el nuevo empleado tiene carnet de conducir o no**
        //Como solo tenemos 2 opciones es del tipo boolean : true / false
        boolean carnet = true; 
        
        //**Un mes del año en formato numérido y como cadena**
        // El formato numero tipo int y el texto es declarado tipo string
        // luego se concatenan los 2 valores.
        final int mesN = 12;
        String Mes = "Diciembre";
        
        //**El nombre y apellidos de una persona** Al ser texto, es tipo string.
        String Nombre = "Mario Bosch";
        
        //Milisegundos transcurridos desde el 01/01/1970 hasta nuestros días(26/10/21).
        //51 años, 9 meses y 25 días -- 18926 días -- 454224horas -- 27253440 minutos
        // 1.635.206.400 segundos -- 1.635.206.400.000 milisegundos
        // Años x 365 dias x 24 horas x 60 min. x 60 seg. x 1000 milisegundos
        //Debido a la longitud del numero es declarado tipo long.(-2147483648 a 2147483647)
        long MiliSeg1970 = 1635206400000L;
        
        //**Saldo de una cuenta bancaria** Al ser un numero con decimales se 
        // declara tipo float.
        float Saldo = (float) 1507.25;
       
        //**Distancia en kms desde la Tierra a Júpiter**
        //Debido a la longitud del numero es declarado tipo long.(-2147483648 a 2147483647)
        long distanciaTierra= 588000000L;
        
        
        sexo opcion =sexo.M;
        
        System.out.print("\n El valor de la variable Valor máximo es : "+ maximo);
        System.out.print("\n El valor de la variable Carnet es : "+ carnet);
        System.out.print("\n El valor de la variable Un mes del año es : "+ mesN +"/"+ Mes);
        System.out.print("\n El valor de la variable Nombre de persona es : "+ Nombre);
        System.out.print("\n El valor de la variable Sexo es : "+ opcion);
        System.out.print("\n El valor de la variable Milisegundos desde 1970 es : "+ MiliSeg1970 + " ms");
        System.out.print("\n El valor de la variable Saldo cuenta es : "+ Saldo +"€");
        System.out.print("\n El valor de la variable Distancia Tierra es : "+ distanciaTierra + " Kms"+ "\n");
        
       
        
       
        
   }
   
      
}
