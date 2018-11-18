package e.acer_aspire.assignment3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends GenerateView {

    private LinearLayout mainLayout, firstLayout, spinnerLayout1, spinnerLayout2;
    private EditText firstNameEditText, lastNameEditText;
    private RadioGroup radioGroup;
    private RadioButton agreeRadioButton, disagreeRadioButton;
    private Spinner drinkSpinner, foodSpinner;
    private Button voteButton, checkingVotesButton;
    private LinearLayout.LayoutParams linear_params;
    private List<String>drinks, foods;
    private StringBuilder stringBuilder;
    private CheckBox fileReadCheckBoxOption;
    private OutputStreamWriter writingStream;
    private File _file;
    private String text;
    private boolean isSpinnerVisible = true;
    private static final String beverage_filename = "liquids.txt";
    private static final String foods_filename = "foods.txt";
    private static final String votes_filename = "votes.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        if (!isExist(votes_filename)) {
            createNewOutputStream(votes_filename);
        }

        voteButton.setOnClickListener(onVoteClickListener);
        checkingVotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileReadCheckBoxOption.isChecked()) {
                createIntent(true);
                } else {
                    createIntent(false);
                }

            }
        });
    }

    private void init() {
        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        mainLayout.setPadding(20, 20, 20, 20);

        //////////////////////////////////////////////////////////////////////////////////////////////////
        TextView firstTextView = generateTextView(getResources().getString(R.string.long_text), 14, 0, 0, 0, 0);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        Space space1 = generateSpace(ViewGroup.LayoutParams.MATCH_PARENT, 30, 0, 0, 0, 0);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        firstLayout = getLinearLayoutContent(LinearLayout.HORIZONTAL, 0, 0, 0, 0);

        //////////
        firstNameEditText = generateEditText(0, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f,
                EditorInfo.TYPE_CLASS_TEXT, android.R.drawable.editbox_background_normal, R.string.first_name, 0, 0, 0, 0);

        //////////
        lastNameEditText = generateEditText(0, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f,
                EditorInfo.TYPE_CLASS_TEXT, android.R.drawable.editbox_background_normal, R.string.last_name, 0, 0, 0, 0);
        //////////
        firstLayout.addView(firstNameEditText);
        firstLayout.addView(lastNameEditText);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        Space space2 = generateSpace(ViewGroup.LayoutParams.MATCH_PARENT, 16, 0, 0, 0, 0);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        radioGroup = generateRadioGroup(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, RadioGroup.HORIZONTAL, 0, 0, 0, 0);
        //////////
        agreeRadioButton = generateRadioButton(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f, false, R.string.agree, 0, 0, 0, 0);
        //////////
        disagreeRadioButton = generateRadioButton(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f, false, R.string.disagree, 0, 0, 0, 0);
        //////////
        radioGroup.addView(agreeRadioButton);
        radioGroup.addView(disagreeRadioButton);
        radioGroup.check(1);
        radioGroup.setOnCheckedChangeListener(radioGroupOnCheckedChangeListener);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        Space space3 = generateSpace(LinearLayout.LayoutParams.MATCH_PARENT, 16, 0, 0, 0, 0);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        spinnerLayout1 = getLinearLayoutContent(LinearLayout.VERTICAL, 0, 0, 0, 0);
        //////////
        drinkSpinner = generateSpinner(getList(beverage_filename), LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0, 5, 0, 0);
        //////////
        spinnerLayout1.addView(generateTextView(getResources().getString(R.string.drink), 15, 0, 0, 0, 0));
        spinnerLayout1.addView(drinkSpinner);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        Space space4 = generateSpace(LinearLayout.LayoutParams.MATCH_PARENT, 20, 0, 0, 0, 0);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        spinnerLayout2 = getLinearLayoutContent(LinearLayout.VERTICAL, 0, 0, 0, 0);
        //////////
        foodSpinner = generateSpinner(getList(foods_filename), LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0, 5, 0, 0);
        //////////
        spinnerLayout2.addView(generateTextView(getResources().getString(R.string.food), 15, 0, 0, 0, 0));
        spinnerLayout2.addView(foodSpinner);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        fileReadCheckBoxOption = generateCheckBox(getResources().getString(R.string.check_box_string), 14, 0, 15, 0, 10, 7);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        Space space5 = generateSpace(LinearLayout.LayoutParams.MATCH_PARENT, 20, 0, 0, 0, 0);
        //////////////////////////////////////////////////////////////////////////////////////////////////
        voteButton = generateButton(245, LinearLayout.LayoutParams.WRAP_CONTENT, 0, 0, 0, 0, R.string.vote,R.color.colorWhite);
        //////////////////////////////////////////////////////////////////////////////////////////////////
        Space space6 = generateSpace(LinearLayout.LayoutParams.MATCH_PARENT, 20, 0, 0, 0, 0);
        //////////////////////////////////////////////////////////////////////////////////////////////////
        checkingVotesButton = generateButton(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0, 0, 0, 0, R.string.check_votes, R.color.colorWhite);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        mainLayout.addView(firstTextView);
        mainLayout.addView(space1);
        mainLayout.addView(firstLayout);
        mainLayout.addView(space2);
        mainLayout.addView(radioGroup);
        mainLayout.addView(space3);
        mainLayout.addView(spinnerLayout1);
        mainLayout.addView(space4);
        mainLayout.addView(spinnerLayout2);
        mainLayout.addView(fileReadCheckBoxOption);
        mainLayout.addView(space5);
        mainLayout.addView(voteButton);
        mainLayout.addView(space6);
        mainLayout.addView(checkingVotesButton);
    }


    /////////////////////////////////////////////// Helper functions



    /////////////////////////////////////////////// File functions
    /*private void addVoteOptionToStringBuilder() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String voteTakerStr;
        stringBuilder = new StringBuilder();

        if (isSpinnerVisible) {
            String drinkChoose = drinks.get(drink_Spin.getSelectedItemPosition());
            String foodChoose = foods.get(foods_Spin.getSelectedItemPosition());
            voteTakerStr = firstName + " " + lastName + " will come to the party and wants "
                    + drinkChoose + " and " + foodChoose + ".";
        } else {
            voteTakerStr = firstName + " " + lastName + " will not come to the party.";
        }

        Toast.makeText(this, "Voted: " + voteTakerStr, Toast.LENGTH_SHORT).show();
        stringBuilder.append("--->").append(voteTakerStr);
        stringBuilder.append("\n");
    }*/

    private String getFilename(String filename) {
        String[] temp = filename.split("[.]");
        return temp[0];
    }

    private List<String> getList(String filename) {
        filename = getFilename(filename);
        Scanner scanner = new Scanner(getResources().openRawResource(getResources().getIdentifier(filename, "raw", getPackageName())));
        List<String>list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());

        }
        return list;
    }

    public boolean isExist(String filename) {
        File packagePath = new File(getPackageName(), "raw");
        File file = new File(packagePath, filename);
        return file.exists();
    }

    private void createNewOutputStream(String filename) {
        try {
            writingStream = new OutputStreamWriter(openFileOutput(filename, Context.MODE_PRIVATE));
            writingStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeIntoFile(String filename, String text) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(filename, Context.MODE_APPEND));
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);
            bw.write(text);
            bw.newLine();
            bw.close();
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////


    /////////////////////////////////////////////// Button's onClickListener
    View.OnClickListener onVoteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String first_name = firstNameEditText.getText().toString();
            String last_name = lastNameEditText.getText().toString();
            String tempText;
            String currentText = "";
            if (first_name.equals("") || last_name.equals("")) {
                Toast.makeText(MainActivity.this, "Please enter your name!", Toast.LENGTH_LONG).show();
                return;
            }
            String fullName = first_name + " " + last_name;
            if (agreeRadioButton.isChecked()) {
                tempText = fullName + " will come to the party and wants " + drinkSpinner.getSelectedItem().toString()
                        + " and " + foodSpinner.getSelectedItem().toString();
                writeIntoFile(votes_filename, tempText);
                currentText += "-> " + tempText + "\n\n";
            } else {
                tempText = fullName + " will not come to the party";
                writeIntoFile(votes_filename, tempText);
                currentText += "-> " + tempText + "\n\n";
            }
            Toast.makeText(MainActivity.this, currentText, Toast.LENGTH_LONG).show();
            text += currentText;
        }
    };
    ///////////////////////////////////////////////


    /////////////////////////////////////////////// RadioButton's onclickListeners
    private RadioGroup.OnCheckedChangeListener radioGroupOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int clickedRadioButton = radioGroup.getCheckedRadioButtonId();
            if (clickedRadioButton == 1) {
                if (!isSpinnerVisible) {
                    isSpinnerVisible = true;
                    spinnerLayout1.setVisibility(View.VISIBLE);
                    spinnerLayout2.setVisibility(View.VISIBLE);
                    fileReadCheckBoxOption.setVisibility(View.VISIBLE);
                }
            } else if (clickedRadioButton == 2) {
                isSpinnerVisible = false;
                spinnerLayout1.setVisibility(View.GONE);
                spinnerLayout2.setVisibility(View.GONE);
                fileReadCheckBoxOption.setVisibility(View.GONE);
            }
        }
    };

    ///////////////////////////////////////////////


    /////////////////////////////////////////////// Intent
    private void createIntent(boolean isReadFromFile) {
        Intent voteTakerActivityIntent = new Intent(this, VoteResultActivity.class);
        voteTakerActivityIntent.putExtra("isReadFromFile", isReadFromFile);
        if (stringBuilder != null && !isReadFromFile) {
            voteTakerActivityIntent.putExtra("votes", stringBuilder.toString());
        }
        startActivity(voteTakerActivityIntent);
    }
    ///////////////////////////////////////////////
}
