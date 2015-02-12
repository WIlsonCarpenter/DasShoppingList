package com.dasshoppinglist.dasshoppinglist;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ListDetailsActivity extends ActionBarActivity {
    int request_Code = 1;
    boolean ticked = false;

    //Items for storing list of items
    ArrayList<String> listItems;
    //string adapter to handle listview
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);

        CheckBox temp = (CheckBox) findViewById(R.id.itemCheck1);
        temp.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                Log.d("DSS", "You held the check box");
                onEditClick(v);
                return true;
            }
        });


        //Items to store the list of items
        listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        //setListAdapter(adapter);

        ListView myList=(ListView)findViewById(R.id.listItems);

        myList.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.calcMenuButton) {
            Intent i = new Intent(this, CalcActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onEditClick(final View view) {
        //startActivity(new Intent("net.learn2develop.SecondActivity"));
        //or
        //startActivity(new Intent(this, SecondActivity.class));
        //startActivity(new Intent(this, ItemDetailsActivity.class));
        //startActivityForResult(new Intent("ItemDetailsActivity.class"), request_Code);

        Intent i = new Intent(this, ItemDetailsActivity.class);

        Bundle extras = new Bundle();
        extras.putString("itemName", ((CheckBox)findViewById(R.id.itemCheck1)).getText().toString());
        extras.putDouble("txt_price", Double.parseDouble(((TextView) findViewById(R.id.itemIndPrice1)).getText().toString()));
        extras.putInt("itemQty", Integer.parseInt(((TextView)findViewById(R.id.itemQty1)).getText().toString()));


        i.putExtras(extras);

        startActivityForResult(i, request_Code);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //findViewById(R.id.itemCheck1);

        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {
                //TextView test = (TextView)findViewById(R.id.itemIndPrice1);
                //test.setText("$" + data.getData().toString() + " ea");
                //Toast.makeText(this,data.getData().toString(), Toast.LENGTH_LONG).show();

                //Bundle bundle = getIntent().getExtras();

                Bundle bundle = data.getExtras();

                //---get the data using the getString()---
                ((TextView)findViewById(R.id.itemCheck1)).setText(bundle.getString("itemName"));
                ((TextView)findViewById(R.id.itemIndPrice1)).setText( Double.toString(bundle.getDouble("txt_price")) );
                ((TextView)findViewById(R.id.itemQty1)).setText( Integer.toString(bundle.getInt("itemQty")) );

            }
        }
    }
    //handles when an item is checked off the list
    public void onCheckClick(View view) {
        CheckBox chkBox = (CheckBox)view;
        if (chkBox.isChecked()) {
            chkBox.setTextColor(Color.GRAY);
            chkBox.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            chkBox.setTextColor(Color.BLACK);
            chkBox.setPaintFlags(0);
        }
    }

    /**
     * Takes text from the textbox and adds it to the listview
     * @param v
     */
    public void addItems(View v) {
        String itemName = ((EditText)findViewById(R.id.enterNewItem)).getText().toString();
        EditText enterNewItem = (EditText)findViewById((R.id.enterNewItem));

        //if the textbox is currently empty, do nothing
        if(itemName.equals("")) {
            return;
        }

        adapter.add(itemName);

        enterNewItem.setText("");
    }


    /*private OnClickListener mCorkyListener = new OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
        }
    };*/
}
