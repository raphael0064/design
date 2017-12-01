策略模式

本文描述的第三个概念是策略设计模式。策略定义了通过不同方式完成相同事情的几个对象。完成任务的方式取决于采用的策略。举个例子说明，我们可以去一个国家。我们可以乘公共汽车，飞机，船甚至汽车去那里。所有这些方法将把我们运送到目的地国家。但是，我们将通过检查我们的银行帐户来选择最适应的方式。如果我们有很多钱，我们将采取最快的方式（可能是私人飞行）。如果我们没有足够的话，我们会采取最慢的（公车，汽车）。该银行账户作为确定适应策略的因素。

Spring在org.springframework.web.servlet.mvc.multiaction.MethodNameResolver类(过时，但不影响拿来研究)中使用策略设计模式。它是 MultiActionController(同样过时)的参数化实现。在开始解释策略之前，我们需要了解MultiActionController的实用性。这个类允许同一个类处理几种类型的请求。作为Spring中的每个控制器，MultiActionController执行方法来响应提供的请求。策略用于检测应使用哪种方法。解析过程在MethodNameResolver实现中实现，例如在同一个包中的ParameterMethodNameResolver中。方法可以通过多个条件解决：属性映射，HTTP请求参数或URL路径。

```
@Override
public String getHandlerMethodName(HttpServletRequest request) throws NoSuchRequestHandlingMethodException {
    String methodName = null;

    // Check parameter names where the very existence of each parameter
    // means that a method of the same name should be invoked, if any.
    if (this.methodParamNames != null) {
        for (String candidate : this.methodParamNames) {
            if (WebUtils.hasSubmitParameter(request, candidate)) {
                methodName = candidate;
                if (logger.isDebugEnabled()) {
                    logger.debug("Determined handler method '" + methodName +
                            "' based on existence of explicit request parameter of same name");
                }
                break;
            }
        }
    }

    // Check parameter whose value identifies the method to invoke, if any.
    if (methodName == null && this.paramName != null) {
        methodName = request.getParameter(this.paramName);
        if (methodName != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Determined handler method '" + methodName +
                        "' based on value of request parameter '" + this.paramName + "'");
            }
        }
    }

    if (methodName != null && this.logicalMappings != null) {
        // Resolve logical name into real method name, if appropriate.
        String originalName = methodName;
        methodName = this.logicalMappings.getProperty(methodName, methodName);
        if (logger.isDebugEnabled()) {
            logger.debug("Resolved method name '" + originalName + "' to handler method '" + methodName + "'");
        }
    }

    if (methodName != null && !StringUtils.hasText(methodName)) {
        if (logger.isDebugEnabled()) {
            logger.debug("Method name '" + methodName + "' is empty: treating it as no method name found");
        }
        methodName = null;
    }

    if (methodName == null) {
        if (this.defaultMethodName != null) {
            // No specific method resolved: use default method.
            methodName = this.defaultMethodName;
            if (logger.isDebugEnabled()) {
                logger.debug("Falling back to default handler method '" + this.defaultMethodName + "'");
            }
        }
        else {
            // If resolution failed completely, throw an exception.
            throw new NoSuchRequestHandlingMethodException(request);
        }
    }

    return methodName;
}

```

正如我们在前面的代码中可以看到的，方法的名称通过提供的参数映射，URL中的预定义属性或参数存在来解决（默认情况下，该参数的名称是action）。