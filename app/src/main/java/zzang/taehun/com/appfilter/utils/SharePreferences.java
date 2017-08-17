package zzang.taehun.com.appfilter.utils.xml;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import zzang.taehun.com.appfilter.utils.Constants;

/**
 * Created by taehun on 2017-06-15.
 */

public class SharePreferences {
    private Context mContext;
    private SharedPreferences mPref;

    public SharePreferences(Context context) {
        mContext = context;
        mPref = mContext.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Activity.MODE_PRIVATE);
    }

    public String getString(String key, String defValue){
        return mPref.getString(key,defValue);
    }
    public int getInteger(String key, Integer defValue){
        return mPref.getInt(key,defValue);
    }
    public boolean getBoolean(String key, boolean defValue){
        return mPref.getBoolean(key,defValue);
    }
    public long getLong(String key, long defValue){
        return mPref.getLong(key,defValue);
    }

    public void putString(String key, String defValue){
        synchronized(mContext){
            mPref.edit().putString(key,defValue).commit();
        }
    }
    public void putInteger(String key, Integer defValue){
        synchronized(mContext){
            mPref.edit().putInt(key,defValue).commit();
        }
    }

    public void putBoolean(String key, boolean defValue){
        synchronized (mContext){
            mPref.edit().putBoolean(key,defValue).commit();
        }
    }
    public void putLong(String key, long defValue){
        synchronized(mContext){
            mPref.edit().putLong(key,defValue).commit();
        }
    }


}
