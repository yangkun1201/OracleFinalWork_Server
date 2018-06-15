package cn.tzc.yk.serviceImpl;

import cn.tzc.yk.mapper.ItemMapper;
import cn.tzc.yk.mapper.UserMapper;
import cn.tzc.yk.po.*;
import cn.tzc.yk.service.ItemService;
import cn.tzc.yk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired UserMapper userMapper;

    @Override
    public List<Item> getAllItem() throws Exception {
        List<Item> list = itemMapper.GetAllItem();
        return list;
    }

    @Override
    public List<Item> getItemByPrice(int begin,int end) throws Exception {
        List<Item> ls = itemMapper.getItemByPrice(begin,end);
        return ls;
    }

    @Override
    public List<Item> search(String keyword) throws Exception {
        List ls = itemMapper.search(keyword);
        return ls;
    }

    @Override
    @Transactional
    public void addShoppingCar(String username, int itemid) throws Exception {
        int userid = userMapper.getIdByName(username);
        itemMapper.addShoppingCar(userid,itemid);
    }

    @Override
    public List<Item> getShoppingCarById(String username) throws Exception {
        int userid = userMapper.getIdByName(username);
        List<ShoppingCarResponseItem> ls = itemMapper.getShoppingCarById(userid);
        List<Item> items = new ArrayList<>();
        for(ShoppingCarResponseItem shoppingCarResponseItem:ls){
            items.add(itemMapper.getItemById(shoppingCarResponseItem.getItemid()));
        }
        return items;
    }

    @Override
    @Transactional
    public void deleteShopCarItem(String username, int itemid) throws Exception {
        int userid = userMapper.getIdByName(username);
        itemMapper.deleteShopCarItem(userid,itemid);
    }

    @Override
    @Transactional
    public void addOrderList(SettleShoppingCar settleShoppingCar) throws Exception {
        int userid = userMapper.getIdByName(settleShoppingCar.getUsername());
        for(SettleShoppingItem settleShoppingItem:settleShoppingCar.getItemList()){
            itemMapper.addOrderList(userid,settleShoppingItem.getItemid(),settleShoppingItem.getNum());
            itemMapper.deleteShopCarItem(userid,settleShoppingItem.getItemid());
        }
    }

    @Override
    public List<SettleShoppingResponse > getOrderList(String username)throws Exception {
        int userid = userMapper.getIdByName(username);
        List<SettleShoppingResponseItem> ls = itemMapper.getOrderList(userid);
        List<SettleShoppingResponse> items = new ArrayList<>();
        for(SettleShoppingResponseItem settleShoppingResponseItem:ls){
            SettleShoppingResponse settleShoppingResponse = new SettleShoppingResponse();
            settleShoppingResponse.setItem(itemMapper.getItemById(settleShoppingResponseItem.getItemid()));
            settleShoppingResponse.setNum(settleShoppingResponseItem.getNum());
            items.add(settleShoppingResponse);
        }
       // System.out.println("SettleShoppingResponseItem:"+ls.size());
        return items;
    }

    @Override
    @Transactional
    public void deleteOrderListItem(String username, int itemid) throws Exception {
        int userid = userMapper.getIdByName(username);
        itemMapper.deleteOrderListItem(userid,itemid);
    }

    @Override
    @Transactional
    public void addItem(Item item) throws Exception {
        item.setId(itemMapper.getMaxId()+1);
        itemMapper.addItem(item);
    }
}
