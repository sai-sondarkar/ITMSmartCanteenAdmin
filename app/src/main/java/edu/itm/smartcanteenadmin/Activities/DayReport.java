package edu.itm.smartcanteenadmin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.philliphsu.bottomsheetpickers.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.itm.smartcanteenadmin.FirebaseExtra.FirebaseInit;
import edu.itm.smartcanteenadmin.Models.CanteenServiceModel;
import edu.itm.smartcanteenadmin.R;

public class DayReport extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener  {


    long startTime, endTime;
    List<CanteenServiceModel> canteenServiceModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_report);

        initCalender();





    }

    public void initCalender(){
        Calendar now = Calendar.getInstance();

        DatePickerDialog date = new DatePickerDialog.Builder(
                DayReport.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH))
                /* ... Set additional options ... */
                .build();

        date.show(getSupportFragmentManager(),"sai");
    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {

        Calendar cal = new java.util.GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        cal.set(Calendar.HOUR,00);
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.SECOND,00);
        cal.set(Calendar.MILLISECOND,1);

        startTime = cal.getTimeInMillis();
        Log.d("time1",startTime+"");

        cal.set(Calendar.HOUR,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        cal.set(Calendar.MILLISECOND,999);

        endTime = cal.getTimeInMillis();
        Log.d("time2",endTime+"");

        getDataFromFirebase();

    }


    public void getDataFromFirebase(){
        FirebaseInit.getDatabase().getReference().child("CanteenData").orderByChild("timeStamp").startAt(startTime).endAt(endTime).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CanteenServiceModel canteenServiceModel = dataSnapshot.getValue(CanteenServiceModel.class);
                canteenServiceModelList.add(canteenServiceModel);

                Toast.makeText(getApplicationContext(),""+canteenServiceModel.getStudentName(),Toast.LENGTH_SHORT).show();

                Log.d("data", canteenServiceModel.getRollNumber() + canteenServiceModel.getStudentName() + canteenServiceModel.getTimeStamp());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
