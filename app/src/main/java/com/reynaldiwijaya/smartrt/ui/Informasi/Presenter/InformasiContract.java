package com.reynaldiwijaya.smartrt.ui.Informasi.Presenter;

import com.reynaldiwijaya.smartrt.model.Informasi.NewsItem;

import java.util.List;

public interface InformasiContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showInformasi(List<NewsItem> newsItemList);
        void showFailureMessage(String msg);
    }

    interface Presenter {
        void getInformasi();
    }
}
