package rest.api.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rest.api.advice.exception.CUserNotFoundException;
import rest.api.entity.User;
import rest.api.model.response.CommonResult;
import rest.api.model.response.ListResult;
import rest.api.model.response.SingleResult;
import rest.api.repository.UserJpaRepo;
import rest.api.service.ResponseService;

import java.util.List;

@Api(tags={"1. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/v1")
public class UserController {
    private final UserJpaRepo userJpaRepo;
    private final ResponseService responseService;

    @ApiOperation(value="회원 리스트 조회",notes="모든 회원을 조회한다.")
    @GetMapping(value="/users")
    public ListResult<User> findAllUser(){
        return responseService.getListResult(userJpaRepo.findAll());
    }

    @ApiOperation(value="회원 단건 조회",notes="userId로 회원을 조회한다.")
    @GetMapping(value="/user/{msrl}")
    public SingleResult<User> findUserById(@ApiParam(value = "회원ID",required = true) @PathVariable long msrl)
    {
        return responseService.getSingleResult(userJpaRepo.findById(msrl).orElseThrow(CUserNotFoundException::new));
    }

    @ApiOperation(value="회원 입력",notes="회원을 입력한다.")
    @PostMapping(value="/user")
    public SingleResult<User> save(@ApiParam(value = "회원아이디",required = true) @RequestParam String uid,
                                   @ApiParam(value = "회원이름",required = true)@RequestParam String name){
        User user=User.builder()
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value="회원 수정",notes="회원정보를 수정한다")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value="회원번호",required = true) @RequestParam long msrl,
            @ApiParam(value="회원아이디",required = true) @RequestParam String uid,
            @ApiParam(value="회원이름",required = true) @RequestParam String name
    ){
        User user = User.builder()
                .msrl(msrl)
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 삭제",notes="userId로 회원정보를 삭제한다")
    @DeleteMapping(value="/user/{msrl}")
    public CommonResult delete(
            @ApiParam(value="회원번호",required = true) @PathVariable long msrl
    ){
        userJpaRepo.deleteById(msrl);

        return responseService.getSuccessResult();
    }

}
