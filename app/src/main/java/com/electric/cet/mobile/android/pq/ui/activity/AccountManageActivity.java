package com.electric.cet.mobile.android.pq.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.electric.cet.mobile.android.pq.R;

public class AccountManageActivity extends Activity implements View.OnClickListener {
   private RelativeLayout contact_rl;
   private RelativeLayout psw_rl;
   private ImageView back_iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        initView();
        initData();
    }

    private void initView(){
        back_iv = (ImageView) findViewById(R.id.my_account_manage_back);
        contact_rl = (RelativeLayout) findViewById(R.id.my_account_manage_update_contact_rl);
        psw_rl = (RelativeLayout) findViewById(R.id.my_account_manage_update_psw_rl);
        back_iv.setOnClickListener(this);
        contact_rl.setOnClickListener(this);
        psw_rl.setOnClickListener(this);
    }

    private void initData(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_account_manage_back:
                finish();
                break;
            case R.id.my_account_manage_update_contact_rl:
                Intent contactIntent = new Intent();
                contactIntent.setClass(AccountManageActivity.this,SetContactActivity.class);
                startActivity(contactIntent);
                break;
            case R.id.my_account_manage_update_psw_rl:
                Intent pswIntent = new Intent();
                pswIntent.setClass(AccountManageActivity.this,MyPwdActivity.class);
                startActivity(pswIntent);
                break;
                default:
                    break;
        }
    }
}
