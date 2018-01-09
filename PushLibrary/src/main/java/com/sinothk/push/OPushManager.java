package com.sinothk.push;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.util.Log;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * Created by 梁玉涛 on 2018/1/9.
 */

public class OPushManager {

    private static final String TAG = "com.sinothk.push";
    public static final int BY_XM = 0;
    public static int BY_WHICH = BY_XM;

    private static String APP_ID;
    private static Context mContext;

    /**
     * 未指定平台初始化，默认走小米
     *
     * @param mContext
     * @param appId
     * @param appKey
     * @param isDebug
     */
    public static void newPush(Context mContext, String appId, String appKey, boolean isDebug) {
        newPush(mContext, OPushManager.BY_XM, appId, appKey, isDebug);
    }

    /**
     * 指定平台初始化
     *
     * @param context
     * @param appId
     * @param appKey
     * @param isDebug
     */
    public static void newPush(Context context, int byWhich, String appId, String appKey, boolean isDebug) {
        APP_ID = appId;
        mContext = context;
        BY_WHICH = byWhich;

        if (BY_WHICH == BY_XM) { // 小米推送
            initXM(mContext, appId, appKey, isDebug);
        } else {

        }
    }

    /*
     * ============================================小米推送===============================================================
     */
    private static boolean shouldInitXM(Context mContext) {
        ActivityManager am = ((ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = mContext.getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 注册MiPush推送服务，建议在app启动的时候调用。
     *
     * @param mContext
     * @param appId
     * @param appKey
     * @param isDebug
     */
    private static void initXM(Context mContext, String appId, String appKey, boolean isDebug) {
        //初始化push推送服务
        if (shouldInitXM(mContext)) {
            MiPushClient.registerPush(mContext, appId, appKey);
        }
        //打开Log
        if (isDebug) {
            LoggerInterface newLogger = new LoggerInterface() {

                @Override
                public void setTag(String tag) {
                    // ignore
                }

                @Override
                public void log(String content, Throwable t) {
                    Log.d(TAG, content, t);
                }

                @Override
                public void log(String content) {
                    Log.d(TAG, content);
                }
            };
            Logger.setLogger(mContext, newLogger);
        } else {
            Logger.disablePushFileLog(mContext);
        }
    }

    /**
     * 销毁推送:调用unregisterPush()之后，服务器不会向app发送任何消息。
     *
     * @param aContext
     */
    public static void delPush(Context aContext) {
        if (BY_WHICH == BY_XM) {
            MiPushClient.unregisterPush(aContext);
            // 关闭MiPush推送服务，当用户希望不再使用MiPush推送服务的时候调用，调用成功之后，app将不会接收到任何MiPush服务推送的数据，直到下一次调用registerPush
        }


    }

    /**
     * 指定别名： 注册MiPush推送服务，建议在app启动的时候调用。
     *
     * @param aContext
     * @param alias    为指定用户设置别名
     *                 category扩展参数，暂时没有用途，直接填null
     */
    public static void setAlias(Context aContext, String alias) {
        if (BY_WHICH == BY_XM) {
            MiPushClient.setAlias(aContext, alias, null);
        }
    }

    /**
     * 取消别名： 开发者可以取消指定用户的某个别名，服务器就不会给这个别名推送消息了。
     *
     * @param aContext
     * @param alias    为指定用户设置别名
     *                 category扩展参数，暂时没有用途，直接填null
     */
    public static void unsetAlias(Context aContext, String alias) {
        if (BY_WHICH == BY_XM) {
            MiPushClient.unsetAlias(aContext, alias, null);
        }
    }

    public static void setUserAccount(Context applicationContext, String userAccount) {

    }

    /**
     * 开发者可以取消指定用户的某个userAccount，服务器就不会给这个userAccount推送消息了。
     *
     * @param applicationContext
     * @param userAccount        用户userAccount
     *                           category扩展参数，暂时没有用途，直接填null
     */
    public static void unsetUserAccount(Context applicationContext, String userAccount) {
    }

    /**
     * 加入某个主题
     *
     * @param aContext
     * @param topic    某个用户设置订阅的主题
     */
    public static void subscribe(Context aContext, String topic) {
        if (BY_WHICH == BY_XM) {
            MiPushClient.subscribe(aContext, topic, null);
        }
    }

    /**
     * 退出某个主题
     *
     * @param aContext
     * @param topic    某个用户取消订阅的主题
     */
    public static void unsubscribe(Context aContext, String topic) {
        if (BY_WHICH == BY_XM) {
            MiPushClient.unsubscribe(aContext, topic, null);
        }
    }

    /*
     * ===============================================推送相关的配置缓存============================================================
     */
    public static class Cache {
        /**
         * 保存用户的regId
         *
         * @param mRegId
         * @return
         */
        public static boolean setRegId(String mRegId) {
            try {
                SharedPreferences sharedPreferences = mContext.getSharedPreferences("OPushManager", Context.MODE_PRIVATE); //私有数据
                SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                editor.putString("RegId_" + APP_ID, mRegId);
                editor.apply();//提交修改
                Log.d(TAG, "推送regId = " + mRegId);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        /**
         * 读取用户的regId
         *
         * @return
         */
        public static String getRegId() {
            try {
                SharedPreferences sharedPreferences = mContext.getSharedPreferences("OPushManager", Context.MODE_PRIVATE); //私有数据
                return sharedPreferences.getString("RegId_" + APP_ID, "");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
    }
}
