package lut.lijihu.airtransport.user.controller;

import lut.lijihu.airtransport.user.invo.LoginIn;
import lut.lijihu.airtransport.user.revo.GetUserVo;
import lut.lijihu.airtransport.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kj on 2017/2/12.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/login")
    public GetUserVo login(@RequestBody LoginIn loginIn){
        String flag= userService.getUser(loginIn.getUsername(),loginIn.getPassword());
        GetUserVo getUserVo=new GetUserVo();
        getUserVo.setStatus(flag);
        return getUserVo;
    }
}
