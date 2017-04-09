package com.example.shubhamchauhan.flopshop;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Shop extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public static Context con;
    public DatabaseAdapter db;
    android.app.FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        con = getApplicationContext();
        db = new DatabaseAdapter(con);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        fm = getFragmentManager();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Main.class);
                i.putExtra("id",R.id.cart);
                startActivity(i);
            }
        });

    }


    /*A placeholder fragment containing a simple view.
    */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        Shop s = new Shop();

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {

                case 1:
                    rootView = inflater.inflate(R.layout.fragment_footwear, container, false);
                    s.initFoot(rootView, getContext());
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_electronics, container, false);
                    s.initElec(rootView, getContext());
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_furniture, container, false);
                    s.initFur(rootView, getContext());
                    break;
                case 4:
                    rootView = inflater.inflate(R.layout.fragment_others, container, false);
                    s.initToy(rootView, getContext());
                    break;
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "FOOTWEAR";
                case 1:
                    return "ELECTRONICS";
                case 2:
                    return "FURNITURE";
                case 3:
                    return "OTHERS";
            }
            return null;
        }
    }

    public void initToy(View view, Context context) {
        GridView grid = (GridView) view.findViewById(R.id.gridViewt);
        grid.setAdapter(new myToyAdapter(context));
    }

    public class myToyAdapter extends BaseAdapter {

        private Context context;

        public myToyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 4;
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

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

            View grid;
            grid = new View(context);

            if (view == null) {
                grid = inflater.inflate(R.layout.item_layout, viewGroup, false);
            }
            else{
                grid = view;
            }

                ImageView img = (ImageView) grid.findViewById(R.id.item_image);
                ImageButton addToCart = (ImageButton) grid.findViewById(R.id.addToCart);
                ImageButton buy = (ImageButton) grid.findViewById(R.id.buyNow);

                switch (i) {
                    case 0:
                        img.setImageResource(R.drawable.toy1);
                        addToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseAdapter db = new DatabaseAdapter(context);
                                int i = db.insertItemsToCart("TOYS", "MINION");
                                if (i == 0) {
                                    Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                                } else if (i == 1) {
                                    Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    case 1:
                        img.setImageResource(R.drawable.toy2);
                        addToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseAdapter db = new DatabaseAdapter(context);
                                int i = db.insertItemsToCart("TOYS", "CAR");
                                if (i == 0) {
                                    Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                                } else if (i == 1) {
                                    Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    case 2:
                        img.setImageResource(R.drawable.toy3);
                        addToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseAdapter db = new DatabaseAdapter(context);
                                int i = db.insertItemsToCart("TOYS", "MONSTER TRUCK");
                                if (i == 0) {
                                    Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                                } else if (i == 1) {
                                    Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    case 3:
                        img.setImageResource(R.drawable.toy4);
                        addToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseAdapter db = new DatabaseAdapter(context);
                                int i = db.insertItemsToCart("TOYS", "GOKU");
                                if (i == 0) {
                                    Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                                } else if (i == 1) {
                                    Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    default:
                        img.setImageResource(R.drawable.profile);
                        break;
                }

            return grid;
        }


    }


    public void initFoot(View view, Context context) {
        GridView grid = (GridView) view.findViewById(R.id.gridViewf);
        grid.setAdapter(new myFootAdapter(context));
    }

    public class myFootAdapter extends BaseAdapter {

        private Context context;

        public myFootAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 4;
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

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

            View grid;
            grid = new View(context);

            if (view == null) {
                grid = inflater.inflate(R.layout.item_layout, viewGroup, false);
            }
            else {
                grid = view;
            }

                ImageView img = (ImageView) grid.findViewById(R.id.item_image);
                ImageButton addToCart = (ImageButton) grid.findViewById(R.id.addToCart);

                switch (i) {
                    case 0:
                        img.setImageResource(R.drawable.puma);
                        addToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseAdapter db = new DatabaseAdapter(context);
                                int i = db.insertItemsToCart("FOOTWEAR", "PUMA");
                                if (i == 0) {
                                    Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                                } else if (i == 1) {
                                    Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    case 1:
                        img.setImageResource(R.drawable.nike);
                        addToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseAdapter db = new DatabaseAdapter(context);
                                int i = db.insertItemsToCart("FOOTWEAR", "NIKE");
                                if (i == 0) {
                                    Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                                } else if (i == 1) {
                                    Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    case 2:
                        img.setImageResource(R.drawable.bata);
                        addToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseAdapter db = new DatabaseAdapter(context);
                                int i = db.insertItemsToCart("FOOTWEAR", "BATA");
                                if (i == 0) {
                                    Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                                } else if (i == 1) {
                                    Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    case 3:
                        img.setImageResource(R.drawable.reebok);
                        addToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseAdapter db = new DatabaseAdapter(context);
                                int i = db.insertItemsToCart("FOOTWEAR", "REEBOK");
                                if (i == 0) {
                                    Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                                } else if (i == 1) {
                                    Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    default:
                        img.setImageResource(R.drawable.profile);
                        break;
                }
            return grid;
        }
    }


    public void initFur(View view, Context context) {
        GridView grid = (GridView) view.findViewById(R.id.gridViewfu);
        grid.setAdapter(new myFurAdapter(context));
    }

    public class myFurAdapter extends BaseAdapter {

        private Context context;

        public myFurAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 4;
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

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

            View grid;
            grid = new View(context);
            if (view == null) {
                grid = inflater.inflate(R.layout.item_layout, viewGroup, false);
            }
            else {
                grid = view;
            }

                ImageView img = (ImageView) grid.findViewById(R.id.item_image);
                ImageButton addToCart = (ImageButton) grid.findViewById(R.id.addToCart);

                switch (i) {
                    case 0:
                        img.setImageResource(R.drawable.sofa);
                        addToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseAdapter db = new DatabaseAdapter(context);
                                int i = db.insertItemsToCart("FURNITURE", "SOFA");
                                if (i == 0) {
                                    Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                                } else if (i == 1) {
                                    Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    case 1:
                        img.setImageResource(R.drawable.chair);
                        addToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseAdapter db = new DatabaseAdapter(context);
                                int i = db.insertItemsToCart("FURNITURE", "CHAIR");
                                if (i == 0) {
                                    Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                                } else if (i == 1) {
                                    Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    case 2:
                        img.setImageResource(R.drawable.showcase);
                        addToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseAdapter db = new DatabaseAdapter(context);
                                int i = db.insertItemsToCart("FURNITURE", "SHOWCASE");
                                if (i == 0) {
                                    Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                                } else if (i == 1) {
                                    Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    case 3:
                        img.setImageResource(R.drawable.table);
                        addToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatabaseAdapter db = new DatabaseAdapter(context);
                                int i = db.insertItemsToCart("FURNITURE", "TABLE");
                                if (i == 0) {
                                    Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                                } else if (i == 1) {
                                    Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                    default:
                        img.setImageResource(R.drawable.profile);
                        break;
                }

                return grid;
            }
        }


    public void initElec(View view, Context context) {
        GridView grid = (GridView) view.findViewById(R.id.gridViewe);
        grid.setAdapter(new myElecAdapter(context));
    }

    public class myElecAdapter extends BaseAdapter {

        private Context context;

        public myElecAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 4;
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

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

            View grid;
            grid = new View(context);
            ImageView img;
            ImageButton addToCart;

            if (view == null) {
                grid = inflater.inflate(R.layout.item_layout, viewGroup, false);

            } else {
                grid = view;
            }
            img = (ImageView) grid.findViewById(R.id.item_image);
            addToCart = (ImageButton) grid.findViewById(R.id.addToCart);
            switch (i) {
                case 0:
                    img.setImageResource(R.drawable.laptop);
                    addToCart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatabaseAdapter db = new DatabaseAdapter(context);
                            int i = db.insertItemsToCart("ELECTRONICS", "LAPTOP");
                            if (i == 0) {
                                Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                            } else if (i == 1) {
                                Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    break;
                case 1:
                    img.setImageResource(R.drawable.smartphone);
                    addToCart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatabaseAdapter db = new DatabaseAdapter(context);
                            int i = db.insertItemsToCart("ELECTRONICS", "SMARTPHONE");
                            if (i == 0) {
                                Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                            } else if (i == 1) {
                                Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    break;
                case 2:
                    img.setImageResource(R.drawable.gaming);
                    addToCart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatabaseAdapter db = new DatabaseAdapter(context);
                            int i = db.insertItemsToCart("ELECTRONICS", "GAMING CONSOLE");
                            if (i == 0) {
                                Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                            } else if (i == 1) {
                                Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    break;
                case 3:
                    img.setImageResource(R.drawable.television);
                    addToCart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatabaseAdapter db = new DatabaseAdapter(context);
                            int i = db.insertItemsToCart("ELECTRONICS", "TELEVISON");
                            if (i == 0) {
                                Toast.makeText(context, "Out  Of  Stock", Toast.LENGTH_SHORT).show();
                            } else if (i == 1) {
                                Toast.makeText(context, "item added to cart", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "item already in cart", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    break;
                default:
                    img.setImageResource(R.drawable.profile);
                    break;
            }
            return grid;
        }
    }

}
