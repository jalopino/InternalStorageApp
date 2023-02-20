package com.example.internalstorageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    TextView data;
    Button save, retrieve;
    EditText studentID, studentName, courseSection, residenceAddress, contactNumber;

    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    String myData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = findViewById(R.id.save);
        retrieve = findViewById(R.id.retrieve);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String student_id = (studentID = findViewById(R.id.student_id)).getText().toString();
                String student_name = (studentName = findViewById(R.id.student_name)).getText().toString();
                String course_section = (courseSection = findViewById(R.id.course_section)).getText().toString();
                String address = (residenceAddress = findViewById(R.id.address)).getText().toString();
                String contact = (contactNumber = findViewById(R.id.contact)).getText().toString();
                String[] arrayData = {student_name, student_id, course_section, address, contact};
                String message = "";
                for(int i=0; i < arrayData.length; i++) {
                    switch(i) {
                        case 0:
                            message += "I am ";
                            message += (arrayData[i] + " ");
                            break;
                        case 1:
                            message += "with ";
                            message += (arrayData[i] + " ");
                            break;
                        case 2:
                            message += "taken up ";
                            message += (arrayData[i] + " ");
                            break;
                        case 3:
                            message += "with ";
                            message += (arrayData[i] + ", ");
                            break;
                        case 4:
                            message += "for any question you may contact me at ";
                            message += arrayData[i];
                            break;
                    }
                }
                writeInFile(message);
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (data = findViewById(R.id.data)).setText(readFromFile());
            }
        });
    }

    //Write file
    private void writeInFile(String data) {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Read File
    private String readFromFile() {
        FileInputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            inputStream = openFileInput(filename);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}