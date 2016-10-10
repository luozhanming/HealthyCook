package com.zhanming.healthycook.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.zhanming.healthycook.R;

/**
 * Created by zhanming on 2016/10/9.
 */
public class EditTextWithDel extends EditText implements View.OnFocusChangeListener, TextWatcher {

    private Drawable delImg;
    private boolean hasFocus;

    private DelClickCallback callback;

    /**
     * 删除文本键按下的回调接口
     */
    public interface DelClickCallback {
        void onDelClick(View view);
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        delImg = getCompoundDrawables()[2];
        if (delImg == null) {
            delImg = getResources().getDrawable(R.mipmap.delete_32px);
        }
        delImg.setBounds(0, 0, delImg.getIntrinsicWidth(), delImg.getIntrinsicHeight());

        setDelIconVisible(false);

        setOnFocusChangeListener(this);

        addTextChangedListener(this);

    }

    private void setDelIconVisible(boolean visible) {
        Drawable right = visible ? delImg : null;
        setCompoundDrawables(null, null, right, null);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (hasFocus) {
            if (text.length() > 0) {
                setDelIconVisible(true);
            } else {
                setDelIconVisible(false);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }


    public void setOnDelClickCallback(DelClickCallback callback) {
        this.callback = callback;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (delImg != null) {
                boolean touchable = (event.getX() > getWidth() - getTotalPaddingRight())
                        && (event.getX() < getWidth() - getPaddingRight());
                if (touchable) {
                    this.setText("");
                    if(callback!=null){
                        callback.onDelClick(this);
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
