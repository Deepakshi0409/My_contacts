package com.example.mycontacts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import org.w3c.dom.Text;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_DATA_NAME = "extra_task_name";
    public static final String EXTRA_DATA_PHONE = "extra_task_phone";
    public static final String EXTRA_DATA_EMAIL = "extra_task_email";
    public static final String EXTRA_DATA_AGE = "extra_task_age";
    public static final String EXTRA_DATA_CITY = "extra_task_city";
    public static final String EXTRA_DATA_COLLEGE = "extra_task_college";
    public static final String EXTRA_DATA_GENDER = "extra_task_gender";

    private addViewModel mAddViewModel;

    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Bundle extras = getIntent().getExtras();
        mAddViewModel = new ViewModelProvider(this).get(addViewModel.class);

        final EditText Name = findViewById(R.id.newname);
        final EditText Phone = findViewById(R.id.newnumber);
        final EditText Age = findViewById(R.id.newage);
        final EditText City = findViewById(R.id.newcity);
        final EditText College = findViewById(R.id.newcollege);
        final EditText Email = findViewById(R.id.newemail);
        final RadioGroup GenderGroup = findViewById(R.id.gender);
        RadioButton male = findViewById(R.id.rb1);
        RadioButton female = findViewById(R.id.rb2);

        TextView save = findViewById(R.id.newbutton);

        if (extras != null) {
            String ContactName = extras.getString(EXTRA_DATA_NAME, "");
            String ContactPhone = extras.getString(EXTRA_DATA_PHONE, "");
            String ContactEmail = extras.getString(EXTRA_DATA_EMAIL, "");
            String ContactAge = extras.getString(EXTRA_DATA_AGE, "");
            String ContactCity = extras.getString(EXTRA_DATA_CITY, "");
            String ContactCollege = extras.getString(EXTRA_DATA_COLLEGE, "");
            String ContactGender = extras.getString(EXTRA_DATA_GENDER, "");


            if (!ContactName.isEmpty()) {
                Name.setText(ContactName);
            }

            if (!ContactPhone.isEmpty()) {
                Phone.setText(ContactPhone);
            }

            if (!ContactAge.isEmpty()) {
                Age.setText(ContactAge);
            }
            if (!ContactGender.isEmpty()) {
                if (ContactGender.equals("Male")) {
                    male.setChecked(true);
                } else
                    female.setChecked(true);
            }


            if (!ContactEmail.isEmpty()) {
                Email.setText(ContactEmail);
            }

            if (!ContactCity.isEmpty()) {
                City.setText(ContactCity);
            }

            if (!ContactCollege.isEmpty()) {
                College.setText(ContactCollege);
                College.setSelection(ContactCollege.length());
                College.requestFocus();
            }


            save.setText("Update");
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String phone = Phone.getText().toString();
                String email = Email.getText().toString();
                String age = Age.getText().toString();
                int genderId = GenderGroup.getCheckedRadioButtonId();
                radioButton =  findViewById(genderId);
                String gender = radioButton.getText().toString();
                String city = City.getText().toString();
                String college = College.getText().toString();
                if (!name.isEmpty() && !phone.isEmpty() && !age.isEmpty() && !gender.isEmpty() && !city.isEmpty() && !college.isEmpty() && !email.isEmpty()) {
                    if (extras != null) {
                        Contact task = new Contact(name, phone, email, age,gender, city, college);
                        Log.e("TAG", task.getTaskName());
                        mAddViewModel.updateTask(task);
                        Intent intent = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), " Updated ", Toast.LENGTH_SHORT).show();
                    } else {
                        Contact task = new Contact(name, phone, email, age,gender, city, college);
                        mAddViewModel.insertTask(task);
                        Toast.makeText(getApplicationContext(), " Contact Added", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Missed an input", Toast.LENGTH_SHORT).show();
                }
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}