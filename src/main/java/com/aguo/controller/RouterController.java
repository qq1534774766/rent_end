package com.aguo.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RouterController {
    //公开
    @RequestMapping({"/","index.html","toLogin"})
    public  String toIndex(){
        return "index";
    }
    //公开
    @RequestMapping("/loginSuccess")
    @ResponseBody
    public Map<String, String> loginSuccess(String username,Boolean rememberMe,HttpServletResponse resp){
        Cookie cookie = new Cookie("username",username);
        if (rememberMe)cookie.setMaxAge(259200);
        resp.addCookie(cookie);
        Map<String, String> map = new HashMap<>();
        map.put("username",username);
        return map;
    }
    //公开
    @RequestMapping("/loginError")
    @ResponseBody
    public Map<String, String> loginError(HttpServletRequest request){
        Map<String, Integer> map1 = (HashMap<String, Integer>) request.getAttribute("stateMap");
        Map<String, String> map = new HashMap<>();
        if (map1.get("state")==1) {
//            被锁定
            map.put("state","1");
        }else if (map1.get("state")==2){
//            不可用
            map.put("state","2");
        }else {
//            账号或密码错误
            map.put("state","0");
        }
        return map;
    }


}
