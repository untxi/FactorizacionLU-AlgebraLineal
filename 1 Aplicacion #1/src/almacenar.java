import java.io.FileWriter;
import java.io.PrintWriter;

public class almacenar {
	float [][]Inversa;
	float[][] Real;
	String operacion;
	float [][] total;
	float [][] EsDet;
	
	
	public almacenar(float[][] A,float[][]C,String op){
		Real=A;
		Inversa=C;
	//	EsDet = D;
		operacion=op;
		int k=0;
		total = new float[A.length][A.length*2];
		for (int i=0;i<total.length;i++){
			for (int j=0;j<total[0].length;j++){
				if (j==A.length){
					k=j;
					for (int z=0;z<C.length;z++){
					total[i][k]=C[i][z];
					k+=1;}
					j=total[0].length;
					
				}
				else{
					total[i][j]=A[i][j];
				}
			}
		}
		
	
	}
	
	public void impresion(){
		
		FileWriter fichero = null;
	    PrintWriter pw = null;
	    try
	    {
	        fichero = new FileWriter("prueba.txt",true);
	        pw = new PrintWriter(fichero);
	        
			pw.println("Operación de fila que se llevo sobre"+operacion + "   ");
			for (int i=0;i<total.length;i++){
				for (int j=0;j<total[0].length;j++){
					if (j==Inversa.length){
						pw.print(" | ");
					}
					pw.print(total[i][j]+" ");
				}
				pw.println();
			}
	   
		
	        

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	       try {
	       // Nuevamente aprovechamos el finally para 
	       // asegurarnos que se cierra el fichero.
	       if (null != fichero)
	          fichero.close();
	       } catch (Exception e2) {
	          e2.printStackTrace();
	       }
	    }
	}
	
	
}
