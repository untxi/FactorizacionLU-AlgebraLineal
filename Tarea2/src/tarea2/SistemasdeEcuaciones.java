/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;
import java.lang.Math;
import java.text.DecimalFormat;  
/**
 *
 * @author Melissa Molina Corrales
 */
public class SistemasdeEcuaciones {
   
    private int rango;
    //private int [][] A;
    public float [][] matriz;
    public float [][] U;
    public float [][] L;
    public float [] vector;
    private float pivote_actual;
    private DecimalFormat decimales = new DecimalFormat("0.00");
    
    SistemasdeEcuaciones(int rango, float [][] A, float [] b){
        this.rango = rango;
        //this.A = new int[rango][rango];
        this.matriz = new float[rango][rango];
        this.matriz = A;
        this.U = new float[rango][rango];
        this.L = new float[rango][rango];
        this.vector = b;
        llenarMatrizL();
        
    }
    
    public float[][] getL(){
        return L;
    }
    public float[][] getU(){
        return U;
    }
    public void mostrarMatriz(float [][] M){
        for(int i = 0; i < getRango(); i++){
            for(int j = 0; j < getRango(); j++){
                System.out.print(M[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

/*---------------------------------------------------------
| Para que llenar la matriz? que pereza no?               |
| por defecto puse que llenara con números entre 1 y 9    |
| pero ahí nada mas lo cambian a gusto                    |
----------------------------------------------------------*/
    public void llenarMatriz(){
        for(int i = 0; i < getRango(); i++){
            for(int j = 0; j < getRango(); j++){
                matriz[i][j] = (int)(Math.random()*9+1);
            }
        }
        llenarMatrizM();
    }

    private void llenarMatrizM(){
        for(int i = 0; i < getRango(); i++){
            for(int j = 0; j < getRango(); j++){
                matriz[i][j] = (float)matriz[i][j];
            }
        }
    }
    
    private void llenarMatrizL(){
        float num = 1;
        for(int i = 0; i < getRango(); i++){
            L[i][i] = num;
        }
    }
/*-----------------------------
|Devuelve el rango de la matriz |
-------------------------------*/   
    public int getRango(){
        return this.rango;
    }

/*-----------------------------
|Cambia el rango de la matriz   |
-------------------------------*/      
    public void setRango(int rango){
        this.rango = rango;
    }

/*-----------------------------
|Devuelve el pivote a usar     |
-------------------------------*/   
    public float getPivoteActual(){
        getPivote();
        return this.pivote_actual;
    }  
    
/*-----------------------------
|Cambia el pivote a usar       |
-------------------------------*/   
    private void setPivoteActual(float pivote_actual){
        this.pivote_actual = pivote_actual;
    }
  
 
/*---------------------------------------------
|Cambia algún elemento especifico de la matriz | 
|    i = fila                                  |
|    j = columna                               |
|    valor = valor (quien lo hubiera dicho?)   |
----------------------------------------------*/
    public void setElement(int i, int j, float valor){
        if(i >= getRango() || j >= getRango() ){
            System.out.println("");
        }else{
         matriz[i][j] = valor;   
        }
    }   
    
/*---------------------------------------------
|Retorna algún elemento especifico de la matriz | 
|    i = fila                                   |
|    j = columna                                |
-----------------------------------------------*/
    public float getElement(int i, int j){
        return matriz[i][j];
    }  

/*----------------------------------------------
|Asigna el siguiente pivote a usar dentro       |
| del atributo "pivote_actual", si no encuentra |
| un pivote (ya no se ocupa mas) devuelve 0, si | 
| encuentra un pivote, devuelve 1               |
------------------------------------------------*/
    public int getPivote(){
        int num_pivote = 0;
        float pivote;
        int resultado = 0;
        
        for(int j = 0; j < getRango() - 1; j++){
            pivote = matriz[num_pivote][num_pivote];
            //System.out.println("pivote = " + pivote + "; j = "+ j);
            for(int i = num_pivote + 1; i < getRango(); i++){
                //System.out.println("pivote = " + pivote + "; i,j = " + i + "," + j + " ; matriz[i][j] = " + matriz[j][i]);
                if(matriz[i][j] != 0){
                    setPivoteActual(pivote);
                    resultado = 1;
                    //System.out.println("pivote = " + pivote + "; i,j = " + i + "," + j + " ; matriz[i][j] = " + matriz[j][i]);
                    j = i = getRango();
                }
            }
            
            num_pivote ++;
        }
        
        return resultado;
    }

/*----------------------------------------------
|multiplica el valor a la fila 1 y se lo suma   |
|a la fila 2, dejando el valor en esta          |
------------------------------------------------*/
    public void operacionE(int fila1, int fila2, int columna){
        float valor = -(matriz[fila2][columna]/getPivoteActual());
        //System.out.println("fila1: "+fila1 +"; fila2: " +fila2 +"; columna: " +columna +"; valor: " +valor);
        int l = 0;
        for(int i = columna; i < getRango(); i++){
            if(l == 0){
                matriz[fila2][i] = matriz[fila2][i] + valor * matriz[fila1][i];
                L[fila2][i] = -valor;
                l++;
            }else{
                matriz[fila2][i] = matriz[fila2][i] + valor * matriz[fila1][i];
            }
        }
    }
    
/*----------------------------------------------
|Aplica las operaciones elementales necesarias  |
|a la matriz para convertirla en triangular     |
|superior, obteniendo la matriz U               |
------------------------------------------------*/
    public float[][] matrizU(){
        for(int j = 0; j < getRango() - 1; j++){
            //System.out.println("Primer for");
            for(int i = j + 1; i < getRango(); i++){
            //System.out.println("segundo for");
                operacionE(j, i ,j);
            }
        }
        this.U = this.matriz;
        return U;
    }
    
    public float [][] matrizL(){
        return L;
    }
    
    public String[][] AcomodarSistemaL(float [][] MatrizL){
        String variablesL[]={"y1","y2","y3","y4","y5"};
      
       String[][] sistemaL = new String[getRango()][getRango()];
       for(int i =  0; i < getRango(); i++){
           for(int j = 0; j < getRango(); j++){
                   sistemaL[i][j] = Float.toString(MatrizL[i][j]) + variablesL[j];       
           }  
          
           
       }
       return sistemaL;
    }
    
    
     public void mostrarSistemaL(String [][] M, float [] b){
         int k = 0;
        for(int i = 0; i < getRango(); i++){
            for(int j = 0; j < getRango(); j++){
                System.out.print(M[i][j] + " ");
            }
        
            System.out.println(" = "+ b[k] );
            k++;
       
        }
    }
  

     public String[][] AcomodarSistemaU(float [][] MatrizU){
        String variablesU[]={"x1","x2","x3","x4","x5"};
      
       String[][] sistemaU = new String[getRango()][getRango()];
       for(int i =  0; i < getRango(); i++){
           for(int j = 0; j < getRango(); j++){
                   sistemaU[i][j] = Float.toString(MatrizU[i][j]) + variablesU[j];       
           }  
       }
       return sistemaU;
    }
       

    public void mostrarSistemaU(String [][] M, float [] b){
        int k = 0;
        for(int i = 0; i < getRango(); i++){
            for(int j = 0; j < getRango(); j++){
                System.out.print(M[i][j] + " ");
            }
            System.out.println(" = "+ b[k] );
            k++;
          
        }
    }
  
 /*----------------------------------------------
| Resuelve el sistema de ecuaciones de la matriz L|
------------------------------------------------*/
    public  float [] ResolversistemaL(float [][] MatrizL, float [] b){
        float y1, y2,y3,y4,y5;
        float valor;
        float suma;
        float [] valoresL = new float[getRango()];
        
              if(getRango() == 2){
                  y1 = b[0];
                  valor = MatrizL[1][0] * y1;
                  suma = b[1] - valor;
                  y2 = suma;
                  valoresL[0] = y1;
                  valoresL[1] = y2;
                           
              }
              if(getRango() == 3){
                  y1 = b[0];
                  valor = MatrizL[1][0] * y1;
                  suma = b[1] - valor;
                  y2 = suma;
                  valor = MatrizL[2][0] * y1 +  MatrizL[2][1] * y2;
                  suma = b[2] - valor;
                  y3 = suma;
                  valoresL[0] = y1;
                  valoresL[1] = y2;
                  valoresL[2] = y3;

              }
              if(getRango() == 4){
                  y1 = b[0];
                  valor = MatrizL[1][0] * y1;
                  suma = b[1] - valor;
                  y2 = suma;
                  valor = MatrizL[2][0] * y1 + MatrizL[2][1] * y2;
                  suma = b[2] - valor;
                  y3 = suma;      
                  valor = MatrizL[3][0] * y1 + MatrizL[3][1] * y2 + MatrizL[3][2] * y3;
                  suma = b[3] - valor;
                  y4 = suma;
                  valoresL[0] = y1;
                  valoresL[1] = y2;
                  valoresL[2] = y3;
                  valoresL[3] = y4;

             }
              
              if(getRango() == 5){
                 y1 = b[0];
                 valor = MatrizL[1][0] * y1;
                 suma = b[1] - valor;
                 y2 = suma;
                 valor = MatrizL[2][0] *y1 + MatrizL[2][0] * y2;
                 suma = b[2] - valor;
                 y3 = suma;
                 valor = MatrizL[3][0] * y1 + MatrizL[3][1] * y2 + MatrizL[3][2] * y3;
                 suma = b[3] - valor;
                 y4 = suma; 
                 valor = MatrizL[4][0] * y1 + MatrizL[4][1] * y2 + MatrizL[4][2] * y3 + MatrizL[4][3] * y4;
                 suma = b[4] - valor;
                 y5 = suma;
                 valoresL[0] = y1;
                 valoresL[1] = y2;
                 valoresL[2] = y3;
                 valoresL[3] = y4;
                 valoresL[4] = y5;

                
              }
     return valoresL;}
    
/*--------------------------------------------------
| Resuelve el sistema de ecuaciones de la matriz U  |
----------------------------------------------------*/
    public float [] ResolverSistemaU(float [][] MatrizU, float [] b){
        float x1, x2,x3,x4,x5;
        float valor;
        float suma;
        float [] valoresU = new float[getRango()];
        
        if(getRango() == 2){
            //System.out.println("Entrando a rango 2");
            x2 = b[1] / MatrizU[1][1];
            valor = MatrizU[0][1] * x2;
            suma = b[0] - valor;
            x1 = suma / MatrizU[0][0];
            valoresU[0] = x1;
            valoresU[1] = x2;
            
            
        }
           
        if (getRango() == 3){
            //System.out.println("Entrando a rango 3");
            x3 = b[2] / MatrizU[2][2];
            valor = MatrizU[1][2] * x3;
            suma = b[1] - valor;
            x2 = suma / MatrizU[1][1];
            valor = MatrizU[0][1] * x2 + MatrizU[0][2] * x3;
            suma = b[0] - valor; 
            x1 = suma / MatrizU[0][0];
            valoresU[0] = x1;
            valoresU[1] = x2;
            valoresU[2] = x3;
               
        }

        if(getRango() == 4){
            //System.out.println("Entrando a rango 4");
            x4 = b[3] / MatrizU[3][3];
            valor = MatrizU[2][3] * x4;
            suma = b[2] - valor;
            x3 = suma / MatrizU[2][2];
            valor = MatrizU[1][2] * x3 + MatrizU[1][3] * x4;
            suma = b[1] - valor;
            x2 = suma / MatrizU[1][1];
            valor = MatrizU[0][1] *  x2 + MatrizU[0][2] * x3 + MatrizU[0][3] *  x4;
            suma = b[0] - valor;
            x1 = suma / MatrizU[0][0];
            valoresU[0] = x1;
            valoresU[1] = x2;
            valoresU[2] = x3;
            valoresU[3] = x4;
              
            }
           
        if(getRango() == 5){
            // System.out.println("Entrando a rango 5");
              x5 = b[4] / MatrizU[4][4];
              valor = MatrizU[3][4] * x5;
              suma = b[3] - valor;
              x4 = suma / MatrizU[3][3];
              valor = MatrizU[2][3] * x4 + MatrizU[2][4] * x5;
              suma = b[2] - valor; 
              x3 = suma / MatrizU[2][2];
              valor = MatrizU[1][2] * x3 + MatrizU[1][3] * x4 + MatrizU[1][4] * x5;
              suma = b[1] - valor;  
              x2 = suma / MatrizU[1][1];
              valor = MatrizU[0][1] * x2 + MatrizU[0][2] * x3 + MatrizU[0][3] * x4 + MatrizU[0][4] * x5;
              suma = b[0] - valor; 
              x1 = suma / MatrizU[0][0];
              valoresU[0] = x1;
              valoresU[1] = x2;
              valoresU[2] = x3;
              valoresU[3] = x4;
              valoresU[4] = x5;
       
        }
               

  return valoresU;  
 }
           
    public  void mostrar_resultadoL(float [] resultado){
   
        if(getRango() == 2){
             System.out.println("y1 = "+resultado[0]+" ");
             System.out.println("y2 = "+resultado[1]+" ");
           
        }
        if(getRango() == 3){
            System.out.println("y1 = "+resultado[0]+" ");
            System.out.println("y2 = "+resultado[1]+" ");
            System.out.println("y3 = "+resultado[2]+" ");
            
        }
        if(getRango() == 4){
            System.out.println("y1 = "+resultado[0]+" ");
            System.out.println("y2 = "+resultado[1]+" ");
            System.out.println("y3 = "+resultado[2]+" ");
            System.out.println("y4 = "+resultado[3]+" ");
            
        }
        if(getRango() == 5){
            System.out.println("y1 = "+resultado[0]+" ");
            System.out.println("y2 = "+resultado[1]+" ");
            System.out.println("y3 = "+resultado[2]+" ");
            System.out.println("y4 = "+resultado[3]+" ");
            System.out.println("y5 = "+resultado[4]+" ");
              
        }
       
     
    }
    
     public  void mostrar_resultadoU(float [] resultado){
   
        if(getRango() == 2){
             System.out.println("x1 = "+resultado[0]+" ");
             System.out.println("x2 = "+resultado[1]+" ");
           
        }
        if(getRango() == 3){
            System.out.println("x1 = "+resultado[0]+" ");
            System.out.println("x2 = "+resultado[1]+" ");
            System.out.println("x3 = "+resultado[2]+" ");
            
        }
        if(getRango() == 4){
            System.out.println("x1 = "+resultado[0]+" ");
            System.out.println("x2 = "+resultado[1]+" ");
            System.out.println("x3 = "+resultado[2]+" ");
            System.out.println("x4 = "+resultado[3]+" ");
            
        }
        if(getRango() == 5){
            System.out.println("x1 = "+resultado[0]+" ");
            System.out.println("x2 = "+resultado[1]+" ");
            System.out.println("x3 = "+resultado[2]+" ");
            System.out.println("x4 = "+resultado[3]+" ");
            System.out.println("x5 = "+resultado[4]+" ");
              
        }
  
    
    
}
}
    
    
    

    
              
                  
              
                  
              
                  
             
    
    
              
    


    

              
              
            
        
    

      
    
    
