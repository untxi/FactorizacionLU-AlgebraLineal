import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class calculadora {

	private JFrame frmCalculadoraDeMatrices;
	private JTable tableA;
	private Matrices logica;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JRadioButton rdbtnGaussjordan;
	private JRadioButton rdbtnMatrizDeCofactores;
	private JRadioButton rdbtnA;
	private JRadioButton rdbtnB;
	private JTextField txtEscalar;
	private JRadioButton rdbtnEscalar;
	private JTable tableC;
	private JTabbedPane tabbedPane;
	private JTable Pasos;
	private JButton btnPasoSig;
	private JButton btnPasoAnterior;
	private int pos=0;
	private JTextArea txttexto;
	private String operacion_mostrar="";
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calculadora window = new calculadora();
					window.frmCalculadoraDeMatrices.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public calculadora() {
		logica = new Matrices();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmCalculadoraDeMatrices = new JFrame();
		frmCalculadoraDeMatrices.setTitle("Aplicacion #!");
		frmCalculadoraDeMatrices.setBounds(100, 100, 780, 480);
		frmCalculadoraDeMatrices.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculadoraDeMatrices.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 744, 419);
		frmCalculadoraDeMatrices.getContentPane().add(tabbedPane);
	       
	       JPanel panel = new JPanel();
	       tabbedPane.addTab("Aplicacion", null, panel, null);
	       panel.setLayout(null);
	       
	       JPanel matA = new JPanel();
	       matA.setBounds(10, 75, 264, 152);
	       panel.add(matA);
	       matA.setLayout(null);
	       
	       tableA = new JTable();
	       tableA.setBounds(23, 33, 216, 83);
	       matA.add(tableA);
	       tableA.setName("A");
	       inicializar_matrices(2,2,tableA);
	       
	       
	       JPanel MatB = new JPanel();
	       MatB.setBounds(282, 75, 288, 184);
	       panel.add(MatB);
	       MatB.setLayout(null);
	       
	       JLabel lblDeterminate = new JLabel("Probar que es linealmente");
	       lblDeterminate.setBounds(6, 6, 166, 14);
	       MatB.add(lblDeterminate);
	       
	       rdbtnA = new JRadioButton("A");
	       rdbtnA.setBounds(16, 32, 33, 23);
	       MatB.add(rdbtnA);
	       buttonGroup_3.add(rdbtnA);
	       

	       txtEscalar = new JTextField();
	       txtEscalar.setBounds(99, 70, 99, 20);
	       MatB.add(txtEscalar);
	       txtEscalar.setColumns(10);
	       
	       JButton btnGuardarCambios = new JButton("Calcular");
	       btnGuardarCambios.setBounds(60, 102, 112, 23);
	       MatB.add(btnGuardarCambios);
	       
	       JLabel lblEscalar = new JLabel("Escalar");
	       lblEscalar.setBounds(6, 72, 55, 16);
	       MatB.add(lblEscalar);
	       btnGuardarCambios.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
	       		
	       	
	       		
	       		
	       		if (rdbtnGaussjordan.isSelected()){
	       			if (comprobar("cuadrada")){
	       				operacion_mostrar="DI";
	       				if (guardar_datos(tableA)){ 
	       				
	       				logica.mostrar=new ArrayList<almacenar>();
	       				logica.detList=new ArrayList<Determinate>();
	       				float det = logica.determinante(logica.A);
	       				if (det!=0){
	       					logica.Gauss_Jordan();
	       					llenar_resultado();
	       					
	       				}
	       				else{
	       					JOptionPane optionPane = new JOptionPane("El determinante es 0",JOptionPane.ERROR_MESSAGE);
	       					 	
	       				}
	       				}
	       				}
	       			else{
	       				JOptionPane optionPane = new JOptionPane("La matriz A debe ser cuadrada",JOptionPane.ERROR_MESSAGE);
	       				
	       			}
	       			
	       		}
	       		if(rdbtnA.isSelected()){
	       	      				
	       				try{
		       				operacion_mostrar="E";
		       				
		       			if (guardar_datos(tableA)){
		       				
		       			 String dato= txtEscalar.getText();
		       			
		       			 
		       			 float num = Float.parseFloat(dato);
		       			 if (num == 0){
		       				JOptionPane optionPane = new JOptionPane("Es linealmente independiente ",JOptionPane.INFORMATION_MESSAGE);
		       				 JDialog dialog = optionPane.createDialog("Información");
		       				 dialog.setVisible(true);
		       			 }
		       			
		       			 logica.escalar(num);
		       			 
		       			llenar_resultado();}
		       			
		       			if (comprobar("cuadrada")){
								operacion_mostrar="DI";
								if(guardar_datos(tableA)){
									
					       			
					       			logica.detList = new ArrayList<Determinate>();
				       				logica.res = new ArrayList<Determinate>();
				       				float det=logica.determinante(logica.A);
				       				imprimir_determinante(0);
				       				if (det ==0){
				       					JOptionPane optionPane = new JOptionPane("Es linealmente independiente "+det,JOptionPane.INFORMATION_MESSAGE);
					       				 JDialog dialog = optionPane.createDialog("Información");
					       				 dialog.setVisible(true);

				       				}
				       				else{
					       				JOptionPane optionPane = new JOptionPane("es linealmente dependiente",JOptionPane.ERROR_MESSAGE);
					       				 JDialog dialog = optionPane.createDialog("¡Ops!");
					       				 dialog.setVisible(true);
					       			}
									
									}
								}
		       			
		       			
		       			}
		       			
		       			catch(NumberFormatException ex){
		       				
		       				JOptionPane optionPane = new JOptionPane("Parece que el valor ingresado no es válido",JOptionPane.ERROR_MESSAGE);
		       				 JDialog dialog = optionPane.createDialog("¡Cuidado!");
		       				 dialog.setVisible(true);
		       			}
	       				
	       			
	       				}
		       			
	     
	       		if (rdbtnMatrizDeCofactores.isSelected()){
	       			if (comprobar("cuadrada")){
	       					if (tableA.getRowCount()!=2){
	       				if(guardar_datos(tableA)){
	       					operacion_mostrar="DI";
	       					logica.detList=new ArrayList<Determinate>();
	       					logica.res=new ArrayList<Determinate>();
	       				float det = logica.determinante(logica.A);
	       				if (det!=0){
	       					logica.detList=new ArrayList<Determinate>();
	       					logica.res=new ArrayList<Determinate>();
	       					logica.cofactores();
	       					llenar_resultado();
	       					
	       				}
	       				else{
	       					JOptionPane optionPane = new JOptionPane("El determinante es 0",JOptionPane.ERROR_MESSAGE);
	       					 JDialog dialog = optionPane.createDialog("¡No invertibe!");
	       					 dialog.setVisible(true);
	       					
	       				}
	       				}
	       			}else{
	       				JOptionPane optionPane = new JOptionPane("No es posible invertir una matriz de 2x2 con este método",JOptionPane.ERROR_MESSAGE);
	       				 JDialog dialog = optionPane.createDialog("¡No invertible!");
	       				 dialog.setVisible(true);
	       			}
	       					}
	       			else{
	       				JOptionPane optionPane = new JOptionPane("La matriz A debe ser cuadrada",JOptionPane.ERROR_MESSAGE);
	       				 JDialog dialog = optionPane.createDialog("¡No invertible!");
	       				 dialog.setVisible(true);
	       			}
	               }
	       		
	       		
	       		//----------------------------------------------------------------------------------------------------
	       		if (rdbtnEscalar.isSelected()){
	       		}
	       		
	       		
	       	}
	       });
	       
	       //-------------------------------------------------------------------------------------------------------------
	       JComboBox cboxFilasA = new JComboBox();
	       cboxFilasA.setBounds(34, 252, 79, 20);
	       panel.add(cboxFilasA);
	       cboxFilasA.setModel(new DefaultComboBoxModel(new String[] {"Filas", "1", "2", "3", "4", "5"}));
	       
	       
	       
	       JComboBox cboxColA = new JComboBox();
	       cboxColA.setBounds(123, 252, 100, 20);
	       panel.add(cboxColA);
	       cboxColA.setModel(new DefaultComboBoxModel(new String[] {"Columnas", "1", "2", "3", "4", "5"}));
	       
	       JButton btnGenerarmatrizA = new JButton("Generar_Matriz A");
	       btnGenerarmatrizA.setBounds(55, 284, 146, 23);
	       panel.add(btnGenerarmatrizA);
	       btnGenerarmatrizA.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
	       		generar_tabla(cboxColA,cboxFilasA,tableA);
	       	}
	       });
	       
	       JLabel lblMatrizA = new JLabel("Matriz A");
	       lblMatrizA.setBounds(90, 50, 79, 14);
	       panel.add(lblMatrizA);
	       
	       rdbtnGaussjordan = new JRadioButton("Gauss-Jordan");
	       buttonGroup_3.add(rdbtnGaussjordan);
	       rdbtnGaussjordan.setBounds(642, 18, 112, 23);
	       panel.add(rdbtnGaussjordan);
	       rdbtnGaussjordan.setVisible(false);
	       
	       
	       rdbtnMatrizDeCofactores = new JRadioButton("Matriz de Cofactores");
	       buttonGroup_3.add(rdbtnMatrizDeCofactores);
	       rdbtnMatrizDeCofactores.setBounds(640, 46, 146, 23);
	       panel.add(rdbtnMatrizDeCofactores);
	       
	       rdbtnB = new JRadioButton("B");
	       rdbtnB.setBounds(690, 75, 48, 23);
	       panel.add(rdbtnB);
	       buttonGroup_3.add(rdbtnB);
	       
	       
	       rdbtnEscalar = new JRadioButton("Escalar");
	       rdbtnEscalar.setBounds(659, 110, 95, 23);
	       panel.add(rdbtnEscalar);
	       buttonGroup_3.add(rdbtnEscalar);
	       rdbtnMatrizDeCofactores.setVisible(false);
	       rdbtnB.setVisible(false);
	       rdbtnEscalar.setVisible(false);
	       
	       JPanel panelres = new JPanel();
	       tabbedPane.addTab("Resultado", null, panelres, null);
	       panelres.setLayout(null);
	       
	       
	       JLabel lblResultadoDeLa = new JLabel("Resultado de la Operaci\u00F3n");
	       lblResultadoDeLa.setBounds(20, 25, 230, 14);
	       panelres.add(lblResultadoDeLa);
	       
	       tableC = new JTable();
	       tableC.setBounds(34, 101, 286, 83);
	       panelres.add(tableC);
	       
	       JLabel label = new JLabel("");
	       label.setBounds(429, 25, 46, 14);
	       panelres.add(label);
	       
	       JLabel lblescalar = new JLabel("New label");
	       lblescalar.setVisible(false);
	       lblescalar.setBounds(346, 101, 55, 43);
	       panelres.add(lblescalar);
	       
	       JButton btnVerPasoA = new JButton("Ver Paso a Paso");
	       btnVerPasoA.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
	       		
	       		switch (operacion_mostrar){
	       		case("SRM"):
	       			Pasos.setVisible(true);
	       		    btnPasoSig.setVisible(true);
	       		    btnPasoAnterior.setVisible(true);
	       		    txttexto.setVisible(false);
	       		    lblescalar.setVisible(false);
	       		    mostrar_pasos();
	       		    
	       		    break;
	       		case("DI"):
	       			txttexto.setVisible(true);
	       		Pasos.setVisible(false);
	       	    btnPasoSig.setVisible(false);
	       	    btnPasoAnterior.setVisible(false);
	       	    lblescalar.setVisible(false);
	       		if (rdbtnGaussjordan.isSelected()){
	       			imprimir_determinante(1);
	       		}
	       		if (rdbtnMatrizDeCofactores.isSelected()){
	       			imprimir_determinante(2);
	       		}
	       		else{
	       			imprimir_determinante(0);
	       		}
	       		break;
	       		case("E"):
	       			
	       			lblescalar.setVisible(true);
	       			lblescalar.setText(txtEscalar.getText());
	       			Pasos.setVisible(true);
	       		    btnPasoSig.setVisible(true);
	       		    btnPasoAnterior.setVisible(true);
	       		    txttexto.setVisible(false);
	       		    logica.resultados=new ArrayList<String[][]>();
	       			mostrar_pasos();
	       			
	       			
	       		}
	       
	       	
	       	
	       	}
	       });
	       btnVerPasoA.setBounds(429, 21, 147, 23);
	       panelres.add(btnVerPasoA);
	       
	       Pasos = new JTable();
	       Pasos.setVisible(false);
	       Pasos.setBounds(413, 88, 286, 85);
	       panelres.add(Pasos);
	       
	       btnPasoSig = new JButton("Paso Siguiente");
	       btnPasoSig.setVisible(false);
	       btnPasoSig.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
	       		pos=pos+1;
	       		mostrar_pasos();
	       	}
	       });
	       btnPasoSig.setBounds(565, 280, 119, 23);
	       panelres.add(btnPasoSig);
	       
	       btnPasoAnterior = new JButton("Paso Anterior");
	       btnPasoAnterior.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
	       		pos=pos-1;
	       		mostrar_pasos();
	       	}
	       });
	       btnPasoAnterior.setVisible(false);
	       btnPasoAnterior.setBounds(429, 280, 114, 23);
	       panelres.add(btnPasoAnterior);
	       
	       txttexto = new JTextArea();
	       txttexto.setVisible(false);
	       txttexto.setBounds(413, 61, 300, 146);
	       
	          JScrollPane scroll = new JScrollPane(txttexto);    
	          scroll.setBounds(new Rectangle(404, 51, 309, 217));                                                                     
	          panelres.add(scroll);   
	          
	          JPanel panel_1 = new JPanel();
	          tabbedPane.addTab("New tab", null, panel_1, null);
		
		
	}
	
	public void inicializar_matrices(int col,int fil,JTable generar){
		
		DefaultTableModel model = new DefaultTableModel();
        for (int i=0;i<col;i++){
        	model.addColumn("Col"+i);
        }
		generar.setModel(model);
		Object[] linea = new Object[col]; 
		for (int i=0;i<col;i++){
			linea[i]="0";
		}
		for (int i=0;i<fil;i++){
			model.addRow(linea);
		}
		
		generar.setModel(model);
		
}
	public void generar_tabla(JComboBox Col,JComboBox Fil,JTable table){
		if (Col.getSelectedIndex()==0 || Col.getSelectedIndex()==0){
			 JOptionPane optionPane = new JOptionPane("Seleccione Fila y Columna",JOptionPane.WARNING_MESSAGE);
			 JDialog dialog = optionPane.createDialog("¡Cuidado!");
			 dialog.setVisible(true);
		}
		else{
		String col = Col.getSelectedItem().toString();
		String fil =Fil.getSelectedItem().toString();
		
		inicializar_matrices(Integer.parseInt(col),Integer.parseInt(fil),table);
		
	}
	}
	
	public boolean guardar_datos(JTable Actual){
		String matriz=" ";
		switch (Actual.getName()){
		case "A":
			matriz="A";
			break;
		case "B":
			matriz="B";
			break;
		case "C":
			matriz="C";
			break;
	
		}
		
	   
		logica.definir_matrices(matriz, Actual.getModel().getColumnCount(), Actual.getRowCount());
		
		
		for (int i=0;i<Actual.getRowCount();i++){
			for (int j=0;j<Actual.getModel().getColumnCount();j++){
			try{
				String valor = (String) Actual.getValueAt(i,j);
				logica.insertar_valores(valor, i, j, matriz);
				
			}
			catch (NumberFormatException ex){
				JOptionPane optionPane = new JOptionPane("No se puede procesar el valor ingresado en la fila "+i+",columna "+j,JOptionPane.ERROR_MESSAGE);
				 JDialog dialog = optionPane.createDialog("¡Error!");
				 dialog.setVisible(true);
				 return false;
			}
		
		
			}
		} return true;
		
		
    }
	
	public boolean comprobar(String ope){
		boolean ret = false;
		switch (ope){
		
		case "cuadrada":
			if (tableA.getRowCount()==tableA.getColumnCount()){
				ret=true;
			}
			break;
		case "det":
			
		}
		return ret;
		
		}
	
