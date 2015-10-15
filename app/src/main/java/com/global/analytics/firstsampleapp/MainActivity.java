package com.global.analytics.firstsampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView helloworld = (TextView)findViewById(R.id.helloworld);
        SharedDataManager sharedDataManager = SharedDataManager.getInstance();
        helloworld.setText(sharedDataManager.Username);
        String username = getIntent().getStringExtra("username");
        Properties properties;
        AssetsPropertyReader assetsPropertyReader = new AssetsPropertyReader(this);
        properties = assetsPropertyReader.getPropertiesFromDocs("ViewConfig.properties");
        helloworld.setText(sharedDataManager.Username+properties.getProperty("Status")+" "+username);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
