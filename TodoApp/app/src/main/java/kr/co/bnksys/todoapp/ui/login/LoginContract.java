package kr.co.bnksys.todoapp.ui.login;

import kr.co.bnksys.todoapp.ui.base.BasePresenter;
import kr.co.bnksys.todoapp.ui.base.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void goMain();

    }

    interface Presenter extends BasePresenter<View> {

        void login(String email, String password);

    }
}
