
public class Determinate {
	float operador;
	String signo;
	float[][] matriz;
	float determinante;
	
	public Determinate(float[][] m,float x){
		matriz=m;
		operador=x;
	}
	
	public Determinate(float d,float[][]m){
		matriz=m;
		determinante=d;
	}

	public float getOperador() {
		return operador;
	}
	public void setOperador(float operador) {
		this.operador = operador;
	}
	public String getSigno() {
		return signo;
	}
	public void setSigno(String signo) {
		this.signo = signo;
	}
	public float[][] getMatriz() {
		return matriz;
	}
	public void setMatriz(int num) {
		this.matriz = new float [num][num];
	}
	public double getDeterminante() {
		return determinante;
	}
	public void setDeterminante(float determinante) {
		this.determinante = determinante;
	}
	
	
	
	

}
