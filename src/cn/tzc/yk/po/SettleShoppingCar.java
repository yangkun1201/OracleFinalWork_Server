package cn.tzc.yk.po;

public class SettleShoppingCar {
    String username;
    SettleShoppingItem[] itemList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SettleShoppingItem[] getItemList() {
        return itemList;
    }

    public void setItemList(SettleShoppingItem[] itemList) {
        this.itemList = itemList;
    }
}
