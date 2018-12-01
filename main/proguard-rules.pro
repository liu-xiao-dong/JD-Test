# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\soft\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# okhttp
-dontwarn okhttp3.**

#retrofit
-dontwarn retrofit2.**
-dontwarn okio.**

#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

# =====================fresco================

-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**
-dontwarn com.facebook.infer.**

# =====================fresco================

#转换JSON的JavaBean，类成员名称保护，使其不被混淆
-keepclassmembernames class com.sxjs.jd.entities.** { *; }

#auto view pager
-keepclassmembernames class com.sxjs.common.widget.autoscrollviewpager.** { *; }

#ARouter
-keep public class com.alibaba.android.arouter.routes.**{*;}
-dontwarn com.alibaba.android.arouter.**
-dontwarn com.alibaba.fastjson.**
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}



