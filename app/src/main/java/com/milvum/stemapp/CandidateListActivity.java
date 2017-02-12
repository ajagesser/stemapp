package com.milvum.stemapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.milvum.stemapp.model.Candidate;
import com.milvum.stemapp.model.Party;
import com.milvum.stemapp.utils.Utils;
import com.milvum.stemapp.view.CandidateAdapter;

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

        final List<Candidate> candidates = Utils.getCandidates();

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
                            Utils.showConfirmationDialog(CandidateListActivity.this, party.getName(), candidate);
                        }
                    }
                }
        );
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
