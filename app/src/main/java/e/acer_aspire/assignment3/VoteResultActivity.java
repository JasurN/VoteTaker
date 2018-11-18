package e.acer_aspire.assignment3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VoteResultActivity extends GenerateView {

    private LinearLayout main_layout;
    private LinearLayout.LayoutParams linear_params;
    private Button clearButton;
    private TextView textArea;
    private String votesText;
    private boolean isReadFromFile;
    private static final String votes_filename = "votes.txt";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_result);

        init();
    }

    private void init() {
        main_layout = (LinearLayout) findViewById(R.id.checking_votes_layout);
        int padding = dpToPx(VoteResultActivity.this, 16);
        main_layout.setPadding(padding, padding, padding, padding);

        //////////////////////////////////////////////////////////////////////////////////////////////////
        main_layout.addView(generateTextView(getResources().getString(R.string.vote_list), 30, 0, 0, 0, 0));
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        main_layout.addView(generateSpace(LinearLayout.LayoutParams.MATCH_PARENT, 15, 0, 0, 0, 0));
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        clearButton = generateButton(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0, 10, 0, 0, R.string.clear_votes, R.color.colorWhite);
        clearButton.setGravity(Gravity.CENTER);
        main_layout.addView(clearButton);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        textArea = generateTextView("", 17, 0, 0, 0, 0);
        padding = dpToPx(VoteResultActivity.this, 5);
        textArea.setPadding(padding, padding, padding, padding);
        textArea.setVerticalScrollBarEnabled(true);
        textArea.setMovementMethod(new ScrollingMovementMethod());
        textArea.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        textArea.setBackgroundColor(Color.parseColor("#f4f4f4"));
        textArea.setTextColor(Color.parseColor("#000000"));

        main_layout.addView(textArea);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        Intent intent = getIntent();
        isReadFromFile = intent.getBooleanExtra("isReadFromFile", true);
        votesText = "";
        if (isReadFromFile) {
            votesText = getVoteList(votes_filename);
        } else {
            if (intent.hasExtra("votes")) {
                votesText = intent.getStringExtra("votes");
            }
        }

        textArea.setText(votesText);

        clearButton.setOnClickListener(onClearClickListener);
        //////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private String getVoteList(String filename) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(openFileInput(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder text = new StringBuilder();
        assert scanner != null;
        while (scanner.hasNextLine()) {
            text.append("-> ").append(scanner.nextLine()).append("\n\n");
        }
        return String.valueOf(text);
    }

    private void clearVotesData(String filename) {
//        FileOutputStream clearer =
        try {
            PrintWriter clearer = new PrintWriter(openFileOutput(votes_filename, Context.MODE_PRIVATE));
            clearer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener onClearClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            votesText = "";
            clearVotesData(votes_filename);
            textArea.setText(votesText);
        }
    };

}
