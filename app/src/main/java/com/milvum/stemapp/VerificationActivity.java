package com.milvum.stemapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.milvum.stemapp.model.Candidate;
import com.milvum.stemapp.model.Vote;
import com.milvum.stemapp.utils.Constants;
import com.milvum.stemapp.utils.Utils;

public class VerificationActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        setTitle(getString(R.string.verificationTitle));

        fillTextViews();

        initialize();
    }


    private void initialize() {
        final Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        final View loadingIcon = findViewById(R.id.loading_icon);
        loadingIcon.startAnimation(rotation);

        final Button homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(VerificationActivity.this, HomeActivity.class);
                        startActivity(i);
                    }
                });
        homeButton.setEnabled(false);

        final Handler handler = new Handler(getMainLooper());

        Runnable showSuccess = new Runnable() {
            @Override
            public void run() {
                onSuccess();
            }
        };

        handler.postDelayed(showSuccess, Constants.SECRET_WAIT_TIME);
    }

    private void onSuccess() {
        final Button homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setEnabled(true);
        Utils.setViewVisibility(this.getDelegate(), R.id.loading_state, View.INVISIBLE);
        Utils.setViewVisibility(this.getDelegate(), R.id.success_state, View.VISIBLE);
        final View loadingIcon = findViewById(R.id.loading_icon);
        loadingIcon.clearAnimation();

    }
    private void fillTextViews() {
        Intent intent = getIntent();
        final Vote vote = intent.getParcelableExtra(Constants.VOTE_BUNDLE);
        final String partyName = vote.getPartyName();
        final String firstRowText = vote.getCandidateId().equals("-1") ? vote.getCandidateLastName() : String.format("%s %s", vote.getCandidateId(), vote.getCandidateLastName());
        final String secondRowText = vote.getCandidateId().equals("-1") ? "" : String.format("%s. (%s) (%s)", vote.getFirstLetter(), vote.getCandidateFirstName(), vote.getCandidateGender());
        final String thirdRowText = vote.getCandidateId().equals("-1") ? "" : String.format("%s", vote.getCandidateCity());

        Utils.setTextViewText(this.getDelegate(), R.id.party_name, partyName);
        Utils.setTextViewText(this.getDelegate(), R.id.candidate_first_row, firstRowText);
        Utils.setTextViewText(this.getDelegate(), R.id.candidate_second_row, secondRowText);
        Utils.setTextViewText(this.getDelegate(), R.id.candidate_third_row, thirdRowText);
    }
}
