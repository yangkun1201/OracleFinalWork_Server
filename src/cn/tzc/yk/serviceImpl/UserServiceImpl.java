package cn.tzc.yk.serviceImpl;

import cn.tzc.yk.mapper.ItemMapper;
import cn.tzc.yk.mapper.UserMapper;
import cn.tzc.yk.po.User;
import cn.tzc.yk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<User> getAllUser() throws Exception {
        List<User> userList = null;
        userList = userMapper.GetAllUser();
        System.out.println(userList.size());
        return userList;
    }

    @Override
    @Transactional
    public void register(User user) throws Exception {
        userMapper.register(user);

    }

    @Override
    public boolean hasAccount(String name) throws Exception {
        boolean hasAccount = false;
        List<User> ls = userMapper.hasAccount(name);
        if(ls.size()>0){
            hasAccount = true;
        }else{
            hasAccount = false;
        }
        return hasAccount;
    }

    @Override
    public int getMaxId() throws Exception {
        int n = userMapper.maxId();
        return n;
    }

    @Override
    public int login(User user) throws Exception {

        int n = 0;
        List<User> ls = userMapper.login(user.getUsername());
        if(ls.size()==0){
            n = 0;//用户不存在
        }else{
            User cur = ls.get(0);
            if(cur.getPassword().equals(user.getPassword())){
                n = 1;//密码正确
            }else{
                n = -1;//密码错误
            }
        }
        return n;
    }

    @Override
    @Transactional
    public void changeUserInfo(User user) throws Exception {
        userMapper.changeUserInfo(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) throws Exception {
        System.out.println("id"+id);
        itemMapper.deleteShoppingCarByUserId(id);
        itemMapper.deleteOrderListByUserId(id);
        userMapper.deleteUser(id);
    }
}
