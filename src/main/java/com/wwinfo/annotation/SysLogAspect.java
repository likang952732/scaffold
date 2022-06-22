package com.wwinfo.annotation;

import com.wwinfo.model.TSyslog;
import com.wwinfo.service.TSyslogService;
import com.wwinfo.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

/**
* @Description:    系统日志：切面处理类
* @CreateDate:     2021/11/7 16:27
* @UpdateDate:     2021/11/7 16:27
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    @Autowired
    private TSyslogService sysLogService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.wwinfo.annotation.MyLog)")
    public void logPoinCut() {
        log.info("切面11");
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        //保存日志
        TSyslog syslog = new TSyslog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            // 操作动作
            syslog.setOperate(myLog.operate());

            // 对象类型
            syslog.setObjectType(myLog.objectType());

            // 对象名称
            syslog.setObjectName(myLog.objectName());

            // 对象描述 -- 功能描述
            String descript = myLog.descript();
            String description = null;
            try {
                description = executeTemplate(descript, joinPoint);
            } catch (Exception e) {
                log.error("获取description异常: {}", e);
            }
            syslog.setDescription(description);
        }

        //获取请求的类名
        /*String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        syslog.setObjectName(className + "." + methodName);*/

        //请求的参数
        /*Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        syslog.setParams(params);*/

        //获取用户名
        Optional.ofNullable(UserUtil.getCurrentUser()).ifPresent(e -> syslog.setUserName(UserUtil.getCurrentUser().getUserName()));

        //获取用户ip地址
        /*HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        sysLog.setIp(IPUtils.getIpAddr(request));*/

        log.info("用户{} 进行了{} 操作", syslog.getUserName(), syslog.getDescription());
       // syslog.set(new Date());
        sysLogService.add(syslog);
    }

    private String executeTemplate(String descript, JoinPoint joinPoint)throws Exception{
        ExpressionParser parser = new SpelExpressionParser();
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        String[] params = discoverer.getParameterNames(method);
        Object[] args = joinPoint.getArgs();
        EvaluationContext context = new StandardEvaluationContext();
        for(int len = 0; len < params.length; len++){
            context.setVariable(params[len], args[len]);
        }
        return parser.parseExpression(descript, new TemplateParserContext()).getValue(context, String.class);
    }

}
