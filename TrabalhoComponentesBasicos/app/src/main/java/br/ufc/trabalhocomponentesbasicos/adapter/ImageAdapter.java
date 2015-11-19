package br.ufc.trabalhocomponentesbasicos.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import br.ufc.trabalhocomponentesbasicos.R;

/**
 * Created by alexsandro on 19/11/15.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public static final int BACKGROUNDS[] = {R.raw.material1, R.raw.material2, R.raw.material3, R.raw.material4};

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return BACKGROUNDS.length;
    }

    public Object getItem(int position) {
        return BACKGROUNDS[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(320, 320));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(BACKGROUNDS[position]);
        return imageView;
    }

}
