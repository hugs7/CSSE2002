package observer;

public interface Subject {
    void Attach(Observer o);
    void Delete(Observer o);
    void Notify();
}
