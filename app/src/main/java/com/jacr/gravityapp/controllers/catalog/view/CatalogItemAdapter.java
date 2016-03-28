package com.jacr.gravityapp.controllers.catalog.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jacr.gravityapp.R;
import com.jacr.gravityapp.model.api.dtos.feed.app.AppInfo;
import com.jacr.gravityapp.utilities.helpers.ViewHelper;

import java.util.List;

/**
 * Created by Jesus on 11/22/2015.
 */
public class CatalogItemAdapter extends RecyclerView.Adapter<CatalogItemAdapter.ViewHolder> {

    //<editor-fold desc="Variables">

    private Context context;
    private List<AppInfo> items;

    //</editor-fold>

    // <editor-fold desc="View Holder">

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView appNameTextView;
        TextView authorTextView;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            appNameTextView = (TextView) view.findViewById(R.id.catalog_item_textview_app_name);
            authorTextView = (TextView) view.findViewById(R.id.catalog_item_textview_app_author);
            imageView = (ImageView) view.findViewById(R.id.catalog_item_image);
        }

    }

    //</editor-fold>

    public CatalogItemAdapter(Context context, List<AppInfo> items) {
        this.context = context;
        this.items = items;
    }

    //<editor-fold desc="Adapter Overrides">

    // Create new views (invoked by the layout manager)
    @Override
    public CatalogItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppInfo appInfo = items.get(position);
        String imageUrl = appInfo.getSmallThumbnailUrl();
        ViewHelper.loadPicture(context, holder.imageView, imageUrl, 20);
        holder.appNameTextView.setText(appInfo.getName());
        holder.authorTextView.setText(appInfo.getAuthorName());
        holder.appNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //</editor-fold>

}
