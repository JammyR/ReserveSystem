package com.example.jammy.ReserveSystem.suggest;

import com.example.jammy.ReserveSystem.base.BaseModel;
import com.example.jammy.ReserveSystem.bean.Suggest;

import rx.Observable;

/**
 * Created by Jammy on 2017/3/8.
 */

public class SuggestModel extends BaseModel implements SuggestContract.Model{

    @Override
    public Observable submitSuggest(Suggest suggest) {
        return suggest.saveObservable();
    }
}
