package com.tencent.tinker.loader;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Email:angcyo@126.com
 *
 * @author angcyo
 * @date 2018/09/14
 */
public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.angcyo.buglydemo.App",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

    protected SampleApplication(int tinkerFlags) {
        super(tinkerFlags);
    }

    protected SampleApplication(int tinkerFlags, String delegateClassName, String loaderClassName, boolean tinkerLoadVerifyFlag) {
        super(tinkerFlags, delegateClassName, loaderClassName, tinkerLoadVerifyFlag);
    }

    protected SampleApplication(int tinkerFlags, String delegateClassName) {
        super(tinkerFlags, delegateClassName);
    }
}
