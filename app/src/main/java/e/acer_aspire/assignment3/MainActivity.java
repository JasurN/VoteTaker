package e.acer_aspire.assignment3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView partyInfoTextView;
    LinearLayout mainLayout;
    EditText firstNameEditText;
    EditText lastNameEditText;
    RadioGroup chooseRadioGroup;
    RadioButton agreeRadioButton;
    RadioButton disagreeRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mainLayout = findViewById(R.id.mainActivityLinearLayout);

        generatePartyInfoTextView();
        generateFirstAndLastNameEditTexts();
        generateChooseRadioButtons();
    }

    @SuppressLint("SetTextI18n")
    private void generatePartyInfoTextView() {
        LinearLayout.LayoutParams params = generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        partyInfoTextView = new TextView(this);
        partyInfoTextView.setText("Will you come to the party? If you come what type of food and drink you want?");
        int paddingValue = dpToPx(10);
        partyInfoTextView.setPadding(paddingValue, paddingValue, paddingValue, paddingValue);
        partyInfoTextView.setTextSize(dpToPx(7));
        partyInfoTextView.setLayoutParams(params);

        mainLayout.addView(partyInfoTextView);
    }

    private void generateFirstAndLastNameEditTexts() {
        LinearLayout.LayoutParams params = generateParams((int) (getScreenWidth(this) * 0.45),
                dpToPx(50));
        firstNameEditText = generateEditText("First Name");
        lastNameEditText = generateEditText("Last Name");

        firstNameEditText.setLayoutParams(params);
        lastNameEditText.setLayoutParams(params);

        LinearLayout.LayoutParams paramsForLayout = generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsForLayout.setMargins(0, dpToPx(15), 0, 0);
        LinearLayout editTextViewLayout = generateLinearLayout(paramsForLayout);

        editTextViewLayout.addView(firstNameEditText);
        editTextViewLayout.addView(lastNameEditText);

        mainLayout.addView(editTextViewLayout);
    }

    @SuppressLint("SetTextI18n")
    private void generateChooseRadioButtons() {
        LinearLayout.LayoutParams params = generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, dpToPx(10), 0, 0);
        params.setMarginStart(dpToPx(10));

        chooseRadioGroup = new RadioGroup(this);
        chooseRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
        chooseRadioGroup.setLayoutParams(params);


        agreeRadioButton = new RadioButton(this);
        agreeRadioButton.setText("Agree");
        agreeRadioButton.setTextSize(dpToPx(7));

        disagreeRadioButton = new RadioButton(this);
        disagreeRadioButton.setText("Disagree");
        disagreeRadioButton.setTextSize(dpToPx(7));

        chooseRadioGroup.addView(agreeRadioButton);
        chooseRadioGroup.addView(disagreeRadioButton);
        mainLayout.addView(chooseRadioGroup);

    }

    private LinearLayout generateLinearLayout(LinearLayout.LayoutParams params) {
        LinearLayout generatedLayout = new LinearLayout(this);
        generatedLayout.setOrientation(LinearLayout.HORIZONTAL);
        generatedLayout.setLayoutParams(params);

        return generatedLayout;
    }

    private EditText generateEditText(String hint) {
        EditText sampleEditText = new EditText(this);
        sampleEditText.setHint(hint);
        return sampleEditText;
    }

    private LinearLayout.LayoutParams generateParams(int widthLayoutParameter, int heightLayoutParameter) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                widthLayoutParameter
                , heightLayoutParameter
        );
        params.setMarginStart(10);

        return params;
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(double dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

}
