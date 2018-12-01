# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

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

 -dontwarn com.alibaba.**
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
 -keep class * implements com.alibaba.android.arouter.facade.template.IProvider

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
 -dontwarn com.facebook.**

 # =====================fresco================

 #转换JSON的JavaBean，类成员名称保护，使其不被混淆
 -keepclassmembernames class com.sxjs.jd.entities.** { *; }

 #auto view pager
 -keepclassmembernames class com.sxjs.common.widget.autoscrollviewpager.** { *; }