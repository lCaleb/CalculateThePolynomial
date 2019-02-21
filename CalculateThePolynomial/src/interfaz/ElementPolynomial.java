package interfaz;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class ElementPolynomial extends Pane{
	
	private Node view;
	private ElementPolynomialController controller;
	
	public ElementPolynomial() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ElementPolynomial.fxml"));
//		controller= new ElementPolynomialController();
//		fxmlLoader.setController(controller);
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                return controller = new ElementPolynomialController();
            }
        });
        try {
            view = (Node) fxmlLoader.load();

        } catch (IOException ex) {
        	System.out.println("paila");
        }
        getChildren().add(view);
	}

	public void setExponent(int value) {
		controller.setExponent(value);
	}
	public void setExponent() {
		controller.setExponent();
	}

	public void setSigno(String value) {
		controller.setSigno(value);
	}

	public int getCoeficiente() {
		return controller.getCoeficiente();
	}
}
