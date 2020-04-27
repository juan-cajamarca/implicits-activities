package com.example.implicitactivities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText contactName;
    EditText phoneNumber;
    Spinner phoneTypeSpinner;
    EditText contactEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting view elements
        contactName = (EditText) findViewById(R.id.contact_name_edit_text);
        phoneNumber = (EditText) findViewById(R.id.contact_phone_edit_text);
        phoneTypeSpinner = (Spinner) findViewById(R.id.phone_type_spinner);
        contactEmail = (EditText) findViewById(R.id.contact_email_text_view);

        // Adding spinner options
        List spinnerList = new ArrayList<String>();
        spinnerList.add("Personal");
        spinnerList.add("Laboral");
        spinnerList.add("Escolar");

        // Setting array adapter for spinner
        ArrayAdapter spinnerListAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, spinnerList);
        spinnerListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        phoneTypeSpinner.setAdapter(spinnerListAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Setting intent
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                // Passing data
                intent.putExtra(ContactsContract.Intents.Insert.NAME, phoneNumber.getText())
                        .putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber.getText())
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, phoneTypeSpinner.getSelectedItem().toString())
                        .putExtra(ContactsContract.Intents.Insert.EMAIL, contactEmail.getText());

                // Starting activity
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
