package com.example.sfwd.shunfengwaidai.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.activity.AddAddressActivity;
import com.example.sfwd.shunfengwaidai.activity.BasicInformationActivity;
import com.example.sfwd.shunfengwaidai.activity.IdentifyActivity;


/**
 * A simple {@link Fragment} subclass.
 */

public class PersonalFragment extends Fragment {

    private ConstraintLayout turnto_4;
    private ConstraintLayout turnto_2;
    private ConstraintLayout turnto_3;
    public PersonalFragment() {
        // Required empty public constructor
    }

    public static PersonalFragment newInstance(String param1) {
        PersonalFragment fragment = new PersonalFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        turnto_2= getActivity().findViewById(R.id.turnto_2);
        turnto_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), IdentifyActivity.class);
                startActivity(intent);
            }


        });
        turnto_3= getActivity().findViewById(R.id.turnto_3);
        turnto_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), BasicInformationActivity.class);
                startActivity(intent);
            }


        });
        turnto_4= getActivity().findViewById(R.id.turnto_4);
        turnto_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddAddressActivity.class);
                startActivity(intent);
            }


        });

    }

}
