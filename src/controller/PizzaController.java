package controller;

import au.edu.uts.ap.javafx.Controller;
import java.util.LinkedList;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.Ingredient;
import model.Pizza;

public class PizzaController extends Controller<Pizza>{
    public final Pizza getPizza() { return model;}
    
    @FXML private Button createBtn;
    @FXML private Button addBtn;
    @FXML private Button removeBtn;
    @FXML private Text message;
    @FXML private Text priceTxt;
    @FXML private ListView<Ingredient> ingredientsLv;
    @FXML private ListView<Ingredient> kitchenLv;
    
    @FXML private StackPane pizzaSp;    
    private ObservableList pizzaStack;
    
    @FXML public void initialize() {
        addBtn.setText("->");
        removeBtn.setText("<-");
        message.textProperty().bind(getPizza().statusProperty());
        priceTxt.textProperty().bind(getPizza().priceProperty().asString("$%.2f"));
        ingredientsLv.setItems(getPizza().getIngredients());
        kitchenLv.setItems(getPizza().getAvailableIngredients());
        createBtn.disableProperty().bind(getPizza().statusProperty().isNotEmpty());    
        removeBtn.disableProperty().bind(ingredientsLv.getSelectionModel().selectedItemProperty().isNull());    
        addBtn.disableProperty().bind(Bindings.or(kitchenLv.getSelectionModel().selectedItemProperty().isNull(),getPizza().getUnaddable()));
        
        kitchenLv.getSelectionModel().selectedItemProperty().addListener((o, oldI, newI) -> getPizza().setUnaddable(getSelectedKitchen()));
        ingredientsLv.getSelectionModel().selectedItemProperty().addListener((o, oldI, newI) -> getPizza().setUnaddable(getSelectedKitchen()));
        
        pizzaStack = pizzaSp.getChildren();
    }
    
    private Ingredient getSelectedIngredient()
    {
        return ingredientsLv.getSelectionModel().getSelectedItem();
    }
    
    private Ingredient getSelectedKitchen()
    {
        return kitchenLv.getSelectionModel().getSelectedItem();
    }
    
    private LinkedList<ImageView> getImages()
    {
        LinkedList<ImageView> images = new LinkedList();
        for (Ingredient i : getPizza().getIngredients())
        {
            images.add(i.getImage());
        }        
        return images;
    }
    
    @FXML private void handleAdd(ActionEvent event) throws Exception
    {
        Ingredient ingredient = getSelectedKitchen();
        if(getPizza().canAdd(ingredient))
        {
            getPizza().add(ingredient);
            getPizza().statusProperty();
        }
        getPizza().setUnaddable(getSelectedKitchen());
        pizzaStack.setAll(getImages());
    }
    
    @FXML private void handleRemove(ActionEvent event) throws Exception
    {
        Ingredient ingredient = getSelectedIngredient();
        if(ingredient != null);
        {
            getPizza().remove(ingredient);
            getPizza().statusProperty();
        }
        getPizza().setUnaddable(getSelectedKitchen());
        pizzaStack.setAll(getImages());
    }
    
    @FXML private void handleCancel(ActionEvent event) throws Exception
    {
        stage.close();
    }
    
    @FXML private void handleCreate(ActionEvent event) throws Exception
    {
        getPizza().order();
        stage.close();
    }
}
