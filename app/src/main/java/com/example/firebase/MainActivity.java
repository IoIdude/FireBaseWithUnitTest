package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
{
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText Phone = findViewById(R.id.phone);
        EditText Email = findViewById(R.id.email);
        EditText Password =findViewById(R.id.password);
        EditText RepeatPswd = findViewById(R.id.repeatPassword);
        Button btn = findViewById(R.id.ok);

        btn.setOnClickListener(view -> {
            AuthValidation form = new AuthValidation();

            if(form.isValid(Phone.getText().toString(), Email.getText().toString(), Password.getText().toString(),
                    RepeatPswd.getText().toString()) == false)
                Toast.makeText(this, "Введите корректные данные", Toast.LENGTH_SHORT).show();
            else
            {

                database = FirebaseDatabase.getInstance("https://registration-af33f-default-rtdb.firebaseio.com/").
                        getReference().child("Users");

                User user = new User(Phone.getText().toString(), Email.getText().toString(), Password.getText().toString());

                database.child("User").setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Phone.getText().clear();
                        Email.getText().clear();
                        Password.getText().clear();
                        RepeatPswd.getText().clear();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                        Log.e("Error", "onFailure: ", e);
                    }
                });
            }
        });
    }
}