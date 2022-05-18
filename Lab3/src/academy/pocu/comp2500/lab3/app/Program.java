package academy.pocu.comp2500.lab3.app;

import academy.pocu.comp2500.lab3.ListItem;

public class Program {

    public static void main(String[] args) {
        testSimpleSublist();
        testComplexSublist();
        testDeepSublist();
    }

    private static void testSimpleSublist() {
        ListItem item1 = new ListItem("This is item1");
        ListItem sublistItem1 = new ListItem("This is sublist item1");
        ListItem subSublistItem1 = new ListItem("This is sub-sublist item1");

        sublistItem1.addSublistItem(subSublistItem1);
        item1.addSublistItem(sublistItem1);

        String s = item1.toString();

        System.out.println(s);
    }

    private static void testComplexSublist() {
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
        System.out.println(s);
    }

    private static void testDeepSublist() {
        ListItem item1 = new ListItem("This is item1");
        ListItem sublistItem1 = new ListItem("This is sublist item1");
        ListItem subSublistItem1 = new ListItem("This is sub-sublist item1");
        ListItem subSubSublistItem1 = new ListItem("This is sub-sub-sublist item1");
        ListItem subSubSubSublistItem1 = new ListItem("This is sub-sub-sub-sublist item1");
        ListItem subSubSubSubSublistItem1 = new ListItem("This is sub-sub-sub-sub-sublist item1");
        ListItem subSubSubSubSubSublistItem1 = new ListItem("This is sub-sub-sub-sub-sub-sublist item1");

        subSubSubSubSublistItem1.addSublistItem(subSubSubSubSubSublistItem1);
        subSubSubSublistItem1.addSublistItem(subSubSubSubSublistItem1);
        subSubSublistItem1.addSublistItem(subSubSubSublistItem1);
        subSublistItem1.addSublistItem(subSubSublistItem1);
        sublistItem1.addSublistItem(subSublistItem1);
        item1.addSublistItem(sublistItem1);

        String s = item1.toString();
        System.out.println(s);
    }
}
