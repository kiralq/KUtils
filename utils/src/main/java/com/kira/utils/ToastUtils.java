package com.kira.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Kirali
 * Time  2018/2/10
 * Describe: Toast的工具类
 */

public class ToastUtils {

    private static Toast mToast;

    /**
     * 安全弹出Toast。处理线程的问题。
     */
    public static void showSafeToast(final Context context, final String text) {

        if (Looper.myLooper() != Looper.getMainLooper()) {//如果不是在主线程弹出吐司，那么抛到主线程弹
            new Handler(Looper.getMainLooper()).post(
                    new Runnable() {
                        @Override
                        public void run() {
                            showUiToast(context, text);
                        }
                    }
            );
        } else {
            showUiToast(context, text);
        }
    }

    /**
     * 弹出Toast，处理单例的问题。----如果是在主线程,可以用这个,子线程就不要用这个,不安全
     */
    public static void showUiToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
//
//        if (text.length() < 10) {
//            mToast.setDuration(Toast.LENGTH_SHORT);
//        } else {
//            mToast.setDuration(Toast.LENGTH_LONG);
//        }
        View view = mToast.getView();
//        view.setPadding(28, 12, 28, 12);
        TextView tv = view.findViewById(android.R.id.message);
        tv.setTextSize(16);
//        tv.setTextColor(Color.WHITE);
        mToast.setText(text);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setView(view);
        mToast.show();
    }

}
