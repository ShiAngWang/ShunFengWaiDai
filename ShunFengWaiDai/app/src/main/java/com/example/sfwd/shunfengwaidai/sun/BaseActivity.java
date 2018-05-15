package com.example.sfwd.shunfengwaidai.sun;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.PopupWindow;

import java.util.List;

public class BaseActivity extends FragmentActivity{
    private FilterPop pop;
    protected Activity activity;

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.activity=this;

    }
    public void showFilterPop(View parentView,
                              List<String> itemTexts,
                              AdapterView.OnItemClickListener itemClickListener,
                              DismissListener dismissListener){
        showFilterPop(parentView,itemTexts,itemClickListener,dismissListener,0);
    }
    public  void showFilterPop(View parentView,
                               List<String> itemTexts,
                               AdapterView.OnItemClickListener itemClickListener,
                               DismissListener dismissListener,float alpha){
        if(pop!=null&&pop.isShowing()){
            pop.dismiss();
            pop=null;
        }
        pop=new FilterPop(activity,itemTexts);
        pop.setOnDismissListener(dismissListener);
        pop.setOnItemSelectedListener(itemClickListener);
        if(0==alpha)
            alpha=0.6f;
        WindowManager.LayoutParams layoutParams=activity.getWindow().getAttributes();
        layoutParams.alpha=alpha;
        activity.getWindow().setAttributes(layoutParams);
        pop.showAsDropDown(parentView);

    }
    public  class DismissListener implements PopupWindow.OnDismissListener{
        public void onDismiss(){
            WindowManager.LayoutParams layoutParams=activity.getWindow().getAttributes();
            layoutParams.alpha=1.0f;
            activity.getWindow().setAttributes(layoutParams);
        }
    }
    public void hidePop(){
        if(pop!=null&&pop.isShowing()){
            pop.dismiss();
            pop=null;
        }
    }

    public void filterToggle(boolean isChecked,View showView,List<String> showMes,
                             AdapterView.OnItemClickListener itemClickListener,final CheckBox... tabs){
        if(isChecked){
            if(tabs.length<=0)
                return;
            for (int i=1;i<tabs.length;i++){
                tabs[i].setChecked(false);
            }
            showFilterPop(showView,showMes,itemClickListener,new DismissListener(){
                public void onDismiss(){
                    super.onDismiss();
                    tabs[0].setChecked(false);
                }
            });
        }else
            hidePop();
    }
}
