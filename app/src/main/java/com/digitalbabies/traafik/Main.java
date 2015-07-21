package com.digitalbabies.traafik;

import android.app.Application;
import android.util.Log;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

/**
 * Created by ankit on 17/7/15.
 */


@ReportsCrashes( // will not be used
        mailTo = "tuffgeekers@gmail.com", // my email here
       // mailTo = "er.ankitsharma15@gmail.com",
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.crash_toast_text)
public class Main  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        // The following line triggers the initialization of ACRA
        ACRA.init(this);
        Log.d("Acra00","Acra00");
    }
}
