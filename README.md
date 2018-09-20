# BuglyDemo
Bugly热更新接入脚本

### 优先阅读
[Bugly Android热更新使用指南](https://bugly.qq.com/docs/user-guide/instruction-manual-android-hotfix/)

[Bugly Android热更新详解](https://bugly.qq.com/docs/user-guide/instruction-manual-android-hotfix-demo/)

[Bugly Android 热更新常见问题](https://bugly.qq.com/docs/user-guide/faq-android-hotfix/)

[热更新API接口](https://bugly.qq.com/docs/user-guide/api-hotfix/)


### 使用说明
#### 1. 复制脚本到工程
![](https://raw.githubusercontent.com/angcyo/BuglyDemo/master/png/1.png)

#### 2. 在工程根目录下的`build.gradle`配置
![](https://raw.githubusercontent.com/angcyo/BuglyDemo/master/png/2.png)
`apply from: './bugly/bugly_config.gradle'`


#### 3. 在模块application目录下的`build.gradle`配置
支持同时在`application`和`library`中设置, 脚本自动区分.

![](https://raw.githubusercontent.com/angcyo/BuglyDemo/master/png/3.png)
`apply from: '../bugly/bugly_config.gradle'`

到此脚本配置完成, 打包时可以生成带有`tinkerId`的基准包和`app-release-R.txt`和`app-release-mapping.txt`文件(如果有)

#### 4. 当你在打包apk时, 根目录下的`buglyApk`目录会收集基准包和补丁需要的`app-release-R.txt`和`app-release-mapping.txt`文件(如果有)
![](https://raw.githubusercontent.com/angcyo/BuglyDemo/master/png/4.png)

#### 5.生成补丁
生成补丁之前, 一定需要:
1. 带有`tinkerId`的基准包.(配置脚本后, 生成的apk都会有)
2. `app-release-R.txt`文件(有资源就会有),`app-release-mapping.txt`文件(混淆后有)

配置脚本:

![](https://raw.githubusercontent.com/angcyo/BuglyDemo/master/png/5.png)

`bugly_patch_apk_dir`:基准包和那2个文件所在的相对路径(默认在`根目录/buglyApk`中), 打包APK后, 会自动生成.

`bugly_patch_apk_name`:需要打补丁的基准包名称(包含后缀的文件名,相对于`bugly_patch_apk_dir`的路径)

执行脚本,即可生成补丁:

![](https://raw.githubusercontent.com/angcyo/BuglyDemo/master/png/6.png)

补丁会出现在`根目录/buglyApk`中, 和`bugly_patch_apk_dir`中.各有一份.

之后在
[Bugly后台](https://bugly.qq.com/v2/workbench/apps)上传补丁,立即下发,即可.


### 脚本干了啥?
针对官方的`tinker-support.gradle`做了一组封装,完全可以摒弃不用.
提供了一些便利性:
- [x] 根目录的`build.grale`和模块的`build.grale`统一配置,脚本自动区分
- [x] 将配置参数, 统一到`bugly_config.gradle`脚本文件入口,方便修改
- [x] 自动从`build/outputs`目录收集补丁文件到指定目录下, 省去查找消耗
- [x] 自动标志创建过补丁文件的基准包目录, 防止被误删.
- [x] 自动清理多次打包`tinker-support.gradle`收集的冗余文件.


### 已验证
- [x] 单个类修改
- [x] 新增/修改String资源
- [x] 新增普通类

### ToDo
- [x] 多渠道打包测试 `美团 walle 通过`
- [x] 加固打包测试 `2018-9-20 360, 乐固 通过`
- [x] 新增四大组件测试 `Activity通过`
- [x] so文件修改测试 `需要使用Beta.loadLibrary("mylib")`
- [x] 新增类测试 `Activity, View, 普通类, 通过`

### 注意 [QA](https://bugly.qq.com/docs/user-guide/faq-android-hotfix/?v=20180709165613)
1. Debug 时不支持instant run
2. 补丁的检查会在杀掉进程重启后执行; 补丁的生效, 会在下一次重启.
3. 当看到log, I/CrashReport: Tinker patch success, result: 表示补丁生效.重启可看到效果

```groovy
//补丁更新相关日志
com.angcyo.buglydemo I/CrashReport: onUpgradeReceived: title: 
    newFeature: 测试9
    publishTime: 0
    publishType: 0
    appBasicInfo: {
        appId: 44e2baffd2
        platformId: 1
        versionCode: 0
        versionName: null
        buildNo: 0
        iconUrl: null
        apkId: 0
        channelId: null
        md5: 2e753f98f482b48cc2d218d8fb8237915149a0e0
        sdkVer: 
        bundleId: null
    }
    apkBaseInfo: {
        apkMd5: 2e753f98f482b48cc2d218d8fb8237915149a0e0
        apkUrl: https://s.beta.gtimg.com/rdmimg/hot_patch/44e2baffd2/226190d8-08d3-4cac-90c0-7ae4561ea6af.zip
        manifestMd5: 
        fileSize: 7071
        signatureMd5: 
    }
    updateStrategy: 0
    popTimes: 0
    popInterval: 0
    diffApkInfo: {
        null}
    netType: null
    reserved: 1, {
        (
            H2
            5
        )
    }
    strategyId: 377c79b8-3d99-4072-a19a-95ed401842ac
    status: 1
    updateTime: 1537155350000
    updateType: 3
     [type: 3]
com.angcyo.buglydemo W/CrashReport: task start com.tencent.bugly.proguard.t
com.angcyo.buglydemo D/CrashReport: [Database] insert dl_1002 success.
com.angcyo.buglydemo I/chatty: uid=10168(com.angcyo.buglydemo) BETA_SDK_DOWNLO identical 1 line
com.angcyo.buglydemo D/CrashReport: [Database] insert dl_1002 success.
com.angcyo.buglydemo I/CrashReport: patch download success !!!
com.angcyo.buglydemo D/CrashReport: copy /data/user/0/com.angcyo.buglydemo/app_tmpPatch/226190d8-08d3-4cac-90c0-7ae4561ea6af.zip to /data/user/0/com.angcyo.buglydemo/app_tmpPatch/tmpPatch.apk success!
    delete temp file
com.angcyo.buglydemo D/Tinker.TinkerManager: onDownloadSuccess.
com.angcyo.buglydemo D/Tinker.TinkerManager: check if has new patch.
com.angcyo.buglydemo D/Tinker.TinkerManager: has new patch.
com.angcyo.buglydemo D/Tinker.TinkerManager: starting patch.
com.angcyo.buglydemo I/Tinker.TinkerPatchListener: receive a patch file: /data/user/0/com.angcyo.buglydemo/app_tmpPatch/tmpPatch.apk, file size:7071
com.angcyo.buglydemo I/Tinker.TinkerPatchListener: get platform:null
com.angcyo.buglydemo I/Tinker.TinkerPatchService: run patch service by job scheduler.
com.angcyo.buglydemo I/Tinker.TinkerPatchService: check if patch service is running.
com.angcyo.buglydemo W/Tinker.TinkerPatchService: patch service is not running, retry with IntentService.
com.angcyo.buglydemo I/Tinker.TinkerPatchService: run patch service by intent service.
com.angcyo.buglydemo I/Tinker.TinkerPatchService: successfully start patch service with IntentService.
com.angcyo.buglydemo I/Tinker.TinkerResultService: TinkerResultService receive result: 
    PatchResult: 
    isSuccess:true
    rawPatchFilePath:/data/user/0/com.angcyo.buglydemo/app_tmpPatch/tmpPatch.apk
    costTime:5513
    patchVersion:f0148dd75d2538d441b138e6f5b75567
com.angcyo.buglydemo I/Process: Sending signal. PID: 15427 SIG: 9
com.angcyo.buglydemo W/Tinker.DefaultTinkerResultService: deleteRawPatchFile rawFile path: /data/user/0/com.angcyo.buglydemo/app_tmpPatch/tmpPatch.apk
com.angcyo.buglydemo I/CrashReport: Tinker patch success, result: 
    PatchResult: 
    isSuccess:true
    rawPatchFilePath:/data/user/0/com.angcyo.buglydemo/app_tmpPatch/tmpPatch.apk
    costTime:5513
    patchVersion:f0148dd75d2538d441b138e6f5b75567
com.angcyo.buglydemo I/Tinker.PatchFileUtil: safeDeleteFile, try to delete path: /data/user/0/com.angcyo.buglydemo/app_tmpPatch/tmpPatch.apk
com.angcyo.buglydemo I/Tinker.TinkerResultService: tinker wait screen to restart process
com.angcyo.buglydemo D/Tinker.DefaultAppLike: onTrimMemory level:5
```


### Bugly SDK 简要接入说明

#### enableProxyApplication = false 的情况
#### 1.自定义Application
```
public class BuglyApplication extends TinkerApplication {
    public BuglyApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL,
        "com.angcyo.ApplicationLike",//真正自己自定义的Application
        "com.tencent.tinker.loader.TinkerLoader", 
        false);
    }
}

//参数解析
参数1：tinkerFlags 表示Tinker支持的类型 dex only、library only or all suuport，default: TINKER_ENABLE_ALL
参数2：delegateClassName Application代理类 这里填写你自定义的ApplicationLike
参数3：loaderClassName Tinker的加载器，使用默认即可
参数4：tinkerLoadVerifyFlag 加载dex或者lib是否验证md5，默认为false
```


#### 2.自定义ApplicationLike
```
public class SampleApplicationLike extends DefaultApplicationLike {

    public static final String TAG = "Tinker.SampleApplicationLike";

    public SampleApplicationLike(Application application, int tinkerFlags,
            boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
            long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(getApplication(), "xxx", false);
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        
        //开发设备
        Bugly.setIsDevelopmentDevice(base, !"release".equalsIgnoreCase(BuildConfig.BUILD_TYPE));
        
        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

}
```

#### enableProxyApplication = true 的情况
```
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(this, "900029763", false);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);


        // 安装tinker
        Beta.installTinker();
    }

}

```