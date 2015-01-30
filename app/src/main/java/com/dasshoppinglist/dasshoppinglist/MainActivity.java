package com.dasshoppinglist.dasshoppinglist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;


public class MainActivity extends ListActivity {
    ArrayList<String> listItems;
    //string adapter to handle listview
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);

    }

    /**
     * Takes text from the textbox and adds it to the listview
     * @param v
     */
    public void addItems(View v) {
        String listName = ((EditText)findViewById(R.id.addListTextBox)).getText().toString();
        listItems.add(listName);
        adapter.notifyDataSetChanged();
        ((EditText)findViewById(R.id.addListTextBox)).setText("");
    }

    public void list_click(View v) {
        Intent intent = new Intent(getApplicationContext(), ListDetailsActivity.class);
        startActivity(intent);
    }

    public void calculator_click(View v) {
        Intent intent = new Intent(getApplicationContext(), CalcActivity.class);
        startActivity(intent);
    }

}
