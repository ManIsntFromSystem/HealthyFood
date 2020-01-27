package com.example.sportthings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SportThingsMain extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.textPrice) TextView txtTotalPrice;
    @BindView(R.id.textQuantity) TextView textViewQuantity;
    @BindView(R.id.imageViewProduct) ImageView imageViewProduct;
    @BindView(R.id.spinner) Spinner spinner;

    HashMap<String, Double> listFoodHashMap;
    private static int quantityProduct = 1;
    String productName;
    Double productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_things_main);
        ButterKnife.bind(this);

        createSpinner();
        createProductMap();
        setDate();
    }

    public void incQuantity(View view) {
        try {
            quantityProduct++;
            setDate();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void decQuantity(View view) {
        try {
            if(quantityProduct > 1){
                quantityProduct--;
                setDate();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        quantityProduct = 1;
        setDate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void setDate() {
        textViewQuantity.setText("" + quantityProduct);

        productName = spinner.getSelectedItem().toString();
        productPrice = listFoodHashMap.get(productName);
        txtTotalPrice.setText("" + (productPrice * quantityProduct));

        switch (productName) {
            case "Broccoli":
                imageViewProduct.setImageResource(R.drawable.broccoli1);
                break;
            case "Tomato":
                imageViewProduct.setImageResource(R.drawable.tomato1);
                break;
            case "Cucumber":
                imageViewProduct.setImageResource(R.drawable.cucumber1);
                break;
            case "Salad":
                imageViewProduct.setImageResource(R.drawable.salad1);
                break;
            case "Avocado":
                imageViewProduct.setImageResource(R.drawable.avocado1);
                break;
            case "White cabbage":
                imageViewProduct.setImageResource(R.drawable.cabbage1);
                break;
            case "Chickpeas":
                imageViewProduct.setImageResource(R.drawable.chickpeas1);
                break;
        }
    }

    public void createSpinner(){
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.food_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void createProductMap(){
        listFoodHashMap = new HashMap<>();
        listFoodHashMap.put("Broccoli", 1.2);
        listFoodHashMap.put("Tomato", 0.7);
        listFoodHashMap.put("Cucumber", 0.5);
        listFoodHashMap.put("Salad", 1.0);
        listFoodHashMap.put("Avocado", 3.0);
        listFoodHashMap.put("White cabbage", 0.9);
        listFoodHashMap.put("Chickpeas", 1.1);
    }
}
