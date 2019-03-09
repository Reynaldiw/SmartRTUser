package com.reynaldiwijaya.smartrt.ui.Home.Presenter;

import com.reynaldiwijaya.smartrt.model.Informasi.NewsItem;
import com.reynaldiwijaya.smartrt.model.Informasi.ResponseGetInformasi;

import java.util.List;

public interface HomeContract {
    interface View {
        void showFailureMsg(String msg);
        void showData(List<NewsItem> newsItemList);
    }
    interface Presenter {
        void getData();
    }
}
