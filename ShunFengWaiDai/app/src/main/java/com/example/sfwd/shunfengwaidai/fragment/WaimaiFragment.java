package com.example.sfwd.shunfengwaidai.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sfwd.shunfengwaidai.R;

/**
 * Created by 士昂 on 2018/4/22.
 */

public class WaimaiFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_waimai,container,false);
        return view;
    }
}