package com.milvum.stemapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;

public class HomeActivity extends AppCompatActivity {

    private AsyncHttpClient client;
    private AlertDialog dialog;
    private AlertDialog alertDialog;

    private String local = "http://localhost";
    private String remote = "http://staging.milvum.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button loginButton = (Button) this.findViewById(R.id.loginButton);

        client = new AsyncHttpClient();
        dialog = new SpotsDialog(this);
        alertDialog = new AlertDialog.Builder(this).create();

        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        verifyPerson();
                    }
                }
        );
    }

    private void verifyPerson() {

        RequestParams params = new RequestParams();
        params.put("id", 1); //todo hardcoded change per build

        client.post(remote + ":3000/api/verifyPerson", params ,new AsyncHttpResponseHandler() {


            @Override
            public void onStart() {
                // called before request is started
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                cashStempas();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                dialog.dismiss();

                alertDialog.setTitle("Error");
                alertDialog.setMessage("Probeer het aub opnieuw. Error:" + statusCode);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogIn, int which) {
                                dialogIn.dismiss();
                            }
                        });
                alertDialog.show();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

    private void cashStempas() {

        RequestParams params = new RequestParams();
        params.put("tx", 1); //todo hardcoded change per build
        params.put("stempas_id", 1); //todo hardcoded change per build

        client.post(remote + ":3000/api/cashStempas", params ,new AsyncHttpResponseHandler() {


            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                dialog.dismiss();
                Intent i = new Intent(HomeActivity.this, PartyListActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                dialog.dismiss();

                alertDialog.setTitle("Error");
                alertDialog.setMessage("Probeer het aub opnieuw. Error:" + statusCode);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogIn, int which) {
                                dialogIn.dismiss();
                            }
                        });
                alertDialog.show();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
}

