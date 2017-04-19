package com.milvum.stemapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.milvum.stemapp.utils.Utils;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button nextButton = (Button) this.findViewById(R.id.nextButton);
        Button verifyButton = (Button) this.findViewById(R.id.verifyButton);

        if(Utils.getVotes().size() == 0){
            verifyButton.setVisibility(View.GONE);
        }

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
