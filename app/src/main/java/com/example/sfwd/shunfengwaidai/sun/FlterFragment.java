package com.example.sfwd.shunfengwaidai.sun;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.sfwd.shunfengwaidai.InfoList;
import com.example.sfwd.shunfengwaidai.R;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlterFragment extends Fragment {
    private TagWidget[] mTAGWidget1,mTAGWidget2,mTAGWidget3,mTAGWidget4;
    private EditText mMinPriceEdt, mMaxPriceEdt;
    private EditText mMinTimeEdt, mMaxTimeEdt;
    private EditText mMinDistanceEdt, mMaxDistanceEdt;
    HashMap<String, String> infomation;
    InfoList infoList;

    private OnSearchListener mSearchCallback;
    public static final String SEPARATOR = "@";
    public interface OnSearchListener {
        void onFilterCompleted(HashMap<String, String> infomation);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_flter, container, false);
        initView(view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }
    public void setSearchCallbackListener(OnSearchListener callbackListener) {
        this.mSearchCallback = callbackListener;
    }
    private void initView(View view) {
            infoList=new InfoList();
        mTAGWidget1 = new TagWidget[4];
        mTAGWidget2 = new TagWidget[4];
        mTAGWidget3 = new TagWidget[4];
        mTAGWidget4 = new TagWidget[4];
        mTAGWidget1[0] = (TagWidget) view.findViewById(R.id.tag1);
        mTAGWidget1[1] = (TagWidget) view.findViewById(R.id.tag2);
        mTAGWidget1[2] = (TagWidget) view.findViewById(R.id.tag3);
        mTAGWidget1[3] = (TagWidget) view.findViewById(R.id.tag4);
        mTAGWidget2[0] = (TagWidget) view.findViewById(R.id.tag5);
        mTAGWidget2[1] = (TagWidget) view.findViewById(R.id.tag6);
        mTAGWidget2[2] = (TagWidget) view.findViewById(R.id.tag7);
        mTAGWidget2[3] = (TagWidget) view.findViewById(R.id.tag8);
        mTAGWidget3[0] = (TagWidget) view.findViewById(R.id.tag9);
        mTAGWidget3[1] = (TagWidget) view.findViewById(R.id.tag10);
        mTAGWidget3[2] = (TagWidget) view.findViewById(R.id.tag11);
        mTAGWidget3[3] = (TagWidget) view.findViewById(R.id.tag12);
        mTAGWidget4[0] = (TagWidget) view.findViewById(R.id.tag13);
        mTAGWidget4[1] = (TagWidget) view.findViewById(R.id.tag14);
        mTAGWidget4[2] = (TagWidget) view.findViewById(R.id.tag15);
        mTAGWidget4[3] = (TagWidget) view.findViewById(R.id.tag16);

        mMinPriceEdt = (EditText) view.findViewById(R.id.min_price_edt);
        mMaxPriceEdt = (EditText) view.findViewById(R.id.max_price_edt);
        mMinTimeEdt = (EditText) view.findViewById(R.id.min_time_edt);
        mMaxTimeEdt = (EditText) view.findViewById(R.id.max_time_edt);
        mMinDistanceEdt = (EditText) view.findViewById(R.id.min_distance_edt);
        mMaxDistanceEdt = (EditText) view.findViewById(R.id.max_distance_edt);
        this.setTAGWidgetsContent();

        setParams(view);
    }
    /**
     * 设置顶部标签内容
     */
    private void setTAGWidgetsContent() {
        String[] contents1 = {"取快递", "取外卖", "代买", "紧急"};
        String[] contents2={"$1","$2","$3","$5"};
        String[] contents3={"0.5km","1km","2km","3km"};
        String[] contents4={"10","20","30","60"};
        for (int i = 0; i < 4; i++) {
            mTAGWidget1[i].setContent(contents1[i]);
            mTAGWidget2[i].setContent(contents2[i]);
            mTAGWidget3[i].setContent(contents3[i]);
            mTAGWidget4[i].setContent(contents4[i]);
        }
    }
    /**
     * 初始化页面数据，并设置事件
     */
    private void setParams(View view) {
        for (final TagWidget widget : mTAGWidget1) {
            widget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToggle(mTAGWidget1);
                    widget.toggle();
                }
            });
        }
        for (final TagWidget widget : mTAGWidget2) {
            widget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToggle(mTAGWidget2);
                    widget.toggle();
                }
            });
        }
        for (final TagWidget widget : mTAGWidget3) {
            widget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToggle(mTAGWidget3);
                    widget.toggle();
                }
            });
        }
        for (final TagWidget widget : mTAGWidget4) {
            widget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToggle(mTAGWidget4);
                    widget.toggle();
                }
            });
        }
        view.findViewById(R.id.reset_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFilter();
            }
        });
        view.findViewById(R.id.confirm_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterConfirm();
            }
        });
    }
    public void clearToggle(TagWidget[] mTAGWidget){
        for(final TagWidget widget : mTAGWidget){
            widget.setStatus(true);
            widget.toggle();
        }
    }

    /**
     * 重置筛选条件
     */
    private void resetFilter() {
        clearToggle(mTAGWidget1);
        clearToggle(mTAGWidget2);
        clearToggle(mTAGWidget3);
        clearToggle(mTAGWidget4);
        mMinPriceEdt.setText("");
        mMaxPriceEdt.setText("");
        mMinTimeEdt.setText("");
        mMaxTimeEdt.setText("");
        mMinDistanceEdt.setText("");
        mMaxDistanceEdt.setText("");
    }
    /**
     * 确认筛选条件
     */
    private void filterConfirm() {
        infomation=getFilter();
        if (mSearchCallback == null) {
            return;
        }
        mSearchCallback.onFilterCompleted(infomation);
        resetFilter();
    }
    /**
     * 获取筛选信息
     */
    public HashMap<String, String> getFilter() {
        HashMap<String, String> filterParams = new HashMap<>();
        String style = getFilter(mTAGWidget1);
        String price=getFilter(mTAGWidget2);
        String distance=getFilter(mTAGWidget3);
        String time=getFilter(mTAGWidget4);
        if (style!=null) {
            filterParams.put("style", style);
        }
        if (price!=null) {
            filterParams.put("price", price);
        }
        if(distance!=null){
            filterParams.put("distance",distance);
        }
        if (time!=null) {
            filterParams.put("time", time);
        }
        filterParams.put("priceSec",getSectionFilter(mMinPriceEdt,mMaxPriceEdt));
        filterParams.put("distanceSec",getSectionFilter(mMinDistanceEdt,mMaxDistanceEdt));
        filterParams.put("timeSec",getSectionFilter(mMinTimeEdt,mMaxTimeEdt));
        return filterParams;
    }
    /**
     * 获取筛选信息
     */
    private String getFilter(TagWidget[] mTAGWidget) {
        String string=null;
        for(final TagWidget widget : mTAGWidget){
            if(widget.getStatus()==true){
                string= widget.getContent();
            }
        }
        return string;
    }
    /**
     * 获取区间筛选信息
     */
    public String getSectionFilter(EditText mEdt1,EditText mEdt2) {
        String minPrice = mEdt1.getText().toString().trim();
        String maxPrice = mEdt2.getText().toString().trim();
        if (minPrice.length() == 0 | maxPrice.length() == 0)
            return null;
        else
            return minPrice + SEPARATOR + maxPrice;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSearchListener) {
            mSearchCallback= (OnSearchListener) context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mSearchCallback = null;
    }

}
