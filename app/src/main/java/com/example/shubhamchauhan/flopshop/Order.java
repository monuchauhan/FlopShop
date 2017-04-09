package com.example.shubhamchauhan.flopshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Order extends AppCompatActivity {

    DatabaseAdapter db;
    Button order;
    TextView itm,qt,pri;
    RadioGroup rg;
    RadioButton cod,crd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        db = new DatabaseAdapter(getApplicationContext());
        final int count = db.getItemsInCart();
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        cod = (RadioButton)findViewById(R.id.cod);
        crd = (RadioButton)findViewById(R.id.credit);
        String[] item = new String[count];
        int[] price = new int[count];
        int[] qty = new int[count];

        item = db.getTable();
        price = db.getPriceTable();
        qty = db.getQtyTable();

        itm = (TextView)findViewById(R.id.itm);
        pri = (TextView)findViewById(R.id.price);
        qt = (TextView)findViewById(R.id.qty);
        TextView mul = (TextView)findViewById(R.id.mul);
        int i = 0;
        itm.setText("");
        pri.setText("");
        qt.setText("");
        mul.setText("");

        while(i<count){
            String str = "\n"+item[i];
            String str1 = "\n"+String.valueOf(qty[i]);
            String str2 = "\n"+String.valueOf(qty[i]*price[i]);
            itm.append(str);
            mul.append("\nX");
            qt.append(str1);
            pri.append(str2);
            i++;
        }
        int total = 0;

        for(int j=0;j<count;j++){
            total+=(qty[j]*price[j]);
        }
        TextView tot = (TextView)findViewById(R.id.total);
        tot.append(String.valueOf(total));

        order = (Button)findViewById(R.id.final_order);
        final String[] finalItem = item;
        final int[] finalQty = qty;
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Thank you for using FlopShop. We are Happy to serve you!!",Toast.LENGTH_SHORT).show();
                for(int j = 0;j<count;j++){
                    int oldQt = Integer.parseInt(db.getStockqty(finalItem[j]));
                    int newQt = oldQt - finalQty[j];
                    db.changeStock(finalItem[j],newQt);
                }
                Intent i = new Intent(getApplicationContext(),Main.class);
                startActivity(i);
                finish();
            }
        });
    }
}
