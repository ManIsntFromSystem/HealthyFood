package com.example.sportthings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity {
    @BindView(R.id.textOrder)
    TextView textViewOrder;

    @BindView(R.id.editInputEmail)
    EditText editTextEmail;

    String addressesEmail;
    String subjectEmail = "Order from FoodHealthy shop";
    String emailText;

    private static DecimalFormat decFormat = new DecimalFormat("###.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        Intent orderIntent = getIntent();
        String nameCustomer = orderIntent.getStringExtra("customer_name");
        String nameProduct = orderIntent.getStringExtra("product_name");
        int quantityProduct = orderIntent.getIntExtra("quantity_product", 0);
        double totalPrice = orderIntent.getDoubleExtra("total_price", 0.0);

        addressesEmail = editTextEmail.getText().toString();
        emailText = "Name: " + nameCustomer + "\n" +
                "Product: " + nameProduct + "\n" +
                "Quantity: " + quantityProduct + "\n" +
                "TotalPrice: " + decFormat.format(totalPrice) + "\n";

        textViewOrder.setText(emailText);
    }

    public void sendToEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        //intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addressesEmail);
        intent.putExtra(Intent.EXTRA_SUBJECT, subjectEmail);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
