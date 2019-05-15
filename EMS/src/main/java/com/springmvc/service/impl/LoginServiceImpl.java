package com.springmvc.service.impl;

import com.springmvc.mapper.LoginMapper;
import com.springmvc.po.Login;
import com.springmvc.po.LoginExample;
import com.springmvc.service.LoginService;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Login findByAccount(String accountNumber) throws Exception {

        return loginMapper.selectByPrimaryKey(accountNumber);
    }

    @Override
    public void updateByAccount(String accountNumber,Login login) throws Exception {
        LoginExample loginExample = new LoginExample();
        LoginExample.Criteria criteria = loginExample.createCriteria();
        criteria.andAccountNumberEqualTo(accountNumber);

        int n = loginMapper.countByExample(loginExample);
        if (n == 0){
            try {
                loginMapper.insert(login);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (n == 1){
            try{
                loginMapper.updateByExampleSelective(login,loginExample);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Boolean addUser(Login login) throws Exception {

        Login L1 = loginMapper.selectByPrimaryKey(login.getAccountNumber());
        if (L1 == null){
            loginMapper.insert(login);
            Login L2 = loginMapper.selectByPrimaryKey(login.getAccountNumber());
            if (L2 != null){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteUser(Login login) throws Exception {

        Login login1 = loginMapper.selectByPrimaryKey(login.getAccountNumber());
        if (login1 != null){
            loginMapper.deleteByPrimaryKey(login.getAccountNumber());
            Login login2 = loginMapper.selectByPrimaryKey(login.getAccountNumber());
            if (login2 == null){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
}
