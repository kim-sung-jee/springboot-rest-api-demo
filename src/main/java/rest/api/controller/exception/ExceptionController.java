package rest.api.controller.exception;


// import 생략

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.api.model.response.CommonResult;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping(value = "/entrypoint")
    public CommonResult entrypointException() {
        throw new CAuthenticationEntryPointException();
    }
    @GetMapping(value = "/accessdenied")
    public CommonResult accessdeniedException() {
        throw new AccessDeniedException("");
    }
}