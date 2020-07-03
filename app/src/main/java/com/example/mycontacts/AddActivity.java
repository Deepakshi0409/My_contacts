package com.example.mycontacts;

import android.os.Bundle;
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

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String EXTRA_DATA_NAME = "extra_task_name";
    public static final String EXTRA_DATA_PHONE = "extra_task_phone";
    public static final String EXTRA_DATA_EMAIL = "extra_task_email";
    public static final String EXTRA_DATA_AGE = "extra_task_age";
    public static final String EXTRA_DATA_CITY = "extra_task_city";
    public static final String EXTRA_DATA_COLLEGE = "extra_task_college";
    public static final String EXTRA_DATA_GENDER = "extra_task_gender";
    private addViewModel viewModel;
    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Bundle extras = getIntent().getExtras();

        viewModel = new ViewModelProvider(this).get(addViewModel.class);

        //creating references to views
        final EditText name = findViewById(R.id.newname);
        final EditText phone = findViewById(R.id.newnumber);
        final Button saveBtn = findViewById(R.id.newbutton);
        final EditText email = findViewById(R.id.newemail);
        final EditText age = findViewById(R.id.newage);
        final EditText city = findViewById(R.id.newcity);
        final EditText college = findViewById(R.id.newcollege);
        final RadioGroup GenderGroup = findViewById(R.id.gender);
        RadioButton Male = findViewById(R.id.rb1);
        RadioButton Female = findViewById(R.id.rb2);
        if (extras != null) {
            String taskName = extras.getString(EXTRA_DATA_NAME, "");
            String taskPhone = extras.getString(EXTRA_DATA_PHONE, "");
            String taskEmail = extras.getString(EXTRA_DATA_EMAIL, "");
            String taskAge = extras.getString(EXTRA_DATA_AGE, "");
            String taskCity = extras.getString(EXTRA_DATA_CITY, "");
            String taskCollege = extras.getString(EXTRA_DATA_COLLEGE, "");
            String taskGender = extras.getString(EXTRA_DATA_GENDER, "");

            if (!taskName.isEmpty()) {
                name.setText(taskName);
            }
            if (!taskEmail.isEmpty()) {
                name.setText(taskEmail);
            }
            if (!taskAge.isEmpty()) {
                name.setText(taskAge);
            }
            if (!taskCity.isEmpty()) {
                name.setText(taskCity);
            }
            if (!taskCollege.isEmpty()) {
                name.setText(taskCollege);
            }
            if (!taskGender .isEmpty()) {
                if(taskGender.equals("Male")){
                    Male.setChecked(true);
                }
                else  {
                    Female.setChecked(true);
                }
            }

            if (!taskPhone.isEmpty()) {
                phone.setText(taskPhone);
                phone.setSelection(taskPhone.length());
                phone.requestFocus();;
                            }
            saveBtn.setText("UPDATE");
        }
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Phone = phone.getText().toString();
                String Email = email.getText().toString();
                String Age = age.getText().toString();
                String City = city.getText().toString();
                String College = college.getText().toString();
                int genderId = GenderGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(genderId);
                String Gender = radioButton.getText().toString();

                if (!Name.isEmpty() && !Phone.isEmpty()) {
                    if (extras != null) {
                        Contact task = new Contact(Name, Phone, Email, Age, City, College,Gender);
                        viewModel.updateTask(task);
                    } else {
                        Contact task = new Contact(Name, Phone, Email, Age, City, College,Gender);
                        viewModel.insertTask(task);
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
