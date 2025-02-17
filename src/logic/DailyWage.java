package logic;

import db.User;

import java.time.Duration;
import java.time.LocalDateTime;

public class DailyWage {
    User user = User.getInstance();
    private LocalDateTime loginTime = user.getLogInTime();
    private LocalDateTime logoutTime = user.getLogOutTime();
    private long workTime;
    private int dailyWage;

    public int calculateDailyWageFrom(LocalDateTime loginTime, LocalDateTime logoutTime) {
        workTime = Duration.between(loginTime, logoutTime).toMinutes();
        dailyWage = (int) workTime * 9800;
        setDailyWage();
        return dailyWage;
    }

    public void setDailyWage() {
        User.getInstance().setDailyWage(dailyWage);
        showDailyWorkComments();
    }

    public void showDailyWorkComments() {
        System.out.println("══════════════════════════════════════════");
        System.out.printf("               일한 시간 : %d 분\n",workTime);
        System.out.println("══════════════════════════════════════════");
        System.out.println("══════════════════════════════════════════");
        System.out.printf("               일당 : %s (원)\n",user.getDailyWage());
        System.out.println("══════════════════════════════════════════");
    }


}
