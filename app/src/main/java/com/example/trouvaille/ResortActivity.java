package com.example.trouvaille;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ResortActivity extends AppCompatActivity {

    ListView list;
    CustomListRe adapter;
    String hotels[] = {
            "The Revanta",
            "Iebua Lucknow",
            "Clarks Avadh",
            "Hotel Charans Plaza",
            "Comfort Inn",
            "Abhi Guest House",
            "Hilton Garden Inn",
            "Hyatt Regency Lucknow",
            "Radisson Lucknow City Center",
            "La Place Sarovar Portico, Lucknow",
            "Hotel Lilawati Grand",
            "Hotel Royal de casa"
    };
    Integer[] imageId = {
            R.drawable.hotel_delhi_1,
            R.drawable.hotel_delhi_2,
            R.drawable.hotel_delhi_3,
            R.drawable.hotel_delhi_4,
            R.drawable.hotel_delhi_5,
            R.drawable.hotel_delhi_6,
            R.drawable.hotel_delhi_7,
            R.drawable.hotel_delhi_8,
            R.drawable.hotel_delhi_9,
            R.drawable.hotel_delhi_10,
            R.drawable.hotel_delhi_11,
            R.drawable.hotel_delhi_12,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resort);
        adapter = new CustomListRe(ResortActivity.this, hotels, imageId);
        list = findViewById(R.id.resort_list);
        list.setAdapter(adapter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            getSupportActionBar().setDisplayShowTitleEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    startActivity(new Intent(ResortActivity.this, MainActivity.class));
                }
            });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId()==R.id.signout)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
        }
        else if(item.getItemId()==R.id.setting)
        {
            Toast.makeText(ResortActivity.this,"You have selected the settings",Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.search)
        {
            SearchView searchView=(SearchView) item.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s)
                {
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String s)
                {
                    adapter.getFilter().filter(s);
                    return false;
                }
            });
        }
        return true;
    }
}