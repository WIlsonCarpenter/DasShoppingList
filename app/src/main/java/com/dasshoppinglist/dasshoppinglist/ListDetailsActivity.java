package com.dasshoppinglist.dasshoppinglist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ListDetailsActivity extends ActionBarActivity {
    int request_Code = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);
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
        if (id == R.id.action_settings) {
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
        startActivityForResult(i, request_Code);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //findViewById(R.id.itemCheck1);

        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {
                TextView test = (TextView)findViewById(R.id.itemIndPrice1);
                test.setText("$" + data.getData().toString() + " ea");
                //Toast.makeText(this,data.getData().toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

}
