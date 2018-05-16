/*
 * Grow with Google Android Basics Nanodegree scholarship
 * Project: Quiz App
 * Author: AnaPaula
 */

package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int quizScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Remove android keyboard as you click back in the screen
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    // question 1
    public int questionOne() {

        RadioButton answer12 = findViewById(R.id.answer12);

        if (answer12.isChecked())
            return 1;
        else
            return 0;
    }

    // question 2
    public int questionTwo() {

        RadioButton answer24 = findViewById(R.id.answer24);
        if (answer24.isChecked())
            return 1;
        else
            return 0;
    }


    // question 3
    public int questionThree() {
        EditText text = (EditText) findViewById(R.id.answer31); //gets the contents of edit text
        String answer31 = text.getText().toString();

        if ("n = n - 1".equalsIgnoreCase(answer31))
            return 1;
        else
            return 0;
    }


    // question 4
    public int questionFour() {

        RadioButton answer42 = findViewById(R.id.answer42);

        if (answer42.isChecked())
            return 1;
        else
            return 0;
    }


    // question 5
    public int questionFive() {

        CheckBox answer51 = findViewById(R.id.answer51);
        CheckBox answer52 = findViewById(R.id.answer52);
        CheckBox answer53 = findViewById(R.id.answer53);
        CheckBox answer54 = findViewById(R.id.answer54);
        if (answer51.isChecked() && (answer53.isChecked()) && !(answer52.isChecked()) && !(answer54.isChecked()))
            return 1;
        else
            return 0;
    }


    // custom toast
    public void resultMsg(String msg) {


        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.bgt);

        ViewGroup group = (ViewGroup) toast.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(20);

        TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
        toastMessage.setTextColor(Color.WHITE);

        toast.show();

    }


    /**
     * This method is called when the result button is clicked.
     */
    public void displayResult(View v) {

        // Find user's name
        EditText text = (EditText) findViewById(R.id.name_field); //gets you the contents of edit text
        String name = text.getText().toString();

        // Calculate the score
        int quizScore = questionFive() + questionFour() + questionThree() + questionTwo() + questionOne();

        // display result in the screen
        TextView result = findViewById(R.id.result);
        result.setText(quizScore + getString(R.string.total));

        // display name and result in the toast
        if (quizScore == 5) {
            resultMsg("Congratulations, " + name + "! Your score is " + quizScore + "/5");
        }
        if (quizScore == 4) {
            resultMsg("Good job, " + name + "! Your score is " + quizScore + "/5");
        }
        if (quizScore == 3) {
            resultMsg("Good start, " + name + "! Your score is " + quizScore + "/5");
        }
        if (quizScore == 2) {
            resultMsg("You could do better, " + name + "! Your score is " + quizScore + "/5");
        }
        if (quizScore <= 1) {
            resultMsg("Don't give up, " + name + "! Your score is " + quizScore + "/5");
        }

    }

    /**
     * Resets the score back to zero and clean answers
     */

    public void reset(View v) {

        quizScore = 0;

        TextView result = findViewById(R.id.result);
        result.setText(quizScore + getString(R.string.total));

        EditText editText = findViewById(R.id.name_field);
        editText.setText("");

        RadioGroup radioGroup = findViewById(R.id.questionOne);
        radioGroup.clearCheck();

        radioGroup = findViewById(R.id.questionTwo);
        radioGroup.clearCheck();

        editText = findViewById(R.id.answer31);
        editText.setText("");

        radioGroup = findViewById(R.id.questionFour);
        radioGroup.clearCheck();

        CheckBox checkBox = findViewById(R.id.answer51);
        checkBox.setChecked(false);
        checkBox = findViewById(R.id.answer52);
        checkBox.setChecked(false);
        checkBox = findViewById(R.id.answer53);
        checkBox.setChecked(false);
        checkBox = findViewById(R.id.answer54);
        checkBox.setChecked(false);

    }

}