package com.keer.job.admin.controller.resolver;

import com.keer.job.admin.core.util.JacksonUtil;
import com.keer.job.biz.model.ReturnT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebExceptionResolver implements HandlerExceptionResolver {
    private static transient Logger logger = LoggerFactory.getLogger
            (WebExceptionResolver.class);

    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object hander,
                                         Exception e) {
        logger.error("WebExceptionResolver:{}", e);

        ModelAndView modelAndView = new ModelAndView();
        HandlerMethod handlerMethod = (HandlerMethod) hander;
        ResponseBody responseBody = handlerMethod.getMethodAnnotation
                (ResponseBody.class);
        if (responseBody != null) {
            response.setContentType("application/json;charset=UTF-8");
            modelAndView.addObject("result", JacksonUtil.writeValueAsString(
                    new ReturnT<String>(500, e.toString().replaceAll("\n",
                            "<br/>"))));
            modelAndView.setViewName("/common/common.result");
        } else {
            modelAndView.addObject("exceptionMsg", e.toString().replaceAll
                    ("\n", "<br/>"));
            modelAndView.setViewName("/common/common.exception");
        }
        return modelAndView;
    }
}
