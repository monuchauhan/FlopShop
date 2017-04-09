package com.example.shubhamchauhan.flopshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/**
 * Created by Shubham chauhan on 21-07-2016.
 */
public class home_fragment extends android.app.Fragment {

    View myView;
    DatabaseAdapter db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        myView = inflater.inflate(R.layout.home_frag,container,false);

        ViewFlipper vf = (ViewFlipper)myView.findViewById(R.id.viewFlipper);
        int[] image = {R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4};

        for(int i = 0 ; i<image.length ; i++){
            ImageView im = new ImageView(getActivity());
            im.setImageResource(image[i]);
            vf.addView(im);
        }

        vf.setInAnimation(getActivity(),android.R.anim.fade_in);
        vf.setOutAnimation(getActivity(),android.R.anim.fade_out);
        vf.setAutoStart(true);
        vf.setFlipInterval(1500);

        db = new DatabaseAdapter(getActivity());
        if(db.getTableStock()==0) {
            db.footInit();
            db.elecInit();
            db.furInit();
            db.toyInit();
        }

        ImageView cat1 = (ImageView)myView.findViewById(R.id.button);
        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),Shop.class);
                startActivity(i);
            }
        });

        return myView;
    }
}
