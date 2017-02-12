package com.milvum.stemapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.milvum.stemapp.model.Candidate;
import com.milvum.stemapp.model.Party;
import com.milvum.stemapp.utils.Utils;
import com.milvum.stemapp.view.PartyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Randy on 10/02/2017.
 */
public class PartyListActivity extends AppCompatActivity {

    private ListView partyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_list);

        setTitle(R.string.partyTitle);

        partyList = (ListView) findViewById(R.id.party_list);

        PartyAdapter adapter = new PartyAdapter(this, R.layout.party_item, getPartyList());
        partyList.setAdapter(adapter);

        partyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    voteBlank(position);
                } else {
                    goToCandidateList(position);
                }
            }
        });
    }

    private void voteBlank(int position) {
        Party party = (Party) partyList.getItemAtPosition(position);
        String partyListNumber = "Lijst " + party.getId();
        Candidate noCandidate = new Candidate("-1", partyListNumber, "", "", "");

        Utils.showConfirmationDialog(this, party.getName(), noCandidate);
    }

    private void goToCandidateList(int position) {
        Intent intent = new Intent(PartyListActivity.this, CandidateListActivity.class);
        intent.putExtra("party", (Party) partyList.getItemAtPosition(position));
        startActivity(intent);
    }

    private List<Party> getPartyList() {
        List<Party> parties = new ArrayList<>();
        parties.add(
                new Party(
                        0,
                        "Blanco",
                        0,
                        R.drawable.background_vote
                )
        );
        parties.add(
                new Party(
                        1,
                        "VVD",
                        1,
                        R.drawable.party
                )
        );
        parties.add(
                new Party(
                        2,
                        "PVDA",
                        2,
                        R.drawable.party
                )
        );
        parties.add(
                new Party(
                        3,
                        "PVV",
                        3,
                        R.drawable.party
                )
        );
        parties.add(
                new Party(
                        4,
                        "SP",
                        4,
                        R.drawable.party
                )
        );
        parties.add(
                new Party(
                        5,
                        "SGP",
                        5,
                        R.drawable.party
                )
        );
        parties.add(
                new Party(
                        6,
                        "D66",
                        6,
                        R.drawable.party
                )
        );
        parties.add(
                new Party(
                        7,
                        "PVDD",
                        7,
                        R.drawable.party
                )
        );
        parties.add(
                new Party(
                        8,
                        "CDA",
                        8,
                        R.drawable.party
                )
        );
        parties.add(
                new Party(
                        9,
                        "CU",
                        9,
                        R.drawable.party
                )
        );
        return parties;
    }
}
