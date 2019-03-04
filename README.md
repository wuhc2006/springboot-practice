# springboot-practice
this is only for springboot test.
与第三方资源的集成实例。
## springboot与redis集成
### 简单缓存
通过判断缓存中是否存在key值，如果不存在，再查找数据库
### 通过注解形式的缓存
全局配置@EnableCaching
方法名配置@Cacheable(value = "", key = "", condition = "")的方式
### 缓存文件
redis作为中间件，高速缓存临时文件，最终上传到文件服务器
## springboot与shiro + jwt 的登录授权
主要实现与shiro的集成，前端layui

