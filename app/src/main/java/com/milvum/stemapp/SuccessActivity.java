package com.milvum.stemapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.milvum.stemapp.model.Candidate;
import com.milvum.stemapp.utils.Constants;
import com.milvum.stemapp.utils.Utils;

import java.util.Random;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Intent intent = getIntent();
        fillTextViews(intent);

        initialize();
    }

    private void initialize() {
        final Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        final View loadingIcon = findViewById(R.id.loading_icon);
        loadingIcon.startAnimation(rotation);

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.grow);
        final Dialog dialog = new Dialog(this, R.style.SecretPopupDialogStyle);
        dialog.setContentView(R.layout.popup_secret_token);
        setDialogSecretToken(dialog);
        final Handler handler = new Handler(getMainLooper());
        final View countdown = dialog.findViewById(R.id.secret_countdown);

        final Runnable hideSecretToken = new Runnable() {
            @Override
            public void run() {
                countdown.clearAnimation();
                dialog.dismiss();
            }
        };

        final Runnable showSecretToken = new Runnable() {
            @Override
            public void run() {
                dialog.show();

                handler.postDelayed(hideSecretToken, animation.getDuration());
                countdown.startAnimation(animation);
            }
        };

        Runnable showSuccess = new Runnable() {
            @Override
            public void run() {
                onSuccess();
                handler.postDelayed(showSecretToken, Constants.SECRET_WAIT_TIME);
            }
        };

        handler.postDelayed(showSuccess, Constants.SECRET_WAIT_TIME);
    }

    private void onSuccess() {
        Utils.setViewVisibility(this.getDelegate(), R.id.loading_state, View.INVISIBLE);
        Utils.setViewVisibility(this.getDelegate(), R.id.success_state, View.VISIBLE);
        final View loadingIcon = findViewById(R.id.loading_icon);
        loadingIcon.clearAnimation();
    }

    private void setDialogSecretToken(Dialog dialog) {
        final String secretToken = getRandomToken();
        final TextView secretTokenTextView = (TextView) dialog.findViewById(R.id.secret_token);
        secretTokenTextView.setText(secretToken);
    }

    @NonNull
    private String getRandomToken() {
        Random random = new Random();
        return String.valueOf(Constants.TOKENS.charAt(random.nextInt(Constants.TOKENS.length())));
    }

    private void fillTextViews(Intent intent) {
        final String partyName = intent.getStringExtra(Constants.PARTY_NAME);
        final Candidate candidate = intent.getParcelableExtra(Constants.CANDIDATE);
        final String firstRowText = String.format("%s %s", candidate.getId(), candidate.getLastName());
        final String secondRowText = String.format("%s. (%s) (%s)", candidate.getFirstLetter(), candidate.getFirstName(), candidate.getGender());
        final String thirdRowText = String.format("%s", candidate.getCity());

        Utils.setTextViewText(this.getDelegate(), R.id.party_name, partyName);
        Utils.setTextViewText(this.getDelegate(), R.id.candidate_first_row, firstRowText);
        Utils.setTextViewText(this.getDelegate(), R.id.candidate_second_row, secondRowText);
        Utils.setTextViewText(this.getDelegate(), R.id.candidate_third_row, thirdRowText);
    }

}
