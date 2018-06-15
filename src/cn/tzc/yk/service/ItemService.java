package cn.tzc.yk.service;

import cn.tzc.yk.po.*;

import java.util.List;

public interface ItemService {

    public List<Item> getAllItem() throws Exception;
    public List<Item> getItemByPrice(int begin,int end)throws Exception;
    public List<Item> search(String keyword) throws Exception;
    public void addShoppingCar(String username,int itemid)throws Exception;
    public List<Item> getShoppingCarById(String username)throws Exception;
    public void deleteShopCarItem(String username,int itemid)throws Exception;
    public void addOrderList(SettleShoppingCar settleShoppingCar)throws Exception;
    public List<SettleShoppingResponse> getOrderList(String username)throws Exception;
    public void deleteOrderListItem(String username,int itemid)throws Exception;
    public void addItem(Item item)throws Exception;
}
