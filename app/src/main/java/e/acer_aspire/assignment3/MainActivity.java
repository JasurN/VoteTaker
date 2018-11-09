package e.acer_aspire.assignment3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout mainLayout;
    LinearLayout spinnerLayout;
    EditText firstNameEditText;
    EditText lastNameEditText;
    RadioGroup chooseRadioGroup;
    RadioButton agreeRadioButton;
    RadioButton disagreeRadioButton;
    Spinner drinkSpin;
    Spinner foodsSpin;
    CheckBox fileReadCheckBoxOption;

    Boolean isSpinnerVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mainLayout = findViewById(R.id.mainActivityLinearLayout);

        mainLayout.addView(generateTextViewWithText("Will you come to the party? If you come what type of food and drink you want?"
                , 0, 1, 0));
        generateFirstAndLastNameEditTexts();
        generateChooseRadioButtons();
        generateDrinksAndFoodSpinner();
        generateReadFromFileCheckBox();
    }

    @SuppressLint("SetTextI18n")
    private TextView generateTextViewWithText(String text, int marginTop, int isPadding, int marginStart) {
        int paddingValue = 0;
        if (isPadding >= 1) {
            paddingValue = dpToPx(10);
        }
        LinearLayout.LayoutParams params = generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, dpToPx(marginTop), 0, 0);
        params.setMarginStart(dpToPx(marginStart));

        TextView sampleTextView = new TextView(this);
        sampleTextView.setText(text);
        sampleTextView.setPadding(paddingValue, paddingValue, paddingValue, paddingValue);
        sampleTextView.setTextSize(dpToPx(7));
        sampleTextView.setLayoutParams(params);

        return sampleTextView;
    }

    private void generateFirstAndLastNameEditTexts() {
        LinearLayout.LayoutParams params = generateParams((int) (getScreenWidth(this) * 0.45),
                dpToPx(50));
        params.setMarginStart(dpToPx(10));
        firstNameEditText = generateEditText("First Name");
        lastNameEditText = generateEditText("Last Name");

        firstNameEditText.setLayoutParams(params);
        lastNameEditText.setLayoutParams(params);

        LinearLayout.LayoutParams paramsForLayout = generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsForLayout.setMargins(0, dpToPx(15), 0, 0);
        LinearLayout editTextViewLayout = generateLinearLayout(paramsForLayout, LinearLayout.HORIZONTAL);

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

        chooseRadioGroup.setOnCheckedChangeListener(radioGroupOnCheckedChangeListener);

        chooseRadioGroup.addView(agreeRadioButton);
        chooseRadioGroup.addView(disagreeRadioButton);
        mainLayout.addView(chooseRadioGroup);
    }

    private void generateDrinksAndFoodSpinner() {
        List<String> drinks = new ArrayList<>();
        drinks.add("Coca-Cola");
        drinks.add("Fanta");
        drinks.add("Sprite");
        drinks.add("Water");

        List<String> foods = new ArrayList<>();
        foods.add("Fried potatoes");
        foods.add("Seafood");
        foods.add("Rice");
        foods.add("Pasta");

        drinkSpin = new Spinner(this);
        ArrayAdapter<String> drinkAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, drinks);
        drinkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drinkSpin.setAdapter(drinkAdapter);

        foodsSpin = new Spinner(this);
        ArrayAdapter<String> foodsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, foods);
        foodsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodsSpin.setAdapter(foodsAdapter);


        LinearLayout.LayoutParams params = generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, dpToPx(5), 0, 0);
        params.setMarginStart(dpToPx(15));

        drinkSpin.setLayoutParams(params);
        foodsSpin.setLayoutParams(params);

        spinnerLayout = generateLinearLayout(generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT), LinearLayout.VERTICAL);

        spinnerLayout.addView(generateTextViewWithText("Drinks", 20, 0, 10));
        spinnerLayout.addView(drinkSpin);
        spinnerLayout.addView(generateTextViewWithText("Food", 5, 0, 10));
        spinnerLayout.addView(foodsSpin);

        mainLayout.addView(spinnerLayout);
    }

    @SuppressLint("SetTextI18n")
    private void generateReadFromFileCheckBox() {
        LinearLayout.LayoutParams  params = generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, dpToPx(15), 0 ,0);
        params.setMarginStart(dpToPx(7));

        fileReadCheckBoxOption = new CheckBox(this);
        fileReadCheckBoxOption.setText("Read votes from votes.txt");
        fileReadCheckBoxOption.setTextSize(dpToPx(6));
        fileReadCheckBoxOption.setLayoutParams(params);

        spinnerLayout.addView(fileReadCheckBoxOption);
    }

    private LinearLayout generateLinearLayout(LinearLayout.LayoutParams params, int orientation) {
        LinearLayout generatedLayout = new LinearLayout(this);
        generatedLayout.setOrientation(orientation);
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

    public static int dpToPx(double dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private RadioGroup.OnCheckedChangeListener radioGroupOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int clickedRadioButton = chooseRadioGroup.getCheckedRadioButtonId();
            if (clickedRadioButton == 1) {
                if (!isSpinnerVisible) {
                    isSpinnerVisible = true;
                    spinnerLayout.setVisibility(View.VISIBLE);
                }
            } else if (clickedRadioButton == 2) {
                isSpinnerVisible = false;
                spinnerLayout.setVisibility(View.INVISIBLE);
            }
        }
    };
}
