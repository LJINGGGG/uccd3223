package my.edu.utar.mobile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class customResult extends AppCompatActivity {

    private EditText customPeopleEditText, customAmountEditText, customPercentageEditText, nameEditText;
    private TextView customResultTextView, customPeopleTextView, customAmountTextView, AmountTextView;
    private Button addPercentageButton, calculateCustomButton;
    private int people = 0;
    private float Amount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_result);

        //customPercentageEditText = findViewById(R.id.custom_percentage_editText);
        customPeopleTextView = findViewById(R.id.people);
        customAmountTextView = findViewById(R.id.amount);

        addPercentageButton = findViewById(R.id.add_percentage);
        customResultTextView = findViewById(R.id.custom_result);
        Button backButton = findViewById(R.id.back_button2);
        Button shareButton = findViewById(R.id.share_button2);


        backButton.setOnClickListener(this::onClick);
        shareButton.setOnClickListener(this::onClick);


        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(customResult.this,custom.class);
            startActivity(intent);

        });
            shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareContent();
            }
        });

        Intent intent = getIntent();
        people = intent.getIntExtra("Number of People", 0);
        Amount = intent.getFloatExtra("Amount", 0);
        if (people == 0) {
            Log.d("people", "people");
        }
        if (Amount == 0) {
            Log.d("amount", "Amount");
        }
        customPeopleTextView.setText(Integer.toString(people));
        customAmountTextView.setText(Float.toString(Amount));

        addPercentageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_percentage();
            }
        });
    }

    private void onClick(View view) {
        Intent intent = new Intent(customResult.this,MainActivity.class);
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
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
    private void add_percentage() {
        TableLayout tableLayout = findViewById(R.id.percentage_table);
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        ));

        tableRow.setPadding(0, 5, 0, 0);

        EditText column1 = new EditText(this);
        EditText column2 = new EditText(this);
        TextView column3 = new TextView(this);

        column1.setText("     ");
        column2.setText("     ");
        column3.setText("     ");

        column2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String newText = charSequence.toString();

                // Check if the current text starts with "0"
                if (newText.startsWith("0")) {
                    // Replace "0" with an empty string to clear it
                    newText = newText.replaceFirst("^0", "");

                    // Update the text in the EditText
                    EditText editText = (EditText) getCurrentFocus();
                    if (editText != null) {
                        editText.setText(newText);

                        // Move the cursor to the end of the text
                        editText.setSelection(newText.length());
                    }
                }
            }

        @Override
        public void afterTextChanged(Editable editable) {
        };
        });


        column2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String input = column2.getText().toString();
                    if (input.isEmpty()) {
                        column3.setText("RM 0");
                        column2.setText("0");
                    }
                    else{
                        float percent = (Float.parseFloat(input)*Amount)/100;
                        column3.setText(Float.toString(percent));
                        column2.setText(input);
                    }
                }
            }
        });
        // Create LayoutParams with weight for EditText views
        TableRow.LayoutParams layoutParams1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2);
        TableRow.LayoutParams layoutParams2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2);
        TableRow.LayoutParams layoutParams3 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2);


        // Apply layout parameters to the EditText views
        column1.setLayoutParams(layoutParams1);
        column2.setLayoutParams(layoutParams2);
        column3.setLayoutParams(layoutParams3);


        // Add views to the TableRow
        tableRow.addView(column1);
        tableRow.addView(column2);
        tableRow.addView(column3);

        // Add TableRow to the TableLayout
        tableLayout.addView(tableRow);
 }

}