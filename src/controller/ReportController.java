package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import model.Customer;
import model.Ingredient;
import model.Kitchen;
import model.Pizzeria;

public class ReportController extends Controller<Kitchen>{
    public final Kitchen getKitchen() { return model;}
        
    @FXML private TableView<Ingredient> reportTv;
    
    @FXML private TableColumn<Ingredient, String> ingredientClm;
    @FXML private TableColumn<Ingredient, String> priceClm;
    @FXML private TableColumn<Ingredient, String> soldClm;
    @FXML private TableColumn<Ingredient, String> incomeClm;
    
    @FXML private Text incomeTxt;
    
    @FXML private void initialize() {
        reportTv.setItems(getKitchen().getIngredients());
        ingredientClm.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceClm.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString("$%.2f"));
        soldClm.setCellValueFactory(cellData -> cellData.getValue().soldProperty().asString());
        incomeClm.setCellValueFactory(cellData -> cellData.getValue().incomeProperty().asString("$%.2f"));
        
        incomeTxt.textProperty().bind(getKitchen().incomeProperty().asString("$%.2f"));
    }
    
    @FXML private void handleCancel(ActionEvent event) throws Exception
    {
        stage.close();
    }
}
