package com.milvum.stemapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button nextButton = (Button) this.findViewById(R.id.nextButton);
        Button verifyButton = (Button) this.findViewById(R.id.verifyButton);

        nextButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(HomeActivity.this, PartyListActivity.class);
                        startActivity(i);
                    }
                }
        );

        verifyButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(HomeActivity.this, VotingTilesActivty.class);
                        startActivity(i);
                    }
                }
        );
    }
}
