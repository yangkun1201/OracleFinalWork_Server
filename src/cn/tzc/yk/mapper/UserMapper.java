package cn.tzc.yk.mapper;

import cn.tzc.yk.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

   public List<User> GetAllUser()throws Exception;

   public void register(User user)throws Exception;

   public List<User> hasAccount(String name) throws Exception;

   public int maxId()throws Exception;

   public List<User> login(String name)throws Exception;

   public int getIdByName(String name)throws Exception;

   public void changeUserInfo(User user)throws Exception;

   public void deleteUser(int id)throws Exception;

   public User GetUserById(int id)throws Exception;
}
