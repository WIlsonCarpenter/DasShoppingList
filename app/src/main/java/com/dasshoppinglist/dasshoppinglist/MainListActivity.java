package com.dasshoppinglist.dasshoppinglist;

import android.app.ListActivity;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;


public class MainListActivity extends ListActivity {
    int request_Code = 2;
    //list of list items
    ArrayList<String> listItems = new ArrayList<String>();
    //string adapter to handle listview
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.calcMenuButton:
                Intent intent = new Intent(getApplicationContext(), CalcActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    public void list_click(View v) {
        Intent intent = new Intent(getApplicationContext(), ListDetailsActivity.class);
        startActivity(intent);
    }

    public void calculator_click(View v) {
        Intent intent = new Intent(getApplicationContext(), CalcActivity.class);
        startActivity(intent);
    }



    public void addItems(View v) {
        String listName = ((EditText)findViewById(R.id.addListTextBox)).getText().toString();
        listItems.add(listName);
        adapter.notifyDataSetChanged();
    }
}
