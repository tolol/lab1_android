package com.lab1.phone_book;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE_PREF = "com.lab1.phone_book.";
    private Contacts contacts;
    private DataAdapter adapter;
    private final View.OnClickListener mOnClickListener = new MyOnClickListener();
    private RecyclerView recyclerView;
    public MyContactsLoadListener myContactsLoadListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myContactsLoadListener = new MyContactsLoadListener();
        Context mainActivityContext = getApplicationContext();
        contacts = Contacts.getInstance(mainActivityContext);
        contacts.setLoadListener(myContactsLoadListener);

        recyclerView = findViewById(R.id.list);
        adapter = new DataAdapter(this, contacts.getContacts(), mOnClickListener);
        recyclerView.setAdapter(adapter);
    }


    public class MyContactsLoadListener implements Contacts.ContactsLoadListener {

        @Override
        public void onLoadComplete(Map<Integer,Person> contacts_array) {
            adapter.updateDataAdapter(contacts_array);
        }
    }

    public class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            int itemPosition;
            if (R.id.imageView == view.getId()) {
                itemPosition = recyclerView.getChildLayoutPosition((View) view.getParent());
                Person person_item = contacts.getContacts().get(itemPosition);
                String phoneText = person_item.getPhone();

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneText));
                startActivity(intent);
                return;
            }
            else{
                itemPosition = recyclerView.getChildLayoutPosition(view);
            }

            Person person_item = contacts.getContacts().get(itemPosition);

            Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
            String firstnameText = person_item.getFirstname();
            String lastnameText = person_item.getLastname();
            String phoneText = person_item.getPhone();
            String emailText = person_item.getEmail();
            Integer id = person_item.getId();

            intent.putExtra(EXTRA_MESSAGE_PREF + "firstname", firstnameText);
            intent.putExtra(EXTRA_MESSAGE_PREF + "lastname", lastnameText);
            intent.putExtra(EXTRA_MESSAGE_PREF + "phone", phoneText);
            intent.putExtra(EXTRA_MESSAGE_PREF + "email", emailText);
            intent.putExtra(EXTRA_MESSAGE_PREF + "id", id);

            startActivity(intent);
        }
    }

    public void addContact(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE_PREF + "addNewContact", true);
        startActivity(intent);
    }

}
