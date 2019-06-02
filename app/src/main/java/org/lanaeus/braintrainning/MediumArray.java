package org.lanaeus.braintrainning;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MediumArray extends ArrayAdapter<Question> {

    public MediumArray(Context context, ArrayList<Question> questions) {
        super(context, 0, questions);

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Question ques = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_question, parent, false);

        }

        TextView txtquestion = (TextView) convertView.findViewById(R.id.txtQuestion);
        txtquestion.setText(ques.getMediumLine());

        TextView txtInput = (TextView) convertView.findViewById(R.id.txtInput);
        txtInput.setText(ques.getInput());

        LinearLayout itemLayout = (LinearLayout) convertView.findViewById(R.id.itemLayout);
        if (ques.getStatus() == Question.QStatus.Right) {
        } else if (ques.getStatus() == Question.QStatus.Wrong) {
            txtInput.append(String.format(" |A: %s", ques.getAns()));
        }
        else {
            itemLayout.setBackgroundColor(Color.parseColor("#DBA901"));
        }
        return convertView;

    }
}

