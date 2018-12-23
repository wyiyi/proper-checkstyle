proper-checkstyle
=================

[![Build Status](https://travis-ci.org/propersoft-cn/proper-checkstyle.svg?branch=master)](https://travis-ci.org/propersoft-cn/proper-checkstyle)
[![Issue Count](https://codeclimate.com/github/propersoft-cn/proper-checkstyle/badges/issue_count.svg)](https://codeclimate.com/github/propersoft-cn/proper-checkstyle)
[![Maintainability](https://api.codeclimate.com/v1/badges/007c133edca4e4c7575a/maintainability)](https://codeclimate.com/github/propersoft-cn/proper-checkstyle/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/007c133edca4e4c7575a/test_coverage)](https://codeclimate.com/github/propersoft-cn/proper-checkstyle/test_coverage)

Customize Checkstyle checkers, inspired by https://github.com/blundell/CreateYourOwnCheckStyleCheck

依赖 proper-checkstyle 库
------------------------

参考 [Proper Enterprise Platform](https://github.com/propersoft-cn/proper-enterprise-platform/tree/v0.5.x)：
* 该库是基于 checkstyle 配置，在需要依赖 proper-checkstyle 库的配置中增加对该库的版本依赖，例如：dependencies.gradle：
````
libraries.checkstyle_proper = 'com.proper:proper-checkstyle:0.1.0-SNAPSHOT'
````
* 代码检查的配置 'gradle'文件中引入 checkstyle_proper 的包：
````
dependencies {
    checkstyle libraries.checkstyle_proper
}
````

配置 proper-checkstyle 规则
------------------------
配置'checkstyle.xml' 的文件，编译即可生效，请参考 (MethodLimitCheck.java)：
* 类名以 XXXCheck.java 结尾的文件，checkstyle会将类名自动补全；
````
   <module name="MethodLimit"/>
````
* 类中定义的变量需要在 'checkstyle.xml' 文件中配置；

````
   <property name="max" value="65"/>
````