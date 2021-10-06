package com.example.photoshow.utils;

public final class Constants {
    private Constants(){ }

    public static final int PHO_NUM = 5;
    public static final String SERVER_URL = "http://412691ul59.qicp.vip/user/";
    public static final String PHOTOS_PAHT = "photos/";
    public static final String CLIENT_ID = "ghgHuavUa_-DUrGyRhgn9I2p-MEo4m_Z-IlufkZZ4n8";
    public static final String PHOTOS_URL = SERVER_URL + PHOTOS_PAHT;
    public static final String REQUEST_URL = PHOTOS_URL + "?" + CLIENT_ID + "&" + PHO_NUM;

}
