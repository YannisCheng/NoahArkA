package com.cwj.genesis.bean_manager.xml.declare;

/**
 * com.cwj.genesis
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-05-25 15:53
 */
public class Book {

    private String name;
    private int price;
    private String address;
    private String out;
    private String out2;

    public Book(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOut(String out) {
        System.out.println("out is : " + out);
    }

    public void setOut2(String out2) {
        this.out2 = out2;
    }

    public void showMeteData() {
        System.out.println("book name is : " + name + ", price is : " + price);
    }

    public void showAddress() {
        System.out.println("Address is : " + address);
    }

    public void showOut(){
        System.out.println("out is : " + out);
    }

    public void showOut2(){
        System.out.println("out2 is : " + out2);
    }

    /*@Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", address='" + address + '\'' +
                ", out='" + out + '\'' +
                ", out2='" + out2 + '\'' +
                '}';
    }*/
}
