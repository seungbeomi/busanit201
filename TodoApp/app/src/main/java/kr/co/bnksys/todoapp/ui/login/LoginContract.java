package kr.co.bnksys.todoapp.ui.login;

import kr.co.bnksys.todoapp.ui.base.BasePresenter;
import kr.co.bnksys.todoapp.ui.base.BaseView;

public interface LoginContract {

    interface View extends BaseView<LoginContract.Presenter> {

        void goMain();

    }

    interface Presenter extends BasePresenter<LoginContract.View> {

        void login(String email, String password);

    }
}
