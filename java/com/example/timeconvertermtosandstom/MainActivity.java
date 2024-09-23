package com.example.timeconvertermtosandstom;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText timeInput;
    private Spinner conversionType;
    private TextView outputTime;

    private static final String[] CONVERSION_OPTIONS = {"Hours to Minutes", "Minutes to Hours"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        timeInput = findViewById(R.id.time_input);
        conversionType = findViewById(R.id.conversion_type);
        outputTime = findViewById(R.id.output_time);
        Button convertButton = findViewById(R.id.convert_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CONVERSION_OPTIONS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conversionType.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTime();
            }
        });
    }

    private void convertTime() {
        String inputValue = timeInput.getText().toString();
        if (inputValue.isEmpty()) {
            outputTime.setText("Please enter a time value.");
            return;
        }

        double timeValue = Double.parseDouble(inputValue);
        String selectedConversion = conversionType.getSelectedItem().toString();
        double convertedTime;

        if (selectedConversion.equals("Hours to Minutes")) {
            convertedTime = timeValue * 60;
            outputTime.setText(String.format("%.2f Hours = %.2f Minutes", timeValue, convertedTime));
        } else {
            convertedTime = timeValue / 60;
            outputTime.setText(String.format("%.2f Minutes = %.2f Hours", timeValue, convertedTime));
        }
    }
}
