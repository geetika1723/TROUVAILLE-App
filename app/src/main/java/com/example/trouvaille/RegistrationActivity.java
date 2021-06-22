package com.example.trouvaille;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    EditText email, password, conpassword;
    Button register, link_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() !=null){
            startActivity(new Intent(RegistrationActivity.this, com.example.trouvaille.MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_registration);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        conpassword=findViewById(R.id.cpassword);

        register = findViewById(R.id.btn_reg);
        link_to_login=findViewById(R.id.btn_login);

        link_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this , loginActivity.class));
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void signUp() {
        Log.d(TAG,"signup"+email);
        if(!validateform()){
            return;
        }

        String em = email.getText().toString();
        String pw= password.getText().toString();

        mAuth.createUserWithEmailAndPassword(em,pw)
                .addOnCompleteListener( this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this,"Registration Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this, com.example.trouvaille.MainActivity.class));
                        }else{
                            Log.w(TAG, "signUpWithEmail:failure",task.getException());
                            Toast.makeText(RegistrationActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validateform() {
        boolean valid = true;
        String em = email.getText().toString();
        if(TextUtils.isEmpty(em)){
            email.setError("Required");
            valid=false;
        }else{
            email.setError(null);
        }
        String ps =password.getText().toString();
        if(TextUtils.isEmpty(ps)){
            password.setError("Required");
            valid= false;
        }else {
            password.setError(null);
        }

        String cps =conpassword.getText().toString();
        if(TextUtils.isEmpty(cps)){
            password.setError("Required");
            valid= false;
        }else {
            password.setError(null);
        }
        if(!(cps.equals(ps))){
            conpassword.setError("Re enter Password !");
            valid =false;
        }else {
            conpassword.setError(null);
        }
        return valid;
    }
}