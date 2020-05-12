package com.lab1.phone_book;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    private Integer person_id;
    private Contacts contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Context activityContext = getApplicationContext();
        contacts = Contacts.getInstance(activityContext);
        Intent intent = getIntent();
        boolean addNewContact = intent.getBooleanExtra(MainActivity.EXTRA_MESSAGE_PREF + "addNewContact", false);

        if (addNewContact){
            Button editButton = findViewById(R.id.editButton);
            editButton.setVisibility(View.GONE);
        }
        else{
            String firstname = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_PREF + "firstname");
            String lastname = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_PREF + "lastname");
            String phone = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_PREF + "phone");
            String email = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_PREF + "email");
            this.person_id = intent.getIntExtra(MainActivity.EXTRA_MESSAGE_PREF + "id", 0);

            TextView first_name_field = findViewById(R.id.first_name_field);
            first_name_field.setText(firstname);
            TextView last_name_field = findViewById(R.id.last_name_field);
            last_name_field.setText(lastname);
            TextView phone_field = findViewById(R.id.phone_field);
            phone_field.setText(phone);
            TextView email_field = findViewById(R.id.email_field);
            email_field.setText(email);

            first_name_field.setEnabled(false);
            first_name_field.setClickable(false);
            last_name_field.setEnabled(false);
            last_name_field.setClickable(false);
            phone_field.setEnabled(false);
            phone_field.setClickable(false);
            email_field.setEnabled(false);
            email_field.setClickable(false);

            Button saveButton = findViewById(R.id.saveButton);
            saveButton.setVisibility(View.GONE);
        }
    }

    public void edit(View view) {
        EditText first_name_field = findViewById(R.id.first_name_field);
        EditText last_name_field = findViewById(R.id.last_name_field);
        EditText phone_field = findViewById(R.id.phone_field);
        EditText email_field = findViewById(R.id.email_field);

        first_name_field.setEnabled(true);
        first_name_field.setClickable(true);
        last_name_field.setEnabled(true);
        last_name_field.setClickable(true);
        phone_field.setEnabled(true);
        phone_field.setClickable(true);
        email_field.setEnabled(true);
        email_field.setClickable(true);

        Button editButton = findViewById(R.id.editButton);
        editButton.setVisibility(View.GONE);
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setVisibility(View.VISIBLE);
    }

    public void save(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText firstname = findViewById(R.id.first_name_field);
        EditText last_name_field = findViewById(R.id.last_name_field);
        EditText phone_field = findViewById(R.id.phone_field);
        EditText email_field = findViewById(R.id.email_field);

        String firstname_text = firstname.getText().toString();
        String last_name_field_text = last_name_field.getText().toString();
        String phone_field_text = phone_field.getText().toString();
        String email_field_text = email_field.getText().toString();

        if (this.person_id == null){
            contacts.addContact(firstname_text, last_name_field_text, phone_field_text, email_field_text);
        }
        else{
            contacts.updateContact(firstname_text, last_name_field_text, phone_field_text, email_field_text, this.person_id);
        }

        startActivity(intent);
    }

}
