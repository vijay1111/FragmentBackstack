package com.example.vijay.fragmentbackstack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        button = findViewById(R.id.button);

        fragmentManager = getSupportFragmentManager();
        textView.setText("counter = " + fragmentManager.getBackStackEntryCount());

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                textView.setText("back stack counter = " + fragmentManager.getBackStackEntryCount());
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment();
            }
        });
    }

    private void addFragment() {
        Fragment fragment;
        switch (fragmentManager.getBackStackEntryCount()) {
            case 0:
                fragment = new BlankFragment1();
                break;
            case 1:
                fragment = new BlankFragment2();
                break;
            case 2:
                fragment = new BlankFragment3();
                break;
            default:
                fragment = new BlankFragment1();
                break;


        }
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}