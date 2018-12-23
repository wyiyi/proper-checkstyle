proper-checkstyle
=================

[![Build Status](https://travis-ci.org/propersoft-cn/proper-checkstyle.svg?branch=master)](https://travis-ci.org/propersoft-cn/proper-checkstyle)
[![Issue Count](https://codeclimate.com/github/propersoft-cn/proper-checkstyle/badges/issue_count.svg)](https://codeclimate.com/github/propersoft-cn/proper-checkstyle)
[![Maintainability](https://api.codeclimate.com/v1/badges/007c133edca4e4c7575a/maintainability)](https://codeclimate.com/github/propersoft-cn/proper-checkstyle/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/007c133edca4e4c7575a/test_coverage)](https://codeclimate.com/github/propersoft-cn/proper-checkstyle/test_coverage)

Customize Checkstyle checkers, inspired by https://github.com/blundell/CreateYourOwnCheckStyleCheck

依赖 proper-checkstyle 库
------------------------
````
libraries.checkstyle_proper = 'com.proper:proper-checkstyle:0.1.0-SNAPSHOT'
````

配置 proper-checkstyle 规则
------------------------
*在类中支持参数配置，参数则需要在 'checkstyle.xml' 文件中配置，例如：MethodLimitCheck.java：
````
    private int max = 30;
    public void setMax(int max) {
        this.max = max;
    }
````
````
    <module name="MethodLimit">
       <property name="max" value="65"/>
    </module>
````
* 类中参数内置，则需要配置，例如：SwaggerAnnotationCheck.java
````
    <module name="SwaggerAnnotation"/>
````