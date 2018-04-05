package com.youdu.imageloder;

import android.graphics.Bitmap;

/**
 * Created by kyrie on 2017/10/3.
 */

public interface BitmapLoadingListener {
    void onSuccess(Bitmap b);

    void onError();
}
