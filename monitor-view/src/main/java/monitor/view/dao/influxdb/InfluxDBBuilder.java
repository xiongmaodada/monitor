package monitor.view.dao.influxdb;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

/**
 * Created by lizhitao on 2018/1/13.
 * InfluxDB  Builder
 */
public class InfluxDBBuilder {
    private String username;
    private String password;
    private String url;

    private InfluxDB influxDB;

    public InfluxDBBuilder(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public InfluxDB build() {
        if (influxDB == null) {
            synchronized (this) {
                if (influxDB == null) {
                    influxDB = InfluxDBFactory.connect(url, username, password);
                }
            }
        }
        return influxDB;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
