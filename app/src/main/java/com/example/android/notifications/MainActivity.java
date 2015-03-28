package com.example.android.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    int notificationID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view) {
        displayNotification();
    }

    private void displayNotification() {
        //pendingIntent per lanciare activity se l'utente seleziona questa notifica
        Intent i = new Intent(this, NotificationView.class);
        i.putExtra("NotificationID", notificationID);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notif = new Notification(
                R.drawable.abc_ic_menu_copy_mtrl_am_alpha,
                "Ricorda che il meeting comincia fra cinque minuti",
                System.currentTimeMillis());

        CharSequence from = "System Alarm";
        CharSequence message = "Meeting con il cliente alle 3pm..";

        notif.setLatestEventInfo(this, from, message, pendingIntent);

        //100ms ritardo, vibrazione per 250ms, pausa per 100ms e poi vibrazione per 500ms
        notif.vibrate = new long[] {100, 250, 100, 500};
        nm.notify(notificationID, notif);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
