package com.reynaldiwijaya.smartrt.ui.Store.Presenter;


public interface InsertStoreContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showSuccesMessage(String msg);
        void showFailureMessage(String msg);
    }

    interface Presenter {
        void getDataStore(String id_user, String nama_toko, String alamat, String deskripsi, String no_tlp, String path);
    }
}
