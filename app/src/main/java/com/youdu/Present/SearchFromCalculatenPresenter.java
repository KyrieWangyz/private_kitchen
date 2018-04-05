package com.youdu.Present;

import android.util.Log;

import com.youdu.fragment.searchfragments.MaterialFromCalculatenFragment;
import com.youdu.module.recommand.ResultBeannModel;
import com.youdu.module.recommand.SearchModel;
import com.youdu.network.http.RequestCenter;
import com.youdu.okhttp.listener.DisposeDataListener;

/**
 * Created by kyrie on 2017/10/27.
 */

public class SearchFromCalculatenPresenter { private ResultBeannModel model;
    private SearchModel searchModel;
    private MaterialFromCalculatenFragment materialFragment;
    public SearchFromCalculatenPresenter(MaterialFromCalculatenFragment materialFragment) {
        this.searchModel = new SearchModel();
        this.materialFragment=materialFragment;
    }

//        public void searchResult(String keywords){
//            searchModel.getSearchResult(new Callback<Article<ResultBean>>() {
//                @Override
//                public void onResponse(Call<Article<ResultBean>> call, Response<Article<ResultBean>> response) {
//                    Log.d("aaaaaa","aaaaaaa");
//                    materialFragment.initResultData(response.body().getResult());
//                }
//
//                @Override
//                public void onFailure(Call<Article<ResultBean>> call, Throwable t) {
//                    Log.d("aaaaaa",t.getMessage());
//                }
//            },keywords);
//        }

    public void requestRecommandData(String keywords) {

        RequestCenter.requestsearchresult(keywords,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {//完成真正的功能逻辑
                model = (ResultBeannModel) responseObj;
                //更新UI
                showSuccessView();

            }

            @Override
            public void onFailure(Object reasonObj) {
                //显示请求失败View
                showErrorView();

            }
        });




    }
    private void showSuccessView() {

//        String nu=model.result.get(0).img;
//
//        HotvegetablenModel modeln=(HotvegetablenModel) model.result.get(0);


        if(model !=null){
//            for (int i = 0, len = model.size(); i < len; i++bean.setDifficulty(model.result.get(0).difficulty);)

            materialFragment.initResultData(model.result);

//           model.result.get(0).img;
//            bean.setName(model.result.get(0).name);
//            bean.setTime(model.result.get(0).time);
//            bean.setMaterial(model.result.get(0).material);

        }
        else {
            showErrorView();
        }

    }

    private void showErrorView() {
        Log.d("lodd", "showSuccessView: hjfj91919515");

    }
}

