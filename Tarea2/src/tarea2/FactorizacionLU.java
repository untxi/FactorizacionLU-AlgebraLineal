/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;
import java.text.DecimalFormat;  
/**
 *
 * @author gabriel
 */
public class FactorizacionLU {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MatrizLU matriz = new MatrizLU(3);
        matriz.llenarMatriz();
        /*matriz.setElement(0,0,4);
        matriz.setElement(0,1,-2);
        matriz.setElement(0,2,1);
        matriz.setElement(1,0,20);
        matriz.setElement(1,1,-7);
        matriz.setElement(1,2,12);
        matriz.setElement(2,0,-8);
        matriz.setElement(2,1,13);
        matriz.setElement(2,2,17);
        */
        System.out.println("Matriz");
        matriz.mostrarMatriz(matriz.matriz);
        
        float [][] Upper = matriz.matrizU();
        System.out.println("");
        System.out.println("Matriz U");
        matriz.mostrarMatriz(Upper);
        
        float [][] Lower = matriz.L;
        System.out.println("");
        System.out.println("Matriz L");
        matriz.mostrarMatriz(Lower);
        
        
    }
    
}
