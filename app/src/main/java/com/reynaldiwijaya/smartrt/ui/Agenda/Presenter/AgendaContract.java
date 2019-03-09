package com.reynaldiwijaya.smartrt.ui.Agenda.Presenter;

public interface AgendaContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showFailureMessage(String msg);
        void showSuccesMessage(String msg);
        void clearData();
    }
    interface Presenter {
        void postAgenda(String judul, String content, String tempat, String tanggal, int konfirmasi);
    }
}
