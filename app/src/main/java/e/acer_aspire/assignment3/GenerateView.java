package e.acer_aspire.assignment3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GenerateView extends AppCompatActivity {
    
    protected LinearLayout.LayoutParams linear_params;
    protected Context some_activity;
    
    
    protected static int dpToPx(Context c, double dp) {
        return Math.round((int) (dp * c.getResources().getDisplayMetrics().density));
    }

    protected TextView generateTextView(String text, int text_size, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        TextView textView = new TextView(this);
        setLinearParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                marginLeft, marginTop, marginRight, marginBottom);
        textView.setLayoutParams(linear_params);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, text_size);

        return textView;
    }

    protected Space generateSpace(int width, int height, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        Space space = new Space(this);
        setLinearParams(width, height, marginLeft, marginTop, marginRight, marginBottom);
        space.setLayoutParams(linear_params);

        return space;
    }

    protected EditText generateEditText(int width, int height, float weight, int inputType, int background, int string_id, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        EditText editText = new EditText(this);
        setLinearParams(width, height, marginLeft, marginTop, marginRight, marginBottom);
        if (weight > 0) {
            editText.getLayoutParams();
            linear_params.weight = weight;
        }
        editText.setLayoutParams(linear_params);
        linear_params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        editText.setInputType(inputType);
        if (background > -1) {
            editText.setBackground(getResources().getDrawable(background));
        }
        editText.setHint(getResources().getString(string_id));

        return editText;
    }

    protected RadioGroup generateRadioGroup(int width, int height, int orientation, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        RadioGroup radioGroup = new RadioGroup(this);
        setLinearParams(width, height, marginLeft, marginTop, marginRight, marginBottom);
        radioGroup.setLayoutParams(linear_params);
        radioGroup.setOrientation(orientation);

        return radioGroup;
    }

    protected RadioButton generateRadioButton(int width, int height, float weight, boolean checked, int string_id, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        RadioButton radioButton = new RadioButton(this);
        setLinearParams(width, height, marginLeft, marginTop, marginRight, marginBottom);
        radioButton.getLayoutParams();
        if (weight > 0) {
            linear_params.weight = 1.0f;
        }
        radioButton.setLayoutParams(linear_params);
        radioButton.setChecked(checked);
        radioButton.setText(getResources().getString(string_id));

        return radioButton;
    }

    protected Spinner generateSpinner(List<String> string_spinner, int width, int height, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        Spinner spinner = new Spinner(this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, string_spinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setSelection(0);
        spinner.setAdapter(arrayAdapter);
        setLinearParams(width, height, marginLeft, marginTop, marginRight, marginBottom);

        return spinner;
    }

    protected Button generateButton(int width, int height, int marginLeft, int marginTop, int marginRight, int marginBottom, int string_id, int color_id) {
        Button button = new Button(this);
        setLinearParams(width, height, marginLeft, marginTop, marginRight, marginBottom);
        linear_params.gravity = Gravity.CENTER;
        button.setLayoutParams(linear_params);
        button.setText(getResources().getString(string_id));
        button.setTextColor(getResources().getColor(color_id));

        return button;
    }

    protected LinearLayout getLinearLayoutContent(int orientation, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        LinearLayout layout = new LinearLayout(this);
        setLinearParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                marginLeft, marginTop, marginRight, marginBottom);
        layout.setLayoutParams(linear_params);
        layout.setOrientation(orientation);
        if (orientation == LinearLayout.HORIZONTAL) {
            layout.setWeightSum(1);
            layout.setBaselineAligned(false);
        }

        return layout;
    }

    protected void setLinearParams(int width, int height, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        if (width != LinearLayout.LayoutParams.MATCH_PARENT && width != LinearLayout.LayoutParams.WRAP_CONTENT) {
            width = dpToPx(this, width);
        }
        if (height != LinearLayout.LayoutParams.MATCH_PARENT && height != LinearLayout.LayoutParams.WRAP_CONTENT) {
            height = dpToPx(this, height);
        }
        linear_params = new LinearLayout.LayoutParams(width, height);
        linear_params.setMargins(dpToPx(this, marginLeft), dpToPx(this, marginTop), dpToPx(this, marginRight), dpToPx(this, marginBottom));
    }

    @SuppressLint("SetTextI18n")
    protected CheckBox generateCheckBox(String text, int textSize, int marginLeft, int marginTop, int marginRight, int marginBottom, int marginStart) {
        CheckBox checkBox = new CheckBox(this);
        setLinearParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, marginLeft, marginTop, marginRight, marginBottom);
        linear_params.setMarginStart(dpToPx(this, marginStart));

        checkBox.setText(text);
        checkBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        checkBox.setLayoutParams(linear_params);

        return checkBox;
    }
    ///////////////////////////////////////////////


    public LinearLayout.LayoutParams getLinear_params() {
        return linear_params;
    }
}
