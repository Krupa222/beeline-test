package org.test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private String name;
    private Long balance;
    private Long traffic;

    public String getBalanceMsg(Setting setting){
        String msg;
        Long limit = setting.getBalanceLimit();
        boolean isThresholdViolated = false;
        switch (setting.getBalanceLogic()) {
            case "<":
                isThresholdViolated = balance < limit;
                break;
            case ">":
                isThresholdViolated = balance > limit;
                break;
            case "=":
                isThresholdViolated = balance.equals(limit);
                break;
            case "<=":
                isThresholdViolated = balance <= limit;
                break;
            case ">=":
                isThresholdViolated = balance >= limit;
                break;
            default:
                throw new IllegalArgumentException("Invalid comparison type: " + setting.getBalanceLogic());
        }

        if (isThresholdViolated) {
            msg = "У Вас на счету меньше " + limit + " тенге. Рекомендуем пополнить баланс.";
        } else {
            msg = "Ваш баланс: " + balance + " тенге.";
        }
        return msg;
    }

    public String getStartMsg(){
        String msg;
        LocalTime time = LocalTime.now();
        int hour = time.getHour();

        if (hour >= 5 && hour < 12) {
            msg = "Доброе утро, ";
        } else if (hour >= 12 && hour < 18) {
            msg = "Добрый день, ";
        } else if (hour >= 18 && hour < 23) {
            msg = "Добрый вечер, ";
        } else {
            msg = "Доброй ночи, ";
        }
        return msg + name + ".";
    }

    public String getTrafficMsg(){
        if (this.traffic < 0) {
            throw new IllegalArgumentException("Трафик не может быть отрицательным");
        }
        Long kilobytes = traffic / 1024;
        Long bytes = traffic % 1024;

        Long megabytes = kilobytes / 1024;
        kilobytes = kilobytes % 1024;

        Long gigabytes = megabytes / 1024;
        megabytes = megabytes % 1024;

        StringBuilder result = new StringBuilder();
        result.append("Ваш интернет трафик: ");

        if (gigabytes > 0) {
            result.append(gigabytes).append(" ГБ ");
        }
        if (megabytes > 0) {
            result.append(megabytes).append(" МБ ");
        }
        if (kilobytes > 0) {
            result.append(kilobytes).append(" КБ ");
        }
        if (bytes > 0 || result.isEmpty()) {
            result.append(bytes).append(" Б");
        }

        return result.toString().trim();
    }
}
