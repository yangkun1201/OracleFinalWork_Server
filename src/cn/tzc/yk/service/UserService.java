package cn.tzc.yk.service;

import cn.tzc.yk.po.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUser() throws Exception;
    public void register(User user) throws Exception;
    public boolean hasAccount(String name)throws Exception;
    public int getMaxId()throws Exception;
    public int login(User user)throws Exception;
    public void changeUserInfo(User user)throws Exception;
    public void deleteUser(int id)throws Exception;
}
