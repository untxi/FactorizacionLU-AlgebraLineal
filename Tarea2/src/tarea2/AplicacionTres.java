/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

/**
 *
 * @author Melissa Molina Corrales
 */
public class AplicacionTres {

    public static void main(String[] args) {
        
        float [][] matrizL = {{4,-2,1},{20,-7,12},{-8,13,17}};
        float [] bL = {11,70,17};
        
        //float [][] matrizU = {{2,3,4},{0,-1,2},{0,0,-2}};
        //float [] bU = {6,4,-2};
   
        SistemasdeEcuaciones resolver = new SistemasdeEcuaciones(3,matrizL,bL);
       
        float [][] MatrizU = resolver.matrizU();
        System.out.println("---------------------");
        System.out.println("Matriz U");
        resolver.mostrarMatriz(MatrizU);
        
        float [][] MatrizL = resolver.L;
        System.out.println("---------------------");
        System.out.println("Matriz L");
        resolver.mostrarMatriz(MatrizL);
        
        System.out.println("---------------------");
        System.out.println("Sistema L");
        float [] vector1 = resolver.vector;
        String [][] sistemaL = resolver.AcomodarSistemaL(MatrizL);
        resolver.mostrarSistemaL(sistemaL,vector1);
        System.out.println("---------------------");
        
        float [] vector2 = resolver.vector;
        float [] solucionL = resolver.ResolversistemaL(MatrizL, vector2);
        System.out.println("Solucion Sistema L");
        resolver.mostrar_resultadoL(solucionL);
        System.out.println();
        System.out.println("----------------------");
        
        
        System.out.println("Sistema U");
        String[][] sistemaU = resolver.AcomodarSistemaU(MatrizU);
        resolver.mostrarSistemaU(sistemaU,solucionL);
        System.out.println("----------------------");
        float[] solucionU = resolver.ResolverSistemaU(MatrizU, solucionL);
        System.out.println("Solucion Sistema U");
        resolver.mostrar_resultadoU(solucionU);
        System.out.println();
        System.out.println("----------------------");
        
       
        
        
       
        
       
    }

 
    
}
