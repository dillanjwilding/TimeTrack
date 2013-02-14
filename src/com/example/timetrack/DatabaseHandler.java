package com.example.timetrack;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "tasksManager";
 
    // Contacts table name
    private static final String TABLE_TASKS = "tasks";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_START_TIME = "startTime";
    private static final String KEY_ELAPSED = "elapsedTime";
    private static final String KEY_ON = "on";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_START_TIME + " FLOAT," + KEY_ELAPSED + "FLOAT," + KEY_ON + "BOOLEAN" + ")";
        db.execSQL(CREATE_TASKS_TABLE);
    }
 
    // Upgrading database
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
 
        // Create tables again
        onCreate(db);
    }
    
    // Adding new task
    public void addTask(Task task) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_ID, task.getID()); // Task ID
        values.put(KEY_NAME, task.getName()); // Task Name
        values.put(KEY_START_TIME, task.getStartTime()); // Task StartTime
        values.put(KEY_ELAPSED, task.getElapsedTime()); // Task ElapsedTime
        values.put(KEY_ON, task.getState()); // Task State
     
        // Inserting Row
        db.insert(TABLE_TASKS, null, values);
        db.close(); // Closing database connection
    }
     
    // Getting single task
    public Task getTask(int id) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	 
        Cursor cursor = db.query(TABLE_TASKS, new String[] { KEY_ID,
                KEY_NAME, KEY_START_TIME, KEY_ELAPSED, KEY_ON }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Task task = new Task();
        task.setID(Integer.parseInt(cursor.getString(0)));
        task.setName(cursor.getString(1));
        task.setTime(Long.parseLong(cursor.getString(2)), 
        		Long.parseLong(cursor.getString(3)), 
        		Boolean.parseBoolean(cursor.getString(4)));
        return task;
    }
     
    // Getting all tasks
    public List<Task> getAllTasks() {
    	List<Task> taskList = new ArrayList<Task>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setID(Integer.parseInt(cursor.getString(0)));
                task.setName(cursor.getString(1));
                task.setTime(Long.parseLong(cursor.getString(2)), 
                		Long.parseLong(cursor.getString(3)), 
                		Boolean.parseBoolean(cursor.getString(4)));
                // Adding contact to list
                taskList.add(task);
            } while (cursor.moveToNext());
        }
     
        // return task list
        return taskList;
    }
     
    // Getting tasks count
    public int getTasksCount() {
    	String countQuery = "SELECT  * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
    // Updating single task
    public int updateTask(Task task) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_START_TIME, task.getStartTime());
        values.put(KEY_ELAPSED, task.getElapsedTime());
        values.put(KEY_ON, task.getState());
     
        // updating row
        return db.update(TABLE_TASKS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(task.getID()) });
    }
     
    // Deleting single task
    public void deleteTask(Task task) {
    	SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID + " = ?",
                new String[] { String.valueOf(task.getID()) });
        db.close();
    }
}