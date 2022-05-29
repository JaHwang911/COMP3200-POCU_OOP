package academy.pocu.comp2500.lab4;

public class Entry {
    private final String key;
    private String value;
    private Entry previous;
    private Entry next;
    private Entry lruPrevious;
    private Entry lruNext;

    public Entry(String key, String value, Entry previousOrNull, Entry lruNextOrNull) {
        this.key = key;
        this.value = value;
        this.previous = previousOrNull;
        this.lruNext = lruNextOrNull;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public Entry getPrevious() {
        return this.previous;
    }

    public Entry getNext() {
        return this.next;
    }

    public Entry getLruPrevious() {
        return this.lruPrevious;
    }

    public Entry getLruNext() {
        return this.lruNext;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setPrevious(Entry previous) {
        this.previous = previous;
    }

    public void setNext(Entry next) {
        this.next = next;
    }

    public void setLruPrevious(Entry prevOrNull) {
        this.lruPrevious = prevOrNull;
    }

    public void setLruNext(Entry nextOrNull) {
        this.lruNext = nextOrNull;
    }
}
