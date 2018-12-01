# JD-Test
仿京东app 采用组件化架构 屏幕适配方案可以较好解决多分辨率及同分辨率不同dpi适配；
全新组件化架构升级，相比之前的方案模块间更为解耦且使用更为方便；

### 声明 ： 本项目资源采用抓包获取，仅供学习交流使用 。





### apk安装 ：
[https://github.com/liu-xiao-dong/JD-Test/raw/master/app/app-release.apk](https://github.com/liu-xiao-dong/JD-Test/raw/master/app/app-release.apk)



![JD-Test](https://github.com/liu-xiao-dong/JD-Test/blob/master/screenshot/screen_home.jpg?raw=true)


### Specs
  [![API](https://img.shields.io/badge/API-12%2B-blue.svg?style=flat)](https://img.shields.io/badge/API-12%2B-blue.svg?style=flat) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


本项目为仿京东项目，资源为抓包获取，项目框架采用路由框架 ARouter 进行模块间通讯，以功能模块进行划分的组件化开发 ，模块内部采用参考google开源的mvp架构 ，
核心框架 包含 retrofit 、rxjava 、dagger2 、fresco 以及个人开源的诸多优秀项目；当然现成的轮子也有不合适的地方，在这些轮子的基础上修改以及自己造轮子组成了
现有的项目，这套架构也是我应用与项目中的架构，后期也会不断扩展维护 ，欢迎大家提issues ，喜欢就直接拿去用 ，绝不收取任何费用（好吧 ， 想收也没人给 ^-^！） 。
话说一切没有gif图的项目都是耍流氓，上图：



![JD-Test](https://github.com/liu-xiao-dong/JD-Test/blob/master/screenshot/home1.gif?raw=true) 


***
再来几张非主流分辨率截图

![JD-Test](https://github.com/liu-xiao-dong/JD-Test/blob/master/screenshot/first.png?raw=true) ![JD-Test](https://github.com/liu-xiao-dong/JD-Test/blob/master/screenshot/second.png?raw=true)  
![JD-Test](https://github.com/liu-xiao-dong/JD-Test/blob/master/screenshot/third.png?raw=true) ![JD-Test](https://github.com/liu-xiao-dong/JD-Test/blob/master/screenshot/480x854.png?raw=true) 


项目架构如下图：

# 旧的架构
![JD-Test](https://github.com/liu-xiao-dong/JD-Test/blob/master/screenshot/structer.png?raw=true)

# 最新架构 

![JD-Test](https://github.com/liu-xiao-dong/JD-Test/blob/master/screenshot/structer.jpg?raw=true) 


架构相关：app只作为壳存在，除了包含MyApplication、SplashActivity及跳往其它module的测试页面，不包含任何其它逻辑
功能模块之间跳转还是通过ARouter,模块间服务接口暴露于app_common中，使用服务的模块通过ARouter获取服务，模块之间完全解
耦；各模块中有xxxModule类，主要承担应用启动时的各模块初始化，也是通过ARouter获取调用；本次架构主要由ARouter承担大部分功能实现
再次跪谢！在项目build.gradle中配置需要参与编译的模块；具体使用见源码！

## 项目持续更新中...  感兴趣就star


# License

```
Copyright 2017 aritraroy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```