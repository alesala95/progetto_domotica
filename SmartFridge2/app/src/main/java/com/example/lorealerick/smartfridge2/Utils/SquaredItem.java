package com.example.lorealerick.smartfridge2.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by itsadmin on 19/03/2018.
 */

public class SquaredItem extends ImageView {

    public SquaredItem(Context context) {
        super(context);
    }

    public SquaredItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquaredItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }
}
