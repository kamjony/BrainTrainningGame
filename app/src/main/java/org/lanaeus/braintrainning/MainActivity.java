package org.lanaeus.braintrainning;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements AboutDialog.DialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newgame (View v){
        Intent intent = new Intent(this,QuestionMode.class);
        startActivity(intent);
    }

    public void about(View v) {
        DialogFragment dialog = new AboutDialog();
        dialog.show(getFragmentManager(), getString(R.string.tag_about));

    }

    public void exit(View view) {finish();}

}