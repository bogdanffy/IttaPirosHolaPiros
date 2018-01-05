package com.example.bogdan.ittapirosholapiros;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

/**
 * Created by Vadek on 2017.12.25..
 */

public class JatekActivity extends AppCompatActivity {

    private ImageView kartya1, kartya2, kartya3;
    private Button ujJatek;
    private int nyertesLap;
    private boolean isClicked;
    private Point kozepsoLapHelyzet;
    private ObjectAnimator translateAnimationKartya1, translateAnimationKartya2;
    private AnimatorSet setAnimation;
    private JatekDBHandler dbHandler;
    private int maxPontSzam;
    private Pontszamok pontszamok;
    private String maxPt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jatek);
        lapKeveres();
        kepek();
        keveresAnim();
        ujJatek = (Button) findViewById(R.id.ujJatek_btn);
        dbHandler = new JatekDBHandler(this, null, null, 1);
        maxPontSzam = 0;





        setAnimation.play(translateAnimationKartya1).with(translateAnimationKartya2);


        kartya1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked = true;
                isClickable();


                if(nyertesLap == 1){
                    kartya1.setImageResource(R.drawable.elolap1);
                    maxPontSzam++;
                }
                else{
                    kartya1.setImageResource(R.drawable.elolap2);
                    if(maxPontSzam != 0){
                        maxPt = maxPontSzam + " Pont";
                        pontszamok = new Pontszamok(maxPt);
                        dbHandler.addPontszam(pontszamok);
                    }
                }
            }
        });

        kartya2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked = true;
                isClickable();

                if(nyertesLap == 2){
                    kartya2.setImageResource(R.drawable.elolap1);
                    maxPontSzam++;
                }
                else{
                    kartya2.setImageResource(R.drawable.elolap2);
                    if(maxPontSzam != 0){
                        maxPt = maxPontSzam + " Pont";
                        pontszamok = new Pontszamok(maxPt);
                        dbHandler.addPontszam(pontszamok);
                    }
                }
            }
        });

        kartya3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked = true;
                isClickable();

                if(nyertesLap == 3){
                    kartya3.setImageResource(R.drawable.elolap1);
                    maxPontSzam++;
                }
                else{
                    kartya3.setImageResource(R.drawable.elolap2);
                    if(maxPontSzam != 0){
                        maxPt = maxPontSzam + " Pont";
                        pontszamok = new Pontszamok(maxPt);
                        dbHandler.addPontszam(pontszamok);
                    }
                }
            }
        });

        ujJatek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lapKeveres();
                isClicked = false;
                isClickable();
                setAnimation.play(translateAnimationKartya1).with(translateAnimationKartya2);

                maxPontSzam = 0;

                kartya1.setImageResource(R.drawable.hatter1);
                kartya2.setImageResource(R.drawable.hatter1);
                kartya3.setImageResource(R.drawable.hatter1);
            }
        });

    }

    // ~33% esély a nyerésre.
    private int lapKeveres() {
        Random r = new Random();
        nyertesLap = r.nextInt(4-1)+1 ;

        return nyertesLap;
    }

    private void kepek(){
        kartya1 = (ImageView) findViewById(R.id.elsoKartya_img);
        kartya2 = (ImageView) findViewById(R.id.masodikKartya_img);
        kartya3 = (ImageView) findViewById(R.id.harmadikKartya_img);

    }

    // Vizsgálja, hogy rányomtunk-e kártyára, ha igen, letiltja a többit, különben feloldja a tiltást
    private void isClickable(){
        if (isClicked){
            kartya1.setClickable(false);
            kartya2.setClickable(false);
            kartya3.setClickable(false);
        }
        else{
            kartya1.setClickable(true);
            kartya2.setClickable(true);
            kartya3.setClickable(true);
        }
    }

    // Pozíció lekérdezése
    private Point getHelyzet(View v){
        int[] location = new int[2];
        v.getLocationInWindow(location);
        return new Point(location[0], location[1]);
    }

    // Valamiért nem hajtja végre a program
    // Eredetileg két animáció lenne amik segítségével a két szélső lap "beúszik" a középsővel egy pozícióba,
    // egyfajta keverést szimbolizálva.

    private void keveresAnim(){
        kozepsoLapHelyzet = getHelyzet(kartya2);

        translateAnimationKartya1 =
                ObjectAnimator.ofFloat(kartya1, View.TRANSLATION_X, kozepsoLapHelyzet.y);
        translateAnimationKartya1.setRepeatCount(1);
        translateAnimationKartya1.setRepeatMode(ValueAnimator.REVERSE);

        translateAnimationKartya2 =
                ObjectAnimator.ofFloat(kartya1, View.TRANSLATION_X, kozepsoLapHelyzet.y);
        translateAnimationKartya2.setRepeatCount(1);
        translateAnimationKartya2.setRepeatMode(ValueAnimator.REVERSE);

        setAnimation = new AnimatorSet();
    }

    @Override
    public void onBackPressed() {
        if(maxPontSzam != 0){
            maxPt = maxPontSzam + " Pont";
            pontszamok = new Pontszamok(maxPt);
            dbHandler.addPontszam(pontszamok);
        }

        Intent gonext = new Intent (JatekActivity.this, MainActivity.class);
        startActivity(gonext);
        finish();
    }

}