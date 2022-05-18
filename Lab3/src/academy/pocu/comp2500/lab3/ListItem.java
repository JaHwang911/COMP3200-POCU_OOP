package academy.pocu.comp2500.lab3;

import java.util.ArrayList;

public class ListItem {
    private String itemName;
    private char bulletStyle;
    private ArrayList<ListItem> subListItems;
    private final String SUB_LIST_LINE = "\n    ";

    public ListItem(String text) {
        this.itemName = text;
        this.bulletStyle = '*';
        this.subListItems = new ArrayList<>();
    }

    public String getText() {
        return this.itemName;
    }

    public void setText(String text) {
        this.itemName = text;
    }

    public char getBulletStyle() {
        return this.bulletStyle;
    }

    public void addSublistItem(ListItem sublistItem) {
        this.subListItems.add(sublistItem);
    }

    public ListItem getSublistItem(int index) {
        return this.subListItems.get(index);
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(String.format("%c %s", this.bulletStyle, this.itemName));

        if (this.subListItems.size() > 0) {
            buffer.append(this.SUB_LIST_LINE);

            for (ListItem l : this.subListItems) {
                buffer.append(l.toString());
                buffer.append(this.SUB_LIST_LINE);
            }
        }

        return buffer.toString();
    }
}
