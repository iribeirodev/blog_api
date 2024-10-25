package com.iribeirodev.meu_blog_api.infrastructure.handlers;

import java.util.List;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import com.iribeirodev.meu_blog_api.responses.APIResponse;

@ControllerAdvice(annotations = RestController.class)
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        APIResponse apiResponse = new APIResponse();
        
        if (body == null || (body instanceof List && ((List<?>) body).isEmpty())) {
            response.setStatusCode(HttpStatus.NOT_FOUND);
            apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
            apiResponse.setMessage("Resource not found");
            apiResponse.setData(null);
            return apiResponse;
        }
        if (body instanceof APIResponse) {
            return body;
        }

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(body);
        return apiResponse;
    }

    // Handler de exceções
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public APIResponse handleException(Exception ex) {
        APIResponse response = new APIResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Erro de requisição");
        response.setData(null);
        return response;
    }
}
