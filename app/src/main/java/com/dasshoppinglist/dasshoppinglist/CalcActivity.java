package com.dasshoppinglist.dasshoppinglist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CalcActivity extends ActionBarActivity {
    private TextView calcScreen;

    private double sum;
    private double[] values;
    private String[] symbols;
    private int index;
    private boolean operatorSet;
    private boolean valueSet;
    private boolean resultDisplayed;
    private boolean periodUsed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        //calcScreen = (R.layout())
        calcScreen = (TextView) findViewById(R.id.calcEntryScreen) ;
        resetCalc();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void calcDigitClick(final View view) {
        Button btn = (Button)view;

        if (resultDisplayed)
        {
            resetCalc();
            resultDisplayed = false;
        }
        //Remove the first 0 when adding another digit.
        if (calcScreen.getText().toString().length() == 1 && calcScreen.getText().toString().substring(0, 1).equals("0")) {
            calcScreen.setText("");
        }

        calcScreen.append(btn.getText());


        operatorSet = false;
        valueSet = true;
    }



    /**
     * Resets the calculator by erasing all previous calculations and clearing the screen.
     */
    private void resetCalc()
    {
        calcScreen.setText("0");
        sum = 0;
        index = 0;
        operatorSet = false;
        valueSet = false;
        periodUsed = false;
        resultDisplayed = true;

        values = new double[50];
        symbols = new String[50];
    }

    /**
     * Handles button clicks from the operator signs and the equals buttons.
     * The C, CE, and backspace buttons are controlled here as well.

     * @param view The android screen view.
     */
    public void OperationButtonClick(final View view)
    {
        Button btn = (Button)view;
        String value = btn.getText().toString();


        if (value.equals("=")) {
            //Calculate only when the last entry was a numeric value.
            if (valueSet && !calcScreen.getText().toString().equals("-")) {
                storeNumber();
                calculate();
            }
        } else if (value.equals("+")) {
            enterOperator("+");
        } else if (value.equals("-")) {
            enterOperator("-");
        }else if (value.equals("/")) {
            enterOperator("/");
        } else if (value.equals("X")) {
            enterOperator("*");
        } else if (value.equals("C")) {
            resetCalc();
        } else if (value.equals("CE")) {
            //Reset the calculator if calculation was just made, otherwise reset the current number.
            if (resultDisplayed)
            {
                resetCalc();
            }
            else
            {
                calcScreen.setText("");
                periodUsed = false;
                valueSet = false;
            }
        } else if (value.equals("←")) {
            if (calcScreen.getText().length() != 0 && valueSet) {
                //Check if the digit being deleted is a period.
                if(calcScreen.getText().toString().substring(calcScreen.getText().length() - 1,
                        calcScreen.getText().length()).equals(".")) {
                    //Allow future period use.
                    periodUsed = false;
                }

                calcScreen.setText(calcScreen.getText().toString().substring(0,
                        calcScreen.getText().toString().length() - 1));

            }
            if (calcScreen.getText().length() == 0 && valueSet) {
                valueSet = false;
            }
        }

    }

    /**
     * Processes all the number (0-9) button clicks as well as the '.' and '+/-' buttons.
     * The values are displayed on the calculator entry screen.
     *
     * @param view The android screen view.
     */
    public void miscButtonClick(final View view) {
        Button btn = (Button)view;
        String value = btn.getText().toString();

        if (resultDisplayed)
        {
            resetCalc();
            resultDisplayed = false;
        }
        //Remove the first 0 when adding another digit.
        if (calcScreen.getText().toString().length() == 1 && calcScreen.getText().toString().substring(0, 1).equals("0")) {
            calcScreen.setText("");
        }

        //Add number pressed to the entry screen.

        if (value.equals(".") && !periodUsed)
        {
            periodUsed = true;
            calcScreen.append(".");
        }
        else if (value.equals("+/-")) {
            if (calcScreen.getText().length() == 0) {
                calcScreen.setText("-");
            } else {
                if (calcScreen.getText().toString().substring(0, 1).equals("-")) {
                    calcScreen.setText(calcScreen.getText().toString().substring(1, calcScreen.getText().toString().length()));
                } else {
                    calcScreen.setText("-" + calcScreen.getText().toString());
                }
            }


        }
        operatorSet = false;
        valueSet = true;

    }

    /**
     * Stores the current value on the entry screen by converting it to a double value
     * that then gets stored into the array of values.
     */
    private void storeNumber()
    {
        //Restart calculator when more than 50 calculations are made in a row.
        if (index == values.length) {
            resetCalc();
        }
        if (calcScreen.getText().toString().equals(".")) {
            calcScreen.setText("0");
        }
        values[index++] = Double.parseDouble(calcScreen.getText().toString());

    }

    /**
     * Stores the operator sign that the user enters. If a previous operator
     * was just set, it gets overwritten with the new one.

     * @param sign The operation sign.
     */
    private void enterOperator(String sign)
    {
        //Change the last operator if not value has been set yet.
        if (operatorSet) {
            symbols[index - 1] = sign;
        } else {
            if ((valueSet || resultDisplayed) && !calcScreen.getText().toString().equals("-")) {
                symbols[index] = sign;
                storeNumber();
                calcScreen.setText("");
                operatorSet = true;
                periodUsed = false;
                valueSet = false;
                resultDisplayed = false;
            }
        }

    }

    /**
     * Performs the calculation by looping through the entire array of previously entered
     * calculations. Left to right operation order is used.
     */
    private void calculate()
    {
        sum = values[0];
        for (int i = 1; i < index; i++)
        {
            if (symbols[i - 1].equals("+"))
            {
                sum += values[i];
            }
            else if (symbols[i - 1].equals("-"))
            {
                sum -= values[i];
            }
            else if (symbols[i - 1].equals("*"))
            {
                sum *= values[i];
            }
            else if (symbols[i - 1].equals("/"))
            {
                sum /= values[i];
            }
        }

        double tempSum = sum;
        resetCalc();
        calcScreen.setText("" + tempSum);
        storeNumber();
        resultDisplayed = true;
    }

}
