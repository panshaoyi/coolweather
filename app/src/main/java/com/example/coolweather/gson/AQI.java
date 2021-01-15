package com.example.coolweather.gson;

/**
 * Created by Administrator on 2021/1/14 0014.
 */

public class AQI {
    public AQICity city;
    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
