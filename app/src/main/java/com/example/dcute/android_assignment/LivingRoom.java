package com.example.dcute.android_assignment;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LivingRoom extends Fragment {

    private TextView textView;
    ViewPager viewPager;
    int foodcounter = 10, drinkcounter = 10; //Food and Drink value start from 10
    TextView textview1, textview2, showfoodvalue, showdrinkvalue;
    ImageView imageview1, imageview2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Handle the child Fragment
        //Get the setting button from another XML file
        FragmentTransaction ft1 = getChildFragmentManager().beginTransaction();
        quick_setting_button quickSettingButton = new quick_setting_button();
        ft1.replace(R.id.quick_setting_button, quickSettingButton);
        ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft1.addToBackStack(null);
        ft1.commit();


        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_living_room, container, false);

        textView = (TextView)v.findViewById(R.id.character); //Get id from layout
        textView.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) textView.getBackground()).start();
            }
        });

        Button btn = (Button)v.findViewById(R.id.goto_bedroom_button);
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewPager);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(0);
            }
        });

        Button btn2 = (Button)v.findViewById(R.id.goto_bathroom_button);
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewPager);

        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(3);
            }
        });

        textview1 = (TextView)v.findViewById(R.id.chat_box);
        textview2 = (TextView)v.findViewById(R.id.character);
        imageview1 = (ImageView)v.findViewById(R.id.food);
        imageview2 = (ImageView)v.findViewById(R.id.drink);
        //set the id from the layout

        imageview1.setOnLongClickListener(longClickListener);
        imageview2.setOnLongClickListener(longClickListener);
        //set the long click function for the "id"

        textview2.setOnDragListener(dragListener);
        //set the location on drop

        showfoodvalue = (TextView)v.findViewById(R.id.food_number);
        showdrinkvalue = (TextView)v.findViewById(R.id.drink_number);
        //get the value of the food_number and drink_number



        //Save the food value and drink value of the character
        //Save data in Share Preferences File
        Button Save = (Button)v.findViewById(R.id.save_button);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getActivity().getSharedPreferences("TheWorldOfBeanSavedFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("FoodValue", foodcounter);
                editor.putInt("DrinkValue", drinkcounter);
                editor.apply();

                Context context = getActivity().getApplicationContext();
                CharSequence text = "Saved";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
            }
        });

        //Print out the saved Data
        Button Load = (Button)v.findViewById(R.id.load_button);
        Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getActivity().getSharedPreferences("TheWorldOfBeanSavedFile", Context.MODE_PRIVATE);
                int food = sharedPref.getInt("FoodValue", foodcounter);
                int drink = sharedPref.getInt("DrinkValue", drinkcounter);

                showfoodvalue.setText(Integer.toString(food));
                showdrinkvalue.setText(Integer.toString(drink));
            }
        });
        return v;
    }

    //User hold(not release while clicking)
    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowbuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowbuilder, v, 0);
            return true;
        }
    };

    //User hold the button and drag to a location
    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view = (View)event.getLocalState();

            switch(dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    //set the function after dragging the item to a location

                    if(view.getId() == R.id.food){
                        textview1.setText("Yummy," + "\n" + "It's so delicious");
                        foodcounter++;
                        showfoodvalue.setText(Integer.toString(foodcounter));
                    }else if(view.getId() == R.id.drink){
                        textview1.setText("Thank for the drinks");
                        drinkcounter++;
                        showdrinkvalue.setText(Integer.toString(drinkcounter));
                    }
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    //set the function if the user drag out the item from the location

                    if(view.getId() == R.id.food){
                        textview1.setText("I'm hungry");
                    }else if(view.getId() == R.id.drink){
                        textview1.setText("I'm thirsty");
                    }
                    break;

                case DragEvent.ACTION_DROP:
                    break;
            }
            return true;
        }
    };
}
