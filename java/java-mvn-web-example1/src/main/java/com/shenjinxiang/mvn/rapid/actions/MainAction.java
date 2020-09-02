package com.shenjinxiang.mvn.rapid.actions;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/2 22:42
 */
public class MainAction extends RapidAction {

    public void index() {
        this.redirect("/main");
    }

    public void main() throws Exception {
//        String rydm = this.getCurrentRyxx().getStr("rydm");
//        List<Bean> yjzyList = ZYGLService.queryZyljForMain(rydm);
//        this.setAttr("yjzyList", yjzyList);
//        this.bindCurrentRyxxToPage();
        this.renderJsp("/WEB-INF/index.jsp");
    }

    public void jump() {
        this.renderJsp("/WEB-INF/pages/common/jump.jsp");
    }

    public void login() {
        this.renderJsp("/WEB-INF/pages/common/login.jsp");
    }

    public void validateLogin() {
        String rydm = this.getPara("rydm");
        String mm = this.getPara("mm");
        UsernamePasswordToken token = new UsernamePasswordToken(rydm, mm);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
//            CurrentRyxx currentRyxx = RYGLService.queryCurrnetRyxxByDm(rydm);
//            this.setSessionAttr("currentRyxxJson", JsonKit.toJson(currentRyxx));
//            this.setSessionAttr("currentRyxx", currentRyxx);
        } catch (AuthenticationException var6) {
            var6.printStackTrace();
            this.setAttr("rydm", rydm);
            this.setAttr("mm", mm);
            this.setAttr("error", var6.getMessage());
            this.forwardAction("/login");
            return;
        } catch (Exception var7) {
            var7.printStackTrace();
            this.setAttr("rydm", rydm);
            this.setAttr("mm", mm);
            this.setAttr("error", var7.getMessage());
            this.forwardAction("/login");
            return;
        }

        this.redirect("/main");
    }

    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        this.redirect("/jump");
    }
}
