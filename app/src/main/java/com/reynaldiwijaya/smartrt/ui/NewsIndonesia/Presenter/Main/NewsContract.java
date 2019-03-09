package com.reynaldiwijaya.smartrt.ui.NewsIndonesia.Presenter.Main;

import com.reynaldiwijaya.smartrt.model.NewsIndonesia.ArticlesItem;

import java.util.List;

public interface NewsContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showData(List<ArticlesItem> newsItemList);
        void showFailureMessage();
    }

    interface Presenter {
        void getData();
    }
}
