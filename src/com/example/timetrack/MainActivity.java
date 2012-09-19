package com.example.timetrack;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	// Called at the start of the fill lifetime.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Initialize Activity and inflate the UI.
        // Inflate your View
        setContentView(R.layout.activity_main);
        
        //Button goToTimeTracker = (Button)findViewById(R.id.goToTimeTracker);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void trackTasks(View v){
		Intent i = new Intent(this, TimeTrack.class);
		startActivity(i);
	}
}
