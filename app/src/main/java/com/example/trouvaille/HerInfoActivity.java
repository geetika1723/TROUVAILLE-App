package com.example.trouvaille;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HerInfoActivity extends AppCompatActivity {

    public static final String TAG="DataBase";

    public String title;
    private TextView t1,t2,t3,t4,t5,t6,t7;
    Button submit,more;
    DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_her_info);

        Bundle bundle =getIntent().getExtras();
        title=bundle.getString("key_her");
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref =database.getReference("attractions");
        myref = ref.child("heritages").child(title);

        t1=findViewById(R.id.name);
        t2=findViewById(R.id.address);
        t3=findViewById(R.id.ranking);
        t4=findViewById(R.id.money);
        t5=findViewById(R.id.details);
        t6=findViewById(R.id.phoneno);
        t7=findViewById(R.id.time);

        submit=findViewById(R.id.btn_confirm);
        more=findViewById(R.id.btn_her_more);

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DetailHer temp= snapshot.getValue(DetailHer.class);

                t1.setText(temp.title);
                t2.setText(temp.address);
                t3.setText(temp.ranking);
                t4.setText(temp.money);
                t5.setText(temp.description);
                t6.setText(temp.phoneno);
                t7.setText(temp.time);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG,"Failed to read the value",error.toException());
                Toast.makeText(HerInfoActivity.this, "Failed To load post", Toast.LENGTH_SHORT).show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HerInfoActivity.this, "Booking Confirmed", Toast.LENGTH_SHORT).show();
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HerInfoActivity.this,Her_MoreDetails.class));
                finish();
            }
        });
    }
}









