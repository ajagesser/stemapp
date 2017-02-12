package com.milvum.stemapp;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.milvum.stemapp.model.Candidate;
import com.milvum.stemapp.utils.Constants;
import com.milvum.stemapp.utils.Utils;

public class ConfirmationDialogFragment extends DialogFragment {
    private String partyName;
    private Candidate candidate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get candidate data & party name from bundle
        partyName = getArguments().getString(Constants.PARTY_NAME);
        candidate = (Candidate) getArguments().get(Constants.CANDIDATE);

        //Set data to the correct TextViews
        View confirmationView = inflater.inflate(R.layout.dialog_confirmation_vote, container, false);
        setDialogContent(confirmationView);

        // Watch for button clicks.
        Button yesButton = (Button) confirmationView.findViewById(R.id.yesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Go to success
                Intent i = new Intent(getActivity(), SuccessActivity.class);
                i.putExtra(Constants.PARTY_NAME, partyName);
                i.putExtra(Constants.CANDIDATE, candidate);

                // Dismiss the dialog
                dismiss();

                startActivity(i);

            }
        });

        Button noButton = (Button) confirmationView.findViewById(R.id.noButton);
        noButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Dismiss the dialog
                dismiss();
            }
        });

        return confirmationView;
    }

    private void setDialogContent(View parentView) {
        String id = candidate.getId().equals("-1") ? "" : "" + candidate.getId();
        final String firstRowText = candidate.getId().equals("-1")? candidate.getLastName() : String.format("%s %s", id, candidate.getLastName());
        final String secondRowText = candidate.getId().equals("-1") ? "" : String.format("%s. (%s) (%s)", candidate.getFirstLetter(), candidate.getFirstName(), candidate.getGender());
        final String thirdRowText = candidate.getId().equals("-1") ? "" : String.format("%s", candidate.getCity());
        Utils.setTextViewText(parentView, R.id.party_name, partyName);
        Utils.setTextViewText(parentView, R.id.candidate_first_row, firstRowText);
        Utils.setTextViewText(parentView, R.id.candidate_second_row, secondRowText);
        Utils.setTextViewText(parentView, R.id.candidate_third_row, thirdRowText);
    }


}

