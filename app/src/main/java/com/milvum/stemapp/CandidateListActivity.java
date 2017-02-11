package com.milvum.stemapp;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.milvum.stemapp.model.Candidate;
import com.milvum.stemapp.model.Party;
import com.milvum.stemapp.utils.Constants;
import com.milvum.stemapp.view.CandidateAdapter;

import java.util.ArrayList;
import java.util.List;

public class CandidateListActivity extends AppCompatActivity {

    private int selectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_list);

        Intent intent = getIntent();

        final Party party = intent.getParcelableExtra("party");
        setTitle(party.getName());

        selectedIndex = -1;


        final List<Candidate> candidates = new ArrayList<>();

        candidates.add(
                new Candidate(
                        0,
                        "Rutte",
                        "Mark",
                        "m",
                        "`s-Gravenhage"
                )
        );
        candidates.add(
                new Candidate(
                        1,
                        "Hennis-Plasschaert",
                        "Jeanine",
                        "v",
                        "`s-Gravenhage"
                )
        );


        final ListView candidateList = (ListView) findViewById(R.id.candidate_list);

        CandidateAdapter adapter = new CandidateAdapter(this, R.layout.candidate_item, candidates);
        candidateList.setAdapter(adapter);

        candidateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean isSelected = toggleVoteButton(position);
                view.setSelected(isSelected);
            }
        });

        Button voteButton = (Button) this.findViewById(R.id.voteButton);
        voteButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if (selectedIndex > -1) {
                            final Candidate candidate = (Candidate) candidateList.getItemAtPosition(selectedIndex);
                            showConfirmationDialog(party.getName(), candidate);
                        }
                    }
                }
        );
    }

    protected void showConfirmationDialog(String partyName, Candidate candidate) {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        Bundle args = new Bundle();
        args.putString(Constants.PARTY_NAME, partyName);
        args.putParcelable(Constants.CANDIDATE, candidate);
        // Create and show the dialog.
        DialogFragment newFragment = new ConfirmationDialogFragment();
        newFragment.setArguments(args);
        newFragment.show(ft, Constants.CONFIRMATION_DIALOG);
    }

    protected boolean toggleVoteButton(int index) {
        Button voteButton = (Button) this.findViewById(R.id.voteButton);

        if (selectedIndex != index) {
            selectedIndex = index;
            voteButton.setEnabled(true);
            return true;
        } else {
            selectedIndex = -1;
            voteButton.setEnabled(false);
            return false;
        }
    }
}
