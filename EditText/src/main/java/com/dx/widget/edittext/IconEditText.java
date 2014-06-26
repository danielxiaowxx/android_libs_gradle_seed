package com.dx.widget.edittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by daniel on 14-6-25.
 */
public class IconEditText extends EditText {

    private Drawable rightIcon;

    private OnRightIconClickListener onRightIconClickListener;

    public IconEditText(Context context) {
        super(context);
        init();
    }

    public IconEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IconEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setOnRightIconClickListener(OnRightIconClickListener listener) {
        onRightIconClickListener = listener;
    }

    private void init() {
        initRightIcon();

        bindEvents();

        setRightIconVisible();
    }

    private void initRightIcon() {
        rightIcon = getCompoundDrawables()[2]; // 用android:drawableRight="@drawable/custom_icon"可设置自己的图标
        if (rightIcon == null) { // 使用系统默认的图标
            rightIcon = getResources().getDrawable(android.R.drawable.ic_lock_lock);
        }
        rightIcon.setBounds(0, 0, rightIcon.getIntrinsicWidth(), rightIcon.getIntrinsicHeight());
    }

    private void bindEvents() {

        this.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                IconEditText iconEditText = IconEditText.this;

                if (iconEditText.getCompoundDrawables()[2] == null)
                    return false;

                if (motionEvent.getAction() != MotionEvent.ACTION_UP)
                    return false;

                if (motionEvent.getX() > iconEditText.getWidth() - iconEditText.getPaddingRight() - rightIcon.getIntrinsicWidth()) {
                    if (onRightIconClickListener != null) {
                        onRightIconClickListener.onClick();
                    }

                    motionEvent.setAction(MotionEvent.ACTION_CANCEL);// 取消事件，使默认行为不会发生
                }

                return false;
            }
        });
    }

    private void setRightIconVisible() {
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], rightIcon, getCompoundDrawables()[3]);
    }


    public static interface OnRightIconClickListener {
        public void onClick();
    }
}
