package com.example.jammy.ReserveSystem.suggest;

import com.example.jammy.ReserveSystem.bean.Suggest;

import rx.Observable;

/**
 * Created by Jammy on 2017/3/8.
 */

public class SuggestContract {
    interface View {
        void submitSuccess();
        void submitFail(String errorCode);
    }

    interface Model {
        Observable submitSuggest(Suggest suggest);
    }
}
