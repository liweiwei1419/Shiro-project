#

"用户"不直接和"权限"产生关系,而是通过"角色"作为桥梁。
因为"用户"具有了一些"角色","角色"上绑定了"权限","用户"才具有"权限"。


集成 Spring 的步骤
1、引入依赖 shiro-spring;
2、在 web.xml 把原来的过滤器和监听器改成代理;
3、添加 shiro-web.xml 的配置;
4、把 shiro-web.xml 这个配置文件添加到 Spring 的监听器中去;
5、把原来 Realm 中使用 InitServelt 加载的 UserService 组件改换成使用依赖注入。

配置 Shiro 默认的缓存(使用 Ehcache 实现)
小技巧:查找类 Command + O
1、添加 shiro-ehcache 接口依赖
2、添加 ehcache 实现依赖
3、在 Spring 的配置文件中声明 EhcacheManager
<!-- 配置缓存相关 -->
<bean id="ehCacheManager" class="org.apache.shiro.cache.ehcache.EhCacheManager"></bean>
4、在 classpath 下添加 ehcache 配置文件

5、在 Realm 中配置开启认证缓存和授权缓存,并且指定使用的缓存的名称.
(1)在 securityManager 中配置 cacheManager
<property name="cacheManager" ref="ehCacheManager"/>
(2)在 realm 中配置是否开启缓存
<!-- 配置缓存相关 -->
<property name="cachingEnabled" value="true"/>
<!-- 开启认证缓存-->
<property name="authenticationCachingEnabled" value="true"/>
<!-- 指定认证缓存的名字(与 ehcache.xml 中声明的相同) -->
<property name="authenticationCacheName" value="shiro-authenticationCache"/>
<!--开启授权缓存-->
<property name="authorizationCachingEnabled" value="true"/>
<!-- 指定授权缓存的名字(与 ehcache.xml 中声明的相同) -->
<property name="authorizationCacheName" value="shiro-authorizationCache"/>

配置了上面的步骤以后,我们发现是有问题的,在使用 <shiro:principal property="nickname"/> 标签的时候,
我们发现认证缓存在退出登录的时候控制台打印如下。

2016-09-21 09:27:27,244 [qtp1501513951-23] INFO  [com.liwei.shiro.realm.MyRealm] - 清除认证时候的缓存
2016-09-21 09:27:27,244 [qtp1501513951-23] INFO  [com.liwei.shiro.realm.MyRealm] - liwei , User{id=1, username='liwei', password='be78263da332dc2c7005f7551d7e57cd', nickname='李威888888', status=1}
2016-09-21 09:27:27,244 [qtp1501513951-23] INFO  [com.liwei.shiro.realm.MyRealm] - 清除授权时候的缓存
2016-09-21 09:27:27,244 [qtp1501513951-23] INFO  [com.liwei.shiro.realm.MyRealm] - User{id=1, username='liwei', password='be78263da332dc2c7005f7551d7e57cd', nickname='李威888888', status=1} , org.apache.shiro.authz.SimpleAuthorizationInfo@616d8742


我们可以看出:

授权时候的 key 值是一个对象
认证时候的 key 值是用户名


<aop:aspectj-autoproxy/> 的作用和 @EnableAspectJAutoProxy 是一样的。


上面我们仅仅只是对 Realm 中的认证和授权方法使用了缓存技术。
下面,我们让 UserService 这个实现类通过 Spring AOP 的方式实现缓存,避免同样的数据多次访问数据库。。
目标:查询列表的方法添加了缓存。

添加了缓存以后,相应的增加、删除、修改的方法要清空缓存。

Spring MVC 使用 AOP 拦截控制器层须要注意的事项:
http://blog.csdn.net/pyxly1314/article/details/47152827

System.out.println("------ 方法签名 ------ " + pjp.getSignature());
System.out.println("------ 方法参数 ------ " + Arrays.toString(pjp.getArgs()));
System.out.println("------ 方法名 ------ " + pjp.getSignature().getName());
System.out.println("------ 方法所在的类的全类名 ------ " + pjp.getSignature().getDeclaringTypeName());


为本项目添加事务支持,此时已经产生了抛出了异常,但是数据一致性没有保证。