package com.youdu.fragment.myFragment;


import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.youdu.R;
import com.youdu.adapter.CourseAdapter;
import com.youdu.adapter.RollViewPagerAdapter;
import com.youdu.fragment.BaseFragment;
import com.youdu.module.recommand.BaseRecommandModel;

import java.util.ArrayList;
import java.util.List;







public class HomeFragment extends BaseFragment implements View.OnClickListener  {

    private RollPagerView mRollPagerView;
    private View view;
    private ViewPager mPage;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private TextView getExpress;
    private TextView history;
    private View express_line;
    private View history_line;
    private RelativeLayout getExp_layout;
    private RelativeLayout history_layout;

    private CourseAdapter cAdapter;
    private BaseRecommandModel mRecommandData;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        init();

        mAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        mPage.setAdapter(mAdapter);
        mPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem=mPage.getCurrentItem();
                switch (position){
                    case 0:
                        getExpress.setTextColor(0xFF00A9FF);
                        express_line.setBackgroundColor(0xFF00A9FF);
                        express_line.setVisibility(View.VISIBLE);
                        history.setTextColor(0xFF999999);
                        history_line.setBackgroundColor(0xFF999999);
                        history_line.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        history.setTextColor(0xFF00A9FF);
                        history_line.setBackgroundColor(0xFF00A9FF);
                        history_line.setVisibility(View.VISIBLE);
                        getExpress.setTextColor(0xFF999999);
                        express_line.setBackgroundColor(0xFF999999);
                        express_line.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //mPage.setOnPageChangeListener(

        //);

        setSelect(0);
        mRollPagerView = (RollPagerView) view.findViewById(R.id.rollviewpager);
        mRollPagerView.setAnimationDurtion(500);//设置切换时间
        mRollPagerView.setAdapter(new RollViewPagerAdapter(mRollPagerView));




        //mRollPagerView.setHintView(new ColorPointHintView(getContext().getApplicationContext(), Color.WHITE,Color.GRAY));
        // Inflate the layout for this fragment
        return view;
    }

    private void init() {
        mPage = (ViewPager) view.findViewById(R.id.viewPager);
        getExpress = (TextView) view.findViewById(R.id.getExp_text);
        history = (TextView) view.findViewById(R.id.histroy_text);
        express_line = view.findViewById(R.id.getExp_line);
        history_line = view.findViewById(R.id.histroy_line);
        getExp_layout = (RelativeLayout) view.findViewById(R.id.getExp_layout);
        getExp_layout.setOnClickListener(this);
        history_layout = (RelativeLayout) view.findViewById(R.id.histroy_layout);
        history_layout.setOnClickListener(this);

        mFragments = new ArrayList<Fragment>();
        GetExpressFragment getExpressFragment = new GetExpressFragment();
        HistoryFragment historyFragment = new HistoryFragment();

        mFragments.add(historyFragment);
        mFragments.add(getExpressFragment);
//        mFragments = new ArrayList<Fragment>();
//        MessageFragment messageFragment = new MessageFragment();
//        HistoryFragment historyFragment = new HistoryFragment();
//
//        mFragments.add(historyFragment);
//        mFragments.add(messageFragment);



    }

    private void setSelect(int i){

        switch (i){
            case 0:
                getExpress.setTextColor(0xFF00A9FF);
                express_line.setBackgroundColor(0xFF00A9FF);
                express_line.setVisibility(View.VISIBLE);
                history.setTextColor(0xFF999999);
                history_line.setBackgroundColor(0xFF999999);
                history_line.setVisibility(View.INVISIBLE);
                break;
            case 1:
                history.setTextColor(0xFF00A9FF);
                history_line.setBackgroundColor(0xFF00A9FF);
                history_line.setVisibility(View.VISIBLE);
                getExpress.setTextColor(0xFF999999);
                express_line.setBackgroundColor(0xFF999999);
                express_line.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
        mPage.setCurrentItem(i);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getExp_layout:
                setSelect(0);
                break;
            case R.id.histroy_layout:
                setSelect(1);
                break;
        }
    }







    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
