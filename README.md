# mini项目文档——第四组

------



## 选题

题目一：**融合安全网关基线告警模型分析**

## 成员

张正天、关欣慰、冯啸林

## 需求解读

- 安全基线制定：需要获取网关日志，对流量、时延、客户端访问信息、请求状态进行提取处理，然后根据平均值及topN进行基线制定；并且将局部时段统计数据持久化，供于展示。
- 用户GUI友好性：登录界面，提供管理员入口；主界面，友好的数据可视化界面，显示时延、客户端访问统计、流量统计、访问状态统计；风险告警历史，让用户知晓告警具体信息。
- spring架构后台：登录校验，提供安全校验；数据请求处理，提供前端发来的供于展示的数据请求；告警接口，根据制定的安全基线进行判断，告警信息持久化，进行微信公众号告警；

## 需要的技术

- 基线制定：使用flink处理现有的orc日志数据
- 前端：echarts展示数据，html+css+jQuery
- 后端：spring boot部署；mybatis-plus，redis存储暂态数据，如token；jwt进行登录验证；

## 分工

前端：张正天

后端：关欣慰

数据处理：冯啸林

## 学习路线

**张正天：**

1.搭建微信小程序的基本知识学习（html，css,js等前端知识）

2.跟着视频学习做一个微信小程序的小项目->

3.完成题目的微信小程序搭建，并学习http协议和网络编程的基础知识

**关欣慰：**

JavaSE->MySQL基础->JDBC

JavaWeb->Spring->SpringMVC

->MyBatis->Maven->Ssm框架整合

**冯啸林：**

大数据平台套件

jwt

Kafka消息队列 

Flink实时计算

## 项目功能

- 身份验证
- 可视化展示
- 数据处理
- 告警

### 总览

![](http://qx61v1g4z.hn-bkt.clouddn.com/all_func.png)

### 可视化

![](http://qx61v1g4z.hn-bkt.clouddn.com/keshihua.png)

### 告警端

![](http://qx61v1g4z.hn-bkt.clouddn.com/wexin.png)

### 身份验证

![](http://qx61v1g4z.hn-bkt.clouddn.com/shenfen.png)

## 功能实现

### 1.安全基线

#### 根据读出的原始数据观察，选取需要的字段。

- 请求时延，通过时延反应出网络状态；
- 响应时延，通过时延反应出网络状态；
- 发送数据大小，如果有大量的流量产生，则会造成负载增大；
- user-agent，是否为正常的浏览器访问，如果不是，则视为程序脚本；
- http状态码，根据状态来反应网络状况和服务器运行状况。

#### 利用窗口聚合

采用以时间段划分的形式去计算更能反应一段时间的状态，利用flink的窗口聚合功能容易实现。根据事件时间，创建时间窗口。

#### 利用水印

原始数据并不是完全的时间顺序，存在一定的乱序；利用flink的水印机制，设置容忍值，可以一定程度上解决数据乱序的问题。

#### 基线制定

- 时延

统计每15秒请求与响应的平均值，然后持久化到mysql；

然后对所有时段的平均值再次求平均值，作为其中一个判断依据，若连续15个时段出现高于此平均值的现象，则视为1级延迟风险；

对时段进行top10统计，若某时段大于top10，则视为2级延迟风险；

对top10进行平均统计，若超过此平均值，则视为3级延迟风险；

- 发送数据量大小

同上

- user-agent

对于非浏览器端的客户端访问视为程序脚本（例如java、okhttp等），利用正则函数统计出每一分钟的非法客户端，然后对所有时段的总数求平均值；若连续出现10个时段高于此值，则视为1级脚本告警；

同样对时段进行top10统计，高于top10则视为2级脚本告警；

top10平均，高于此值视为3级脚本告警；

- http状态

统计每分钟内404、500数量，然后求出全时段404、500的平均值，若出现10次高于404平均值的连续时段，则视为1级页面丢失告警；若出现10次高于500平均值的连续时段，则视为4级持续性服务器内部异常；

得出404top10，某时段404超过此值，则视为2级页面丢失告警；再根据top10平均值，超过则视为3级页面丢失告警

500top10，若超过次值，视为4级服务器内部异常。



### 2.身份验证

使用jwt认证，颁发token；前台通过ajax提交用户登录信息，然后后台将会查找该用户，没有找到则反馈给前台；若存在用户，则根据加密盐和传入的密码进行base64加密比对是否正确，校验成功则返回token，浏览器端存在redis，客户端存在cookie中；后续请求需带上token，根据token校验身份；token过期需重新登录。



### 3.可视化展示

进入主页面后，会同时发送不同的ajax请求以获取不同的指标数据，然后将数据填充到echarts的option中并且在option中进行细节的修饰，其中在toolbox工具中的feature中调用dataview可以查看目前的数据，并进行可视化，前台有七个按钮，其中五个按钮是用来控制不同基线数据可视化的展现，一个按钮是用来进入admin界面，查看告警信息，可以自定义选中告警的类型，以及基线参考数据，最后一个按钮是用来退出登录。

### 4.告警端

微信access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token，由于微信每次返回的token有效期为2小时，为了避免后台业务逻辑各自反复请求token形成冲突，则实现方法定期获取和刷新access token，服务器启动后10秒后获取token，并采用redis存储，存储有效期为1小时55分钟，每1小时55分钟重新获取token存入redis，可实现服务的平滑过渡，sevice层与数据库交互时对数据进行基线判断，如为异常数据，从redis读出token，向微信指定的接口地址发送post格式的HTTP请求，实现对指定微信用户的消息推送。



## 联合调试

### 跨域请求问题

由于使用了前后端分离的模式开发，所以前后台运行的端口号不同。当前台发送请求时，受到同源策略的限制。

### 解决

后台设置全局配置类，默认允许所有的域名访问。
        