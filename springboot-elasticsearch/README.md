# springboot-shiro-jwt
1. 各个系统的版本

（1）elasticsearch-6.5.4免安装，直接双击elasticsearch.bat启动

……\elasticsearch-6.5.4\config\elasticsearch.yml配置如下

```yaml
#放开注释
cluster.name: my-application
node.name: node-1_

#新增注释
http.cors.enabled: true 
http.cors.allow-origin: "*"
node.master: true
node.data: true
network.host: 0.0.0.0  #允许外网访问
```
es在springboot的配置
```properties
#节点的地址 注意api模式下端口号是9300，千万不要写成9200
spring.data.elasticsearch.cluster-nodes=192.168.1.8:9300
# elasticsearch集群名称，默认的是elasticsearc，具体在配置文件中查看
spring.data.elasticsearch.cluster-name=my-application
#是否开启本地存储
spring.data.elasticsearch.repositories.enabled=true

```
(2)elasticsearch-head-master安装
````
参考https://www.cnblogs.com/hts-technology/p/8477258.html
注意：
1. 安装node.js
2. 如果npm install 时报 phantomjs-prebuilt@2.1.14 安装失败，执行以下命令
npm install phantomjs-prebuilt@2.1.14 --ignore-scripts
````


