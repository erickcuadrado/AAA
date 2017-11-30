package com.example.cuadrado.frontend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuadrado on 22/07/2017.
 */

public interface PushNotificationContract {

    interface View extends BaseView<Presenter>{

        void showNotifications(ArrayList<PushNotification> notifications);

        void showEmptyState(boolean empty);

        void popPushNotification(PushNotification pushMessage);
    }

    interface Presenter extends BasePresenter{

        void registerAppClient();

        void loadNotifications();

        void savePushMessage(String title, String description,
                             String expiryDate, String discount);
    }
}
