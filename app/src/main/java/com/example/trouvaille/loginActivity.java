package com.example.trouvaille;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";

    EditText email,password;
    Button login,link_to_reg,forPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth= FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(loginActivity.this,MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);


        link_to_reg=findViewById(R.id.btn_reg);
        login=findViewById(R.id.btn_login);
        forPassword=findViewById(R.id.reset);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        link_to_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActivity.this,RegistrationActivity.class));
                finish();
            }
        });
        forPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText resetMail =new EditText(view.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("ResetPassword?");
                passwordResetDialog.setMessage("Enter your Email");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String mail= resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(loginActivity.this,"Rest link set to your mail", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(loginActivity.this, "Error! Reset Link is not sent "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                passwordResetDialog.create().show();
            }
        });
    }
    private void signIn() {
        Log.d(TAG,"signIn"+email);
        if(!validateform()){
            return ;
        }
        String em =email.getText().toString();
        String pw = password.getText().toString();

        mAuth.signInWithEmailAndPassword(em,pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(loginActivity.this,"Sign In is Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(loginActivity.this,MainActivity.class));
                            finish();
                        }else{
                            Log.w(TAG , "SignInwithEmail:failure",task.getException());
                            Toast.makeText(loginActivity.this,"Sign In Failed",Toast.LENGTH_SHORT).show();
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
        return valid;
    }

}
