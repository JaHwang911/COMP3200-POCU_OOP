package academy.pocu.comp2500.lab3;

import java.util.ArrayList;

public class ListItem {
    private String text;
    private char bulletStyle;
    private ArrayList<ListItem> subListItems;
    private final String SUB_LIST_LINE = "\n    ";

    public ListItem(String text) {
        this(text, '*');
    }

    public ListItem(String text, char bulletStyle) {
        this.text = text;
        this.bulletStyle = bulletStyle;
        this.subListItems = new ArrayList<>();
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
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

    public void removeSublistItem(int index) {
        this.subListItems.remove(index);
    }

    public void setBulletStyle(char bulletStyle) {
        this.bulletStyle = bulletStyle;
    }

    public String toString() {
        final int DEPTH_LEVEL = 0;
        StringBuilder buffer = new StringBuilder();
        buffer.append(String.format("%c %s%s", this.bulletStyle, this.text, System.lineSeparator()));

        for (ListItem subListItem : this.subListItems) {
            buffer.append(subListItem.toString(DEPTH_LEVEL + 1));
        }

        return buffer.toString();
    }

    private String toString(final int depthLevel) {
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < depthLevel; ++i) {
            buffer.append("    ");
        }

        buffer.append(String.format("%c %s%s", this.bulletStyle, this.text, System.lineSeparator()));

        for (ListItem subListItem : this.subListItems) {
            buffer.append(subListItem.toString(depthLevel + 1));
        }

        return buffer.toString();
    }
}
