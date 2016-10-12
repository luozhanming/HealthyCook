package com.zhanming.healthycook.collection;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedVignetteBitmapDisplayer;
import com.zhanming.healthycook.R;
import com.zhanming.healthycook.beans.Recipe;
import com.zhanming.healthycook.detail.DetailActivity;
import com.zhanming.healthycook.models.LocalRecipeSource;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhanming on 2016/10/10.
 */
public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.VH> {

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


    public CollectionAdapter(Context context) {
        mContext = context;
    }

    public void setDatas(List<Recipe> list) {
        datas = list;
        notifyDataSetChanged();
    }


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(View.inflate(mContext, R.layout.item_colletection, null));
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        final Recipe recipe = datas.get(position);
        String imgUrl = "http://tnfs.tngou.net/img/" + recipe.getImgUrl();
        String name = recipe.getName();
        String key = recipe.getKey();

        ImageLoader.getInstance().displayImage(imgUrl, holder.iv_recipeImg, options);
        holder.tv_name.setText(name);
        holder.tv_key.setText(key);
        holder.rl_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                Bundle extras = new Bundle();
                extras.putParcelable("recipe", datas.get(position));
                intent.putExtras(extras);
                mContext.startActivity(intent);
            }
        });
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalRecipeSource.getInstance(mContext).deleteRecipe(datas.get(position));
                datas.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (datas != null) {
            return datas.size();
        }
        return 0;
    }

    class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_item_collection_root)
        RelativeLayout rl_root;
        @BindView(R.id.iv_item_collection_pic)
        ImageView iv_recipeImg;
        @BindView(R.id.tv_item_collection_name)
        TextView tv_name;
        @BindView(R.id.tv_item_collection_key)
        TextView tv_key;
        @BindView(R.id.iv_item_collection_delete)
        ImageView iv_delete;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
