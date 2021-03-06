package com.zhanming.healthycook.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhanming.healthycook.R;

/**
 * Created by zhanming on 2016/9/22.
 */
public class CatalogueAdapter extends RecyclerView.Adapter<CatalogueAdapter.VH> {


    private String[] names;
    private int[] pictureId = {R.mipmap.beauty, R.mipmap.keepfit, R.mipmap.shijie,
            R.mipmap.canshi, R.mipmap.tiaoyang, R.mipmap.xiaohua, R.mipmap.pifu,
            R.mipmap.man, R.mipmap.cancer};

    Context mContext;
    MainContract.View mView;


    public CatalogueAdapter(Context context, MainContract.View view) {
        mContext = context;
        mView = view;
        names = mContext.getResources().getStringArray(R.array.catalogueName);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(mContext).inflate(R.layout.item_catalogue, null));
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.tv_name.setText(names[position]);
        holder.iv_pic.setImageResource(pictureId[position]);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewCompat.animate(view)
                        .scaleX(0.8f).scaleY(0.8f)
                        .setDuration(200)
                        .withLayer()
                        .setInterpolator(new CycleInterpolator())
                        .setListener(new ViewPropertyAnimatorListener() {
                            @Override
                            public void onAnimationStart(View view) {

                            }

                            @Override
                            public void onAnimationEnd(View view) {
                                view.setScaleX(1f);
                                view.setScaleY(1f);
                                performClick(position);
                            }

                            @Override
                            public void onAnimationCancel(View view) {

                            }
                        }).start();
            }
        });
    }

    private void performClick(int position) {
        mView.jumpToPage(position);
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    private class CycleInterpolator implements android.view.animation.Interpolator {

        private final float mCycles = 0.5f;

        @Override
        public float getInterpolation(final float input) {
            return (float) Math.sin(2.0f * mCycles * Math.PI * input);
        }
    }

    class VH extends RecyclerView.ViewHolder {

        RelativeLayout root;
        ImageView iv_pic;
        TextView tv_name;

        public VH(View itemView) {
            super(itemView);
            root = (RelativeLayout) itemView.findViewById(R.id.rl_item_catalogue_root);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_item_catalogue);
            tv_name = (TextView) itemView.findViewById(R.id.tv_item_catalogue);

        }
    }
}
