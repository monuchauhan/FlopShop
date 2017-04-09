package com.example.shubhamchauhan.flopshop;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Shubham chauhan on 30-07-2016.
 */
public class buyNowDialog extends DialogFragment {

    int[] Image = {R.drawable.toy1, R.drawable.toy2, R.drawable.toy3, R.drawable.toy4,R.drawable.bata,
            R.drawable.nike, R.drawable.reebok, R.drawable.puma,R.drawable.laptop, R.drawable.smartphone,
            R.drawable.gaming, R.drawable.television,R.drawable.sofa, R.drawable.chair,
            R.drawable.table, R.drawable.showcase};
    String[] item = {"MINION","CAR","MONSTER TRUCK","GOKU","BATA","NIKE","REEBOK","PUMA","LAPTOP","SMARTPHONE",
            "GAMING CONSOLE","TELEVISION","SOFA","CHAIR","TABLE","SHOWCASE"};
    DatabaseAdapter db;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.buynow,null);
        ImageView img = (ImageView)v.findViewById(R.id.DialogImage);
        Button ord = (Button)v.findViewById(R.id.DialogOrder);
        TextView pri = (TextView)v.findViewById(R.id.dialogPrice);

        db = new DatabaseAdapter(getActivity());

        int id = getArguments().getInt("id");
        int price = db.getPrice(item[id]);

        img.setImageResource(Image[id]);
        pri.append(String.valueOf(price));

        ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Thanks for purchasing!",Toast.LENGTH_SHORT).show();
                onDestroy();
            }
        });

        builder.setView(v);
        return builder.create();
    }
}
