package com.syncworks.s_light.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by vosami on 2015-06-11.
 */
public class SLightPref {
    private final static String PREF_NAME = "com.syncworks.slight";

    // ó�� ���� Ȯ��
    private final static String PREF_EXEC_FIRST = "Exec_First";
    // ������ ��ġ �̸� ����
    public final static String PREF_DEVICE_NAME = "Device_Name";
    // ������ ��ġ �ּ� ����
    public final static String PREF_DEVICE_ADDR = "Device_Addr";


    private SharedPreferences pref = null;

    public SLightPref(Context c) {
        pref = c.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);

    }

    public void putString(String key, String val) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, val);
        editor.apply();
    }

    public void putBoolean(String key, boolean val) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, val);
        editor.apply();
    }

    public String getString(String key) {
        // �����Ͱ� ���ٸ� "NONE"�� ��ȯ, ���� �߻��ϸ� "ERROR" ��ȯ
        try {
            return pref.getString(key, "NONE");
        } catch (Exception e) {
            Log.e(PREF_NAME, "Error getString");
            return "ERROR";
        }
    }
    public boolean getBoolean(String key) {
        return pref.getBoolean(key,false);
    }


    public void setPrefDevice(String name, String addr) {
        putString(PREF_DEVICE_NAME, name);
        putString(PREF_DEVICE_ADDR, addr);
    }

    public SLightDev getPrefDevice() {
        String retName = getString(PREF_DEVICE_NAME);
        String retAddr = getString(PREF_DEVICE_ADDR);
        return new SLightDev(retName, retAddr);
    }
}
