package org.headroyce.mehdih2022.templer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity  { //usinhg activtity main .xml

    private TempleView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.templeview);
    }

    @Override
    public void onResume(){
        super.onResume();

        tv.onResume();

    }
    @Override
    public void onPause(){
        super.onPause();

        tv.onPause();

    }
}
