package observer;

import java.util.ArrayList;

/**
 * Created by ounaixi on 1/3/16
 */

public class PostOffice implements Subject{
    private ArrayList<Mail> allMail;
    private ArrayList<Observer> observers;

    public PostOffice(){
        allMail = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void addMail(Mail m){
        allMail.add(m);
        Notify();
    }

    public ArrayList<Mail> getState(){
        return allMail;
    }

    @Override
    public void Attach(Observer o) {
        observers.add(o);

    }

    @Override
    public void Delete(Observer o) {
        observers.remove(o);

    }

    @Override
    public void Notify() {
        for(int i=0; i<observers.size();i++){
            observers.get(i).update(this);
        }

    }
}
