package com.example.shunfengwaidai;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeesageFragment extends Fragment {


    public MeesageFragment() {
        // Required empty public constructor
    }

    public static MeesageFragment newInstance(String param1) {
        MeesageFragment fragment = new MeesageFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meesage, container, false);
    }

}
