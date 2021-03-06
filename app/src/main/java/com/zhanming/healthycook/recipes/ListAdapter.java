package com.zhanming.healthycook.recipes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedVignetteBitmapDisplayer;
import com.umeng.analytics.MobclickAgent;
import com.zhanming.healthycook.R;
import com.zhanming.healthycook.beans.Recipe;
import com.zhanming.healthycook.detail.DetailActivity;
import com.zhanming.healthycook.models.RemoteRecipeSource;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhanming on 2016/9/30.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.VH> {

    private static final String TAG = "ListAdapter";


    private Context mContext;
    private List<Recipe> datas;
    private final DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .showImageForEmptyUri(R.mipmap.cook_128px)
            .showImageOnLoading(R.mipmap.loading)
            .showImageOnFail(R.mipmap.wrong_468px)
            .displayer(new RoundedVignetteBitmapDisplayer(20, 10))
            .build();

    public ListAdapter(Context context) {
        mContext = context;
        datas = new LinkedList<>();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        return new VH(LayoutInflater.from(mContext).inflate(R.layout.item_recipe, null));
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        final Recipe recipe = datas.get(position);
        String imgUrl = "http://tnfs.tngou.net/img/" + recipe.getImgUrl();
        String name = recipe.getName();
        String key = recipe.getKey();
        String description = recipe.getDescription();

        ImageLoader.getInstance().displayImage(imgUrl, holder.iv_img, options);
        holder.tv_name.setText(name);
        holder.tv_key.setText(key);
        holder.rl_itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //统计
                Map<String,String> map = new HashMap<>();
                map.put("菜谱名",datas.get(position).getName());
                map.put("菜谱ID",datas.get(position).getId());
                MobclickAgent.onEvent(mContext,"2",map);
                Intent intent = new Intent(mContext, DetailActivity.class);
                Bundle extras = new Bundle();
                extras.putParcelable("recipe", datas.get(position));
                intent.putExtras(extras);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addMoreDatasToEnd(List<Recipe> more) {
        int itemCount = getItemCount();
        if(more!=null){
            datas.addAll(getItemCount(), more);
            notifyItemRangeInserted(itemCount, more.size());
        }
    }

    public void cleanDatas(){
        datas.clear();
        notifyDataSetChanged();
    }

    public void addMoreDatasToTop(List<Recipe> more) {
        if(more!=null){
            datas.addAll(0, more);
            notifyItemRangeInserted(0, more.size());
        }
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_itemRoot)
        RelativeLayout rl_itemRoot;
        @BindView(R.id.iv_item_recipe)
        ImageView iv_img;
        @BindView(R.id.tv_itemRecipe_key)
        TextView tv_key;
        @BindView(R.id.tv_itemRecipe_name)
        TextView tv_name;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
