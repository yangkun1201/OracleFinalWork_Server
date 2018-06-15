package cn.tzc.yk.mapper;

import cn.tzc.yk.po.Item;
import cn.tzc.yk.po.SettleShoppingResponseItem;
import cn.tzc.yk.po.ShoppingCarResponseItem;
import cn.tzc.yk.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemMapper {

   public List<Item> GetAllItem()throws Exception;
   public List<Item> getItemByPrice(@Param("begin") int begin,@Param("end") int end);
   public List<Item> search(String keyword)throws Exception;
   public void addShoppingCar(@Param("userid")int userid,@Param("itemid") int itemid)throws Exception;
   public List<ShoppingCarResponseItem> getShoppingCarById(int userid)throws Exception;
   public void deleteShopCarItem(@Param("userid")int userid,@Param("itemid")int itemid)throws Exception;
   public void deleteOrderListItem(@Param("userid")int userid,@Param("itemid")int itemid)throws Exception;
   public void addOrderList(@Param("userid")int userid,@Param("itemid")int itemid,@Param("num")int num)throws Exception;
   public List<SettleShoppingResponseItem> getOrderList(int userid)throws Exception;
   public Item getItemById(int itemid)throws Exception;
   public int getMaxId()throws Exception;
   public void addItem(Item item)throws Exception;
   public void deleteOrderListByUserId(int id)throws Exception;
   public void deleteShoppingCarByUserId(int id)throws Exception;
}
