package com.jacr.gravityapp.utilities.helpers;

import android.content.Context;
import android.os.Build;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Jesus Castro on 11/21/2015.
 */
public class ViewHelper {

    public static void removeGlobalLayoutListener(ViewTreeObserver observer,
                                                  ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            observer.removeOnGlobalLayoutListener(listener);
        } else {
            //noinspection deprecation
            observer.removeGlobalOnLayoutListener(listener);
        }
    }

    public static void loadPicture(Context context, ImageView imageView, String imageUrl,
                                   int cornerRadius) {
        // Picasso supports cache and image processing, e.g, rounding its corners
        Picasso.with(context).load(imageUrl)
                .error(android.R.drawable.ic_dialog_alert)
                .transform(new PictureRoundedTransformation(cornerRadius, 0))
                .fit()
                .into(imageView);
    }

}

