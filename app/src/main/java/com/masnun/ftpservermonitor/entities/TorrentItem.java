package com.masnun.ftpservermonitor.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TorrentItem implements Comparable {
    public String name;
    public String modified;

    public TorrentItem(String name, String modified) {
        this.name = name;
        this.modified = modified;
    }

    public TorrentItem() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    @Override
    public int compareTo(Object o) {
        TorrentItem other = (TorrentItem) o;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        try {
            Date myDate = format.parse(getModified());
            Date otherDate = format.parse(other.getModified());

            return myDate.after(otherDate) ? -1 : 1;
        } catch (ParseException e) {
            return 0;
        }

    }
}


