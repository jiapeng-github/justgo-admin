package com.justgo.admin.controller.advice;

import com.alibaba.fastjson.JSONObject;
import com.justgo.admin.dto.base.BaseResponseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

import static com.justgo.admin.enums.AdminResponseEnum.PARAMETER_IS_NULL;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalControllerAdvice {


    private static Logger logger = LogManager.getLogger(GlobalControllerAdvice.class);

    /**
     * 没有权限
     *
     * @param response
     * @return
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public String unauthorized(HttpServletResponse response, HttpServletRequest request) {
        String asynch = request.getHeader("X-Requested-With");
        if (asynch == null) {
            return "/security/authority/authorization";
        }
        return "/security/authority/authorization-json";
    }




    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public String handle404Error(NoHandlerFoundException e, HttpServletRequest request) {
        String asynch = request.getHeader("X-Requested-With");
        if (!Objects.equals(asynch, "")) {
            return "/sys/404";
        }
        return "/sys/404-json";
    }

    @ExceptionHandler(Exception.class)
    public String error(Exception e, HttpServletRequest request) {
        logger.error("发生异常!!", e);
        String asynch = request.getHeader("X-Requested-With");
        if (!Objects.equals(asynch, "")) {
            return "/sys/500";
        }
        return "/sys/500-json";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponseDTO methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) throws Exception {
        String uri = request.getRequestURI();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        JSONObject jsonObject = new JSONObject();
        fieldErrors.forEach(fieldError -> jsonObject.put(fieldError.getField(), fieldError.getDefaultMessage()));
        String errorDesc = jsonObject.toJSONString();
        logger.info("接收到\"{}\"的请求,参数验证错误-->{}", uri, errorDesc);
        return new BaseResponseDTO<>(jsonObject, PARAMETER_IS_NULL);
    }

}
