package com.example.kh.myfacebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kh.myfacebook.View.MyFragment;

public class MainActivity extends AppCompatActivity {

    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(myFragment==null){
            myFragment = new MyFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.linemain,myFragment, "MyFragment").commit();
        }else{
            myFragment = (MyFragment) getSupportFragmentManager().findFragmentByTag("MyFragment");
        }
    }
}
