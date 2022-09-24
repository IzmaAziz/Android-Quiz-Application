package com.example.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;
    private List<Question> questionList;
    private int questionCountTotal;
    private Question currentQuestion;
    private boolean answered;
    public static int marks=0,correct=0,wrong=0;
    int questionCounter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        TextView textView=(TextView)findViewById(R.id.DispName);
        Intent intent = getIntent();
        String name= intent.getStringExtra("myname");
        if (name.trim().equals(""))
            textView.setText("Hello User");
        else
            textView.setText("Hello " + name.toUpperCase());
        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);
        showNextQuestion();
        buttonConfirmNext.setText("Next");
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                        questionCounter++;
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }  if (questionCounter < questionCountTotal){showNextQuestion(); if(questionCounter==9){buttonConfirmNext.setText("Finish");}}
                    else{
                        marks=correct;
                        finishQuiz(); }
            }
        });
    }

    private void showNextQuestion() {
         rbGroup.clearCheck();
           currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOptA());
            rb2.setText(currentQuestion.getOptB());
            rb3.setText(currentQuestion.getOptC());
            textViewQuestionCount.setText("Question: " +(questionCounter+1)+"/"+ questionCountTotal);
            answered = false;}

   private void checkAnswer() {
       answered = true;
       RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
       int answer = rbGroup.indexOfChild(rbSelected) + 1;
       if (answer == currentQuestion.getAnswer()) {
           correct++;
           textViewScore.setText("Score: " + correct);
       } else {
           wrong++;
       }
   }
       private void finishQuiz () {
           Intent intent = new Intent(QuizActivity.this, ExitQuiz.class);
              startActivity(intent);

       }

   }









