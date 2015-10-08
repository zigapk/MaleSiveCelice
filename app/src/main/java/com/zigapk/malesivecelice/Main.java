package com.zigapk.malesivecelice;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class Main extends AppCompatActivity {

    private WebView webView;
    private Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        tracker = application.getDefaultTracker();
        tracker.setScreenName("Main");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Open")
                .setLabel(getOwnersName())
                .build());

        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onResume(){
        super.onResume();
        String url = Settings.getUrl(getApplicationContext());
        if(url == null){
            startActivity(new Intent(this, Settings.class));
        }else {
            webView.loadUrl(url);
            tracker.send(new HitBuilders.EventBuilder()
                    .setCategory("Action")
                    .setAction("Load")
                    .setLabel(url)
                    .build());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            startActivity(new Intent(this, Settings.class));
        } else if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) webView.goBack();
                    else finish();
                    return true;
            }

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, Settings.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private String getOwnersName() {
        Cursor c = getApplication().getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
        c.moveToFirst();
        return c.getString(c.getColumnIndex("display_name"));
    }
}
