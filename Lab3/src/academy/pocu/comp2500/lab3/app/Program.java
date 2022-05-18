package academy.pocu.comp2500.lab3.app;

import academy.pocu.comp2500.lab3.ListItem;

public class Program {

    public static void main(String[] args) {
        ListItem item1 = new ListItem("This is item1");
        ListItem sublistItem1 = new ListItem("This is sublist item1");
        ListItem sublistItem2 = new ListItem("This is sublist item2");
        ListItem subSublistItem1 = new ListItem("This is sub-sublist item1");
        ListItem subSublistItem2 = new ListItem("This is sub-sublist item2");

        sublistItem1.addSublistItem(subSublistItem1);
        sublistItem1.addSublistItem(subSublistItem2);

        sublistItem2.addSublistItem(subSublistItem1);
        sublistItem2.addSublistItem(subSublistItem2);

        item1.addSublistItem(sublistItem1);
        item1.addSublistItem(sublistItem2);

        String s = item1.toString();
    }
}
