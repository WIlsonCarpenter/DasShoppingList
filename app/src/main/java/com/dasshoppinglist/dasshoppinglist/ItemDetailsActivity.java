package com.dasshoppinglist.dasshoppinglist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;


public class ItemDetailsActivity extends ActionBarActivity {
    String[] categories;
    ArrayList<String> catList;
    ArrayAdapter<String> adapter;
    Spinner catSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

/*        NumberPicker np = (NumberPicker)findViewById(R.id.quantity);
        String[] nums = new String[300];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.toString(i);
        }
        np.setMinValue(1);
        np.setMaxValue(300);
        np.setWrapSelectorWheel(false);
        np.setDisplayedValues(nums);
        np.setValue(1);*/

        //initialize category array with values from strings.xml
        categories = getResources().getStringArray(R.array.categoryArray);
        catSpinner = (Spinner) findViewById(R.id.categorySpinner);
        catList= new ArrayList<String>(Arrays.asList(categories));

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, catList);
        catSpinner.setAdapter(adapter);

        //---get the Bundle object passed in---
        Bundle bundle = getIntent().getExtras();


        //---get the data using the getString()---
        ((EditText)findViewById(R.id.itemName1)).setText(bundle.getString("itemName"));
        ((EditText)findViewById(R.id.priceField)).setText( Double.toString(bundle.getDouble("txt_price")) );
        ((EditText)findViewById(R.id.quantity)).setText((Integer.toString(bundle.getInt("itemQty"))));


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
        //Do not send back the data if cancel was pressed.
        if( ((Button)view).equals(findViewById(R.id.cancelButton))) {
            Log.d("ButtonCheck", "Cancel Hit through object check");
            setResult(RESULT_CANCELED);
            finish();
        }

        Intent data = new Intent();
        Bundle extras = new Bundle();

        //---get the EditText view---
        //EditText txt_price = (EditText) findViewById(R.id.priceField);


        extras.putString("itemName", ((EditText) findViewById(R.id.itemName1)).getText().toString());
        extras.putDouble("txt_price", Double.parseDouble(((EditText) findViewById(R.id.priceField)).getText().toString()));
        extras.putInt("itemQty", Integer.parseInt(((EditText) findViewById(R.id.quantity)).getText().toString()));
    //    extras.putInt("itemQty", ((NumberPicker) findViewById(R.id.quantity)).getValue());


        //---set the data to pass back---
        //data.setData(Uri.parse(txt_price.getText().toString()));
        //data.setData(Uri.parse("junk"));
        //data.putExtra("age3", 45);



        data.putExtras(extras);
        setResult(RESULT_OK, data);

        //---closes the activity---
        finish();
    }

    public void addCategoryClick(View view) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View addCategoryPrompt = layoutInflater.inflate(R.layout.prompt_add_category, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(addCategoryPrompt);

        final EditText newCategory = (EditText)addCategoryPrompt.findViewById(R.id.addCategoryEdit);

        alertDialogBuilder.setCancelable(false)
                          .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  String newCat = newCategory.getText().toString();
                                  adapter.add(newCat);
                                  catSpinner.setAdapter(adapter);
                              }
                          })
                          .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  dialog.cancel();
                              }
                          });
        AlertDialog addCategoryDialog = alertDialogBuilder.create();
        addCategoryDialog.show();

    }

    public void okAddCategory(View view) {

    }
}
