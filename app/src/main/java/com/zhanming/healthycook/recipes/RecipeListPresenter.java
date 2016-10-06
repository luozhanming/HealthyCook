package com.zhanming.healthycook.recipes;

import android.content.Context;
import android.os.SystemClock;

import com.zhanming.healthycook.beans.Catalogue;
import com.zhanming.healthycook.beans.Recipe;
import com.zhanming.healthycook.recipes.pagemanager.PageManager;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhanming on 2016/9/23.
 */
public class RecipeListPresenter implements RecipesContract.Presenter {

    private RecipesContract.PageView mView;
    private Catalogue mCatalogue;


    public RecipeListPresenter(Context context, RecipesContract.PageView view, String group, String calogue) {
        mView = view;
        mCatalogue = PageManager.getInstance(context).getCatalogue(group, calogue);
        mView.setPresenter(this);
    }


    private void loadInitialRecipe() {
        List<Recipe> datas = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            Recipe recipe = new Recipe();
            recipe.setName("芹菜皮蛋牛肉粒");
            recipe.setDescription("锅热油，下姜蒜爆香，入红椒、芹菜翻炒出香味，加入少许干椒丝（怕辣者可不加），拌匀后下牛肉粒快速翻炒，调生抽、盐、蠔油、鸡精，快出锅时加入皮蛋粒拌匀即可");
            recipe.setKey("牛肉 芹菜 皮蛋 少许 拌匀");
            recipe.setImgUrl("http://tnfs.tngou.net/img/cook/150802/79b05ad0532a0ef682ae920ff9b67764.jpg");
            recipe.setMessage("<h2>菜谱简介</h2><hr>   芹菜皮蛋牛肉粒近期我家很受欢迎的菜菜，每次还在锅里炒着，小瑶就能闻香而至，眼巴巴地看着锅芹菜皮蛋牛肉粒近期我家很受欢迎的菜菜，每次还在锅里炒着，小瑶就能闻香而至，眼巴巴地看着锅里。只要有这道菜，老大绝对能多吃一碗饭，让我十分有成就感的说。里。只要有这道菜，老大绝对能多吃一碗饭，让我十分有成就感的说。   <h2>材料 </h2><hr>  \n<p>芹菜，皮蛋，红椒，姜，蒜，酱油，料酒，水少许，油，干椒丝少许，生抽，盐，蠔油，鸡精</p>   <h2>做法 </h2><hr>  \n<p>1.芹菜取茎切粒、皮蛋略煮（为凝固蛋黄）切粒、红椒切圈或粒、姜蒜切粒；　　</p> \n<p>2.牛肉切粒后用酱油、料酒、少许水拌匀后腌约半小时；</p> \n<p>3.锅烧热油，下牛肉入内滑散，约有一半变色时即可捞出，沥干油分置于碗中备用；　　</p> \n<p>4.锅热油，下姜蒜爆香，入红椒、芹菜翻炒出香味，加入少许干椒丝（怕辣者可不加），拌匀后下牛肉粒快速翻炒，调生抽、盐、蠔油、鸡精，快出锅时加入皮蛋粒拌匀即可。</p>");
            datas.add(recipe);
        }
        mView.showRecipeList();
        mView.updateList(datas);
    }


    @Override
    public void loadMoreRecipesByPullDown() {

    }

    @Override
    public void loadMoreRecipesByPullUp() {

    }

    @Override
    public void changePage(int page) {

    }

    @Override
    public void start() {
        mView.showLoadingView();
        loadInitialRecipe();
    }

    @Override
    public void destroy() {
        mView = null;
        mCatalogue = null;
    }
}
