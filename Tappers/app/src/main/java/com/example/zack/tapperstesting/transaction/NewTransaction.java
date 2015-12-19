package com.example.zack.tapperstesting.transaction;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.zack.tapperstesting.R;
import com.example.zack.tapperstesting.contact.ContactUtil;

import java.util.Calendar;


public class NewTransaction extends Activity {


    private int yearDate, monthDate, dayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);


        Calendar cal = Calendar.getInstance();

        yearDate = cal.get(Calendar.YEAR);
        monthDate = cal.get(Calendar.MONTH);
        dayDate = cal.get(Calendar.DAY_OF_MONTH);

        Button btnAccept = (Button) findViewById(R.id.cmdConfirmTrans);
        Button btnDate = (Button) findViewById(R.id.btnPickNewDateTran);

        Typeface thin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface regular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");


        TextView txtTransactionTitle = (TextView) findViewById(R.id.txtTransactionTitle);
        txtTransactionTitle.setTypeface(light);

        TextView lblReasonTran = (TextView) findViewById(R.id.lblReasonTran);
        lblReasonTran.setTypeface(light);

        TextView lblDateTran = (TextView) findViewById(R.id.lblDateTran);
        lblDateTran.setTypeface(light);

        final TextView amount = (TextView) findViewById(R.id.txtTransactionTran);
        amount.setTypeface(light);
        final TextView reason = (TextView) findViewById(R.id.txtReasonTran);
        reason.setTypeface(light);
        final TextView date = (TextView) findViewById(R.id.lblSetDateTran);
        date.setTypeface(light);
        final TextView back = (TextView) findViewById(R.id.lblBackContactsTransaction);
        back.setTypeface(regular);
        final RadioButton to = (RadioButton) findViewById(R.id.rdbToTran);
        to.setTypeface(light);
        final RadioButton from = (RadioButton) findViewById(R.id.rdbFromTran);
        from.setTypeface(light);

        date.setText("Date Selected: " + dayDate + "/" + (monthDate + 1) + "/" + yearDate);


        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double am = 1;
                try
                {
                    am = Double.parseDouble(amount.getText().toString());
                }
                catch(Exception e)
                {

                }
                String tofrom = "";

                if(to.isChecked())
                {
                    tofrom = "TO";
                }
                else
                {
                    tofrom = "FROM";
                }

                TransactionType type = TransactionType.valueOf(tofrom);

                ContactUtil.contact.addTransaction(new Transaction(
                        type, am, date.getText().toString().substring(15),
                        reason.getText().toString()));

                Intent returnIntent = getIntent();
                setResult(1, returnIntent);
                finish();
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBackNewTransaction);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = getIntent();
                setResult(-1, returnIntent);
                finish();
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(id == 0)
        {
            return new DatePickerDialog(this, dPickerListener, yearDate, monthDate, dayDate);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dPickerListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    yearDate = year;
                    monthDate = monthOfYear + 1;
                    dayDate = dayOfMonth;
                    TextView text = (TextView) findViewById(R.id.lblSetDateTran);
                    text.setText("Date Selected: " + dayDate + "/" + monthDate + "/" + yearDate);
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_transaction, menu);
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
}
