package tarea2;
import java.util.Scanner;

public class FactorizacionLU2 {

    public static void main(String[] args) {
        //aqui entra el rango
        System.out.println("digite rango: ");
        Scanner sc = new Scanner(System.in);
        int rango = sc.nextInt();
        Scanner vc = new Scanner(System.in);
        
        MatrizLU matriz = new MatrizLU(rango);
        for (int i = 0; i < matriz.getRango(); i++)
        {
            for(int j = 0; j < matriz.getRango(); j++)
            {
                // Aqui entra la matriz
                System.out.print("Columna: " + i + " Fila: " + j + " --> ");
                //System.out.print();
                float valor = vc.nextFloat();
            }
        }
        // matriz L
        float [][] Lower = matriz.L;
        
        //vector 
        float [] vector = new float[matriz.getRango()];
        for(int k = 0; k < matriz.getRango(); k++)
        {
            System.out.println("Elemento Vector ["+k+"]: ");
            float vec = sc.nextInt();
            vector[k] = vec;
        }
        //imprime vector
        for(int i = 0; i < matriz.getRango(); i++)
        {
            System.out.println("Vector b");
            System.out.println(vector[i]);
        }
        // encontrar y
        float [] x_array = new float[matriz.getRango()];
        for(int k = 0; k < matriz.getRango(); k++)
        {
            x_array[k]=(float)k;
        }
        // Calcula Y
        float [] vector_y = new float[matriz.getRango()];
        for(int i = 0; i < matriz.getRango(); i++)
        {
            float y;
            y = 1/Lower[i][i];
            float sum =0; 
            for(int j = 0; j < (i-1);i++)
            {
                sum = sum + (Lower[i][j] * x_array[j]);
            }

            y = y * (vector[i] - sum);
            vector_y[i]=y;
            System.out.println("y "+i+" ="+y); // muestra y
        }
        // Calcular y
        float [][] Upper = matriz.U;
        
        float [] vector_x = new float[matriz.getRango()];
        for(int i = 0; i < matriz.getRango(); i++)
        {
            float x = 0;
            x = 1/Upper[i][i];
            float sum =0;
            int myJ = matriz.getRango()-1;
            for(int j = (i+1); j > 0;j--)
            {
                sum = sum + (Upper[i][j] * x_array[j]);
            }
            //System.out.println(x);
            x = x * (vector_y[i] - sum);
            vector_x[myJ] = x;
            System.out.println("x " +i+ " = " + x); // muestra y
            
            myJ--;
        }
    }
}
