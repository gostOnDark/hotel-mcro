package com.company.hotel.admin.controller;

import com.company.hotel.admin.attributes.SystemProperties;
import com.company.hotel.admin.entity.ResultMsg;
import com.company.hotel.admin.entity.UserCredentials;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class LogInOutController {
    @Autowired
    private SystemProperties systemProperties;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(String msg) {
        log.info(msg);
        return "home";
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg toLogin(@RequestBody UserCredentials credentials, @RequestParam(defaultValue = "false") Boolean rememberme, Model model, RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(credentials.getUsername(), credentials.getPassword());
            token.setRememberMe(rememberme);
            String osName = systemProperties.getOsName();
            log.info(systemProperties.toString());
            try {
                subject.login(token);
                Session session = subject.getSession();
                model.addAttribute("msg", "登录成功,当前操作系统为：" + osName);
                redirectAttributes.addFlashAttribute("msg", "登录成功,当前操作系统为：" + osName);
                return ResultMsg.ok();
            } catch (UnknownAccountException e) {
                log.error("msg:该账号不存在");
                model.addAttribute("msg", "该账号不存在,当前操作系统为：" + osName);
                return ResultMsg.build(403, "该账号不存在");
            } catch (IncorrectCredentialsException e) {
                log.error("msg:密码错误，请重试");
                model.addAttribute("msg", "密码错误，请重试");
                return ResultMsg.build(403, "密码错误，请重试");
            } catch (LockedAccountException e) {
                log.error("msg:该账号已被锁定，请联系管理员");
                model.addAttribute("msg", "该账号已被锁定，请联系管理员");
                return ResultMsg.build(403, "该账号已被锁定，请联系管理员");
            } catch (Exception e) {
                log.error("msg:登录失败，原因未知");
                model.addAttribute("msg", "登录失败，原因未知");
                return ResultMsg.build(403, "登录失败，原因未知");
            }
        }
        return ResultMsg.ok();
    }

    @GetMapping("/logout")
    public ResultMsg logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        log.info("退出登录");
        return ResultMsg.ok();
    }

    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }
}
