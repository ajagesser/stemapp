package com.milvum.stemapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.ArraySet;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.milvum.stemapp.model.Candidate;
import com.milvum.stemapp.model.Vote;
import com.milvum.stemapp.utils.Constants;
import com.milvum.stemapp.utils.Utils;

import java.util.Random;

import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;

public class SuccessActivity extends AppCompatActivity {

    private AsyncHttpClient client;
    private AlertDialog dialog;
    private AlertDialog alertDialog;

    private String local = "http://localhost";
    private String remote = "http://staging.milvum.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        setTitle(getString(R.string.successTitle));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);


//        client = new AsyncHttpClient();
//        alertDialog = new AlertDialog.Builder(this).create();

        fillTextViews();

        initialize();
//        vote();
    }

    private void vote () {
        RequestParams params = new RequestParams();
        params.put("stembiljet_id", 1); //todo hardcoded change per build
        params.put("kandidaat", 1); //todo hardcoded change per build

        client.post(remote + ":3000/api/vote", params ,new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {

            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

    private void initialize() {
        final Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        final View loadingIcon = findViewById(R.id.loading_icon);
        loadingIcon.startAnimation(rotation);



        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.grow);
        final Dialog dialog = new Dialog(this, R.style.SecretPopupDialogStyle);
        dialog.setContentView(R.layout.popup_secret_token);
        final View countdown = dialog.findViewById(R.id.secret_countdown);

        final Handler handler = new Handler(getMainLooper());


        final Runnable hideSecretToken = new Runnable() {
            @Override
            public void run() {
                try {
                    countdown.clearAnimation();
                    dialog.dismiss();
                } catch (Error e){

                }
            }
        };

        final Runnable showSecretToken = new Runnable() {
            @Override
            public void run() {
              try {
                  dialog.show();

                  handler.postDelayed(hideSecretToken, animation.getDuration());
                  countdown.startAnimation(animation);
              } catch (Error e){

              }
            }
        };

        final Runnable showSuccess = new Runnable() {
            @Override
            public void run() {
                try {
                    generateVotes(dialog);
                    onSuccess();
                    handler.postDelayed(showSecretToken, Constants.SECRET_WAIT_TIME);
                } catch (Error e){

                }

            }
        };


        handler.postDelayed(showSuccess, Constants.SECRET_WAIT_TIME);

        final Button homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        handler.removeCallbacks(showSuccess);
                        handler.removeCallbacks(showSecretToken);
                        handler.removeCallbacks(hideSecretToken);
                        Intent i = new Intent(SuccessActivity.this, HomeActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                });
        homeButton.setEnabled(false);
    }

    private void generateVotes(Dialog dialog) {
        // Clear votes
        Utils.clearVotes();


        // User vote
        String voteId = "1234BRA";
        String userToken = Utils.getRandomToken();

        // Inflate Dialog with custom layout.
        setDialogSecretToken(dialog, userToken);


        // Generate random position;
        int position = new Random().nextInt(Constants.AMOUNT_VOTES);

        String partyName = getIntent().getStringExtra(Constants.PARTY_NAME);
        Candidate candidate = getIntent().getParcelableExtra(Constants.CANDIDATE);

        Vote vote = new Vote(voteId, userToken, partyName, candidate, position);
        // TODO: Store votes on device.
        Utils.saveVotes(vote);


        ArraySet<String> tokens = new ArraySet();
        tokens.add(userToken);
        tokens = getUniqueTokens(tokens, Constants.AMOUNT_VOTES);
        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.valueAt(i);
            if (!userToken.equals(token)) {
                generateRandomVote(token);
            }
        }
    }

    private ArraySet<String> getUniqueTokens(ArraySet<String> list, int size) {
        String token;
        int i = 0;
        int remainingSize = size - list.size();
        while (i < remainingSize) {
            token = Utils.getRandomToken();

            if (!list.contains(token)) {
                list.add(token);
                i++;
            }
        }
        return list;
    }

    private void generateRandomVote(String token) {
        Random random = new Random();
        String voteId = "" + random.nextInt(Constants.VOTE_ID_LIMIT);
        Candidate salim = new Candidate("1", "Hadri", "Salim", "m", "`s-Gravenhage");
        Candidate viresh = new Candidate("2", "Jagesser", "Viresh", "m", "`s-Gravenhage");
        Candidate selected = random.nextBoolean() ? salim : viresh;

        Vote vote = new Vote(voteId, token, "DAPP", selected, -1);
        // TODO: Store vote locally
        Utils.saveVotes(vote);
    }


    private void onSuccess() {
        final Button homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setEnabled(true);
        Utils.setViewVisibility(this.getDelegate(), R.id.loading_state, View.INVISIBLE);
        Utils.setViewVisibility(this.getDelegate(), R.id.success_state, View.VISIBLE);
        final View loadingIcon = findViewById(R.id.loading_icon);
        loadingIcon.clearAnimation();

    }


    private void setDialogSecretToken(Dialog dialog, String token) {
        final TextView secretTokenTextView = (TextView) dialog.findViewById(R.id.secret_token);
        secretTokenTextView.setText(token);
    }


    private void fillTextViews() {
        Intent intent = getIntent();
        final String partyName = intent.getStringExtra(Constants.PARTY_NAME);
        final Candidate candidate = intent.getParcelableExtra(Constants.CANDIDATE);
        final String firstRowText = candidate.getId().equals("-1") ? candidate.getLastName() : String.format("%s %s", candidate.getId(), candidate.getLastName());
        final String secondRowText = candidate.getId().equals("-1") ? "" : String.format("%s. (%s) (%s)", candidate.getFirstLetter(), candidate.getFirstName(), candidate.getGender());
        final String thirdRowText = candidate.getId().equals("-1") ? "" : String.format("%s", candidate.getCity());

        Utils.setTextViewText(this.getDelegate(), R.id.party_name, partyName);
        Utils.setTextViewText(this.getDelegate(), R.id.candidate_first_row, firstRowText);
        Utils.setTextViewText(this.getDelegate(), R.id.candidate_second_row, secondRowText);
        Utils.setTextViewText(this.getDelegate(), R.id.candidate_third_row, thirdRowText);
    }

}
