package com.masnun.ftpservermonitor.utils;


import com.masnun.ftpservermonitor.entities.TorrentItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TorrentFetcher {
    public final static String SERVER_URL = "http://103.54.39.133/";

    public static List<TorrentItem> fetchURLs() {

        ArrayList<TorrentItem> list = new ArrayList<>();

        try {
            Document document = Jsoup.connect(SERVER_URL).get();
            Elements elements = document.select("a");

            for (Element el : elements) {
                String name = el.attr("data-name");
                if (!name.equals("")) {
                    TorrentItem torrentItem = new TorrentItem();
                    torrentItem.setName(name);

                    // Find modified date
                    Elements modifiedSpans = el.select("span.file-modified");
                    if (modifiedSpans.size() > 0) {
                        Element span = modifiedSpans.first();
                        String modfiedDate = span.text();

                        if (!modfiedDate.isEmpty()) {
                            torrentItem.setModified(modfiedDate);
                        } else {
                            torrentItem.setModified("Found Span but no!");
                        }
                    } else {
                        torrentItem.setModified("No Spans!");
                    }

                    list.add(torrentItem);

                }

            }


        } catch (Exception ex) {
            System.out.println(ex);
        }

        Collections.sort(list);

        return list;
    }
}
