package com.dasshoppinglist.dasshoppinglist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class ItemDetailsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        //---get the Bundle object passed in---
        Bundle bundle = getIntent().getExtras();

        //---get the data using the getString()---
        ((EditText)findViewById(R.id.editText5)).setText(bundle.getString("itemName"));
        ((EditText)findViewById(R.id.priceField)).setText( Double.toString(bundle.getDouble("txt_price")) );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_details, menu);
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

    public void onSave(final View view) {
        Intent data = new Intent();
        Bundle extras = new Bundle();

        //---get the EditText view---
        //EditText txt_price = (EditText) findViewById(R.id.priceField);


        extras.putString("itemName", ((EditText)findViewById(R.id.editText5)).getText().toString());
        extras.putDouble("txt_price", Double.parseDouble(((EditText)findViewById(R.id.priceField)).getText().toString()));


        //---set the data to pass back---
        //data.setData(Uri.parse(txt_price.getText().toString()));
        //data.setData(Uri.parse("junk"));
        //data.putExtra("age3", 45);



        data.putExtras(extras);
        setResult(RESULT_OK, data);

        //---closes the activity---
        finish();
    }
}
