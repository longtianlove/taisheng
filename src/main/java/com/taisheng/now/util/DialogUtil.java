package com.taisheng.now.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.taisheng.now.R;
import com.taisheng.now.base.BaseActivity;
import com.taisheng.now.view.CustomProgress;
import com.taisheng.now.view.dialog.ZixunDialog;


/**
 * Created by long on 2017/4/25.
 */

public class DialogUtil {
    private static CustomProgress mCustomProgress;

    /**
     * 显示带文本的加载进度对话框
     */
    public static void showProgress(Context context, String str) {
        try {
            if ("".equals(str)) {
                str = "请稍等";
            }
            if (mCustomProgress == null) {
                mCustomProgress = CustomProgress.show(context, str, false, null);
            } else {
                mCustomProgress.setMessage(str);
                mCustomProgress.show();
            }
        } catch (Exception e) {

        }
    }

    /**
     * 关掉加载进度对话框
     */
    public static void closeProgress() {
        if (mCustomProgress != null && mCustomProgress.isShowing()) {
            try {//bug fixxed with umeng at 5.0.1 by long
                mCustomProgress.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mCustomProgress = null;
        }
    }
public static Dialog zixunDialog;
    /**
     * 如果Activity destory了不能运行dialog bug fixxed with umeng at 5.0.1 by long
     *
     * @param context
     * @return
     */
    public static boolean canShowDialog(Context context) {
        if (context == null) return false;
        if (context instanceof BaseActivity) {
            BaseActivity act = (BaseActivity) context;
            if (act.isDestroy()) {
                return false;
            }
        }
        return true;
    }

    public static View ll_shipin, ll_yuyin, ll_quxiao;

//咨询弹窗
public static void showzixunDialog(Context context,final View.OnClickListener shipinlistener,final View.OnClickListener yunyinlistener,final View.OnClickListener quxiaolistener) {
    if (!canShowDialog(context)) return;
    closeAllDialog();
    if (zixunDialog == null) {
        zixunDialog = new ZixunDialog(context);
        ll_shipin = zixunDialog.findViewById(R.id.ll_shipin);
        ll_shipin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zixunDialog.isShowing()) {
                    zixunDialog.dismiss();
                }
                if (shipinlistener != null) {
                    shipinlistener.onClick(v);
                }

            }
        });
        ll_yuyin = zixunDialog.findViewById(R.id.ll_yuyin);
        ll_yuyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zixunDialog.isShowing()) {
                    zixunDialog.dismiss();
                }
                if (yunyinlistener != null) {
                    yunyinlistener.onClick(v);
                }

            }
        });
        ll_quxiao = zixunDialog.findViewById(R.id.ll_quxiao);
        ll_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zixunDialog.isShowing()) {
                    zixunDialog.dismiss();
                }
                if (quxiaolistener != null) {
                    quxiaolistener.onClick(v);
                }

            }
        });
        zixunDialog.setCancelable(true);
        zixunDialog.setCanceledOnTouchOutside(true);
    }
    zixunDialog.show();
}

    //关闭所有弹窗
    public static void closeAllDialog() {
        if (zixunDialog != null && zixunDialog.isShowing()) {
            try {
                zixunDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }




}