package com.example.lorealerick.smartfridge2.Utils;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by LoreAleRick on 09/03/2018.
 */

public class RecyclerDivider extends RecyclerView.ItemDecoration {

    private int mItemOffset;

    public RecyclerDivider(int itemOffset) {

        mItemOffset = itemOffset;
    }

    public RecyclerDivider(Context context, int itemOffsetId) {

        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        super.getItemOffsets(outRect, view, parent, state);

        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
    }

}