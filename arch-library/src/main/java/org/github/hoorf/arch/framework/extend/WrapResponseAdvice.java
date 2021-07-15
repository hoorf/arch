package org.github.hoorf.arch.framework.extend;

import com.alibaba.fastjson.JSON;
import org.github.hoorf.arch.framework.common.RestResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class WrapResponseAdvice implements ResponseBodyAdvice {

    private static AntPathMatcher pathMatcher = new AntPathMatcher();

    @Value("${arch.result.ignore:#{null}}")
    private String[] ignorePath;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        if (null != ignorePath) {
            RequestMapping url = returnType.getMethodAnnotation(RequestMapping.class);
            String[] urlPaths = url.path();
            for (String urlPath : urlPaths) {
                for (String path : ignorePath) {
                    if (pathMatcher.match(path, urlPath)) {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (returnType.getGenericParameterType().equals(String.class)) {
            return JSON.toJSONString(RestResult.success(body).setMsg((String) body));
        }
        if (returnType.getGenericParameterType().equals(Void.TYPE)) {
            return RestResult.success();
        }
        RestResult result = RestResult.success();
        if (null != body) {
            if (body instanceof RestResult) {
                result = (RestResult) body;
            } else {
                result.setData(body);
            }
        }

        return result;
    }
}
