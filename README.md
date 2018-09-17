# BuglyDemo
Bugly热更新接入脚本

### 优先阅读
[Bugly Android热更新使用指南](https://bugly.qq.com/docs/user-guide/instruction-manual-android-hotfix/?v=20180913155123)

[Bugly Android热更新详解](https://bugly.qq.com/docs/user-guide/instruction-manual-android-hotfix-demo/?v=20180709165613)

[Bugly Android 热更新常见问题](https://bugly.qq.com/docs/user-guide/faq-android-hotfix/?v=20180709165613)


### 使用说明
#### 1. 复制脚本到工程
![](https://raw.githubusercontent.com/angcyo/BuglyDemo/master/png/1.png)

#### 2. 在工程根目录下的`build.gradle`配置
![](https://raw.githubusercontent.com/angcyo/BuglyDemo/master/png/2.png)


#### 3. 在模块目录下的`build.gradle`配置
![](https://raw.githubusercontent.com/angcyo/BuglyDemo/master/png/3.png)

#### 4. 当你在打包apk时, 根目录下的`buglyApk`目录会手机基准包和补丁需要的`app-release-R.txt`和`app-release-mapping.txt`文件(如果有)
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
[Bugly后台](https://bugly.qq.com/v2/workbench/apps)上传补丁即可.

### 已验证
- [x] 单个类修改
- [x] 新增/修改String资源
- [x] 新增普通类

### ToDo
- [ ] 多渠道打包测试
- [ ] 加固打包测试
- [ ] 新增四大组件测试
- [ ] so文件修改测试
- [ ] 新增类测试

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
