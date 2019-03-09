package com.reynaldiwijaya.smartrt.ui.Report.Presenter;

import com.reynaldiwijaya.smartrt.model.Laporan.LaporanItem;

import java.util.List;

public interface ReportContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showData(List<LaporanItem> laporanItemList);
        void showFailureMessage(String message);
        void showPesan();
        void hidePesan();
    }
    interface Presenter {
        void getLaporanKonfirmasi();
    }
}
