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

        for (ListItem l : this.subListItems) {
            buffer.append("\n");
            buffer.append(l.toString(1));
        }

        return buffer.toString();
    }

    public String toString(int level) {
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < level; ++i) {
            buffer.append("    ");
        }

        buffer.append(String.format("%c %s", this.bulletStyle, this.itemName));

        for (ListItem l : this.subListItems) {
            buffer.append("\n");
            buffer.append(l.toString(level + 1));
        }

        return buffer.toString();
    }
}
