package com.milvum.stemapp.utils;

import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Randy Tjin Asjoe on 2/11/2017.
 */

public class Utils {
    private Utils() {

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


}
