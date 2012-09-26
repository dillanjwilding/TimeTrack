package com.example.timetrack;

//import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
//import android.os.Handler;
//import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
//import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;
//import android.widget.TextView;
//import android.widget.ListView;
import android.widget.EditText;
//import android.widget.ArrayAdapter;
//import android.widget.Toast;

public class TimeTrack extends Activity {
	
	private int id = 0;
	
	// Called at the start of the fill lifetime.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Initialize Activity and inflate the UI.
        // Inflate your View
        setContentView(R.layout.activity_time_track);
        
        // Create DatabaseHandler
        final DatabaseHandler database = new DatabaseHandler(this);
        
        List<Task> tasks = database.getAllTasks();       
        
        for (Task t : tasks) {
        	FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			Task task = new Task();
			task.setID(t.getID());
			task.setName(t.getName());
			task.setTime(t.getStartTime(), t.getElapsedTime(), t.getState());
			fragmentTransaction.add(R.id.taskFragmentContainer, task);
			fragmentTransaction.commit();
			id = t.getID();
        }
        id++;
        
        // Get references to UI widgets
        //ListView listView = (ListView)findViewById(R.id.taskContainer);
        final EditText addTaskText = (EditText)findViewById(R.id.addTaskText);
        
        // Create the Array List of task items
        /*final ArrayList<String> taskItems = new ArrayList<String>();
        
        // Create the Array Adapter to bind the array to the List View 
        final ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskItems);
        
        // Bind the Array Adapter to the List View
        listView.setAdapter(arrayAdapter);*/
        
        final Button addTaskButton = (Button)findViewById(R.id.addTaskButton);
        
        addTaskText.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)) {
					if(!addTaskText.getText().toString().equals("")) {
						//taskItems.add(0, addTaskText.getText().toString());
						//arrayAdapter.notifyDataSetChanged();
						FragmentManager fragmentManager = getFragmentManager();
	        			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	        			Task task = new Task();
	        			task.setID(id);
	        			id++;
	        			task.setName(addTaskText.getText().toString());
	        			fragmentTransaction.add(R.id.taskFragmentContainer, task);
	        			fragmentTransaction.commit();
						addTaskText.setText("");
						database.addTask(task);
						return true;
					}
					else {
						Toast toast = Toast.makeText(getApplicationContext(), "Please enter a name for your task", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
					}
				}
				return false;
			}
		});
        
        addTaskButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		if(!addTaskText.getText().toString().equals("")) {
	        		//taskItems.add(0, addTaskText.getText().toString());
	        		//arrayAdapter.notifyDataSetChanged();
	        		FragmentManager fragmentManager = getFragmentManager();
	        		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	        		Task task = new Task();
	        		task.setID(id);
	        		id++;
	        		task.setName(addTaskText.getText().toString());
	        		fragmentTransaction.add(R.id.taskFragmentContainer, task);
	        		fragmentTransaction.commit();
	        		addTaskText.setText("");
	        		database.addTask(task);
        		}
        		else {
        			Toast toast = Toast.makeText(getApplicationContext(), "Please enter a name for your task", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
        		}
        	}
        });
        
        /*listView.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		Toast.makeText(getApplicationContext(), "Click ListItem Number " + position, Toast.LENGTH_LONG).show();
        	}
        });*/
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_time_track, menu);
        return true;
    }
    
    public void mainMenu(View v){
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();
	}
    
    /*public void onClick(View view) {
    	switch(view.getId()) {
    	//case R.id.button1:
    		
    	}
    }*/
    
    // Called after onCreate has finished, use to restore UI state
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
    	// Restore UI state from the savedInstanceState.
    	// This bundle has also been passed to onCreate.
    	// Will only be called if the Activity has been 
    	// killed by the system since it was last visible.
    	
    	final DatabaseHandler database = new DatabaseHandler(this);
        
        List<Task> tasks = database.getAllTasks();       
        
        for (Task t : tasks) {
        	FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			Task task = new Task();
			task.setID(t.getID());
			task.setName(t.getName());
			task.setTime(t.getStartTime(), t.getElapsedTime(), t.getState());
			fragmentTransaction.add(R.id.taskFragmentContainer, task);
			fragmentTransaction.commit();
			id = t.getID();
        }
        id++;
    }

    // Called before subsequent visible lifetimes
    // for an Activity process.
    @Override
    public void onRestart() {
    	super.onRestart();
    	// Load changes knowing that the Activity has already
    	// been visible within this process.
    	
    	final DatabaseHandler database = new DatabaseHandler(this);
        
        List<Task> tasks = database.getAllTasks();       
        
        for (Task t : tasks) {
        	FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			Task task = new Task();
			task.setID(t.getID());
			task.setName(t.getName());
			task.setTime(t.getStartTime(), t.getElapsedTime(), t.getState());
			fragmentTransaction.add(R.id.taskFragmentContainer, task);
			fragmentTransaction.commit();
			id = t.getID();
        }
        id++;
    }
    
    // Called at the start of the visible lifetime.
    @Override
    public void onStart() {
    	super.onStart();
    	// Apply any required UI change now that the Activity is visible.
    	
    	final DatabaseHandler database = new DatabaseHandler(this);
        
        List<Task> tasks = database.getAllTasks();       
        
        for (Task t : tasks) {
        	FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			Task task = new Task();
			task.setID(t.getID());
			task.setName(t.getName());
			task.setTime(t.getStartTime(), t.getElapsedTime(), t.getState());
			fragmentTransaction.add(R.id.taskFragmentContainer, task);
			fragmentTransaction.commit();
			id = t.getID();
        }
        id++;
    }
    
    // Called at the start of the activity lifetime.
    @Override
    public void onResume() {
    	super.onResume();
    	// Resume any paused UI updates, threads, or processes required 
    	// by the Activity but suspended when it was inactive.
    	
    	final DatabaseHandler database = new DatabaseHandler(this);
        
        List<Task> tasks = database.getAllTasks();       
        
        for (Task t : tasks) {
        	FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			Task task = new Task();
			task.setID(t.getID());
			task.setName(t.getName());
			task.setTime(t.getStartTime(), t.getElapsedTime(), t.getState());
			fragmentTransaction.add(R.id.taskFragmentContainer, task);
			fragmentTransaction.commit();
			id = t.getID();
        }
        id++;
    }
    
    // Called to save UI state changes at the 
    // end of the activity life cycle.
    @Override 
    public void onSaveInstanceState(Bundle savedInstanceState){
    	// Save UI state changes to the savedInstanceState.
    	// This bundle will be passed to onCreate and 
    	// onRestoreInstanceState if the process is
    	// killed and restarted by the run time.
    	super.onSaveInstanceState(savedInstanceState);
    }
    
    // Called at the end of the active lifetime.
    @Override
    public void onPause() {
    	// Suspend UI updates, threads, or CPU intensive processes
    	// that don't need to be updated when the Activity isn't
    	// the active foreground Activity.
    	super.onPause();
    }
    
    // Called at the end of the visible lifetime.
    @Override
    public void onStop() {
    	// Suspend remaining UI updates, threads, or processing 
    	// that aren't required when the Activity isn't visible.
    	// Persist all edits or state changes
    	// as after this call the process is likely to be killed.
    	super.onStop();
    }
    
    // Sometimes called at the end of the full lifetime.
    @Override
    public void onDestroy() {
    	// Clean up any resources including ending threads,
    	// closing database connections etc.
    	super.onDestroy();
    }
}
