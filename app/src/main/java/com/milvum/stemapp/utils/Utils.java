package com.milvum.stemapp.utils;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.TextView;

import com.milvum.stemapp.ConfirmationDialogFragment;
import com.milvum.stemapp.model.Candidate;
import com.milvum.stemapp.model.Vote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


    @NonNull
    public static String getRandomToken() {
        Random random = new Random();
        return String.valueOf(Constants.TOKENS.charAt(random.nextInt(Constants.TOKENS.length())));
    }


    private static List<Vote> savedVotes = new ArrayList();

    public static void clearVotes() {
        savedVotes.clear();
    }

    public static void saveVotes(Vote vote) {
        savedVotes.add(vote);
    }

    public static List<Vote> getVotes() {
        return savedVotes;
    }

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
