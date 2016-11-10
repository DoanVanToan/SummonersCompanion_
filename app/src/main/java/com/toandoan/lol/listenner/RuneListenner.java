package com.toandoan.lol.listenner;

import com.toandoan.lol.model.item.ItemEnity;
import com.toandoan.lol.model.rune.RuneEnity;

import java.util.List;

/**
 * Created by ToanDoan on 10/29/2016.
 */

public interface RuneListenner {
    void getAllRunesSuccessful(List<RuneEnity> listData);
    void getAllRunesFail(String message);
}
