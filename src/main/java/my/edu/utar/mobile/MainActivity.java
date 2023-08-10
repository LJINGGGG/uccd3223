package my.edu.utar.mobile;

import static android.widget.Button.*;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText billAmountEditText, numPeopleEditText, customPeopleEditText, customAmountEditText;
    private TextView equalResultTextView, customResultTextView;
    private Button equalButton,customButton;

    private Bundle savedInstanceState;
    public MainActivity(){};
    public MainActivity(TextView equalResultTextView) {
        this.equalResultTextView = equalResultTextView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button equalButton = findViewById(R.id.Equal_select);
        Button customButton = findViewById(R.id.Custom_select);


        equalButton.setOnClickListener(this::onClick);
        customButton.setOnClickListener(this::onClick);


        equalButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,equal.class);
            startActivity(intent);

        });

        customButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,custom.class);
            startActivity(intent);
        });


    }

    private void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, equal.class);
        startActivity(intent);

    }
}


