package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Customer;
import model.Pizzeria;
import au.edu.uts.ap.javafx.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PizzeriaController extends Controller<Pizzeria> {

    @FXML private ListView<Customer> customerLv;
    @FXML private Button serveBtn;
    
    @FXML public void initialize() {
        serveBtn.disableProperty().bind(customerLv.getSelectionModel().selectedItemProperty().isNull());
    }
    
    public final Pizzeria getPizzeria() {
        return model;
    }
    
    private Customer getSelectedCustomer()
    {
        return customerLv.getSelectionModel().getSelectedItem();
    }
    
    @FXML private void handleServeCustomer(ActionEvent event) throws Exception
    {
        Customer customer = getSelectedCustomer();
        if(customer != null)
        {
            ViewLoader.showStage(customer, "/view/serve.fxml", "Serve customer", new Stage());
            //customerLv.getSelectionModel().clearSelection();
        }
    }
    
    @FXML private void handleAddCustomer(ActionEvent event) throws Exception
    {
        ViewLoader.showStage(getPizzeria(), "/view/customer_add.fxml", "Add customer", new Stage());
    }
    
    @FXML private void handleViewReport(ActionEvent event) throws Exception
    {
        ViewLoader.showStage(getPizzeria().getKitchen(), "/view/report.fxml", "Income report", new Stage());
    }
}
