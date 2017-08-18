package com.jdyy.cfunding.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.jdyy.cfunding.R;

/**
 * Created by Administrator on 2016/12/23 0023.
 */

public class BottomMenu implements View.OnClickListener, View.OnTouchListener {

    private PopupWindow popupWindow;
    private Button mTakePhotoBtn, mPickPhotoBnt, CancelBtn;
    private View mMenuView;
    private Activity mContext;
    private View.OnClickListener clickListener;

    public BottomMenu(Activity context,View.OnClickListener clickListener) {
        LayoutInflater inflater = LayoutInflater.from(context);
        this.clickListener=clickListener;
        mContext=context;
        mMenuView = inflater.inflate(R.layout.popu_bottom_layout, null);
        mTakePhotoBtn = (Button) mMenuView.findViewById(R.id.takePhotoBtn);
        mPickPhotoBnt = (Button) mMenuView.findViewById(R.id.pickPhotoBtn);
        CancelBtn = (Button) mMenuView.findViewById(R.id.cancelBtn);
        CancelBtn.setOnClickListener(this);
        mTakePhotoBtn.setOnClickListener(this);
        mPickPhotoBnt.setOnClickListener(this);
        popupWindow=new PopupWindow(mMenuView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.statusColor));
        popupWindow.setBackgroundDrawable(dw);
        mMenuView.setOnTouchListener(this);
    }

    /**
     * 显示菜单
     */
    public void show(){
        //得到当前activity的rootView
        View rootView=((ViewGroup)mContext.findViewById(android.R.id.content)).getChildAt(0);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onClick(View view) {
        popupWindow.dismiss();
        switch (view.getId()) {
            case R.id.btn_cancel:
                popupWindow.dismiss();
                break;
            default:
                clickListener.onClick(view);
                break;
        }
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent event) {
        int height = mMenuView.findViewById(R.id.pop_layout).getTop();
        int y=(int) event.getY();
        if(event.getAction()==MotionEvent.ACTION_UP){
            if(y<height){
                popupWindow. dismiss();
            }
        }
        return true;
    }
}
