package cn.org.rookie.jeesdp.core;

import java.util.Date;

public class Demo {
    Date date;
    Date timestamp;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "date=" + date +
                ", timestamp=" + timestamp +
                '}';
    }
}
