package e.acer_aspire.assignment3;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView partyInfoTextView;
    LinearLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
    }

    private void init() {
        mainLayout = findViewById(R.id.mainActivityLinearLayout);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT
                , LinearLayout.LayoutParams.WRAP_CONTENT
        );

        params.gravity = Gravity.CENTER;


        generateTextInfo(params);
    }

    @SuppressLint("SetTextI18n")
    private void generateTextInfo(LinearLayout.LayoutParams params) {
        partyInfoTextView = new TextView(this);
        partyInfoTextView.setText("Will you come to the party? If you come what type of food and drink you want?");
        partyInfoTextView.setPadding(3,1,1,1);
        partyInfoTextView.setTextSize(18);

        mainLayout.addView(partyInfoTextView);
    }
}
