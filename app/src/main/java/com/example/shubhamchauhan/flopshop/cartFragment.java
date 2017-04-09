package com.example.shubhamchauhan.flopshop;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Shubham chauhan on 26-07-2016.
 */
public class cartFragment extends Fragment {

    View myView;
    DatabaseAdapter db;
    GridView gv;
    Button order;

    int count;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.yourcart,container,false);

        db = new DatabaseAdapter(getActivity());
        gv = (GridView) myView.findViewById(R.id.cartGrid);
        order = (Button) myView.findViewById(R.id.orderButton);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),Order.class);
                startActivity(i);
            }
        });

        count = db.getItemsInCart();

        gv.setAdapter(new myCartAdapter(getActivity()));


        return myView;
    }

    private class myCartAdapter extends BaseAdapter {

        Context _context;
        final String[] cat;
        int[] toyImage = {R.drawable.toy1, R.drawable.toy2, R.drawable.toy3, R.drawable.toy4};
        int[] footImage = {R.drawable.bata, R.drawable.nike, R.drawable.reebok, R.drawable.puma};
        int[] elecImage = {R.drawable.laptop, R.drawable.smartphone, R.drawable.gaming, R.drawable.television};
        int[] furImage = {R.drawable.sofa, R.drawable.chair, R.drawable.table, R.drawable.showcase};
        cartFragment cfrag = new cartFragment();

        public myCartAdapter(Context context) {
            _context = context;
            cat = db.getTable();
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View grid;
            if (i != count) {
                if (view == null) {
                    grid = inflater.inflate(R.layout.custom_cart_item, viewGroup, false);
                } else {
                    grid = view;
                }

                ImageView img = (ImageView) grid.findViewById(R.id.cart_item_image);
                Button add = (Button)grid.findViewById(R.id.addButton);
                Button sub = (Button)grid.findViewById(R.id.subButton);
                Button remove =(Button)grid.findViewById(R.id.remove);
                final TextView qty = (TextView)grid.findViewById(R.id.cart_quantity);
                TextView title = (TextView)grid.findViewById(R.id.cart_text);
                final DatabaseAdapter db = new DatabaseAdapter(_context);
                final String itm = cat[i];
                //Toast.makeText(_context, itm + "....", Toast.LENGTH_SHORT).show();
                    if (itm.equals("MINION")) {
                        img.setImageResource(R.drawable.toy1);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("MINION"));
                        int sq = Integer.parseInt(db.getStockqty("MINION"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    } else if (itm.equals("CAR")) {
                        img.setImageResource(R.drawable.toy2);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("CAR"));
                        int sq = Integer.parseInt(db.getStockqty("CAR"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    } else if (itm.equals("MONSTER TRUCK")) {
                        img.setImageResource(R.drawable.toy3);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("MONSTER TRUCK"));
                        int sq = Integer.parseInt(db.getStockqty("MONSTER TRUCK"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    } else if (itm.equals("GOKU")) {
                        img.setImageResource(R.drawable.toy4);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("GOKU"));
                        int sq = Integer.parseInt(db.getStockqty("GOKU"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    }
                    else if (itm.equals("TABLE")) {
                        img.setImageResource(R.drawable.table);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("TABLE"));
                        int sq = Integer.parseInt(db.getStockqty("TABLE"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    } else if (itm.equals("CHAIR")) {
                        img.setImageResource(R.drawable.chair);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("CHAIR"));
                        int sq = Integer.parseInt(db.getStockqty("CHAIR"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    } else if (itm.equals("SHOWCASE")) {
                        img.setImageResource(R.drawable.showcase);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("SHOWCASE"));
                        int sq = Integer.parseInt(db.getStockqty("SHOWCASE"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    } else if (itm.equals("SOFA")) {
                        img.setImageResource(R.drawable.sofa);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("SOFA"));
                        int sq = Integer.parseInt(db.getStockqty("SOFA"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    }
                    else if (itm.equals("PUMA")) {
                        img.setImageResource(R.drawable.puma);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("PUMA"));
                        int sq = Integer.parseInt(db.getStockqty("PUMA"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    } else if (itm.equals("REEBOK")) {
                        img.setImageResource(R.drawable.reebok);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("REEBOK"));
                        int sq = Integer.parseInt(db.getStockqty("REEBOK"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    } else if (itm.equals("BATA")) {
                        img.setImageResource(R.drawable.bata);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("BATA"));
                        int sq = Integer.parseInt(db.getStockqty("BATA"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    } else if (itm.equals("NIKE")) {
                        img.setImageResource(R.drawable.nike);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("NIKE"));
                        int sq = Integer.parseInt(db.getStockqty("NIKE"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    }
                    else if (itm.equals("LAPTOP")) {
                        img.setImageResource(R.drawable.laptop);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("LAPTOP"));
                        int sq = Integer.parseInt(db.getStockqty("LAPTOP"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    } else if (itm.equals("SMARTPHONE")) {
                        img.setImageResource(R.drawable.smartphone);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("SMARTPHONE"));
                        int sq = Integer.parseInt(db.getStockqty("SMARTPHONE"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    } else if (itm.equals("GAMING CONSOLE")) {
                        img.setImageResource(R.drawable.gaming);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("GAMING CONSOLE"));
                        int sq = Integer.parseInt(db.getStockqty("GAMING CONSOLE"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    } else if (itm.equals("TELEVISION")) {
                        img.setImageResource(R.drawable.television);
                        title.setText(itm);
                        int cq = Integer.parseInt(db.getqty("TELEVISION"));
                        int sq = Integer.parseInt(db.getStockqty("TELEVISION"));
                        if(sq>=cq){
                            qty.setText(String.valueOf(cq));
                        }
                        else{
                            qty.setText("0");
                            db.changeStockInCart(itm,0);
                        }
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.incCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"No more stock available for "+itm,Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        sub.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int stat = db.decCart(itm);
                                if(stat==1){
                                    Toast.makeText(_context,"Minimum Order Reached\n<---REMOVE THE ITEM IF YOU DON'T WANT IT--->",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    qty.setText(db.getqty(itm));
                                }
                            }
                        });
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                db.remove(itm);
                                Toast.makeText(_context,"Item removed from cart",Toast.LENGTH_SHORT).show();
                                FragmentManager fm = getFragmentManager();
                                android.app.FragmentTransaction ft = fm.beginTransaction();
                                ft.replace(R.id.frame,cfrag);
                                ft.commit();
                            }
                        });
                    }
                return grid;
            } else {
                return null;
            }
        }
    }
}
