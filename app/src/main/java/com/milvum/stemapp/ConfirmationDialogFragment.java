package com.milvum.stemapp;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.milvum.stemapp.model.Candidate;
import com.milvum.stemapp.utils.Constants;

public class ConfirmationDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get candidate data & party name from bundle
        String partyName = getArguments().getString(Constants.PARTY_NAME);
        Candidate candidate = (Candidate) getArguments().get(Constants.CANDIDATE);
        String firstRowText = String.format("%s %s", candidate.getId(), candidate.getLastName());
        String secondRowText = String.format("%s. (%s) (%s)", candidate.getFirstLetter(), candidate.getFirstName(), candidate.getGender());
        String thirdRowText = String.format("%s", candidate.getCity());

        //Set data to the correct TextViews
        View confirmationView = inflater.inflate(R.layout.dialog_confirmation_vote, container, false);
        TextView partyNameTextView = (TextView) confirmationView.findViewById(R.id.party_name);
        TextView candidateFirstRowTextView = (TextView) confirmationView.findViewById(R.id.candidate_first_row);
        TextView candidateSecondRowTextView = (TextView) confirmationView.findViewById(R.id.candidate_second_row);
        TextView candidateThirdRowTextView = (TextView) confirmationView.findViewById(R.id.candidate_third_row);
        partyNameTextView.setText(partyName);
        candidateFirstRowTextView.setText(firstRowText);
        candidateSecondRowTextView.setText(secondRowText);
        candidateThirdRowTextView.setText(thirdRowText);

        // Watch for button clicks.
        Button yesButton = (Button) confirmationView.findViewById(R.id.yesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), HomeActivity.class);
                startActivity(i);
            }
        });

        Button noButton = (Button) confirmationView.findViewById(R.id.noButton);
        noButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });

        return confirmationView;
    }


}

