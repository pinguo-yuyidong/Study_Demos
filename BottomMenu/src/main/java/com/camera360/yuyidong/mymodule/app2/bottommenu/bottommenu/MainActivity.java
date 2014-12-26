package com.camera360.yuyidong.mymodule.app2.bottommenu.bottommenu;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, MenuDialog.OnMenuItenClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_menu).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        MenuDialog menuDialog = new MenuDialog(MainActivity.this);
        menuDialog.show();
        menuDialog.setOnMenuItenClickListener(this);
    }

    @Override
    public void onMenuItemClick(View v) {
        switch (v.getId()) {
            case R.id.layout_menu1:
                Toast.makeText(MainActivity.this, "menu1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_menu2:
                Toast.makeText(MainActivity.this, "menu2", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
