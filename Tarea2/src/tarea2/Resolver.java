/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicaciontres;

/**
 *
 * @author Administrador
 */
public class Resolver {
    
    void resolver(double a[][], double[] b, double[] x, int n) {
    
    // cálculo de los resultados
    for (int i= n-1; i>=0; i--) {
      double sum= 0.0;
      for (int j= i+1; j<n; j++)
        sum += x[j]*a[i][j];
      x[i]= (b[i]-sum)/a[i][i];
    }
  }
    
     // Metódo de Gauss fase de suitución hacia atras.
/*public static int[] Sustitucion(int[][] matriz, int rango)
{
    int[] resultado = new int[rango];

    resultado[rango - 1] = matriz[rango - 1][rango] / matriz[rango- 1][rango-1];

    for (int f = rango- 2; f >= 0; f--)
    {
        int sum = 0;

        for (int i = rango - 1; i > f; i--)
        {
            sum += matriz[f][i] * resultado[i];
        }

        resultado[f] = (matriz[f][rango] - sum) / matriz[f][f];
    }

    return resultado;
    
}*/

 public static int [] resolver(int [][] matriz, int[] b, int n) {
    int [] x = new int [n];
    // cálculo de los resultados
    for (int i= n-1; i>=0; i--) {
      int sum= 0;
      for (int j= i+1; j<n; j++)
        sum += x[j]*matriz[i][j];
      x[i]= (b[i]-sum)/matriz[i][i];
    }
    return x;
 }
public static void mostrar_resultado(int [] resultado, int rango){
    
    for (int i= 0; i<rango; i++) {
        System.out.print(resultado[i]+" ");
     
    }
  }
}

    


