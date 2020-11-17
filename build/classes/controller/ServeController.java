package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.text.DecimalFormat;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Customer;
import model.Pizza;

public class ServeController extends Controller<Customer>{
    public final Customer getCustomer() { return model;}
    
    @FXML private ComboBox<Pizza> orderedCb;
    @FXML private ListView<Pizza> orderLv;
    @FXML private Button selectBtn;
    @FXML private Button submitBtn;
    @FXML private Text orderPriceTxt;
    
    @FXML public void initialize() {
        selectBtn.disableProperty().bind(orderedCb.valueProperty().isNull());
        submitBtn.disableProperty().bind(Bindings.size(getCustomer().getOrder()).isEqualTo(0));
        orderPriceTxt.textProperty().bind(getCustomer().orderPriceProperty().asString("$%.2f"));
    }
    
    @FXML private void handleSelectOrdered(ActionEvent event) throws Exception
    {
        Pizza pizza = orderedCb.getSelectionModel().getSelectedItem();
        if(pizza != null)
        {
            pizza.order();
            orderedCb.getSelectionModel().clearSelection();
        }
    }
    
    @FXML private void handleCreatePizza(ActionEvent event) throws Exception
    {
        Pizza pizza = getCustomer().createPizza();
        ViewLoader.showStage(pizza, "/view/pizza.fxml", "Create pizza", new Stage());
    }
    
    @FXML private void handleCancel(ActionEvent event) throws Exception
    {
        getCustomer().cancelOrder();
        stage.close();
    }
    
    @FXML private void handleSubmit(ActionEvent event) throws Exception
    {
        getCustomer().submitOrder();
        stage.close();
    }
}
