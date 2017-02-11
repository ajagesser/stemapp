package com.milvum.stemapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.milvum.stemapp.R;
import com.milvum.stemapp.model.Party;

import java.util.List;

/**
 * Created by Randy Tjin Asjoe on 10/02/2017.
 */

public class PartyAdapter extends ArrayAdapter<Party> {

    private int mResource;

    public PartyAdapter(Context context, int resource, List<Party> objects) {
        super(context, resource, objects);

        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Party party = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);
        }
        // Lookup view for data population
        TextView nameView = (TextView) convertView.findViewById(R.id.party_name);
        TextView listNumView = (TextView) convertView.findViewById(R.id.party_num);
        ImageView partyIconView = (ImageView) convertView.findViewById(R.id.party_image);
        // Populate the data into the template view using the data object
        nameView.setText(party.getName());
        listNumView.setText("" + party.getPosition());
        partyIconView.setImageResource(party.getIcon());
        // Return the completed view to render on screen
        return convertView;

    }
}
