package my.edu.utar.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class custom extends AppCompatActivity {

    private EditText customPeopleEditText, customAmountEditText, customPercentageEditText;
    private TextView customResultTextView;
    private Button addButton, calculateCustomButton,backButton;
    private LinearLayout containerLayout;
    private float Amount;
    private int people;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);


        customPeopleEditText = findViewById(R.id.num_people_editText);
        customAmountEditText = findViewById(R.id.bill_amount_editText);

        addButton = findViewById(R.id.add_button);
        containerLayout = findViewById(R.id.container_layout); // Assuming you have a layout with this ID
        Button backButton = findViewById(R.id.back_button3);

        backButton.setOnClickListener(this::onClick);


        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(custom.this,MainActivity.class);
            startActivity(intent);

        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(custom.this, customResult.class);

                Amount = Float.parseFloat(customAmountEditText.getText().toString());
                people = Integer.parseInt(customPeopleEditText.getText().toString());

                if (people > 0 && Amount > 0) {
                    intent.putExtra("Amount", Amount);
                    intent.putExtra("Number of People", people);
                    startActivity(intent);
                } else {

                }
            }
        });


    }


    private void onClick(View view) {
        Intent intent = new Intent(custom.this,MainActivity.class);
        startActivity(intent);
    }

    ;
}
