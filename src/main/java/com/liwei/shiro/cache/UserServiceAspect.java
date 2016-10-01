package com.liwei.shiro.cache;

import com.liwei.shiro.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

/**
 * Created by liwei on 16/9/22.
 */
@Service
@Aspect
@EnableAspectJAutoProxy // 开启 AOP ,作用同 <aop:aspectj-autoproxy/>
public class UserServiceAspect extends BaseCacheService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceAspect.class);
    /**
     * id 前缀
     */
    private String idPrefix = "id-";

    /**
     * 用户名前缀
     */
    private String usernamePrefix = "username-";

    public UserServiceAspect(){
        this.setCacheName("shiro-userCache");
    }

    /**
     * target 表明只针对某个类实现 AOP 代理
     */
    @Pointcut("target(com.liwei.shiro.service.impl.UserService)")
    public void userServicePointcut(){
    }

    /**
     * 增加
     * 删除
     * 登录的方法
     *
     * 这三个方法不应该被缓存
     */
    @Pointcut("execution(* add(..))|| execution(* update(..)) || execution(* login(..))")
    public void userPutPointcut(){
    }

    /**
     * 加载和按照用户名加载的方法要缓存起来
     */
    @Pointcut("execution(* load(..)) || execution(* loadByUsername(..))")
    public void userReadPointcut(){
    }

    @Pointcut(value = "execution(* delete(*)) && args(arg)",argNames = "arg")
    public void userEvictPointcut(Object arg){
    }

    /**
     * 当执行删除操作的时候的增强逻辑(目前暂时没有提供删除功能)
     * @param arg
     */
    @After(value = "userServicePointcut() && userEvictPointcut(arg)",argNames = "arg")
    public void userEvictAdvice(Object arg){
        logger.debug("------ UserServiceAspect ------ 删除增强 ----- 参数 ----- " + arg);
        super.evict(idPrefix + arg);
    }

    /**
     * 当发生增加、更新、登录操作的时候缓存一下对象(更新一次缓存)
     * @param rel
     */
    // 返回通知,可以访问到方法的返回值
    // 注意 AfterReturning 配置必须有argNames参数，且参数值和 returning 值一样，
    // 这样在织入代码里面便可通过 returning 的值获取被织入函数的返回值。
    @AfterReturning(pointcut = "userServicePointcut() && userPutPointcut()",returning = "rel")
    public void userPutAdvice(Object rel){
        // rel 表示返回值
        logger.debug("--- UserService 切面 ----- 返回值 => " + rel);
        // 首先使用返回通知访问到这个对象
        put((User) rel);
    }

    /**
     * 加载数据的时候的增强逻辑
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "userServicePointcut() && userReadPointcut()")
    public Object userReadPointcut(ProceedingJoinPoint pjp) throws Throwable{
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        Object arg = args.length > 0 ? args[0] : null;
        String key = null;
        boolean isId = false;
        if(methodName.equals("load")){
            isId = true;
            key = idPrefix + arg;
        }else if(methodName.equals("loadByUsername")){
            key = usernamePrefix + arg;
        }
        User user = null;
        if(isId){
            user = (User)super.get(key);
        }else {
            // 先根据用户名从缓存中找到 id
            String idKey = idPrefix + super.get(key);
            user = (User) super.get(idKey);
        }
        if(user!=null){
            return user;
        }
        return  pjp.proceed();
    }

    /**
     * 【重要】
     * 缓存一个对象的具体流程
     * 1、底层永远使用 id 前缀来缓存这个对象;
     * 2、通过其它属性访问对象的时候,缓存 id 属性
     * @param rel
     */
    private void put(User rel){
        super.put(idPrefix + rel.getId(),rel);
        // 建立了一个 用户名前缀和 id 之间的关系
        super.put(usernamePrefix + rel.getUsername(),rel.getId());
    }
}
