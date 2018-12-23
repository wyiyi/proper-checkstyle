proper-checkstyle
=================

[![Build Status](https://travis-ci.org/propersoft-cn/proper-checkstyle.svg?branch=master)](https://travis-ci.org/propersoft-cn/proper-checkstyle)
[![Issue Count](https://codeclimate.com/github/propersoft-cn/proper-checkstyle/badges/issue_count.svg)](https://codeclimate.com/github/propersoft-cn/proper-checkstyle)
[![Maintainability](https://api.codeclimate.com/v1/badges/007c133edca4e4c7575a/maintainability)](https://codeclimate.com/github/propersoft-cn/proper-checkstyle/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/007c133edca4e4c7575a/test_coverage)](https://codeclimate.com/github/propersoft-cn/proper-checkstyle/test_coverage)

Customize Checkstyle checkers, inspired by https://github.com/blundell/CreateYourOwnCheckStyleCheck

依赖 proper-checkstyle 库
------------------------

 在 'gradle' 的配置文件中增加对 checkstyle_proper 的版本依赖，例如：dependencies.gradle：
````
libraries.checkstyle_proper = 'com.proper:proper-checkstyle:0.1.0-SNAPSHOT'
````

配置 proper-checkstyle 规则
------------------------
在类中需定义变量并赋予值，通过配置'checkstyle.xml' 的文件，编译即可生效，请参考 MethodLimitCheck.java：
* 类名以 XXXCheck.java 结尾的文件，checkstyle会将类名自动补全；
````
    private int max = 30;
    public void setMax(int max) {
        this.max = max;
    }
````
* 类中定义的变量需要在 'checkstyle.xml' 文件中配置即可生效；
````
    <module name="MethodLimit">
       <property name="max" value="65"/>
    </module>
````