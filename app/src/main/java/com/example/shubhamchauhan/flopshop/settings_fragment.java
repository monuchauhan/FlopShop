package com.example.shubhamchauhan.flopshop;

import android.app.Fragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

/**
 * Created by Shubham chauhan on 25-07-2016.
 */
public class settings_fragment extends Fragment {

    View myView;
    RadioGroup catgroup;
    RadioButton foot,elec,toy,fur;
    Spinner item;
    String category;
    EditText price,quantity;
    Button update;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        myView = inflater.inflate(R.layout.settings,container,false);
        catgroup = (RadioGroup)myView.findViewById(R.id.radcat);
        foot = (RadioButton)myView.findViewById(R.id.foot);
        elec = (RadioButton)myView.findViewById(R.id.elec);
        fur = (RadioButton)myView.findViewById(R.id.fur);
        toy = (RadioButton)myView.findViewById(R.id.toy);

        item = (Spinner)myView.findViewById(R.id.item_spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,new String[] {"none"});
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        item.setAdapter(spinnerAdapter);
        item.setOnItemSelectedListener(new spinnerHandler());

        price = (EditText)myView.findViewById(R.id.price);
        quantity = (EditText)myView.findViewById(R.id.quantity);

        update = (Button)myView.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(price.getText().toString().trim().equals("") && quantity.getText().toString().trim().equals(""))) {
                    if (foot.isChecked() || elec.isChecked() || fur.isChecked() || toy.isChecked()) {
                        DatabaseAdapter db = new DatabaseAdapter(getActivity());
                        db.addStock(category,item.getSelectedItem().toString(),
                                Integer.parseInt(quantity.getText().toString()),Integer.parseInt(price
                                .getText().toString()));
                        Toast.makeText(getActivity(),"Updated successfully!!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getActivity(),"Please ensure that all the info is filled properly",Toast.LENGTH_SHORT).show();
                }
            }
        });

        catgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.foot:
                        category = "FOOTWEAR";
                        ArrayAdapter<String> footSpinner = new ArrayAdapter<String>(getActivity(),android.
                        R.layout.simple_spinner_dropdown_item,new String[]{"PUMA","REEBOK","NIKE","BATA"});
                        footSpinner.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
                        item.setAdapter(footSpinner);
                        break;
                    case R.id.elec:
                        category = "ELECTRONICS";
                        ArrayAdapter<String> elecSpinner = new ArrayAdapter<String>(getActivity(),android.
                                R.layout.simple_spinner_dropdown_item,new String[]{"LAPTOP","SMARTPHONE","GAMIN CONSOLE","TELEVISION"});
                        elecSpinner.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
                        item.setAdapter(elecSpinner);
                        break;
                    case R.id.fur:
                        category = "FURNITURE";
                        ArrayAdapter<String> furSpinner = new ArrayAdapter<String>(getActivity(),android.
                                R.layout.simple_spinner_dropdown_item,new String[]{"SOFA","TABLE","SHOWCASE","CHAIR"});
                        furSpinner.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
                        item.setAdapter(furSpinner);
                        break;
                    case R.id.toy:
                        category = "TOYS";
                        ArrayAdapter<String> toySpinner = new ArrayAdapter<String>(getActivity(),android.
                                R.layout.simple_spinner_dropdown_item,new String[]{"CAR","MONSTER TRUCK","GOKU","MINION"});
                        toySpinner.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
                        item.setAdapter(toySpinner);
                        break;
                }
            }
        });
        return myView;
    }

    private class spinnerHandler implements Spinner.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(getActivity(),item.getSelectedItem().toString()+" is selected",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
