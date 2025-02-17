package logic;

import db.User;

import java.time.LocalDateTime;

public class Logout {
    User user = User.getInstance();

    public void setLogOutTimeTo(LocalDateTime currentTime) {
        user.setLogOutTime(currentTime);
    }
}

