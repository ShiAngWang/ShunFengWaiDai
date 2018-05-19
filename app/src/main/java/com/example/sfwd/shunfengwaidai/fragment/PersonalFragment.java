package com.example.sfwd.shunfengwaidai.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sfwd.shunfengwaidai.R;
import com.example.sfwd.shunfengwaidai.activity.AddAddressActivity;



/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment {

private ImageView identify;
private ImageView turnto_4;
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
        identify=getActivity().findViewById(R.id.identify);

        turnto_4= (ImageView) getActivity().findViewById(R.id.turnto_4);
        turnto_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddAddressActivity.class);
                startActivity(intent);
            }


        });
        identify.setImageResource(R.drawable.identified);
    }

}
