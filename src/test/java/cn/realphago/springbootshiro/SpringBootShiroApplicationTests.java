package cn.realphago.springbootshiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootShiroApplicationTests {

    @Test
    void contextLoads() {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        CustomerRealm customerRealm = new CustomerRealm();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        defaultSecurityManager.setRealm(customerRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("user", "user");
        try {
            subject.login(usernamePasswordToken);
            System.out.println("登录成功");
        } catch (UnknownAccountException e) {
            System.out.println("用户不存在");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
            e.printStackTrace();
        }
        if (subject.isAuthenticated()) {
            System.out.println(subject.hasRole("admin"));
            System.out.println(subject.hasRole("user"));
            System.out.println(subject.hasRole("super"));
        }
    }

    @Test
    public void md5() {
        Md5Hash md5Hash = new Md5Hash("user", "qwert");
        System.out.println(md5Hash.toHex());
    }

    class CustomerRealm extends AuthorizingRealm {

        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
            System.out.println("primaryPrincipal = " + primaryPrincipal);
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole("admin");
            simpleAuthorizationInfo.addRole("user");
            return simpleAuthorizationInfo;
        }

        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            Md5Hash md5Hash = new Md5Hash("user", "qwert", 1024);
            if ("user".equals(authenticationToken.getPrincipal())) {
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(authenticationToken.getPrincipal(),
                        md5Hash.toHex(),
                        ByteSource.Util.bytes("qwert"),
                        this.getName());
                return simpleAuthenticationInfo;
            }
            return null;
        }
    }

}
