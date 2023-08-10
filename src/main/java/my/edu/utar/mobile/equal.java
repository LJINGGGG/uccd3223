package my.edu.utar.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class equal extends AppCompatActivity {
    private EditText billAmountEditText, numPeopleEditText;
    private TextView equalResultTextView;
    private Button backButton,shareButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equal);

        billAmountEditText = findViewById(R.id.bill_amount_editText);
        numPeopleEditText = findViewById(R.id.num_people_editText);
        Button calculateEqualButton = findViewById(R.id.calculate_equal_button);
        equalResultTextView = findViewById(R.id.equal_result_textView);

        Button backButton = findViewById(R.id.back_button);
        Button shareButton = findViewById(R.id.share_button);


        backButton.setOnClickListener(this::onClick);
        shareButton.setOnClickListener(this::onClick);


        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(equal.this,MainActivity.class);
            startActivity(intent);

        });
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareContent();
            }
        });
        calculateEqualButton.setOnClickListener(v -> calculateEqualBreakdown(equalResultTextView));

    }

    private void onClick(View view) {
        Intent intent = new Intent(equal.this,MainActivity.class);
        startActivity(intent);
    }
    private void shareContent() {
        String message = "Check out this cool app!";

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Sharing Example");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, message);

        // Create a chooser that includes WhatsApp and Instagram
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp"); // Package name of WhatsApp

        Intent instagramIntent = new Intent(Intent.ACTION_SEND);
        instagramIntent.setType("text/plain");
        instagramIntent.setPackage("com.instagram.android"); // Package name of Instagram

        Intent chooserIntent = Intent.createChooser(sharingIntent, "Share via");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { whatsappIntent, instagramIntent });

        startActivity(chooserIntent);
    }

    private void calculateEqualBreakdown(TextView text) {
        String billAmountString = billAmountEditText.getText().toString();
        String numPeopleString = numPeopleEditText.getText().toString();

        if (billAmountString.isEmpty() || numPeopleString.isEmpty()) {
            text.setText("Please enter bill amount and number of people.");
            return;
        }

        double billAmount = Double.parseDouble(billAmountString);
        int numPeople = Integer.parseInt(numPeopleString);

        if (numPeople <= 0) {
            equalResultTextView.setText("Number of people must be greater than 0.");
            return;
        }

        double equalShare = billAmount / numPeople;
        String result = "Equal Breakdown: Each person pays RM" + String.format("%.2f", equalShare);
        equalResultTextView.setText(result);
    }
}