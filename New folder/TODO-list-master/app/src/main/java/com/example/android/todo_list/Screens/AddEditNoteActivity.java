package com.example.android.todo_list.Screens;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.todo_list.R;

public class AddEditNoteActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    NumberPicker numberPicker;
    String prio;
    String repeat;


    // notification



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        //notification
        createNotificationChannel();

        title = findViewById(R.id.addNoteActivity_title);
        description = findViewById(R.id.addNoteActivity_description);
        numberPicker = findViewById(R.id.addNote_numberPacker);
        numberPicker.setMaxValue(3);
        numberPicker.setMinValue(1);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.importance, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Toast.makeText(parent.getContext(), "Prioity:Low", Toast.LENGTH_SHORT).show();
                        spinner.setSelection(position);
                        prio="Low";
                        break;
                    case 1:
                        Toast.makeText(parent.getContext(), "Prioity:Medium", Toast.LENGTH_SHORT).show();
                        spinner.setSelection(position);
                        prio="Medium";
                        break;
                    case 2:
                        Toast.makeText(parent.getContext(), "Prioity:High", Toast.LENGTH_SHORT).show();
                        spinner.setSelection(position);
                        prio="High";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.setSelection(0);
                prio="Low";

            }
        });



        //spinner2 code

        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.reps, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(0);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Toast.makeText(parent.getContext(), "Repeat:Daily", Toast.LENGTH_SHORT).show();
                        spinner2.setSelection(position);
                        repeat="Daily";
                        break;
                    case 1:
                        Toast.makeText(parent.getContext(), "Repeat:Weekly", Toast.LENGTH_SHORT).show();
                        spinner2.setSelection(position);
                        repeat="Weekly";
                        break;
                    case 2:
                        Toast.makeText(parent.getContext(), "Repeat:Monthly", Toast.LENGTH_SHORT).show();
                        spinner2.setSelection(position);
                        repeat="Monthlty";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner2.setSelection(0);
                repeat="Daily";

            }
        });

        //notification

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            setTitle("Edit Note");
            title.setText(intent.getStringExtra("title"));
            description.setText(intent.getStringExtra("description"));
            numberPicker.setValue(intent.getIntExtra("priority", 1));
            prio="prio";
            repeat="repeat";
//            prio.equals(intent.getStringExtra("prio"));
//            repeat.equals(intent.getStringExtra("repeat"));


        } else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save_note) {
            saveNote();
        }
        return true;
    }
    //notification

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name="Tss";
            String description="hhhh";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel("kksks",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


    private void saveNote() {
        String note_title = title.getText().toString();
        String note_description = description.getText().toString();
        int note_priority = numberPicker.getValue();
        String note_prio=prio;
        String note_repeat=repeat;

        Intent intent = new Intent();
        intent.putExtra("note_title", note_title);
        intent.putExtra("note_description", note_description);
        intent.putExtra("note_priority", note_priority);
        intent.putExtra("note_prio",note_prio);
        intent.putExtra("note_repeat",note_repeat);
        int id = getIntent().getIntExtra("id",-1);
        if (id != -1){
            intent.putExtra("id",id);
        }
        //notificaion

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"newnot")
                .setSmallIcon(R.drawable.s)
                .setContentTitle("Task Created")
                .setContentText("Task Desc")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(10, builder.build());

        setResult(RESULT_OK, intent);
        finish();

    }
}
