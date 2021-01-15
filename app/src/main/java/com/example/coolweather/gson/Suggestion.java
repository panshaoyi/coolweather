package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2021/1/14 0014.
 */

public class Suggestion {
    @SerializedName("comf")
    public Comfort comfort;
    @SerializedName("cw")
    public CarWash carwash;
    public Sport sport;
    public class Comfort{
        @SerializedName("txt")
        public String info;
    }
    public class CarWash{
        @SerializedName("txt")
        public String info;
    }
    public class Sport{
        @SerializedName("txt")
        public String info;
    }

}
