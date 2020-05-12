package com.lab1.phone_book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Map<Integer,Person> contacts;
    private final View.OnClickListener mOnClickListener;


    DataAdapter(Context context, Map<Integer,Person> contacts, View.OnClickListener mOnClickListener) {
        this.contacts = contacts;
        this.inflater = LayoutInflater.from(context);
        this.mOnClickListener = mOnClickListener;
    }

    void updateDataAdapter(Map<Integer, Person> contacts){
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_contact, parent, false);
        view.setOnClickListener(mOnClickListener);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setOnClickListener(mOnClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Person person = contacts.get(position);
        holder.firstnameView.setText(person.getFirstname());
        holder.lastnameView.setText(person.getLastname());
        holder.phoneView.setText(person.getPhone());
        holder.emailView.setText(person.getEmail());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView firstnameView;
        final TextView lastnameView;
        final TextView phoneView;
        final TextView emailView;
        ViewHolder(View view){
            super(view);
            firstnameView = view.findViewById(R.id.firstname);
            lastnameView = view.findViewById(R.id.lastname);
            phoneView = view.findViewById(R.id.phone);
            emailView = view.findViewById(R.id.email);
        }
    }
}
