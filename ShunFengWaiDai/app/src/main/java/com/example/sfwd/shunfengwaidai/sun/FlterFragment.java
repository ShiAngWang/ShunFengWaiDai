package com.example.sfwd.shunfengwaidai.sun;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sfwd.shunfengwaidai.R;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlterFragment extends Fragment {
    private TagWidget[] mTAGWidget;
    private EditText mMinPriceEdt, mMaxPriceEdt;
    private EditText mMinTimeEdt, mMaxTimeEdt;
    private EditText mMinDistanceEdt, mMaxDistanceEdt;

    private ListView mListView;
//    private MenuAdapter mMenuAdapter;
    private FilterInfo mFilterInfo;

    private OnSearchListener mSearchCallback;
    public static final String SEPARATOR = "@";
    public interface OnSearchListener {
        void onFilterCompleted();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flter, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }
    public void setSearchCallbackListener(OnSearchListener callbackListener) {
        this.mSearchCallback = callbackListener;
    }
    /**
     * 设置菜单的内容
     *
     * FilterInfo 菜单内容所持有的数据类型。
     */
//    public void setMenuData(FilterInfo FilterInfo) {
//        this.mFilterInfo = FilterInfo;
//        mMenuAdapter.setData(mFilterInfo);
//    }
    private void initView(View view) {
        mTAGWidget = new TagWidget[4];
        mTAGWidget[0] = (TagWidget) view.findViewById(R.id.tag1);
        mTAGWidget[1] = (TagWidget) view.findViewById(R.id.tag2);
        mTAGWidget[2] = (TagWidget) view.findViewById(R.id.tag3);
        mTAGWidget[3] = (TagWidget) view.findViewById(R.id.tag4);

        mMinPriceEdt = (EditText) view.findViewById(R.id.min_price_edt);
        mMaxPriceEdt = (EditText) view.findViewById(R.id.max_price_edt);
        mMinTimeEdt = (EditText) view.findViewById(R.id.min_time_edt);
        mMaxTimeEdt = (EditText) view.findViewById(R.id.max_time_edt);
        mMinDistanceEdt = (EditText) view.findViewById(R.id.min_distance_edt);
        mMaxDistanceEdt = (EditText) view.findViewById(R.id.max_distance_edt);

//        mListView = (ListView) view.findViewById(R.id.filter_list);
//        mMenuAdapter = new MenuAdapter(getContext(), mFilterInfo);
//        mMenuAdapter.setDataNotifyListener(this);
//        mListView.setAdapter(mMenuAdapter);

        setTAGWidgetsContent();

        setParams(view);
    }
    /**
     * 设置顶部标签内容
     */
    private void setTAGWidgetsContent() {
        String[] contents = {"取快递", "取外卖", "代买", "紧急"};
        for (int i = 0; i < mTAGWidget.length; i++) {
            mTAGWidget[i].setContent(contents[i]);
        }
    }
    /**
     * 初始化页面数据，并设置事件
     */
    private void setParams(View view) {
        for (TagWidget widget : mTAGWidget) {
            widget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.tag1:
                            mTAGWidget[0].toggle();
                            break;
                        case R.id.tag2:
                            mTAGWidget[1].toggle();
                            break;
                        case R.id.tag3:
                            mTAGWidget[2].toggle();
                            break;
                        case R.id.tag4:
                            mTAGWidget[3].toggle();
                            break;
                    }
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

//    public void onDataNotified() {
//        setListViewHeightBasedOnChildren(mListView);
//    }
    /**
     * 修改ListView 的高度.
     */
//    private void setListViewHeightBasedOnChildren(ListView listView) {
//        //获取listView的适配器
//        ListAdapter listAdapter = listView.getAdapter(); //item的高度
//        if (listAdapter == null) {
//            return;
//        }
//        int totalHeight = 0;
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            View listItem = listAdapter.getView(i, null, listView);
//            listItem.measure(0, 0); //计算子项View 的宽高 //统计所有子项的总高度
//            totalHeight += ScreenUtil.dip2px(getContext(), listItem.getMeasuredHeight()) + listView.getDividerHeight();
//        }
//        //TODO 这里计算高度的方式不正确，待修改
//        totalHeight += ScreenUtil.dip2px(getContext(), 150);
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight;
//        listView.setLayoutParams(params);
//    }

//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.tag1:
//                mTAGWidget[0].toggle();
//                break;
//            case R.id.tag2:
//                mTAGWidget[1].toggle();
//                break;
//            case R.id.tag3:
//                mTAGWidget[2].toggle();
//                break;
//            case R.id.tag4:
//                mTAGWidget[3].toggle();
//                break;
//            case R.id.reset_btn:
//                resetFilter();
//                break;
//            case R.id.confirm_btn:
//                filterConfirm();
//                break;
//        }
//    }
    /**
     * 重置筛选条件
     */
    private void resetFilter() {
//        int size = mListView.getCount();
//        for (int i = 0; i < size; i++) {
//            View view = mListView.getChildAt(i);
//            MenuAdapter.ViewHolder viewHolder = (ViewHolder) view.getTag();
//            if (viewHolder.menuAdapter != null) {
//                viewHolder.menuAdapter.clearSelectedIndex();
//            }
//        }
//
//        mMenuAdapter.notifyDataSetChanged();

        for (TagWidget widget : mTAGWidget) {
            if (widget.getStatus()) {
                widget.toggle();
            }
        }

        mMinPriceEdt.setText("");
        mMaxPriceEdt.setText("");
        mMinTimeEdt.setText("");
        mMaxTimeEdt.setText("");
    }
    /**
     * 确认筛选条件
     */
    private void filterConfirm() {
        if (mSearchCallback == null) {
            return;
        }

        mSearchCallback.onFilterCompleted();
    }
    /**
     * 获取筛选信息
     */
    public HashMap<String, String> getFilter() {
        HashMap<String, String> filterParams = new HashMap<>();
        String supplier = getSupplierFilter();
        if (supplier.length() > 0) {
            filterParams.put("supplier", supplier);
        }

        String price = getPriceFilter();
        if (price.length() > 0) {
            filterParams.put("price", price);
        }
        String time = getTimeFilter();
        if (time.length() > 0) {
            filterParams.put("time", time);
        }

//        String spec = getSpecFilter();
//        if (spec.length() > 0) {
//            filterParams.put("spec", spec);
//        }
//
//        String brand = getBrandFilter();
//        if (brand.length() > 0) {
//            filterParams.put("brand", brand);
//        }
        return filterParams;
    }
    /**
     * 获取筛选信息
     */
    private String getSupplierFilter() {
        return "";
    }
    /**
     * 获取价格筛选信息
     */
    public String getPriceFilter() {
        try {
            String minPrice = mMinPriceEdt.getText().toString().trim();
            String maxPrice = mMaxPriceEdt.getText().toString().trim();
            if (minPrice.length() == 0 | maxPrice.length() == 0)
                return "";
            else
                return minPrice + SEPARATOR + maxPrice;
        } catch (Exception e) {
            return "";
        }
    }
    public String getTimeFilter() {
        try {
            String minTime = mMinTimeEdt.getText().toString().trim();
            String maxTime = mMaxTimeEdt.getText().toString().trim();
            if (minTime.length() == 0 | maxTime.length() == 0)
                return "";
            else
                return minTime + SEPARATOR + maxTime;
        } catch (Exception e) {
            return "";
        }
    }
//    /**
//     * 获取参数筛选信息
//     */
//    public String getSpecFilter() {
//        try {
//            int size = mListView.getCount();
//            if (size <= 1)
//                return "";
//
//            StringBuilder strBuild = new StringBuilder();
//
//            for (int i = 1; i < size; i++) {
//                View childView = mListView.getChildAt(i);
//                ViewHolder viewHolder = (ViewHolder) childView.getTag();
//                GridView childGridView = viewHolder.gridView;
//
//                if (childGridView == null) {
//                    continue;
//                }
//
//                GridLayout.Spec spec = mFilterInfo.getFilter_spec().get(i - 1);
//                for (int j = 0; j < childGridView.getCount(); j++) {
//                    View childGridViewItem = childGridView.getChildAt(j);
//                    SubMenuAdapter.ViewHolder gridViewHolder = (SubMenuAdapter.ViewHolder) childGridViewItem.getTag();
//
//                    boolean isSelected = gridViewHolder.widget.getStatus();
//                    if (!isSelected) {
//                        continue;
//                    }
//
//                    String specValue;
//                    try {
//                        //specValue = spec.getItem().get(j).getApp().getSpec();
//                        specValue = spec.getItem().get(j).getId();
//                    } catch (Exception e) {
//                        specValue = "";
//                    }
//
//                    if (specValue != null && specValue.length() > 0) {
//                        strBuild.append(specValue).append(SEPARATOR);
//                    }
//                }
//            }
//
//            String result = "";
//            if (strBuild.length() > SEPARATOR.length()) {
//                int endIndex = strBuild.lastIndexOf(SEPARATOR);
//                result = strBuild.substring(0, endIndex);
//            }
//            return result;
//        } catch (Exception e) {
//            return "";
//        }
//    }
//    /**
//     * 获取品牌筛选信息
//     */
//    public String getBrandFilter() {
//        try {
//            StringBuilder strBuild = new StringBuilder();
//
//            View childView = mListView.getChildAt(0);
//            ViewHolder viewHolder = (ViewHolder) childView.getTag();
//            GridView childGridView = viewHolder.gridView;
//
//            if (childGridView == null) {
//                return "";
//            }
//
//            ArrayList<Brand> brands = mFilterInfo.getFilter_brand();
//            for (int j = 0; j < childGridView.getCount(); j++) {
//                View childGridViewItem = childGridView.getChildAt(j);
//                SubMenuAdapter.ViewHolder gridViewHolder = (SubMenuAdapter.ViewHolder) childGridViewItem.getTag();
//
//                boolean isSelected = gridViewHolder.widget.getStatus();
//                if (!isSelected) {
//                    continue;
//                }
//
//                String brandId;
//                try {
//                    //brandId = brands.get(j).getApp().getBrand_id();
//                    brandId = brands.get(j).getId();
//                } catch (Exception e) {
//                    brandId = "";
//                }
//
//                if (brandId != null && brandId.length() > 0) {
//                    strBuild.append(brandId).append(SEPARATOR);
//                }
//            }
//
//            String result = "";
//            if (strBuild.length() > SEPARATOR.length()) {
//                int endIndex = strBuild.lastIndexOf(SEPARATOR);
//                result = strBuild.substring(0, endIndex);
//            }
//            return result;
//        } catch (Exception e) {
//            return "";
//        }
//    }
//重写此方法用来设置当点击activity外部时候，关闭此弹出框
//    public boolean onTouchEvent(MotionEvent event){
//        thisfinish();
//        return true;
//    }
//    public void tip(View view)
//    {
//        Toast.makeText(this, "点击弹出框外部关闭窗口~", Toast.LENGTH_SHORT).show();
//    }





}
