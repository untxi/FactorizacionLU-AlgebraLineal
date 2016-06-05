import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Matrices {
	 // A [operacion] B = C;
	//La inversa tambien se almacena en C
	float [][]A;
	 float [][]B;
	 float [][]C;
	 float [][]D;
	 String [][]R;
	 int paso=1;
	 List <float[][]> matrices;
	 List <String[][]> resultados; 
	ArrayList <Determinate> detList;
	ArrayList <Determinate> res;
	public	ArrayList <almacenar> mostrar;
	public void definir_matrices(String matriz,int col,int filas){
		switch (matriz){
		case "A":
			A = new float[filas][col];
			break;
		case "B":
			B = new float[filas][col];
			break;
		case "C":
			C= new float[filas][col];
			break;
		case "D":
			D= new float[filas][col];
			break;
		case "R":
			R=new String[filas][col];
		}
	}
	
	public void insertar_valores(String valor,int filas,int col,String matriz){
		switch (matriz){
		case "A":
			A[filas][col] = Float.parseFloat(valor);
			break;
		case "B":
			B [filas][col] = Float.parseFloat(valor);
			break;
		case "D":
			D [filas][col] = Float.parseFloat(valor);
			break;
		case "C":
			C [filas][col] = Float.parseFloat(valor);
		}
	}
	 
	public  void escalar(float f){
		
		this.resultados = new ArrayList<String[][]>();
		definir_matrices("C",A[0].length,A.length);
		for (int i=0;i<A.length;i++){
			for (int j=0;j<A[0].length;j++){
				imprimir_escalar(i,j,f);
				
				C[i][j]= A[i][j] * f; 

			}
		}
	}
	
	
	public  float determinante(float[][] matriz)
	{
	    
		 float det;
		    if(matriz.length==2)
		    {
		    	det=(matriz[0][0]*matriz[1][1])-(matriz[0][1]*matriz[1][0]);
		    	detList.add(new Determinate(matriz,det));
		        return det;
		    }
		    float suma=0;
		    for(int i=0; i<matriz.length; i++){
		    float[][] nm=new float[matriz.length-1][matriz.length-1];
		        for(int j=0; j<matriz.length; j++){
		            if(j!=i){
		                for(int k=1; k<matriz.length; k++){
		                int indice=-1;
		                if(j<i)
		                indice=j;
		                else if(j>i)
		                indice=j-1;
		                nm[indice][k-1]=matriz[j][k];
		                float val = matriz[j][k];
		                }
		            }
		        }
		        if(i%2==0){
		        	res.add(new Determinate(nm,matriz[i][0]));
		        	suma+=matriz[i][0] * determinante(nm);
		        }
		        else{
		         res.add(new Determinate(nm,-matriz[i][0]));
		         suma-=matriz[i][0] * determinante(nm);
		    }}
		    return suma;
	}
	

	
	public  void crear_identidad(float [][] mat){
		for (int i=0;i<mat.length;i++){
			for (int j=0;j<mat[0].length;j++){
				if (j==i){
				 mat[i][j]=1;
				}
				else{
					mat[i][j]=0;	
				}
			}
		}
	}
	
	
	
	public  void imprimir_matrices_suma(int x,int y){
		
		String [][] temporal;
		temporal= new String[C.length][C[0].length];
		paso = paso+1;
		for (int i=0;i<C.length;i++){
			for (int j=0;j<C.length;j++){
				if (x==i && j==y){
				temporal[i][j]=(A[i][j] +"+" + B[i][j] + "=" + C[i][j]+ " ");}
				else{
					temporal[i][j]=(C[i][j]+ " ");
				}
			}
			
		}
		resultados.add(temporal);
		
	
	}


public  void imprimir_escalar(int x,int y,float escalar){
	
	String [][] temporal;
	temporal= new String[C.length][C[0].length];
	paso = paso+1;
	for (int i=0;i<C.length;i++){
		for (int j=0;j<C.length;j++){
			if (x==i && j==y){
			temporal[i][j]=(A[i][j] +"*"+ escalar);}
			else{
				temporal[i][j]=(C[i][j]+ " ");
			}
		}
		
	}
	resultados.add(temporal);
	

}

	public  void sumar_matrices(){
		resultados = new ArrayList<String[][]>();
		definir_matrices("R",A[0].length,A.length);
		definir_matrices("C",A[0].length,A.length);
		for (int i=0;i<A.length;i++){
			for (int j=0;j<A[0].length;j++){
				C[i][j]= A[i][j] + B[i][j]; 
				imprimir_matrices_suma(i,j);
			}
		}
		
	}
	

	
	

	
public  void imprimir_matrices_mul(int x,int y,List<String>d){
		
		String [][] temporal;
		temporal= new String[C.length][C[0].length];
		paso = paso+1;
		for (int i=0;i<C.length;i++){
			for (int j=0;j<C.length;j++){
				if (x==i && j==y){

				temporal[i][j]=d.get(0);	
				for (int c=1;c<d.size()-1;c++){
						temporal[i][j]=temporal[i][j]+"+"+ d.get(c);
					}
					temporal[i][j]=temporal[i][j]+"+"+d.get(d.size()-1);
				}
				else{
					temporal[i][j]=(C[i][j]+ " ");
				}
			}
			
		}
		
		resultados.add(temporal);
		
	
	}
	
	public  void multiplicar_matrices(){
		definir_matrices("C",B[0].length,A.length);
		resultados = new ArrayList<String[][]>();
		List <String> datos= new ArrayList<String>() ;
		for (int i=0;i<A.length;i++){
			for (int j=0;j<B[0].length;j++){
				for (int z=0;z<A[0].length;z++){
					C[i][j]= C[i][j] + (A[i][z]* B[z][j]); 
					datos.add(A[i][z] + "*" + B[z][j]);
				}
			 imprimir_matrices_mul(i,j,datos);
			 datos.clear();
				
			}
		}
	}
	/***********************************************
	 	Inversa Mediante Gauss_Jordan
	 ***********************************************/
	public  void Gauss_Jordan(){
		definir_matrices("C",A.length,A.length);
		this.crear_identidad(C);
	
		for (int i=0;i<A.length;i++){
			for (int j=0;j<A.length;j++){
				//Para comprobar la diagonal
				if (i==j){
					if (A[i][j]==1){
						for (int x=0;x<i;x++){
							//El pivote niega la entra de A
							float pivote = -A[x][j];
							//Función auxiliar que multipica todos los valores de la fila por el pivote
							modificar_fila(pivote,x,i,j);
							
					}
						for (int x=i+1;x<A.length;x++){
							float pivote = -A[x][j]*A[i][j];
							modificar_fila(pivote,x,i,j);
							
						}
						
				}
					else{
						if (A[i][j]==0){
							localizar_fila(i,j);
						
						}
						//Para hacer un uno
						float escalar = 1/A[i][j];
						for (int y=0;y<A.length;y++){
							//Multiplico ese uno tanto en A como en C
							mostrar.add(new almacenar(A,C,"1/"+A[i][j]));
							A[i][y]=A[i][y]*escalar;
							C[i][y]=C[i][y]*escalar;
							
														
						}
						//Invoco a la función anterior, ya que tenemos un 1
						for (int x=0;x<i;x++){
							float pivote = -A[x][j];
							modificar_fila(pivote,x,i,j);
							
							
					}
						for (int x=i+1;x<A.length;x++){
						
							float pivote = -A[x][j]*A[i][j];
							modificar_fila(pivote,x,i,j);}
					
				
					}
					
			}
		
	}}}
	
	public  void localizar_fila(int f,int c){
		
		for (int j=0;j<A.length;j++){
			if (j!=f){
				if (A[j][c]!=0){
					cambiar_fila(f,j);
					j=A.length;
				}
				
			}
		}
	}

public  void cambiar_fila(int fila1,int fila2){
	mostrar.add(new almacenar(A,C,"F"+fila1+"<->"+"F"+fila2));
	float guardar;
	for (int j=0;j<A.length;j++){
		guardar=A[fila1][j];
		A[fila1][j]=A[fila2][j];
		A[fila2][j]=guardar;
		guardar=C[fila1][j];
		C[fila1][j]=C[fila2][j];
		C[fila2][j]=guardar;
	}
	
}
	
	public  void modificar_fila(float pivote,int FIL,int COLAct,int FILAct){
		//Recorre toda la matriz, pero unicamente cambia la columna
		//Hace la operación de #*Fn+Fn+1
		for (int i=0;i<A.length;i++){
			float j=pivote*A[FILAct][i];
			float c_cambio=pivote*C[FILAct][i];
			
			mostrar.add(new almacenar(A,C,pivote+"F"+FILAct+"+"+"F"+FIL));
			C[FIL][i]=c_cambio+C[FIL][i];
			A[FIL][i]=j+A[FIL][i];
			
	
		}
		}
	
	public  void cofactores(){
		this.matrices = new ArrayList <float[][]>();
		ArrayList<String> resdet= new ArrayList <String>();
		
		definir_matrices("C",A.length,A.length);
		this.crear_identidad(C);
		float detA = determinante(A);
		if (detA!=0){
		for (int i=0;i<A.length;i++){
			for (int j=0;j<A.length;j++){
				matrices.add(extraer_matriz(i,j));
				
			}
		}
		int puntero=0;
		for (int i=0;i<C.length;i++){
			for (int j=0;j<C.length;j++){
				float[][] valor = matrices.get(puntero);
				
				
				C[j][i]=determinante(valor);
			    if (puntero%2!=0){
			    	C[j][i]=-1*C[j][i];
			    }
			    resdet.add("El determinante es "+C[j][i]);
				puntero ++;
			}
		}
		
		
		
		escalar(1/detA);
		
		
		}
		FileWriter fichero = null;
	    PrintWriter pw = null;
		try
	    {
	        fichero = new FileWriter("prueba.txt",true);
	        pw = new PrintWriter(fichero);
		
		int p=0;
		for (int i=0;i<C.length;i++){
			for (int j=0;j<C.length;j++){
				pw.println("La matriz de cofactores en"+ i+","+j);
				for (int z=0;z<matrices.get(p).length;z++){
					for (int x=0;x<matrices.get(p).length;x++){
						pw.print(matrices.get(p)[z][x]+" ");
					}
					pw.println();
					}
				pw.println(resdet.get(p));
				p++;
			}
		}
		
		
		pw.println("Este es C sin trasponer: ");
		for (int i=0;i<C.length;i++){
			for (int j=0;j<C.length;j++){
				pw.print(C[j][i]+" ");
			}
			pw.println();
		}
		
		pw.println("Este es C tranpuesto");
		for (int i=0;i<C.length;i++){
			for (int j=0;j<C.length;j++){
				pw.print(C[i][j]+" ");
			}
			pw.println();
		}
		
	    }catch (Exception e) {
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
	
	public  float[][] extraer_matriz(int fila,int columna){
		float [][]aux = new float[A.length-1][A.length-1];
		int i=0;
		int j=0;
		boolean entro=false;
		int conti=0;
		while (conti<A.length){
			int contj=0;
			if (entro){
				i++;
				j=0;
				entro=false;
			}
			while (contj<A.length){
			if (conti!=fila && contj!=columna){
				aux[i][j] = A[conti][contj];
				j++;
				entro=true;
			}
			contj++;
			}
			conti++;
			}
		return aux;
		}

public void imprimir_pasos(){
	FileWriter fichero = null;
    PrintWriter pw = null;
    try
    {
        fichero = new FileWriter("prueba.txt");
        pw = new PrintWriter(fichero);
        for (Determinate x:res){
        	pw.println();
        	pw.print(x.operador + "  * ");
        	pw.print("det(");
			for (int i=0;i<x.matriz.length;i++){
				
				pw.print(x.matriz[0][i]+"   ");
				
			}
			
			for (int i=1;i<x.matriz.length;i++){
				pw.println();
				pw.print("           ");
				for (int j=0;j<x.matriz.length;j++){
					
					pw.print(x.matriz[i][j]+"   ");
			
			}}
			pw.print(") ");
		}
        pw.println();
        pw.println("-------------Determinates------------------");
		for (Determinate x:detList){
			pw.println("La matriz es ");
			for (int i=0;i<x.matriz.length;i++){
				for (int j=0;j<x.matriz[0].length;j++){
					pw.print(x.matriz[i][j]+" ");
				}
				pw.println();
			}
			pw.println("El determinante de la matriz es:"+x.determinante);
			pw.println("Ya qué:"+x.matriz[0][0] +"*" +x.matriz[1][1]+ "-"+x.matriz[0][1]+"*"+x.matriz[1][0] );
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
