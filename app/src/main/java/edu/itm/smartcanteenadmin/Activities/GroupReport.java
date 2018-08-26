package edu.itm.smartcanteenadmin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.philliphsu.bottomsheetpickers.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.itm.smartcanteenadmin.Adapters.CanteenAdapter;
import edu.itm.smartcanteenadmin.FirebaseExtra.FirebaseInit;
import edu.itm.smartcanteenadmin.Models.CanteenServiceModel;
import edu.itm.smartcanteenadmin.R;

public class GroupReport extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener {

    long startTime, endTime;
    private List<CanteenServiceModel> canteenServiceModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CanteenAdapter mAdapter;
    private TextView dateTextView,totaltextView;
    private Calendar cal;
    private int totalNumber=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_report);
        initUiElements();
        initCalender();

    }

    public void initUiElements(){
        dateTextView = (TextView) findViewById(R.id.dateTime);
        totaltextView = (TextView) findViewById(R.id.totalCount);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new CanteenAdapter(canteenServiceModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    public void initCalender(){
        Calendar now = Calendar.getInstance();

        DatePickerDialog date = new DatePickerDialog.Builder(
                GroupReport.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH))
                .build();

        date.show(getSupportFragmentManager(),"sai");

    }

    public void endCalender(){
        Calendar now = Calendar.getInstance();

        DatePickerDialog date = new DatePickerDialog.Builder(
                GroupReport.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH))

                .build();

        date.show(getSupportFragmentManager(),"sai");

    }

    public void setDateValueInUI(){
        String outputPattern = "dd-MMM-yy ";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        dateTextView.setText(outputFormat.format(cal.getTime()));
    }

    public void checkDataAvaliable(){
        FirebaseInit.getDatabase().getReference().child("CanteenData").orderByChild("timeStamp").startAt(startTime).endAt(endTime).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Toast.makeText(getApplicationContext(),"exisits",Toast.LENGTH_SHORT).show();
                    getDataFromFirebase();
                }else {
                    Toast.makeText(getApplicationContext(),"not Exsist",Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getDataFromFirebase(){
        FirebaseInit.getDatabase().getReference().child("CanteenData").orderByChild("timeStamp").startAt(startTime).endAt(endTime).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CanteenServiceModel canteenServiceModel = dataSnapshot.getValue(CanteenServiceModel.class);

                try{
                    totalNumber++;
                    canteenServiceModelList.add(canteenServiceModel);
                    mAdapter.notifyDataSetChanged();
                    totaltextView.setText("Total - "+totalNumber);

                }catch (Exception e){

                }

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
                Toast.makeText(getApplicationContext(),"No nothing",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {

        cal = new java.util.GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        cal.set(Calendar.HOUR,00);
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.SECOND,00);
        cal.set(Calendar.MILLISECOND,1);
        startTime = cal.getTimeInMillis();

        cal.set(Calendar.HOUR,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        cal.set(Calendar.MILLISECOND,999);
        endTime = cal.getTimeInMillis();

        checkDataAvaliable();

        setDateValueInUI();
    }

}
