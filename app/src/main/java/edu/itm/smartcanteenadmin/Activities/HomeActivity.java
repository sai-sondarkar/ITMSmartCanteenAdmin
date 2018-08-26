package edu.itm.smartcanteenadmin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.philliphsu.bottomsheetpickers.date.DatePickerDialog;

import java.util.Calendar;

import edu.itm.smartcanteenadmin.R;


public class HomeActivity extends AppCompatActivity  {

    TextView textViewA1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textViewA1 = (TextView) findViewById(R.id.a1);

        textViewA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this,DayReport.class);
                startActivity(intent);

            }
        });

    }


}
