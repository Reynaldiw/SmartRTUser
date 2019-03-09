package com.reynaldiwijaya.smartrt.ui.Agenda.Presenter;

import com.reynaldiwijaya.smartrt.model.Agenda.AgendaItem;

import java.util.List;

public interface GetAgendaContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showFailureMessage(String msg);
        void showDataAgenda(List<AgendaItem> agendaItemList);
    }

    interface Presenter {
        void getDataAgenda();

    }
}
