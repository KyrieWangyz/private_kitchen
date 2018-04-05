package com.youdu.fragment.myFragment;

/**
 * Created by cleverlin on 2017/8/11.
 */

public class FragmentInfo {
    private Class fragment;


    private String title;
    public FragmentInfo(Class fragment) {
        this.fragment = fragment;
    }
    public FragmentInfo(String title, Class fragment) {
        this.title=title;
        this.fragment = fragment;
    }
    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
