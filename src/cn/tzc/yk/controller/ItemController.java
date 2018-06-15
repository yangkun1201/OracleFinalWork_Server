package cn.tzc.yk.controller;

import cn.tzc.yk.po.*;
import cn.tzc.yk.serviceImpl.ItemServiceImpl;
import cn.tzc.yk.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemServiceImpl itemService;

    @RequestMapping("/getItems/{n}")
    @ResponseBody
    public List<Item> getItems(@PathVariable int n){

        List<Item> ls = null;

        try {
            ls = itemService.getAllItem();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(n*12);
        return ls.subList(n*12,n*12+12);
    }

    @RequestMapping("/getItemByPrice")
    @ResponseBody
    public List<Item> getItemByPrice(@RequestParam("begin")int begin, @RequestParam("end")int end, @RequestParam("page")int n){

        List<Item> ls = null;

        try {
            ls = itemService.getItemByPrice(begin, end);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ls.size() < n*12+12 ? null:ls.subList(n*12,n*12+12);
    }


    @RequestMapping("/search")
    @ResponseBody
    public List<Item> search(@RequestParam("keyword") String keyword){
        List<Item> ls = null;
        try {
            ls = itemService.search(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    @RequestMapping("/addShoppingCar")
    @ResponseBody
    public String addShoppingCar(@RequestBody ShoppingCarItem shoppingCarItem){
        try {
            itemService.addShoppingCar(shoppingCarItem.getUsername(),shoppingCarItem.getItemId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping("/showShoppingCar")
    @ResponseBody
    public List<Item> showShoppingCar(@RequestParam("username") String username){
        List<Item> ls = null;
        try {
            ls = itemService.getShoppingCarById(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    @RequestMapping("/deleteShoppingCar")
    @ResponseBody
    public String deleteShoppingCar(@RequestBody ShoppingCarItem shoppingCarItem){
        try {
            itemService.deleteShopCarItem(shoppingCarItem.getUsername(),shoppingCarItem.getItemId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping("/addOrderList")
    @ResponseBody
    public String addOrderList(@RequestBody SettleShoppingCar settleShoppingCar){
        try {
            itemService.addOrderList(settleShoppingCar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping("/getOrderList")
    @ResponseBody
    public List<SettleShoppingResponse> getOrderList(@RequestParam String username){
        List<SettleShoppingResponse> ls = null;
        try {
           ls = itemService.getOrderList(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    @RequestMapping("/deleteOrderListItem")
    @ResponseBody
    public String deleteOrderListItem(@RequestBody ShoppingCarItem shoppingCarItem){
        try {
            itemService.deleteOrderListItem(shoppingCarItem.getUsername(),shoppingCarItem.getItemId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping("/addItem")
    @ResponseBody
    public String addItem(@RequestBody Item item){
        try {
            itemService.addItem(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }


}
