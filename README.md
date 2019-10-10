
# 引入
## Step 1. Add the JitPack repository to your build file
  Add it in your root build.gradle at the end of repositories:

    allprojects {
      repositories {
        ...
        maven { url 'https://www.jitpack.io' }
      }
    }

## Step 2. Add the dependency
    dependencies {
            implementation 'com.github.sinothk:Push8XM:1.19.1010'
    }
# 使用

##  Application
    public static final String APP_ID = "2882303761517692269";
    public static final String APP_KEY = "5791769242269";

    @Override
    public void onCreate() {
        super.onCreate();

        OPushManager.newPush(this, APP_ID, APP_KEY, true);
    }
  
##  JAVA
      String mRegId = OPushManager.Cache.getRegId();
        if (mRegId == null) {
        }

    //        OPushManager.newPush(this, APP_ID, APP_KEY, true);
    //        OPushManager.delPush(getApplicationContext());

            OPushManager.setAlias(getApplicationContext(), "381518188@qq.com");
    //        OPushManager.unsetAlias(getApplicationContext(), "381518188@qq.com");
            // 设置推送
            OPushManager.setUserAccount(getApplicationContext(), "381518188");
    //        OPushManager.unsetUserAccount(getApplicationContext(), "381518188");

            OPushManager.subscribe(getApplicationContext(), "宠物ID");
    //        OPushManager.unsubscribe(getApplicationContext(), "宠物ID");
    
##  清单文件
        <uses-permission android:name="android.permission.INTERNET" />
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
        <uses-permission android:name="android.permission.READ_PHONE_STATE" />
        <uses-permission android:name="android.permission.GET_TASKS" />
        <uses-permission android:name="android.permission.VIBRATE" />

        <permission
            android:name="com.sinothk.push.demo.permission.MIPUSH_RECEIVE"
            android:protectionLevel="signature" />
        <!--这里com.xiaomi.mipushdemo改成app的包名-->
        <uses-permission android:name="com.sinothk.push.demo.permission.MIPUSH_RECEIVE" />
        <!--这里com.xiaomi.mipushdemo改成app的包名-->
        
        <!--推送接收者-->
        <receiver
            android:name="com.sinothk.push.OPushMessageReceiver"
            android:exported="true">
            <!--这里com.xiaomi.mipushdemo.DemoMessageRreceiver改成app中定义的完整类名-->
            <intent-filter>
                <action android:name="com.xiaomi.mipush.RECEIVE_MESSAGE" />
            </intent-filter>
            <intent-filter>
                <action android:name="com.xiaomi.mipush.MESSAGE_ARRIVED" />
            </intent-filter>
            <intent-filter>
                <action android:name="com.xiaomi.mipush.ERROR" />
            </intent-filter>
        </receiver>
