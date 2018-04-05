package com.youdu.Progress;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.youdu.R;

import java.io.IOException;

/**
 * Created by cleverlin on 2016/12/21.音效
 */

public class Music {
    private SoundPool sp;
    private int musss;
    private Context mcontext;
    public Music(Context context)
    {
        mcontext=context;
        sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        musss = sp.load(mcontext, R.raw.buttonmusic, 1);
    }
    public void playMusic(boolean play) throws IOException {
        if(play)
        {
            sp.play(musss, 1, 1, 0, 0, 1);
        }
    }
}
