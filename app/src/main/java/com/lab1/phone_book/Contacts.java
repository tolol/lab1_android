package com.lab1.phone_book;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Contacts {
    private static Contacts contacts;
    public MyAsyncTask loadContacts;
    private Map<Integer,Person> contacts_array = new HashMap<>();
    private ContactsLoadListener myContactsLoadListener;
    private Context mainActivityContext;

    private Contacts(Context mainActivityContext){
        this.mainActivityContext = mainActivityContext;
        loadContacts = new MyAsyncTask();
        loadContacts.execute();
    }

    public void addContact(String firstname, String lastname, String phone, String email){
        Integer new_id = contacts.getContacts().size();
        Person new_person = new Person(firstname, lastname, phone, email, new_id);
        contacts.getContacts().put(new_id, new_person);
    }

    public void updateContact(String firstname, String lastname, String phone, String email, Integer id){
        Person person = contacts.getContacts().get(id);

        person.setFirstname(firstname);
        person.setLastname(lastname);
        person.setPhone(phone);
        person.setEmail(email);
    }

    public Map<Integer,Person> getContacts() {
        if (contacts_array == null) {
            return new HashMap<>();
        }
        else{
            return contacts_array;}
    }

    public static Contacts getInstance(Context mainActivityContext){
        if (contacts == null){
            contacts = new Contacts(mainActivityContext);
        }

        return contacts;
    }

    public void setLoadListener(ContactsLoadListener myContactsLoadListener){
        this.myContactsLoadListener = myContactsLoadListener;
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Map<Integer,Person>> {

        @Override
        protected Map<Integer,Person> doInBackground(Void... params) {
            String fileContent;


            Map<Integer,Person> contacts = new HashMap<>();
            try {
                AssetManager assetManager = mainActivityContext.getAssets();
                InputStream stream = assetManager.open("contacts_data.json");
                Integer size = stream.available();
                byte[] bufer = new byte[size];
                stream.read(bufer);
                stream.close();
                fileContent = new String(bufer);
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
            try {
                JSONArray jsonArray = new JSONArray(fileContent);
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    Person person = new Person(jsonObject.getString("firstname"),
                            jsonObject.getString("lastname"),
                            jsonObject.getString("phone"),
                            jsonObject.getString("email"),
                            i);
                    contacts.put(i, person);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
//            e.printStackTrace();
            }
            return contacts;
        }

        @Override
        protected void onPostExecute(Map<Integer,Person> result) {
            contacts_array = result;
            myContactsLoadListener.onLoadComplete(contacts_array);
        }
    }


    public interface ContactsLoadListener{

        public void onLoadComplete(Map<Integer,Person> contacts_array);

    }
}
