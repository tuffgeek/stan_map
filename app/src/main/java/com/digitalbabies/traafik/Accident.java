package com.digitalbabies.traafik;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

/**
 * Created by ankit on 22/7/15.
 */
public class Accident extends Activity{

    final CharSequence[] accidentitem = {"Minor","Major"};
    Object checkedItem,sidevalue;
    AlertDialog levelDialog;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accident);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setSingleChoiceItems(accidentitem, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                ListView lw = ((AlertDialog) dialog).getListView();
                // 1
                checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());

                if (checkedItem.equals("Minor")) {


                } else if (checkedItem.equals("Major")) {


                }
                Log.d("Listtt valueee", "" + checkedItem);
                levelDialog.dismiss();
            }

        });

        levelDialog = builder.create();
        levelDialog.show();

    }
}
