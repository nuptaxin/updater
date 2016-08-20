Updater for Java
===================
Java更新器。通过将其嵌入程序中或者单独引用，启动时与远程文件对比自动查找程序变化进行更新。

## 版本变更日志： ##
--0.0.0.1
Java更新器第一版
--1.0.0.1
优化UI、Bug修复、更改打包方式
--1.0.0.2
新增自动读取注册表并打开CMD命令扩展功能

## 运行方式： ##
1. 在程序目录下，使用命令行执行 mvn clean compile assembly:single，会在dist文件夹下生成updater-java-x.x.x.x.jar包。
2. 生成本地目录MD5文件：java –jar updater-java-x.x.x.x.jar –m E:\dist1
3. 获取帮助信息：java –jar updater-java-x.x.x.x.jar –h 
4. 启动更新程序：java –jar updater-java-x.x.x.x.jar –u http://127.0.0.1/update/updatertest.xml  E:/dist1 E:/dist1/bin/dmt-oracle.bat



