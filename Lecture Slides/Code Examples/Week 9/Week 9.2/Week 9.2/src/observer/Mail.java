package observer;

/**
 * Created by ounaixi on 1/3/16
 */

public class Mail {
    String receiverName;
    String address;
    String content;

    public Mail(String name, String address, String content){
        this.receiverName = name;
        this.address = address;
        this.content = content;
    }
}
