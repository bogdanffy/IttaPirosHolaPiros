package com.example.bogdan.ittapirosholapiros;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ujJatekButton, rekordButton, nevjegyButton, kilepesButton;
    final Context contex = this;
    private JatekDBHandler dbHandler;
    private String dbString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new JatekDBHandler(this, null, null, 1);

        ujJatekButton = (Button) findViewById(R.id.ujJatek_btn);
        rekordButton = (Button) findViewById(R.id.rekord_btn);
        nevjegyButton = (Button) findViewById(R.id.nevjegy_btn);
        kilepesButton = (Button) findViewById(R.id.kilepes_btn);


        ujJatekButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gonext = new Intent (MainActivity.this, JatekActivity.class);
                startActivity(gonext);
                finish();
            }
        });

        rekordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dbString += dbHandler.adatbazisToString();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        contex);

                // set title
                alertDialogBuilder.setTitle("Legjobb eredmények");

                // set dialog message
                alertDialogBuilder
                        .setMessage(dbString)
                        .setCancelable(false)
                        .setNegativeButton("Vissza",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        nevjegyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        contex);

                // set title
                alertDialogBuilder.setTitle("Készítette:");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Bogdánffy Bogdán 14.P")
                        .setCancelable(false)
                        .setNegativeButton("Vissza",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        kilepesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        contex);

                // set title
                alertDialogBuilder.setTitle("Kilépés");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Biztosan ki akar lépni?")
                        .setCancelable(false)
                        .setPositiveButton("Igen",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                MainActivity.this.finish();
                            }
                        })
                        .setNegativeButton("Nem",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                contex);

        // set title
        alertDialogBuilder.setTitle("Kilépés");

        // set dialog message
        alertDialogBuilder
                .setMessage("Biztosan ki akar lépni?")
                .setCancelable(false)
                .setPositiveButton("Igen",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Nem",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}
