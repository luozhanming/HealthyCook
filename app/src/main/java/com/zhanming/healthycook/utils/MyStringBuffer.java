package com.zhanming.healthycook.utils;

/**
 * Created by zhanming on 2016/10/3.
 */
public class MyStringBuffer {
    private StringBuffer sb;

    public MyStringBuffer() {
        sb = new StringBuffer();
    }

    public MyStringBuffer appendWithSpace(CharSequence chars){
        sb.append(chars+" ");
        return this;
    }

    public MyStringBuffer append(CharSequence chars){
        sb.append(chars);
        return this;
    }

    public String string(){
        return sb.toString();
    }
}
