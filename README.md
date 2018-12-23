proper-checkstyle
=================

[![Build Status](https://travis-ci.org/propersoft-cn/proper-checkstyle.svg?branch=master)](https://travis-ci.org/propersoft-cn/proper-checkstyle)
[![Issue Count](https://codeclimate.com/github/propersoft-cn/proper-checkstyle/badges/issue_count.svg)](https://codeclimate.com/github/propersoft-cn/proper-checkstyle)
[![Maintainability](https://api.codeclimate.com/v1/badges/007c133edca4e4c7575a/maintainability)](https://codeclimate.com/github/propersoft-cn/proper-checkstyle/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/007c133edca4e4c7575a/test_coverage)](https://codeclimate.com/github/propersoft-cn/proper-checkstyle/test_coverage)

Customize Checkstyle checkers, inspired by https://github.com/blundell/CreateYourOwnCheckStyleCheck

依赖 proper-checkstyle 库
------------------------
### Maven

````
<dependency>
     <groupId>com.proper</groupId>
     <artifactId>proper-checkstyle</artifactId>
     <version>0.1.0-SNAPSHOT</version>
</dependency>
````

### Gradle

````
'com.proper:proper-checkstyle:0.1.0-SNAPSHOT'
````

配置 proper-checkstyle 规则
------------------------
proper-checkstyle 库包含两个规则分别是：
* 方法数量检查，默认允许一个类中存在 30 个方法，可通过 max 属性设置，配置方式如下：
````
    <module name="MethodLimit">
       <property name="max" value="30"/>
    </module>
````

* Swagger 注解检查（仅检查通过注解方式配置的 controller，不检查 xml 方式配置的 controller）：
1. 类中存在注解 '@Controller'或 '@RestController'的基础上，是否存在Swagger '@Api' 注解；
1. 在1的基础上，方法中存在Spring 的 'RequestMapping'或以'Mapping'结尾的注解的同时，是否存在Swagger 的 '@ApiOperation' 注解

配置方式如下：
````
    <module name="SwaggerAnnotation"/>
````