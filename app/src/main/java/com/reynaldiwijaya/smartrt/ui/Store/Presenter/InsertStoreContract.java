package com.reynaldiwijaya.smartrt.ui.Store.Presenter;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface InsertStoreContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showSuccesMessage(String msg);
        void showFailureMessage(String msg);
    }

    interface Presenter {
        void getDataStore(RequestBody id_user, RequestBody nama_toko, RequestBody alamat, RequestBody deskripsi, RequestBody no_tlp, MultipartBody.Part path, RequestBody konfirmasi);
    }
}
