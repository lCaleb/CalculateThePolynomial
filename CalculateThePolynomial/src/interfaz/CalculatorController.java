package interfaz;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import modelo.Graeffe;


public class CalculatorController {
	


	

		@FXML private Button generarAleatorio;
		@FXML private Button generarPolinomio;
		@FXML private SplitMenuButton seleccionGrado;
		
		@FXML private MenuItem uno;
		@FXML private MenuItem dos;
		@FXML private MenuItem tres;
		@FXML private MenuItem cuatro;
		@FXML private MenuItem cinco;
		@FXML private MenuItem seis;
		@FXML private MenuItem siete;
		@FXML private MenuItem ocho;
		@FXML private MenuItem nueve;
		@FXML private MenuItem diez;
		
		@FXML
		private TextArea mostrarRaices;
		
		@FXML
		private Button calcular;

		@FXML private GridPane polinomio;
		
		  private TextField independient;
		
		private int polynomialDegree;
		
		private Graeffe graefe;
		public CalculatorController() {
			
		}
		
//		public Pane iWantToSleepPane() {
//			Pane panel= new Pane();
//			panel.setPrefHeight(45);
//			panel.setPrefWidth(65);
//			Label x= new Label("X");
//			x.setFont(Font.font("Gabriola", 39.0));
//			panel.setC
//			
//			
//			
//		}
		
		public void initialize() {
			
		}
		
		public void calcular() {
			double[] poly= new double[polynomialDegree+1];
			int i = 0;
			for (; i < poly.length-1; i++) {
				ElementPolynomial element= (ElementPolynomial)polinomio.getChildren().get(i);
				poly[i]=element.getCoeficiente();
			}
			poly[i]=Integer.parseInt(independient.getText());
			graefe= new Graeffe(poly);
			mostrarRaices.setText(graefe.showRoots());
			
		}
		
	
		public void ponerAleatorio() {
			polynomialDegree= (int)(Math.random()*9)+1;
			System.out.println(polynomialDegree+"");
			generarPolinomio();
			int i = 0;
			for (; i < polynomialDegree; i++) {
				ElementPolynomial element= (ElementPolynomial)polinomio.getChildren().get(i);
				int random= (int)(Math.random()*1000)+1;
				element.setCoeficiente(random);
			}
			int randomi= (int)(Math.random()*1000)+1;
			independient.setText(randomi+"");
			
		}
		
		
		public void loadPolynommial(int degree) {
			int i=0;
			for (; i < degree-1; i++) {
				ElementPolynomial a=new ElementPolynomial();
				a.setExponent(degree-i);
				polinomio.add(a, i, 0);
			}
			ElementPolynomial b=new ElementPolynomial();
			b.setExponent();
			polinomio.add(b, i, 0);
			
			independient= new TextField();
			independient.setPrefSize(35, 23);
			independient.setStyle("-fx-font-size: 12px; -fx-font: Arial Rounded MT Bold; ");
			polinomio.add(independient, i+1, 0);
		}
		public void generarAleatorio() {}
		public void generarPolinomio() {
			polinomio.getChildren().clear();
			loadPolynommial(polynomialDegree);
		}
		public void seleccionGrado() {}
		public void polinomioEnElGrid() {}
		
		public void uno() {
			polynomialDegree=1;
			seleccionGrado.setText("Grado Uno");
		}
		public void dos() {
			polynomialDegree=2;
			seleccionGrado.setText("Grado Dos");
		}
		public void tres() {
			polynomialDegree=3;
			seleccionGrado.setText("Grado Tres");
		}
		public void cuatro() {
			polynomialDegree=4;
			seleccionGrado.setText("Grado Cuatro");
		}
		
		public void cinco() {
			polynomialDegree=5;
			seleccionGrado.setText("Grado Cinco");
		}
		
		public void seis() {
			polynomialDegree=6;
			seleccionGrado.setText("Grado Seis");
		}
		public void siete() {
			polynomialDegree=7;
			seleccionGrado.setText("Grado Siete");
		}
		public void ocho() {
			polynomialDegree=8;
			seleccionGrado.setText("Grado Ocho");
		}
		public void nueve() {
			polynomialDegree=9;
			seleccionGrado.setText("Grado Nueve");
		}
		public void diez() {
			polynomialDegree=10;
			seleccionGrado.setText("Grado Diez");
		}
		
		
		
		
		
	

}
