package com.example.timetrack;

import android.os.Bundle;
import android.app.Activity;
<<<<<<< HEAD
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

=======
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
		finish();
	}
>>>>>>> 1a619541cd51884bce0d5b78c410bbe61aa795fb
}
