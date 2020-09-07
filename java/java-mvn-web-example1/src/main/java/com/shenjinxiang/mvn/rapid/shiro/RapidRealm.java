package com.shenjinxiang.mvn.rapid.shiro;

import com.jfinal.aop.Aop;
import com.jfinal.plugin.ehcache.CacheKit;
import com.shenjinxiang.mvn.rapid.consts.RapidConsts;
import com.shenjinxiang.mvn.rapid.domain.Bean;
import com.shenjinxiang.mvn.services.xtwh.RyglService;
import com.shenjinxiang.mvn.services.xtwh.ZyglService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;


/**
 * shiroRealm
 */
public class RapidRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		return info;
		String ryzh = (String)principals.fromRealm(this.getName()).iterator().next();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		try {
			RyglService ryglService = Aop.get(RyglService.class);
			Bean ryxx = ryglService.queryRyZhxx(ryzh);
			int ryid = ryxx.getInt("ryid");
			List<String> permissions = (List) CacheKit.get("permissions", ryid);
			if (permissions == null) {
			    ZyglService zyglService = Aop.get(ZyglService.class);
				permissions = zyglService.queryZyljByRyid(ryxx);
				CacheKit.put("permissions", ryid, permissions);
			}

			info.addStringPermissions(permissions);
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
//		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//		return new SimpleAuthenticationInfo(usernamePasswordToken.getUsername(), usernamePasswordToken.getPassword(),getName());
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;

		Bean ryxx;
		try {
			RyglService ryglService = Aop.get(RyglService.class);
			ryxx = ryglService.queryRyZhxx(usernamePasswordToken.getUsername());
		} catch (Exception var5) {
			var5.printStackTrace();
			throw new AuthenticationException("数据库连接失败！");
		}

		if (ryxx == null) {
			throw new UnknownAccountException("用户名或密码错误！");
		} else {
			String password = (new Md5Hash(new String(usernamePasswordToken.getPassword()), RapidConsts.MD5_SALT)).toString();
			if (!password.equals(ryxx.getStr("mm"))) {
				throw new IncorrectCredentialsException("用户名或密码错误！");
			} else {
				return new SimpleAuthenticationInfo(usernamePasswordToken.getUsername(), usernamePasswordToken.getPassword(), this.getName());
			}
		}
	}

}
