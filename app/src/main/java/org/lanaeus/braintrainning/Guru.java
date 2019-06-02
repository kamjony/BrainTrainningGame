package org.lanaeus.braintrainning;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Guru extends FragmentActivity implements KeyboardFragment.NumpadListener, ScoreFragment.ScoreListener {
    int Length = 10; //changes number of questions
    int Count = 1;
    int nCorrect = 0;
    long timeElapsed = 0;
    String timeFormat = "";
    boolean quizOver = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_quiz);


        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setDivider(null);

        Question q1 = getQuizQuestion(Count);
        ArrayList<Question> listQuestions = new ArrayList<Question>();
        listQuestions.add(q1);
        GuruArray adapter = new GuruArray(this, listQuestions);
        listView.setAdapter(adapter);

        TextView Progress = (TextView) findViewById(R.id.Progress);
        Progress.setText(String.format("%d / %d", Count, Length));



        if (savedInstanceState == null) {
            KeyboardFragment numpadFrag = new KeyboardFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.numpadContainer,numpadFrag).commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!quizOver) {
            Chronometer quizTimer = (Chronometer) findViewById(R.id.chronometer);
            quizTimer.setBase(SystemClock.elapsedRealtime() - timeElapsed);
            quizTimer.start();
        }
    }

    @Override
    protected void onStop() {
        if (!quizOver) {
            Chronometer quizTimer = (Chronometer) findViewById(R.id.chronometer);
            timeElapsed = SystemClock.elapsedRealtime() - quizTimer.getBase();
            quizTimer.stop();
        }
        super.onStop();
    }

    public void onNumClick(int input) {
        try {
            ListView listView = (ListView) findViewById(R.id.listView);
            GuruArray adapter = (GuruArray) listView.getAdapter();
            Question item = adapter.getItem(Count - 1);
            String prevInput = item.getInput();
            item.setInput(prevInput + String.format("%d", input));
            adapter.notifyDataSetChanged();
            listView.smoothScrollToPosition(Count - 1);
        } catch (Exception e) {
        }
    }

    public void onMinusClick() {
        try {
            ListView listView = (ListView) findViewById(R.id.listView);
            GuruArray adapter = (GuruArray) listView.getAdapter();
            Question item = adapter.getItem(Count - 1);
            //String prevInput = item.getInput();
            item.setInput("-");
            adapter.notifyDataSetChanged();
            listView.smoothScrollToPosition(Count - 1);
        } catch (Exception e) {

        }
    }


    public void onDelClick() {
        try {
            ListView listView = (ListView) findViewById(R.id.listView);
            GuruArray adapter = (GuruArray) listView.getAdapter();
            Question item = adapter.getItem(Count - 1);
            String prevInput = item.getInput();
            if (prevInput.length() != 0) {
                item.setInput(prevInput.substring(0, (prevInput.length() - 1)));
                adapter.notifyDataSetChanged();
                listView.smoothScrollToPosition(Count - 1);
            }
        } catch (Exception e) {
        }
    }

    public void onDelHold() {
        try {
            ListView listView = (ListView) findViewById(R.id.listView);
            GuruArray adapter = (GuruArray) listView.getAdapter();
            Question item = adapter.getItem(Count - 1);
            String prevInput = item.getInput();
            if (prevInput.length() != 0) {
                item.setInput("");
                adapter.notifyDataSetChanged();
                listView.smoothScrollToPosition(Count - 1);
            }
        } catch (Exception e) {
        }
    }

    public void onEntClick() {
        try {
            ListView listView = (ListView) findViewById(R.id.listView);
            GuruArray adapter = (GuruArray) listView.getAdapter();

            Question item = adapter.getItem(Count - 1);
            String strInput = item.getInput();
            if (strInput.length() != 0) {
                int intInput = Integer.parseInt(strInput);
                if (intInput == item.getAns()) {
                    item.setStatus(Question.QStatus.Right);
                    nCorrect++;
                    TextView result = (TextView) findViewById(R.id.result);
                    result.setText("CORRECT!");
                    result.setTextColor(Color.parseColor("#04B404"));
                } else {
                    item.setStatus(Question.QStatus.Wrong);
                    TextView result = (TextView) findViewById(R.id.result);
                    result.setText("INCORRECT!");
                    result.setTextColor(Color.parseColor("#DF0101"));

                }
                adapter.notifyDataSetChanged();

                if (Count < Length) {
                    Count++;
                    Question q1 = getQuizQuestion(Count);
                    adapter.add(q1);
                    listView.smoothScrollToPosition(Count - 1);
                } else {
                    quizOver = true;
                    Chronometer quizTimer = (Chronometer) findViewById(R.id.chronometer);
                    timeElapsed = SystemClock.elapsedRealtime() - quizTimer.getBase();
                    quizTimer.stop();

                    timeFormat = String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(timeElapsed),
                            TimeUnit.MILLISECONDS.toSeconds(timeElapsed) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeElapsed))
                    );

                    ScoreFragment resultsFrag = new ScoreFragment();
                    Bundle args = new Bundle();
                    args.putInt(ScoreFragment.Ext_Score, nCorrect);
                    args.putInt(ScoreFragment.Ext_Length, Length);
                    args.putString(ScoreFragment.Ext_Time, timeFormat);
                    resultsFrag.setArguments(args);

                    getSupportFragmentManager().beginTransaction().replace(R.id.numpadContainer, resultsFrag).commit();
                }
            }
        } catch (Exception e) {
        }
    }

    public void MainMenu() {
        finish();
    }


    public Question getQuizQuestion(int count) {
        Random rand = new Random();
        int intOper = rand.nextInt(2);
        int num1 = 0, num2 = 0, ans = 0, num3 = 0, num4 = 0, num5 = 0, num6 = 0;

        char oper1 = '+';
        char oper2 = '-';
        char oper3 = '×';
        char oper4 = '÷';
        char oper5 = '+';



        switch (intOper) {   //4,5 or 6 integers
            case 0: //+
                oper1 = '+';
                oper2 = '-';
                oper3 = '×';
                oper4 = '+';
                num1 = rand.nextInt(30) + 1;
                num2 = rand.nextInt(30) + 1;
                num3 = rand.nextInt(15) + 1;
                num4 = rand.nextInt(15) + 1;
                num5 = rand.nextInt(30) + 1;
                ans = num1 + num2 - num3 * num4 + num5;
                break;

            case 1: //÷
                oper1 = '÷';
                oper2 = '×';
                oper3 = '÷';
                oper4 = '+';
                ArrayList<Integer> factors = new ArrayList<Integer>();
                do {
                    num1 = rand.nextInt(97) + 4;
                    int limit = num1;
                    for (int i = 2; i < limit; i++) {
                        if (num1 % i == 0) {
                            factors.add(i);
                            limit = num1 / i;
                            factors.add(limit);
                        }
                    }
                } while (factors.size() == 0); //when num1 is prime

                int index = rand.nextInt(factors.size());
                num2 = factors.get(index);
                do {
                    num3 = rand.nextInt(97) + 4;
                    int limit2 = num3;
                    for (int j = 2; j < limit2; j++) {
                        if (num2 % j == 0) {
                            factors.add(j);
                            limit2 = num3 / j;
                            factors.add(limit2);
                        }
                    }

                } while (factors.size() == 0);
                int index2 = rand.nextInt(factors.size());
                num4 = factors.get(index2);
                num5 = factors.get(index2);
                ans = num1 / num2 * num3 / num4 + num5;
                break;

        }


        Question q = new Question(num1, num2, num3, num4, num5, num6, oper1,oper2,oper3,oper4,oper5, ans, count);
        return q;
    }
}