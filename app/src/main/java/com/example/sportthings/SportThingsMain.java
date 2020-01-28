package com.example.sportthings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SportThingsMain extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.textPrice) TextView txtTotalPrice;
    @BindView(R.id.textQuantity) TextView textViewQuantity;
    @BindView(R.id.edit_input_name) TextView editInputName;
    @BindView(R.id.imageViewProduct) ImageView imageViewProduct;
    @BindView(R.id.spinner) Spinner spinner;

    private static DecimalFormat decFormat = new DecimalFormat("###.##");

    HashMap<String, Double> listFoodHashMap;
    private static int quantityProduct = 1;
    private static double finalTotalPrice = 0.0;
    String productName;
    Double productPrice;
    Order order;

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

    public void addToCart(View view) {
        order = new Order();
        order.setName(editInputName.getText().toString());
        order.setNameProduct(productName);
        order.setQuantity(quantityProduct);
        order.setTotalPrice(finalTotalPrice);

        System.out.println("Name: " + order.getName() + "\n" +
                "Product: " + order.getNameProduct() + "\n" +
                "Quantity: " + order.getQuantity() + "\n" +
                "TotalPrice: " + order.getTotalPrice() + "\n");

        Intent orderIntent = new Intent(SportThingsMain.this, OrderActivity.class);
        orderIntent.putExtra("customer_name", order.getName());
        orderIntent.putExtra("product_name", order.getNameProduct());
        orderIntent.putExtra("quantity_product", order.getQuantity());
        orderIntent.putExtra("total_price", order.getTotalPrice());
        startActivity(orderIntent);
    }

    public void setDate() {
        try {
            textViewQuantity.setText("" + quantityProduct);

            productName = spinner.getSelectedItem().toString();
            productPrice = listFoodHashMap.get(productName);
            finalTotalPrice = productPrice * quantityProduct;

            txtTotalPrice.setText("" + decFormat.format(finalTotalPrice));

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
        } catch (Exception e) {
            e.printStackTrace();
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
