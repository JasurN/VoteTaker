package e.acer_aspire.assignment3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    LinearLayout main_Layout;
    LinearLayout spinner_Layout;
    EditText first_Name_EditText;
    EditText last_Name_EditText;
    RadioGroup choose_RadioGroup;
    RadioButton agree_RadioButton;
    RadioButton disagree_RadioButton;
    Spinner drink_Spin;
    Spinner foods_Spin;
    CheckBox file_Read_CheckBox_Option;
    Button vote_Button;
    Button check_Vote_Button;
    Boolean isSpinnerVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        main_Layout = findViewById(R.id.mainActivityLinearLayout);

        main_Layout.addView(generateTextViewWithText("Will you come to the party? " +
                        "If you come what type of food and drink you want?"
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
        first_Name_EditText = generateEditText("First Name");
        last_Name_EditText = generateEditText("Last Name");

        first_Name_EditText.setLayoutParams(params);
        last_Name_EditText.setLayoutParams(params);

        LinearLayout.LayoutParams paramsForLayout = generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsForLayout.setMargins(0, dpToPx(15), 0, 0);
        LinearLayout editTextViewLayout = generateLinearLayout(paramsForLayout, LinearLayout.HORIZONTAL);

        editTextViewLayout.addView(first_Name_EditText);
        editTextViewLayout.addView(last_Name_EditText);

        main_Layout.addView(editTextViewLayout);
    }

    @SuppressLint("SetTextI18n")
    private void generateChooseRadioButtons() {
        LinearLayout.LayoutParams params = generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, dpToPx(10), 0, 0);
        params.setMarginStart(dpToPx(10));

        choose_RadioGroup = new RadioGroup(this);
        choose_RadioGroup.setOrientation(LinearLayout.HORIZONTAL);
        choose_RadioGroup.setLayoutParams(params);


        agreeRadioButton = new RadioButton(this);
        agreeRadioButton.setText("Agree");
        agreeRadioButton.setTextSize(dpToPx(7));

        disagree_RadioButton = new RadioButton(this);
        disagree_RadioButton.setText("Disagree");
        disagree_RadioButton.setTextSize(dpToPx(7));

        chooseRadioGroup.setOnCheckedChangeListener(radioGroupOnCheckedChangeListener);

        choose_RadioGroup.addView(agree_RadioButton);
        choose_RadioGroup.addView(disagree_RadioButton);
        main_Layout.addView(choose_RadioGroup);
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

        drink_Spin = new Spinner(this);
        ArrayAdapter<String> drinkAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, drinks);
        drinkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drinkSpin.setAdapter(drinkAdapter);

        foods_Spin = new Spinner(this);
        ArrayAdapter<String> foodsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, foods);
        foodsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodsSpin.setAdapter(foodsAdapter);


        LinearLayout.LayoutParams params = generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, dpToPx(5), 0, 0);
        params.setMarginStart(dpToPx(15));

        drink_Spin.setLayoutParams(params);
        foods_Spin.setLayoutParams(params);

        spinner_Layout = generateLinearLayout(generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT), LinearLayout.VERTICAL);

        spinner_Layout.addView(generateTextViewWithText("Drinks", 20, 0, 10));
        spinner_Layout.addView(drink_Spin);
        spinner_Layout.addView(generateTextViewWithText("Food", 5, 0, 10));
        spinner_Layout.addView(foods_Spin);

        main_Layout.addView(spinner_Layout);
    }

    @SuppressLint("SetTextI18n")
    private void generateReadFromFileCheckBox() {
        LinearLayout.LayoutParams params = generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, dpToPx(15), 0, dpToPx(10));
        params.setMarginStart(dpToPx(7));

        file_Read_CheckBox_Option = new CheckBox(this);
        file_Read_CheckBox_Option.setText("Read votes from votes.txt");
        file_Read_CheckBox_Option.setTextSize(dpToPx(6));
        file_Read_CheckBox_Option.setLayoutParams(params);

        spinner_Layout.addView(file_Read_CheckBox_Option);
    }

    /**
     * [1] String button name
     * [2] button option - (1) vote button; (2) check votes button
     */
    private void  generateButtonWithOnClickListener(String buttonName, int buttonOption) {
        LinearLayout.LayoutParams params = generateParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, dpToPx(5), 0, 0);
        params.gravity = Gravity.CENTER;

        Button sampleButton = new Button(this);
        sampleButton.setTextSize(dpToPx(7));
        sampleButton.setLayoutParams(params);
        sampleButton.setText(buttonName);

        if (buttonOption == 1) {
            vote_Button = sampleButton;
            vote_Button.setOnClickListener(voteButtonOnClickListener);
        } else if (buttonOption == 2) {
            check_Vote_Button = sampleButton;
            check_Vote_Button.setOnClickListener(checkVoteButtonOnClickListener);
        }

        main_Layout.addView(sampleButton);
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

    private static int dpToPx(double dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private RadioGroup.OnCheckedChangeListener radioGroupOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int clickedRadioButton = choose_RadioGroup.getCheckedRadioButtonId();
            if (clickedRadioButton == 1) {
                if (!isSpinnerVisible) {
                    isSpinnerVisible = true;
                    spinner_Layout.setVisibility(View.VISIBLE);
                }
            } else if (clickedRadioButton == 2) {
                isSpinnerVisible = false;
                spinnerLayout.setVisibility(View.INVISIBLE);
            }
        }
    };
}
