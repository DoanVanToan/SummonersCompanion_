package com.toandoan.lol.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import com.toandoan.lol.R;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

//import com.facebook.AccessToken;
//import com.facebook.login.LoginManager;
//import com.sembangchat.sembang.chat.messenger.chatapp.sembangkomunikasi.api.base.PushParameter;

/**
 * Created by vuslenovo on 6/30/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public ACProgressFlower loadingDialog;
    protected BaseActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMaterialDialog();
        activity = this;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected void initMaterialDialog() {
        if (loadingDialog == null) {
            loadingDialog = new ACProgressFlower.Builder(this)
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE)
                    .text(getString(R.string.loading))
                    .fadeColor(Color.DKGRAY).build();
        } else {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
            loadingDialog = null;
        }
    }

    public void showDialog() {
        if (null != loadingDialog && !loadingDialog.isShowing()) {
            try {
                loadingDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            loadingDialog = new ACProgressFlower.Builder(this)
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE)
                    .text(getString(R.string.loading))
                    .fadeColor(Color.DKGRAY).build();
            try {
                loadingDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void setCancelDialog(boolean isCancel) {
        if (null != loadingDialog) {
            try {
                loadingDialog.setCancelable(isCancel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            loadingDialog = new ACProgressFlower.Builder(this)
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE)
                    .text(getString(R.string.loading))
                    .fadeColor(Color.DKGRAY).build();
            loadingDialog.setCancelable(isCancel);
        }
    }


    public void dismissDialog() {
        try {
            if (null != loadingDialog && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        } catch (final IllegalArgumentException e) {
            // Handle or log or ignore
        } catch (final Exception e) {
            // Handle or log or ignore
        } finally {
            this.loadingDialog = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
