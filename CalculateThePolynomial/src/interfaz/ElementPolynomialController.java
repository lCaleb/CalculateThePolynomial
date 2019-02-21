package interfaz;

import java.io.IOException;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ElementPolynomialController  {

	@FXML
	private TextField coeficiente;

	@FXML
	private Label exponente;

	@FXML
	private Label signo;

	public ElementPolynomialController(){
		
	}

	public void setExponent(int value) {
		this.exponente.setText(value + "");
	}
	public void setExponent() {
		this.exponente.setText("");
	}
	public void setSigno(String value) {
		signo.setText(value + "");
	}

	public int getCoeficiente() {
		return Integer.parseInt(coeficiente.getText());
	}
}
