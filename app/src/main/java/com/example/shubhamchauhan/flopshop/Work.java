package com.example.shubhamchauhan.flopshop;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Shubham chauhan on 30-07-2016.
 */
public class Work extends Fragment {
    public Work(){
        super();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.wip,container,false);
    }
}
