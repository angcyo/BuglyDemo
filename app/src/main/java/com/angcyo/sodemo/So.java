package com.angcyo.sodemo;

import com.angcyo.buglydemo.App;
import com.tencent.bugly.beta.Beta;

/**
 * Email:angcyo@126.com
 *
 * @author angcyo
 * @date 2018/09/18
 */
public class So {
    static {
        //System.loadLibrary("native-lib");
        Beta.loadArmV7Library(App.application, "native-lib");
    }

    public static native String stringFromJNI();
}
