package kr.co.bnksys.todoapp.data.base.prefs;

import kr.co.bnksys.todoapp.data.base.util.LoggedInMode;

public interface PreferencesManager {

    int getUserLoggedInMode();

    void setUserLoggedIn(LoggedInMode mode);

    Long getUserId();

    void setUserId(Long userId);

    String getUserName();

    void setUserName(String userName);

    String getUserEmail();

    void setUserEmail(String email);

    String getUserProfilePicUrl();

    void setUserProfilePicUrl(String profilePicUrl);

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getUserMobile();

    void setUserMobile(String mobileNumber);

    boolean isCoachMarkView();

    void setCoachMarkView(boolean coachMark);

    boolean isFirstTime();

    void setFirstTime(boolean firstTime);

    void logoutUser();

}
