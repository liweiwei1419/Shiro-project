#

"用户"不直接和"权限"产生关系,而是通过"角色"作为桥梁。
因为"用户"具有了一些"角色","角色"上绑定了"权限","用户"才具有"权限"。


集成 Spring 的步骤
1、引入依赖 shiro-spring;
2、在 web.xml 把原来的过滤器和监听器改成代理;
3、添加 shiro-web.xml 的配置;
4、把 shiro-web.xml 这个配置文件添加到 Spring 的监听器中去;
5、把原来 Realm 中使用 InitServelt 加载的 UserService 组件改换成使用依赖注入。