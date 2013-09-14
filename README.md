Campus
======
Here is a pile of junk,but it will be excellent!

# **Campus Android客户端项目解析** #

## **一、工程目录结构** ##

根目录<br>
>├ code <br> 
>├ docs <br>
>├ README.md <br>

目录简要解释<br>
根目录<br>
>├ code --源代码 <br> 
>├ docs --文档 <br>
>├ README.md --项目帮助及项目信息 <br>

## **二、源代码目录结构** ##
code<br>
>├ CampusPrime  andrdoid客户端源代码<br> 
>├ deps 工程依赖库文件<br>

**1、CampusPrime/src/目录**<br>

> src <br>

>├ com.campus.prime.adapter --存放adapter类的包<br>
>├ com.campus.prime.app --存放app全局设置类的包<br>
>├ com.campus.prime.utils --存放工具类的包<br>
>├ com.campus.prime.constant --存放常量，配置和api接口等类的包<br>
>├ com.campus.prime.database  -- database层的类的包<br>
>├ com.campus.prime.model --实体类包<br>
>├ com.campus.prime.protocol --protocol层类的包<br>
>├ com.campus.prime.slidingmenu --滑动菜单相关类包<br>
>├ com.campus.prime.ui --界面相关的包，activity的类<br>
>├ com.campus.prime.ui.fragment --fragment相关的基类包<br>
>├ com.campus.prime.ui.widget --自定义view组件包<br>


** 2、CampusPrime/libs/目录**<br>
libs目录用于存放项目引用的第三方jar包。

libs目录里的jar包文件：

libs
>├  android-support-v4.jar --v4兼容包<br>
>├  gson-2.2.2.jar --解析json的包<br>
>├  mas.jar --百度框架sdk<br>
>├  bcs-sdk-java_1.4.5.jar --百度云存储SDK<br>
>├  
>├  
>├  
