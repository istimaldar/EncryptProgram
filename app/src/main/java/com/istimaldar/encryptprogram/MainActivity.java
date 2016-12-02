package com.istimaldar.encryptprogram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.istimaldar.encryptprogram.controller.Controller;
import com.istimaldar.encryptprogram.controller.Storage;
import com.istimaldar.encryptprogram.model.Model;

public class MainActivity extends AppCompatActivity implements Retrievable{

    private Spinner actionSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView actionSelectText = (TextView) findViewById(R.id.actionSelectText);
        final TextView sourceSelectText = (TextView) findViewById(R.id.sourceSelectText);
        final Button confirmButton = (Button) findViewById(R.id.confirmButton);

        actionSelector = Controller.getInstance().setSpinner(this, Storage.ACTIONS, 0,
                R.id.actionSelector);
        final Spinner sourceSelector = Controller.getInstance().setSpinner(this, Storage.SOURCE, 0,
                R.id.sourceSelector);
        final Button loadButton = (Button) findViewById(R.id.load);
        final EditText text = (EditText) findViewById(R.id.input);
        final TextView fileText = (TextView) findViewById(R.id.file);
        sourceSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if(position==0) {
                    loadButton.setVisibility(View.GONE);
                    fileText.setVisibility(View.GONE);
                    text.setVisibility(View.VISIBLE);
                }
                else {
                    loadButton.setVisibility(View.VISIBLE);
                    fileText.setVisibility(View.VISIBLE);
                    text.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        final MainActivity activity = this;
        View.OnClickListener next = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller.getInstance().showActivity(activity, EncryptActivity.class);
                Model.getInstance().loadText(text.getText().toString());
            }
        };
        confirmButton.setOnClickListener(next);
    }

    @Override
    public int retrieve() {
        return actionSelector.getSelectedItemPosition();
    }
}
