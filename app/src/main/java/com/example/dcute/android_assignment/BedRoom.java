package com.example.dcute.android_assignment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import static android.content.Context.ALARM_SERVICE;


public class BedRoom extends Fragment {

    private TextView textView;
    ViewPager viewPager;

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
        View v = inflater.inflate(R.layout.fragment_bed_room, container, false);

        textView = (TextView)v.findViewById(R.id.character); //Get id from layout
        textView.post(new Runnable() {
            @Override
            public void run() {
                ((AnimationDrawable) textView.getBackground()).start();
            }
        });

        Button btn = (Button)v.findViewById(R.id.exit_bedroom_button);
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewPager);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewPager.setCurrentItem(1);
            }
        });

        Button b = (Button)v.findViewById(R.id.lamp_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TurnOffLight.class));
                //pop out the pages from an activity
            }
        });

        //Set the reminder for the user to pop out notification
        Button reminder = (Button)v.findViewById(R.id.reminder);
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = getActivity().getApplicationContext();
                CharSequence text = "Reminder has enabled";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();

                Calendar calendar = Calendar.getInstance();

                // Set the time to pop out
                calendar.set(Calendar.HOUR_OF_DAY, 18);
                calendar.set(Calendar.MINUTE, 52);
                calendar.set(Calendar.SECOND, 30);

                Intent intent = new Intent(getContext().getApplicationContext(), NotificationReceiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext().getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager)getContext().getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            }
        });
        return v;
    }

}
