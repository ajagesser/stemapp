package com.milvum.stemapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.milvum.stemapp.R;
import com.milvum.stemapp.model.Candidate;

import java.util.List;

/**
 * Created by Randy Tjin Asjoe on 10/02/2017.
 */

public class CandidateAdapter extends ArrayAdapter<Candidate> {

    private int mResource;

    public CandidateAdapter(Context context, int resource, List<Candidate> objects) {
        super(context, resource, objects);

        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Candidate candidate = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);
        }
        // Lookup view for data population
        TextView numView = (TextView) convertView.findViewById(R.id.candidate_number);
        TextView lastNameView = (TextView) convertView.findViewById(R.id.candidate_last_name);
        TextView firstLetterView = (TextView) convertView.findViewById(R.id.candidate_first_letter);
        TextView firstNameView = (TextView) convertView.findViewById(R.id.candidate_first_name);
        TextView genderView = (TextView) convertView.findViewById(R.id.candidate_gender);
        TextView cityView = (TextView) convertView.findViewById(R.id.candidate_city);

        // Populate the data into the template view using the data object
        numView.setText("" + candidate.getId());
        lastNameView.setText(candidate.getLastName());
        firstLetterView.setText(candidate.getFirstLetter());
        firstNameView.setText(candidate.getFirstName());
        genderView.setText(candidate.getGender());
        cityView.setText(candidate.getCity());

        // Return the completed view to render on screen
        return convertView;
    }
}
