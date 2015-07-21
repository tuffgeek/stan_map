package com.digitalbabies.traafik;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.navdrawer.SimpleSideDrawer;

/**
 * Created by ankit on 15/7/15.
 */
public class Drawer {

    private SimpleSideDrawer nav;
    private Context context;

    public Drawer(final Activity avt) {
        // TODO Auto-generated constructor stub
        this.nav = new SimpleSideDrawer(avt);
        this.context = avt;
        nav.setLeftBehindContentView(R.layout.sidebar);

        avt.findViewById(R.id.sidebar).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        nav.toggleLeftDrawer();
                    }
                });

        nav.findViewById(R.id.Mytrafik).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (nav.isClosed() == true) {

                        } else {

                            Intent in = new Intent(avt,Profile.class);
                            avt.startActivity(in);
                            avt.overridePendingTransition(0, 0);

                        }
                    }
                });

        nav.findViewById(R.id.Setting).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (nav.isClosed() == true) {

                        } else {

                            Intent in = new Intent(avt,Setting.class);
                            avt.startActivity(in);
                            avt.overridePendingTransition(0, 0);

                        }
                    }
                });

        nav.findViewById(R.id.logout).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (nav.isClosed() == true) {

                        } else {

                            Toast.makeText(avt.getApplicationContext(),"hii",Toast.LENGTH_LONG).show();

                        }
                    }
                });


        nav.setRightBehindContentView(R.layout.menu);

        avt.findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.toggleRightDrawer();
            }
        });

        nav.findViewById(R.id.rel5).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (nav.isClosed() == true) {

                        } else {

                           /* Intent in = new Intent(avt,Mapchat.class);
                            avt.startActivity(in);
                            avt.overridePendingTransition(0, 0);*/

                            Toast.makeText(avt.getApplicationContext(),"hiichatt",Toast.LENGTH_LONG).show();

                        }
                    }
                });


    }




}
