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
 * @author gabriel
 */
public class MatrizLU {
    
    private int rango;
    private int [][] A;
    public float [][] matriz;
    public float [][] U;
    public float [][] L;
    private float pivote_actual;
    private DecimalFormat decimales = new DecimalFormat("0.00");
    
    MatrizLU(int rango){
        this.rango = rango;
        this.A = new int[rango][rango];
        this.matriz = new float[rango][rango];
        this.U = new float[rango][rango];
        this.L = new float[rango][rango];
        llenarMatrizL();
    }
 
    
/*---------------------------------------------------------
| quien sabe que carajos hará este método                 |
----------------------------------------------------------*/
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
                A[i][j] = (int)(Math.random()*9+1);
            }
        }
        llenarMatrizM();
    }

    private void llenarMatrizM(){
        for(int i = 0; i < getRango(); i++){
            for(int j = 0; j < getRango(); j++){
                matriz[i][j] = (float)A[i][j];
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

}