public void llenar_resultado(){
	DefaultTableModel model = new DefaultTableModel();
	int col=logica.C[0].length;
	int fil=logica.C.length;
    for (int i=0;i<col;i++){
    	model.addColumn("Col"+i);
    }
	tableC.setModel(model);
	Object[] linea = new Object[col]; 
	for (int i=0;i<col;i++){
		linea[i]="0";
	}
	for (int i=0;i<fil;i++){
		model.addRow(linea);
	}
	for (int i=0;i<fil;i++){
		for (int j=0;j<col;j++){
			model.setValueAt(logica.C[i][j],i ,j);
		}
	}
	
	tableC.setModel(model);
	tabbedPane.setSelectedIndex(1);
	

    
}

public void mostrar_pasos() {
	if (pos<=0){
		this.btnPasoAnterior.setEnabled(false);
	}
	
	if (pos<logica.resultados.size() && pos>=0){
		this.btnPasoAnterior.setEnabled(true);
		this.btnPasoSig.setEnabled(true);
		DefaultTableModel model = new DefaultTableModel();
		int col=logica.C[0].length;
		int fil=logica.C.length;
    for (int i=0;i<col;i++){
    	model.addColumn("Col"+i);
    }
	Pasos.setModel(model);
	Object[] linea = new Object[col]; 
	for (int i=0;i<col;i++){
		linea[i]="0";
	}
	for (int i=0;i<fil;i++){
		model.addRow(linea);
	}
	for (int i=0;i<fil;i++){
		for (int j=0;j<col;j++){
			model.setValueAt(logica.resultados.get(pos)[i][j],i ,j);
		}
	}
	
	
	Pasos.setModel(model);
	}
	else{
		if (pos > logica.resultados.size()){
		this.btnPasoSig.setEnabled(false);
		}
	}
	
}
public void imprimir_determinante(int t){
	txttexto.setText("");
	//T=0 determinante T=1 Inversa
	if (t==0){
	logica.imprimir_pasos();}
	if (t==1){
		for (almacenar x:logica.mostrar){
			x.impresion();
			
			
		}
	}
	String Text;
	try{  
        FileReader Fichero=new FileReader("prueba.txt");
     BufferedReader leer=new BufferedReader(Fichero);
     while((Text=leer.readLine())!=null){
        txttexto.append(Text+ "\n"); 
       }
     leer.close();
            
      File archivo = new File("prueba.txt");
      archivo.delete();
     }catch(IOException e){};
   

}

public void escalar(){
	
}
}
