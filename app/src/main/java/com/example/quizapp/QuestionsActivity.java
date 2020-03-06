package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    private TextView question, noIndicator;
    private FloatingActionButton bookmarkBtn;
    private LinearLayout optionsConteiner;
    private Button shareBtn, nextBtn;
    private int count = 0;
    private List<QuestionModel> list;
    private int position =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        question = findViewById(R.id.question);
        noIndicator = findViewById(R.id.no_indicator);
        bookmarkBtn = findViewById(R.id.bookmark_btn);
        optionsConteiner = findViewById(R.id.options_conteiner);
        shareBtn = findViewById(R.id.share_btn);
        nextBtn = findViewById(R.id.next_btn);

        list = new ArrayList<>();
        list.add(new QuestionModel("question1", "a","b","c","d","a"));
        list.add(new QuestionModel("question2", "a","b","c","d","d"));
        list.add(new QuestionModel("question3", "a","b","c","d","b"));
        list.add(new QuestionModel("question4", "a","b","c","d","a"));
        list.add(new QuestionModel("question5", "a","b","c","d","c"));
        list.add(new QuestionModel("question6", "a","b","c","d","a"));
        list.add(new QuestionModel("question7", "a","b","c","d","d"));
        list.add(new QuestionModel("question8", "a","b","c","d","b"));


        for (int i =0; i < 4;i++){
            optionsConteiner.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                playAnim(question,0, list.get(position).getQuestion());
            }
        });
    }

    private void playAnim(final View view, final int value, final String data){

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (value == 0){
                    String option = "";
                    if (count == 0){
                        option = list.get(position).getOptionA();
                    } else if (count == 1){
                        option = list.get(position).getOptionB();
                    } else if (count == 2){
                        option = list.get(position).getOptionC();
                    } else if (count == 3){
                        option = list.get(position).getOptionD();

                    }
                    playAnim(optionsConteiner.getChildAt(count),0,option);
                    count++;
                }

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (value == 0){
                    ((TextView)view).setText(data);
                    playAnim(view, 1,data);
                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

}
