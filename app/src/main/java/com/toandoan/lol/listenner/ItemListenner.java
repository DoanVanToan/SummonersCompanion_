package com.toandoan.lol.listenner;

import android.view.View;

import com.toandoan.lol.model.item.ItemEnity;

import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public interface ItemListenner {
    void getAllItemSuccessful(List<ItemEnity> listData);
    void getAllItemFail(String message);
    void onItemClickListenner(View view, int position);
}
