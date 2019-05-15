package com.springmvc.Realm;

import com.springmvc.po.Login;
import com.springmvc.po.Role;
import com.springmvc.service.LoginService;
import com.springmvc.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ZWX
 */
@Component
public class UserRealm extends AuthorizingRealm {

    //用户对应的角色信息与权限信息都保存在数据库中，通过LoginService RoleService获取数据
    @Resource(name = "loginServiceImpl")
    private LoginService loginService;
    @Resource(name = "roleServiceImpl")
    private RoleService roleService;
    /**
     * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     * 当调用权限验证时，就会调用此方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String accountNumber = (String) getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //根据用户名查询当前用户拥有的角色
        Role role = null;
        try {
            Login userLogin = loginService.findByAccount(accountNumber);
            //获取角色对象
            role = roleService.findByRoleId(userLogin.getRole());
        }catch (Exception e){
            e.printStackTrace();
        }
        //将角色信息提高给info
        Set<String> r = new HashSet<>();
        if (role != null){
            r.add(role.getRoleName());
            authorizationInfo.setRoles(r);
        }
        return authorizationInfo;
    }

    /**
     * 此方法 进行身份认证 login时调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       //账号
        String accountNumber = (String) authenticationToken.getPrincipal();
        //密码
        String password = new String((char[])authenticationToken.getCredentials());

        Login userLogin = null;
        try {
            userLogin = loginService.findByAccount(accountNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userLogin == null){
         //账号错误
         throw new UnknownAccountException();

        }else if (!password.equals(userLogin.getPassword())){
            //密码错误
            throw new IncorrectCredentialsException();
        }

        //盐值
//        ByteSource credentionInfo = ByteSource.Util.bytes(accountNumber);

        //身份验证通过，返回一个身份信息
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(accountNumber,password,getName());

        return authenticationInfo;
    }

    //清除缓存
    public void clearCached(){
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principalCollection);
    }
}
