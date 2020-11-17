package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Pizzeria;

public class CustomerAddController extends Controller<Pizzeria> {
    public final Pizzeria getPizzeria() { return model;}
    
    @FXML private Button addBtn;
    @FXML private Text message;
    @FXML private TextField numberTxt;
    @FXML private TextField nameTxt;
    
    @FXML public void initialize() {
        addBtn.disableProperty().bind(Bindings.or(numberTxt.textProperty().isEmpty(),nameTxt.textProperty().isEmpty()));
    }
    
    @FXML private void handleAdd(ActionEvent event) throws Exception
    {
        try {
            getPizzeria().addCustomer(numberTxt.getText(),nameTxt.getText());
            stage.close();
        }
        catch (Exception e) {
            message.textProperty().set(e.getMessage());
        }
    }
    
    @FXML private void handleCancel(ActionEvent event) throws Exception
    {
        stage.close();
    }
    
}
