package com.example.soluciontaller2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soluciontaller2.quizactivity.QuizActivityint;
import com.example.soluciontaller2.quizactivity.QuizActivitystring;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = QuizActivitystring.EXTRASCORE.getMensaje();
    private static final long COUNTDOWN_IN_MILLIS = QuizActivityint.TREINTAMIL.getNum();

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, QuizActivitystring.SELECCIONA_UNA_RESPUESTA.getMensaje(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText(QuizActivitystring.PREGUNTA.getMensaje() +
                    questionCounter + QuizActivitystring.ESPACIO.getMensaje() + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText(QuizActivitystring.CONFIRMAR.getMensaje());

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, QuizActivityint.DIEZMIL.getNum()) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / QuizActivityint.DIEZMIL.getNum()) / QuizActivityint.SESENTA.getNum();
        int seconds = (int) (timeLeftInMillis / QuizActivityint.DIEZMIL.getNum()) % QuizActivityint.SESENTA.getNum();

        String timeFormatted = String.format(Locale.getDefault(), QuizActivitystring.FORMATO.getMensaje(), minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < QuizActivityint.DIEZMIL.getNum()) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }

    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
            textViewScore.setText(QuizActivitystring.PUNTOS.getMensaje()+ score);
        }

        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(Color.rgb(QuizActivityint.VENTITRES.getNum(),QuizActivityint.CIENTOTREINTAYCINCO.getNum(),
                        QuizActivityint.CUARENTAYSEIS.getNum()));
                textViewQuestion.setText(QuizActivitystring.RESPUESTA_UNO.getMensaje());
                break;
            case 2:
                rb2.setTextColor(Color.rgb(QuizActivityint.VENTITRES.getNum(),QuizActivityint.CIENTOTREINTAYCINCO.getNum(),
                        QuizActivityint.CUARENTAYSEIS.getNum()));
                break;
            case 3:
                rb3.setTextColor(Color.rgb(QuizActivityint.VENTITRES.getNum(),QuizActivityint.CIENTOTREINTAYCINCO.getNum(),
                        QuizActivityint.CUARENTAYSEIS.getNum()));
                break;
            default: Toast.makeText(QuizActivity.this, QuizActivitystring.SELECCIONA_UNA_RESPUESTA.getMensaje(),
                    Toast.LENGTH_SHORT).show();

        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText(QuizActivitystring.SEGUIR.getMensaje());
        } else {
            buttonConfirmNext.setText(QuizActivitystring.VOLVER_AL_INICIO.getMensaje());
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + QuizActivityint.VEINTEMIL.getNum() > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, QuizActivitystring.SALIR_PARA_INICIO.getMensaje(), Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
