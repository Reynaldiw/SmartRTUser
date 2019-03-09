package com.reynaldiwijaya.smartrt.ui.Store.Presenter;

import com.reynaldiwijaya.smartrt.model.Store.StoreItem;

import java.util.List;

public interface StoreContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showDataStore(List<StoreItem> storeItemList);
        void showFailureMessage(String msg);
    }

    interface Presenter {
        void getDataStore();
    }
}
