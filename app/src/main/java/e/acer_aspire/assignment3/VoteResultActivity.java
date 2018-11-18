package e.acer_aspire.assignment3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

public class VoteResultActivity extends GenerateView {

    private LinearLayout main_layout;
    private LinearLayout.LayoutParams linear_params;
    private TextView textArea;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_result);
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
        textArea = generateTextView("", 17, 0, 0, 0, 0);
        padding = dpToPx(VoteResultActivity.this, 5);
        textArea.setPadding(padding, padding, padding, padding);
        textArea.setVerticalScrollBarEnabled(true);
        textArea.setMovementMethod(new ScrollingMovementMethod());
        textArea.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        textArea.setBackgroundColor(Color.parseColor("#f4f4f4"));
        textArea.setTextColor(Color.parseColor("#000000"));

        main_layout.addView(main_layout);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        Intent intent = getIntent();

        String votes = intent.getStringExtra("votes");

        textArea.setText(votes);
        //////////////////////////////////////////////////////////////////////////////////////////////////
    }

}
