package com.milvum.stemapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.milvum.stemapp.model.Vote;
import com.milvum.stemapp.utils.Constants;
import com.milvum.stemapp.utils.Utils;
import com.milvum.stemapp.view.VoteAdapter;

import java.util.ArrayList;
import java.util.List;

public class VotingTilesActivty extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_tiles);
        setTitle(getString(R.string.votingTilesTitle));

        final GridView voteGrid = (GridView) findViewById(R.id.vote_grid);

        VoteAdapter adapter = new VoteAdapter(this, R.layout.vote_item, getVoteList());
        voteGrid.setAdapter(adapter);

        voteGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VotingTilesActivty.this, VerificationActivity.class);
                intent.putExtra(Constants.VOTE_BUNDLE, (Vote) voteGrid.getItemAtPosition(position));
                startActivity(intent);
            }
        });
    }

    private List<Vote> getVoteList() {
//        String[] tokens = new String[Constants.AMOUNT_VOTES];
        List<Vote> savedVotes = Utils.getVotes();

//        Utils.getStoreSet(getApplicationContext(), Constants.TOKEN_PREFS, Constants.TOKENS).toArray(tokens);

        // TODO: Randomize positions

//        for (int i = 0; i < tokens.length; i++) {
//            Vote vote = ;
//            votes.add(vote);
//        }


        return savedVotes;
    }
}
