# 接口SDK

方法及参数介绍

| 方法名          | 参数 | 返回        | 功能介绍 |
| --------------- | ---- | ----------- | -------- |
| getNameByRandom | 无   | String name | 随机     |
|                 |      |             |          |


## 接口调用演示

随机取名

```java
String accessKey="your accessKey";
String secretKey="your secretKey";
SakuraClient sakuraClient=new SakuraClient(accessKey,secretKey);
sakuraClient.getNameByRandom();
```
