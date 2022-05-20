package rest.api.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rest.api.advice.exception.CUserNotFoundException;
import rest.api.model.response.CommonResult;
import rest.api.service.ResponseService;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseService responseService;

    private final MessageSource messageSource;
    
    // exception 발생하면 해당 Handler로 처리하겠다고 명시
    // 어떤 exception이 발생할 때 handler를 적용할 것인디 exception class를 인자로
    // exception.class 는 최상위 예외처리 객체이므로 다른 exceptionhandler에서 걸러지지
    //않은 예외가 있으면 최종으로 이 handler를 거쳐 처리가 된다.
    // method 이름도 defaultexception으로 만들엇음
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request,Exception e){
        return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")),getMessage("unKnown.msg"));
    }

    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request,CUserNotFoundException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("userNotFound.code")),getMessage("userNotFound.msg"));
    }


    private String getMessage(String code){
        return getMessage(code,null);
    }
    private String getMessage(String code,Object[] args){
        return messageSource.getMessage(code,args, LocaleContextHolder.getLocale());
    }

}
