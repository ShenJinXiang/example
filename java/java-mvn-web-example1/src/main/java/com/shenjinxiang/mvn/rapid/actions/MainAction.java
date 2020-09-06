package com.shenjinxiang.mvn.rapid.actions;

import com.jfinal.aop.Inject;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.shenjinxiang.mvn.rapid.domain.Bean;
import com.shenjinxiang.mvn.rapid.domain.CurrentRyxx;
import com.shenjinxiang.mvn.services.xtwh.RyglService;
import com.shenjinxiang.mvn.services.xtwh.ZyglService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/2 22:42
 */
public class MainAction extends RapidAction {

    @Inject
    private RyglService ryglService;
    @Inject
    private ZyglService zyglService;

    public void index() {
        this.redirect("/main");
    }

    public void main() throws Exception {
        List<Bean> yjzyList = zyglService.queryZyljForMain(getCurrentRyxx());
        this.setAttr("yjzyList", yjzyList);
        this.bindCurrentRyxxToPage();
        this.renderJsp("/WEB-INF/index.jsp");
    }

    public void jump() {
        this.renderJsp("/WEB-INF/pages/common/jump.jsp");
    }

    public void login() {
        this.renderJsp("/WEB-INF/pages/common/login.jsp");
    }

    public void validateLogin() {
        String ryzh = this.getPara("ryzh");
        String mm = this.getPara("mm");
        if (StrKit.isBlank(ryzh)) {
            this.setAttr("ryzh", ryzh);
            this.setAttr("mm", mm);
            this.setAttr("error", "请输入账号");
            this.forwardAction("/login");
            return;
        }
        if (StrKit.isBlank(mm)) {
            this.setAttr("ryzh", ryzh);
            this.setAttr("mm", mm);
            this.setAttr("error", "请输入密码");
            this.forwardAction("/login");
            return;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(ryzh, mm);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
            CurrentRyxx currentRyxx = ryglService.queryCurrnetRyxxByRyzh(ryzh);
            this.setSessionAttr("currentRyxxJson", JsonKit.toJson(currentRyxx));
            this.setSessionAttr("currentRyxx", currentRyxx);
        } catch (AuthenticationException var6) {
            var6.printStackTrace();
            this.setAttr("ryzh", ryzh);
            this.setAttr("mm", mm);
            this.setAttr("error", var6.getMessage());
            this.forwardAction("/login");
            return;
        } catch (Exception var7) {
            var7.printStackTrace();
            this.setAttr("rydm", ryzh);
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
