/*
 Diseña un programa Java denominado PROG02_Ejerc8 que dados el número de
alumnos matriculados en Programación, número de alumnos matriculados en Entornos
de Desarrollo y número de alumnos matriculados en Base de datos. El programa deberá
mostrar el % de alumnos matriculado en cada uno de los tres módulos. Se supone que
un alumno sólo puede estar matrículado en un módulo. Trata de mostrar un solo decimal
en los porcentajes.
 */
package prog02_ejerc8;

import java.text.DecimalFormat;

/**
 *
 * @author FColls
 */
public class PROG02_Ejerc8 {

    public static void main(String[] args) {
        int alumnosPROG= 15;
        int alumnosED= 28;
        int alumnosDB= 19;
        int totalAlumnos;
        
        //Formato para obtener sólo un decimal en los resultados.
        DecimalFormat NumeroDecimales = new DecimalFormat("#.0");
        
        //Ya que un alumno sólo puede estar matriculado en un módulo la cantidad
        //total de alumnos es la suma de los alumnos en cada módulo.
        totalAlumnos= alumnosPROG+alumnosED+alumnosDB;
        
        float porcentajePROG=(float)(alumnosPROG*100)/totalAlumnos;
        float porcentajeED=(float)(alumnosED*100)/totalAlumnos;
        float porcentajeDB=(float)(alumnosDB*100)/totalAlumnos;
        
        System.out.println("Cantidad total de alumnos matriculados: "+totalAlumnos);
        System.out.println("Porcentaje de alumnos en PROG :"+ NumeroDecimales.format(porcentajePROG)+"%");
        System.out.println("Porcentaje de alumnos en ED: "+NumeroDecimales.format(porcentajeED)+"%");
        System.out.println("Porcentaje de alumnos en DB: "+NumeroDecimales.format(porcentajeDB)+"%");
    }
    
}
