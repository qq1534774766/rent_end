package com.aguo.controller.code;

import com.aguo.untils.code.CodeUtil;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/code")
public class CodeController {
    @Autowired
    private Producer captchaProducer;

    /**
     * 获取验证码图片
     *  验证码放在session的Constants.KAPTCHA_SESSION_KEY中
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/getKaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
    //  公开

    /**
     * 验证验证码是否正确
     * @param request
     * @return
     */
    @RequestMapping("/verifyCode")
    @ResponseBody
    public Map<String, Boolean> verifyCode(HttpServletRequest request){
        Map<String, Boolean> map = new HashMap<>();
        if (!CodeUtil.checkVerifyCode(request)) {
            map.put("state",false);//错误
        } else {
            map.put("state",true);//正确
        }
        return map;
    }
}