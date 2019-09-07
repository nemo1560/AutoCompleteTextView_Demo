package com.example.autocompletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView countrySpn;
    private List<iCountry> list;
    private AutoCompleteTextViewAdapter.AutoCompleteAdapter spnAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countrySpn = findViewById(R.id.countrySpn);
        getData();
    }

    private List<iCountry> getData() {
        list = new ArrayList<>();
        list.add(new iCountry("Viet Nam",1));
        list.add(new iCountry("Combodia",2));
        list.add(new iCountry("Brunei",3));
        list.add(new iCountry("Indonesia",4));
        list.add(new iCountry("Laos",5));
        list.add(new iCountry("Malaysia",6));
        list.add(new iCountry("Myanmar",7));
        list.add(new iCountry("Philippines",8));
        list.add(new iCountry("Singapore",9));
        list.add(new iCountry("Thailand",10));
        return list;
    }

    @Override
    protected void onStart() {
        super.onStart();

        spnAdapter = new AutoCompleteTextViewAdapter.AutoCompleteAdapter(this,R.layout.item_spinner,list);
        countrySpn.setAdapter(spnAdapter);
        countrySpn.setThreshold(1);
        countrySpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iCountry iCountry = (com.example.autocompletetextview.iCountry) parent.getAdapter();
                Toast.makeText(getApplicationContext(),iCountry.getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
