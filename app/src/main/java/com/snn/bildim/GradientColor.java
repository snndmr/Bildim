package com.snn.bildim;

import android.graphics.drawable.GradientDrawable;

class GradientColor {

    static final GradientDrawable RED
            = new GradientDrawable(GradientDrawable.Orientation.BL_TR,
            new int[]{android.graphics.Color.parseColor("#EF5350"),
                    android.graphics.Color.WHITE, android.graphics.Color.WHITE});

    static final GradientDrawable GRAY
            = new GradientDrawable(GradientDrawable.Orientation.BL_TR,
            new int[]{android.graphics.Color.parseColor("#BDBDBD"),
                    android.graphics.Color.WHITE, android.graphics.Color.WHITE});

    static final GradientDrawable GREEN
            = new GradientDrawable(GradientDrawable.Orientation.BL_TR,
            new int[]{android.graphics.Color.parseColor("#9CCC65"),
                    android.graphics.Color.WHITE, android.graphics.Color.WHITE});

    static final GradientDrawable YELLOW
            = new GradientDrawable(GradientDrawable.Orientation.BL_TR,
            new int[]{android.graphics.Color.parseColor("#FAD543"),
                    android.graphics.Color.WHITE, android.graphics.Color.WHITE});
}
