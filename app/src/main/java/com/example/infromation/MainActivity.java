package com.example.infromation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name,dept,id,year,semester,number,address;
    Button click;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseFirestore = FirebaseFirestore.getInstance();

        name = findViewById(R.id.name);
        dept = findViewById(R.id.dept);
        id = findViewById(R.id.id);
        year = findViewById(R.id.year);
        semester = findViewById(R.id.semester);
        number = findViewById(R.id.number);
        address = findViewById(R.id.address);

        click = findViewById(R.id.click);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String identity = name.getText().toString().trim();
                String department = dept.getText().toString().trim();
                String roll = id.getText().toString().trim();
                String age = year.getText().toString().trim();
                String semi = semester.getText().toString().trim();
                String num = number.getText().toString().trim();
                String add = address.getText().toString().trim();

                if(TextUtils.isEmpty(identity) && TextUtils.isEmpty(department) && TextUtils.isEmpty(roll) && TextUtils.isEmpty(age)
                && TextUtils.isEmpty(semi) && TextUtils.isEmpty(num) && TextUtils.isEmpty(add))
                {
                    Toast.makeText(MainActivity.this, "Please full fill all your information", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Student Name", identity);
                    user.put("Student Department", department);
                    user.put("Student Id", roll);
                    user.put("Student Year", age);
                    user.put("Student Semester", semi);
                    user.put("Student Phone number", num);
                    user.put("Student Address", add);

                    firebaseFirestore.collection("Student's Infromation").add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this,"Successfully added your information",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });

    }
}