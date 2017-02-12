package com.milvum.stemapp.utils;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.TextView;

import com.milvum.stemapp.ConfirmationDialogFragment;
import com.milvum.stemapp.R;
import com.milvum.stemapp.model.Candidate;
import com.milvum.stemapp.model.Party;
import com.milvum.stemapp.model.Vote;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


/**
 * Created by Randy Tjin Asjoe on 2/11/2017.
 */

public class Utils {
    private Utils() {

    }

    public static void showConfirmationDialog(Activity activity, String partyName, Candidate candidate) {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        Fragment prev = activity.getFragmentManager().findFragmentByTag("dialog");
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


    public static void setViewVisibility(AppCompatDelegate delegate, int viewId, int visibility) {
        final View childView = delegate.findViewById(viewId);
        switch (visibility) {
            case View.VISIBLE:
                childView.setVisibility(visibility);
                break;
            case View.INVISIBLE:
                childView.setVisibility(visibility);
                break;
            case View.GONE:
                childView.setVisibility(visibility);
                break;
            default:
                break;
        }
    }

    public static void setTextViewText(AppCompatDelegate delegate, int viewId, String text) {
        TextView partyNameTextView = (TextView) delegate.findViewById(viewId);
        partyNameTextView.setText(text);
    }

    public static void setTextViewText(View parentView, int viewId, String text) {
        TextView textView = (TextView) parentView.findViewById(viewId);
        textView.setText(text);
    }

    public static void storeVote(Context context, Vote vote) {
        SharedPreferences votes = context.getSharedPreferences(Constants.TOKEN_PREFS, 0);
        SharedPreferences.Editor editor = votes.edit();
        editor.putStringSet(vote.getToken(), vote.toArraySet());
        editor.commit();
    }

    public static void storeSet(Context context, String name, String id, Set set) {
        SharedPreferences votes = context.getSharedPreferences(name, 0);
        SharedPreferences.Editor editor = votes.edit();
        editor.putStringSet(id, set);
        editor.commit();
    }

    public static Set getStoreSet(Context context, String name, String id) {
        SharedPreferences settings = context.getSharedPreferences(name, 0);
        return settings.getStringSet(id, null);
    }

    @NonNull
    public static String getRandomToken() {
        Random random = new Random();
        return String.valueOf(Constants.TOKENS.charAt(random.nextInt(Constants.TOKENS.length())));
    }

    public static Vote getVote(Context context, String token, int position) {
        // Restore preferences
        SharedPreferences settings = context.getSharedPreferences(Constants.TOKEN_PREFS, 0);
        String[] voteArray = new String[8];
        HashSet<String> test = (HashSet<String>) settings.getStringSet(token, null);
        settings.getStringSet(token, null).toArray(voteArray);

        Vote vote;

        if (voteArray.length > 0) {
            Candidate candidate = new Candidate(voteArray[3], voteArray[4], voteArray[5], voteArray[6], voteArray[7]);
            vote = new Vote(voteArray[0], voteArray[1], voteArray[2], candidate, position);
        } else {
            vote = new Vote("-1", "-1", "", new Candidate("-1", "", "", "", ""), -1);
        }

        return vote;
    }

    private static List<Vote> savedVotes = new ArrayList();

    public static void clearVotes(){
        savedVotes.clear();
    }
    public static void saveVotes(Vote vote) {
        savedVotes.add(vote);
    }

    public static List<Vote> getVotes() {
        return savedVotes;
    }

//    public static List<Party> generateParties() {
//
//        List<Party> parties = new ArrayList<>();
//        Party blanco = new Party(0, "Blanco", 0, R.drawable.background_vote);
//        Party vvd = new Party(1, "VVD", 1, R.drawable.party);
//        Party pvda = new Party(2, "PVDA", 2, R.drawable.party);
//        Party pvv = new Party(3, "PVV", 3, R.drawable.party);
//        Party sp = new Party(4, "SP", 4, R.drawable.party);
//        Party sgp = new Party(5, "SGP", 5, R.drawable.party);
//        Party d66 = new Party(6, "D66", 6, R.drawable.party);
//        Party pvdd = new Party(7, "PVDD", 7, R.drawable.party);
//        Party cda = new Party(8, "CDA", 8, R.drawable.party);
//        Party cu = new Party(9, "CU", 9, R.drawable.party);
//
//        parties.add(blanco);
//        parties.add(vvd);
//        parties.add(pvda);
//        parties.add(pvv);
//        parties.add(sp);
//        parties.add(sgp);
//        parties.add(d66);
//        parties.add(pvdd);
//        parties.add(cda);
//        parties.add(cu);
//
//
//        return parties;
//    }

    public static List<Candidate> getCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(
                new Candidate(
                        "1",
                        "Rutte",
                        "Mark",
                        "m",
                        "`s-Gravenhage"
                )
        );
        candidates.add(
                new Candidate(
                        "2",
                        "Hennis-Plasschaert",
                        "Jeanine",
                        "v",
                        "`s-Gravenhage"
                )
        );

        return candidates;
    }

}
