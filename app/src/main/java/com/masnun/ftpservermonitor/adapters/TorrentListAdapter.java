package com.masnun.ftpservermonitor.adapters;


import android.content.Context;
import android.database.DataSetObserver;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.masnun.ftpservermonitor.R;
import com.masnun.ftpservermonitor.entities.TorrentItem;

import java.util.List;

public class TorrentListAdapter extends ArrayAdapter {
    private final Context context;
    private final List<TorrentItem> values;

    public TorrentListAdapter(Context context, List<TorrentItem> values) {
        super(context, R.layout.torrent_list, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.torrent_list, parent, false);

        TextView nameTextView = (TextView) rowView.findViewById(R.id.label);
        TextView modifiedTextView = (TextView) rowView.findViewById(R.id.modified);

        TorrentItem torrentItem = values.get(position);

        nameTextView.setText(torrentItem.getName());
        modifiedTextView.setText(Html.fromHtml("<b>Downloaded:</b> " + torrentItem.getModified()));

        return rowView;
    }
}
