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

/**
 * 路由控制器
 * @author Administrator
 */
@Controller
public class RouterController {
    /**
     * 前端页面路由
     * @return
     */
    @RequestMapping({"/","index.html","toLogin"})
    @ResponseBody
    public  String toIndex(){
//        return "index";
        return "<h3>欢迎使用出租房管理系统后端API，请通过前端访问！</h3>";
    }

    /**
     * 登录控制器
     * @param username
     * @param rememberMe
     * @param resp
     * @return
     */
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

    /**
     * 登录错误处理器
     * @param request
     * @return
     */
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
