package com.camera360.yuyidong.mymodule.app2.bottommenu.bottommenu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by yuyidong on 14-12-26.
 */
public class MenuDialog extends Dialog implements View.OnClickListener {

    private OnMenuItenClickListener onMenuItenClickListener;

    public void setOnMenuItenClickListener(OnMenuItenClickListener onMenuItenClickListener) {
        this.onMenuItenClickListener = onMenuItenClickListener;
    }

    public MenuDialog(Context context) {
        super(context, R.style.DialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog);
        initDialog();
        initUI();
    }

    public void initDialog() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = display.getWidth();
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.DialogAnimation);
        getWindow().setAttributes(params);
        this.setCanceledOnTouchOutside(true);
    }

    public void initUI() {
        findViewById(R.id.layout_menu1).setOnClickListener(this);
        findViewById(R.id.layout_menu2).setOnClickListener(this);
        findViewById(R.id.layout_cancel).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onMenuItenClickListener.onMenuItemClick(v);
        this.dismiss();
    }

    interface OnMenuItenClickListener {
        public void onMenuItemClick(View v);
    }
}
