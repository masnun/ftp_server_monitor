package com.masnun.ftpservermonitor;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.masnun.ftpservermonitor.adapters.TorrentListAdapter;
import com.masnun.ftpservermonitor.entities.TorrentItem;
import com.masnun.ftpservermonitor.utils.TorrentFetcher;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<TorrentItem> list = TorrentFetcher.fetchURLs();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ListView listView = (ListView) findViewById(R.id.list_view);
                        listView.setAdapter(new TorrentListAdapter(MainActivity.this, list));

                    }
                });
            }
        }).start();
    }
}
