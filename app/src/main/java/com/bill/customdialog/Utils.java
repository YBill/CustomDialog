package com.bill.customdialog;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by Bill on 2017/4/8.
 */

public class Utils {

    public static void copy(String text) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) MyAppLication.appLication.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", text);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);

    }
}
