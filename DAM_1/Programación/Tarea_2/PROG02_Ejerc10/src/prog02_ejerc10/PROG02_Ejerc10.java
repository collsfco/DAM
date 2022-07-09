/*
Diseña un programa Java, denominado PROG02_Ejerc10, que realice las siguientes
operaciones, en el orden que se muestran. Se indica la variable y el tipo de dato que
recibe el valor o resultado de la operación indicada:
 */
package prog02_ejerc10;

/**
 *
 * @author FColls
 */
public class PROG02_Ejerc10 {

    public static void main(String[] args) {
        //------- Conversiones entre enteros y coma flotante -------
        System.out.println("------- Conversiones entre enteros y coma flotante -------\n");
        float x= (float) 4.5;
        float y= (float) 3.0;
        int i=2;
        int j= (int) (i*x);
        double dx= 2.0;
        double dz=dx*y;
        System.out.println("Producto de int por float: j=i*x = "+ j);
        System.out.println("Producto de float por double: dz=dx*y = "+dz + "\n");
        
        //------- Operaciones con byte -------
        System.out.println("------- Operaciones con byte -------\n");
        byte bx=5;
        byte by=2;
        byte bz= (byte) (bx-by);
        System.out.println("byte:5-2 = "+ bz);
        
        bx = -128;
        by = 1;
        bz = (byte) (bx - by);
        int entero= (bx-by);
        System.out.println("byte -128-1 = "+ bz );
        System.out.println("(int)(-128-1) = "+ entero+"\n");
        
        //------- Operaciones con short -------
        System.out.println("------- Operaciones con short -------\n");
        short sx=5;
        short sy=2;
        short sz=(short) (sx-sy);
        System.out.println("short:5-2 = "+ sz);
        
        sx= 32767;
        sy=1;
        sz=(short) (sx+sy);
        System.out.println("short:32767+1 = "+ sz+"\n");
        
        //------- Operaciones con char -------
        System.out.println("------- Operaciones con char -------\n");
        char cx= '\u000F';
        char cy='\u0001';
        int z=(char)cx-cy;
        System.out.println("char:- = "+ z);
        
        z=((int) cx) - 1;
        System.out.println("char(0x000F)-1 = "+z);
        
        cx='\uFFFF';
        z= cx;
        System.out.println("(int)( ) = "+ z);
        
        sx= (short) cx;
        System.out.println("(short)( ) = "+sx);
        
        sx =-32768;
        cx=(char) sx;
        z= (int)sx;
        System.out.println("-32768 short-char-int = "+ z);
       
        sx= -1;
        cx= (char) sx;
        z=(int) cx;
        System.out.println("-1 short-char-int = "+z);
        
        
        
        
        
    }
    
}
