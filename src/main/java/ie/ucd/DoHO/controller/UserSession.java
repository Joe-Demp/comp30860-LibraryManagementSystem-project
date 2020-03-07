package ie.ucd.DoHO.controller;

import ie.ucd.DoHO.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {
    private User user;
    private boolean loginFailed;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoginFailed() {
        return loginFailed;
    }

    public void setLoginFailed(boolean loginFailed) {
        this.loginFailed = loginFailed;
    }

    public boolean isAdmin() {
        return user != null && user.isAdmin();
    }

    public boolean isMember() {
        return user != null && user.isMember();
    }
}
