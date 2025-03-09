import java.util.List;

// Abstract class for object managers (Species and Room)
public abstract class BaseObjectManager<T> {
    public abstract void add(List<T> list);
    public abstract void display(List<T> list);
}
