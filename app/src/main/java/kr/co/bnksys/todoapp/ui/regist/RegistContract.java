package kr.co.bnksys.todoapp.ui.regist;

import kr.co.bnksys.todoapp.ui.base.BasePresenter;
import kr.co.bnksys.todoapp.ui.base.BaseView;

public interface RegistContract {

    interface View extends BaseView<RegistContract.Presenter> {

    }

    interface Presenter extends BasePresenter<RegistContract.View> {

    }
}
