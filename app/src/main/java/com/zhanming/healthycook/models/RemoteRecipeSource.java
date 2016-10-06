package com.zhanming.healthycook.models;

import android.provider.CalendarContract;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.zhanming.healthycook.beans.Catalogue;
import com.zhanming.healthycook.beans.Recipe;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanming on 2016/10/5.
 */
public class RemoteRecipeSource {


    public static final String BASE_URL_LIST = "http://apis.baidu.com/tngou/cook/list";
    public static final String BASE_URL_DETAIL = "http://apis.baidu.com/tngou/cook/show";
    public static final String BASE_URL_NAME = "http://apis.baidu.com/tngou/cook/name";
    public static final int DEFAULT_PAGE_COUNT = 10;
    private static RemoteRecipeSource instance;

    private OkHttpClient mClient;
    private Gson gson;
    private int EACH_LOAD_PAGE_COUNT = DEFAULT_PAGE_COUNT;

    private RemoteRecipeSource() {
        mClient = new OkHttpClient();
        gson = new Gson();
    }

    public static RemoteRecipeSource getInstance() {
        if (instance == null) {
            synchronized (RemoteRecipeSource.class) {
                instance = new RemoteRecipeSource();
            }
        }
        return instance;
    }

    public List<Recipe> getRecipeList(Catalogue catalogue) throws Exception {
        final int id = catalogue.getId();
        final int page = catalogue.getPage();
        StringBuilder sb = new StringBuilder();
        String requestStr = sb.append(BASE_URL_LIST)
                .append("?").append("id=").append(id)
                .append("&page=").append(page)
                .append("&rows=").append(EACH_LOAD_PAGE_COUNT).toString();
        try {
            URLEncoder.encode(requestStr, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Request request = new Request.Builder()
                .url(requestStr)
                .addHeader("apikey", "ef88432092c5c5e3db6654bc17a966fb")
                .get().build();
        List<Recipe> list = null;
        try {
            Response response = mClient.newCall(request).execute();
            if (response.code() != 200) {
                throw new Exception("Request code isn't 200!");
            }
            String body = response.body().string();
            RecipeResponse result = gson.fromJson(body, RecipeResponse.class);
            if (result.status != true) {
                throw new Exception("The response result's status isn't true !");
            }
            Recipe[] recipes = gson.fromJson(result.tngou, Recipe[].class);
            list = new ArrayList();
            for (Recipe recipe : recipes) {
                list.add(recipe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Recipe getRecipeById(String id) throws Exception {
        StringBuilder sb = new StringBuilder();
        String requestStr = sb.append(BASE_URL_DETAIL).append("?").append("id=").append(id).toString();
        Request request = new Request.Builder().url(requestStr)
                .addHeader("apikey", "ef88432092c5c5e3db6654bc17a966fb")
                .build();
        Recipe recipe = null;
        try {
            Response response = mClient.newCall(request).execute();
            if (response.code() != 200) {
                throw new Exception("Request code isn't 200!");
            }
            String body = response.body().string();
            recipe = gson.fromJson(body, Recipe.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public List<Recipe> searchRecipes(String name) throws Exception {
        List<Recipe> list = null;
        StringBuilder sb = new StringBuilder();
        String requestStr = null;
        try {
            requestStr = URLEncoder.encode(sb.append(BASE_URL_NAME)
                    .append("?").append("name=").append(name).toString(), "utf-8");
            Request request = new Request.Builder().url(requestStr)
                    .addHeader("apikey", "ef88432092c5c5e3db6654bc17a966fb")
                    .build();
            Response response = mClient.newCall(request).execute();
            String responseString = response.body().string();
            RecipeResponse recipeResponse = gson.fromJson(responseString, RecipeResponse.class);
            if (recipeResponse.status != true) {
                throw new Exception("The response result's status isn't true !");
            }
            list = new ArrayList<>();
            Recipe[] recipes = gson.fromJson(recipeResponse.tngou, Recipe[].class);
            for (Recipe recipe : recipes) {
                list.add(recipe);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    private class RecipeResponse {
        boolean status;
        int total;
        String tngou;
    }


}
