package model;

import java.text.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableStringValue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ingredient {
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty price = new SimpleDoubleProperty();
    private IntegerProperty sold = new SimpleIntegerProperty();
    private DoubleProperty income = new SimpleDoubleProperty();
    private Category category;
    
    @FXML private ImageView image = new ImageView();

    public Ingredient(String name, double price, Category category) {
        this.name.set(name);
        this.price.set(price);
        this.category = category;
        sold.set(0);
        income.set(0.00);
        
        String[] pngs = {"Barbeque","Beef","Capsicum","Jalapenos","Olives","Pepperoni","Thick","Thin","Tomato"};
        image.setImage(null);
        for(int i=0;i<pngs.length;i++)
        {
            if(pngs[i] == name)
            {
                image.setImage(new Image("/bonus/" + name + ".png"));
                break;
            }
        }
    }

    public void sell() {
        sold.set(sold.get() + 1);
        income.set(getIncome());
    }

    public boolean in(Category category) {
        return this.category == category;
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }

    public int getSold() {
        return sold.get();
    }

    public double getIncome() {
        return sold.get() * price.get();
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name.get() + " " + category;
    }

    private String formatted(double n) {
        return new DecimalFormat("###,##0.00").format(n);
    }
    
    public StringProperty nameProperty()
    {
        return name;
    }
    
    public ReadOnlyDoubleProperty priceProperty()
    {
        return price;
    }
    
    public ReadOnlyIntegerProperty soldProperty()
    {
        return sold;
    }
    
    public ReadOnlyDoubleProperty incomeProperty()
    {
        return income;
    }
    
    public ImageView getImage()
    {
        return image;
    }
}
