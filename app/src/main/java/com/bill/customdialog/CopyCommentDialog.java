package com.bill.customdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Bill on 2017/4/8.
 */
public class CopyCommentDialog extends Dialog {

    public CopyCommentDialog(Context context) {
        super(context);
    }

    public CopyCommentDialog(Context context, int theme) {
        super(context, theme);
    }

    public void showDialog() {
        if (!this.isShowing())
            this.show();
    }

    public static class Builder {
        private Context context;
        private boolean cancelAble = true;
        private boolean cancelAbleTouchOutside = true;
        private OnClickListener leftClickListener;
        private OnClickListener rightClickListener;
        private String leftBtnText;
        private String rightBtnText;
        private int leftImage = -1;
        private int rightImage = -1;
        private boolean showLeftBtn = true;
        private boolean showRightBtn = true;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 左边按钮点击事件
         *
         * @param leftClickListener
         * @return
         */
        public Builder setLeftClickListener(OnClickListener leftClickListener) {
            this.leftClickListener = leftClickListener;
            return this;
        }

        /**
         * 右边按钮点击事件
         *
         * @param rightClickListener
         * @return
         */
        public Builder setRightClickListener(OnClickListener rightClickListener) {
            this.rightClickListener = rightClickListener;
            return this;
        }

        /**
         * 左边按钮上面icon
         *
         * @param leftImage
         * @return
         */
        public Builder setLeftImage(@DrawableRes int leftImage) {
            this.leftImage = leftImage;
            return this;
        }

        /**
         * 右边按钮上面icon
         *
         * @param rightImage
         * @return
         */
        public Builder setRightImage(@DrawableRes int rightImage) {
            this.rightImage = rightImage;
            return this;
        }

        /**
         * 左边按钮文本，默认"回复"
         *
         * @param leftBtnText
         * @return
         */
        public Builder setLeftBtnText(String leftBtnText) {
            this.leftBtnText = leftBtnText;
            return this;
        }

        /**
         * 右边按钮文本，默认"复制"
         *
         * @param rightBtnText
         * @return
         */
        public Builder setRightBtnText(String rightBtnText) {
            this.rightBtnText = rightBtnText;
            return this;
        }

        /**
         * 是否显示左边按钮，默认true，显示
         *
         * @param showLeftBtn
         * @return
         */
        public Builder setShowLeftBtn(boolean showLeftBtn) {
            this.showLeftBtn = showLeftBtn;
            return this;
        }

        /**
         * 是否显示右边按钮，默认true，显示
         *
         * @param showRightBtn
         * @return
         */
        public Builder setShowRightBtn(boolean showRightBtn) {
            this.showRightBtn = showRightBtn;
            return this;
        }

        /**
         * 是否可点击其他区域关闭dialog，默认true，可以
         *
         * @param cancelAbleTouchOutside
         * @return
         */
        public Builder setCancelAbleTouchOutside(boolean cancelAbleTouchOutside) {
            this.cancelAbleTouchOutside = cancelAbleTouchOutside;
            return this;
        }

        /**
         * 是不可返回关闭dialog，默认true,可以
         *
         * @param cancelAble
         * @return
         */
        public Builder setCancelAble(boolean cancelAble) {
            this.cancelAble = cancelAble;
            return this;
        }

        public CopyCommentDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CopyCommentDialog dialog = new CopyCommentDialog(context, R.style.mydialog);
            View layout = inflater.inflate(R.layout.copy_comment_dialog, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            TextView leftText = (TextView) layout.findViewById(R.id.tv_left);
            if (!showLeftBtn)
                leftText.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(leftBtnText)) {
                leftText.setText(leftBtnText);
            }
            if (leftImage > 0)
                leftText.setCompoundDrawablesWithIntrinsicBounds(0, leftImage, 0, 0);
            TextView rightText = (TextView) layout.findViewById(R.id.tv_right);
            if (!showRightBtn)
                rightText.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(rightBtnText)) {
                rightText.setText(rightBtnText);
            }
            if (rightImage > 0)
                rightText.setCompoundDrawablesWithIntrinsicBounds(0, rightImage, 0, 0);
            TextView cancelText = (TextView) layout.findViewById(R.id.tv_cancel);
            leftText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (leftClickListener != null)
                        leftClickListener.onClick();
                }
            });
            rightText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (rightClickListener != null)
                        rightClickListener.onClick();
                }
            });
            cancelText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.setContentView(layout);
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
            params.width = (int) (display.getWidth() * 0.5);
            dialog.getWindow().setAttributes(params);

            dialog.setCancelable(cancelAble);
            dialog.setCanceledOnTouchOutside(cancelAbleTouchOutside);
            return dialog;
        }
    }

    public interface OnClickListener {
        void onClick();
    }
}
