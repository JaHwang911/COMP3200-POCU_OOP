package academy.pocu.comp2500.lab3.app;

import academy.pocu.comp2500.lab3.ListItem;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class Program {

    public static void main(String[] args) {
        testOfficial();
        testSimpleSublistItem();
        testComplexSublistItem();
        testDeepSublistItem();
        testRemoveSublistItem();

        System.out.println("No prob");
    }

    private static void testOfficial() {
        ArrayList<ListItem> list = new ArrayList<>();

        ListItem listItem1 = new ListItem("My first item");

        ListItem sublistItem1 = new ListItem("This is sublist item1", '>');
        ListItem sublistItem2 = new ListItem("This is sublist item2", '>');

        listItem1.addSublistItem(sublistItem1);
        listItem1.addSublistItem(sublistItem2);

        ListItem listItem2 = new ListItem("My second item");

        ListItem listItem3 = new ListItem("My third item");

        ListItem sublistItem3 = new ListItem("This is sublist item3", '>');
        ListItem subSublistItem1 = new ListItem("This is sub-sublist item1", '-');

        sublistItem3.addSublistItem(subSublistItem1);
        listItem3.addSublistItem(sublistItem3);

        list.add(listItem1);
        list.add(listItem2);
        list.add(listItem3);

        String actual = toString(list);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("* My first item%s", System.lineSeparator()));
        sb.append(String.format("    > This is sublist item1%s", System.lineSeparator()));
        sb.append(String.format("    > This is sublist item2%s", System.lineSeparator()));
        sb.append(String.format("* My second item%s", System.lineSeparator()));
        sb.append(String.format("* My third item%s", System.lineSeparator()));
        sb.append(String.format("    > This is sublist item3%s", System.lineSeparator()));
        sb.append(String.format("        - This is sub-sublist item1%s",
                System.lineSeparator()));

        String expected = sb.toString();

        System.out.println(expected);
        System.out.println(actual);

        assert expected.equals(actual);

        System.out.println("==============================");
    }

    private static String toString(ArrayList<ListItem> list) {
        StringBuilder sb = new StringBuilder();
        for (ListItem item : list) {
            sb.append(item);
        }

        return sb.toString();
    }

    private static void testSimpleSublistItem() {
        ListItem item1 = new ListItem("This is item1");
        ListItem sublistItem1 = new ListItem("This is sublist item1");
        ListItem subSublistItem1 = new ListItem("This is sub-sublist item1");

        sublistItem1.addSublistItem(subSublistItem1);
        item1.addSublistItem(sublistItem1);

        String s = item1.toString();

        System.out.println(s);
        System.out.println("==============================");
    }

    private static void testComplexSublistItem() {
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

        System.out.println("==============================");
    }

    private static void testDeepSublistItem() {
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

        System.out.println("==============================");
    }

    private static void testRemoveSublistItem() {
        ListItem item1 = new ListItem("This is item1");
        ListItem sublistItem1 = new ListItem("This is sublist item1");
        ListItem sublistItem2 = new ListItem("This is sublist item2");

        item1.addSublistItem(sublistItem1);
        item1.addSublistItem(sublistItem2);

        System.out.println(item1.toString());

        item1.removeSublistItem(0);

        System.out.println(item1.toString());

        System.out.println("==============================");
    }
}
