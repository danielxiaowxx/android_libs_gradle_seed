/**
 * Copyright 2014 Alex Yanchenko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package com.dx.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 *
 * <pre>
 * android:drawableRight="@drawable/custom_icon"
 * </pre>
 */
public class ClearableEditText extends EditText {

    private Drawable clearableIcon;

    private boolean isClearableIconShow = true;

    public ClearableEditText(Context context) {
        super(context);
        init();
    }

    public ClearableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        initClearableIcon();

        bindEvents();

        setClearIconVisibleStatue();

        String initText = getText().toString();
        if (!initText.equals("")) { // 初始有值，将光标移到文字最后
            setSelection(initText.length());
        }
    }

    private void initClearableIcon() {
        clearableIcon = getCompoundDrawables()[2]; // 用android:drawableRight="@drawable/custom_icon"可设置自己的图标
        if (clearableIcon == null) { // 使用系统默认的图标
            clearableIcon = getResources().getDrawable(android.R.drawable.presence_offline);
        }
        clearableIcon.setBounds(0, 0, clearableIcon.getIntrinsicWidth(), clearableIcon.getIntrinsicHeight());
    }

    private void bindEvents() {
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                setClearIconVisibleStatue();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ClearableEditText clearableEditText = ClearableEditText.this;

                if (clearableEditText.getCompoundDrawables()[2] == null)
                    return false;

                if (motionEvent.getAction() != MotionEvent.ACTION_UP)
                    return false;

                if (motionEvent.getX() > clearableEditText.getWidth() - clearableEditText.getPaddingRight() - clearableIcon.getIntrinsicWidth()) {
                    clearableEditText.setText("");
                    setClearIconVisibleStatue();
                }

                return false;
            }
        });
    }

    private void setClearIconVisibleStatue() {
        boolean visible = getText().toString().equals("") ? false : true;
        if (isClearableIconShow != visible) {
            isClearableIconShow = visible;
            Drawable drawable = visible ? clearableIcon : null;
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], drawable, getCompoundDrawables()[3]);
        }
    }

}