package academy.pocu.comp2500.lab4.app;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import academy.pocu.comp2500.lab4.MemoryCache;
import academy.pocu.comp2500.lab4.EvictionPolicy;

public class Program {

    public static void main(String[] args) {
        testOfficial();
//        testManyCaches();
//        testManyEntry();

        System.out.println("No prob");
    }

    private static void testOfficial() {
        {
            MemoryCache memCacheA = MemoryCache.getInstance("A");

            MemoryCache memCacheB = MemoryCache.getInstance("B");
            MemoryCache memCacheC = MemoryCache.getInstance("C");

            assert memCacheA == MemoryCache.getInstance("A");
            assert memCacheB == MemoryCache.getInstance("B");
            assert memCacheC == MemoryCache.getInstance("C");

            MemoryCache.setMaxInstanceCount(3);

            MemoryCache memCacheD = MemoryCache.getInstance("D");

            assert memCacheA != MemoryCache.getInstance("A");
            assert memCacheC == MemoryCache.getInstance("C");
            assert memCacheB != MemoryCache.getInstance("B");
            assert memCacheD != MemoryCache.getInstance("D");
        }
        {
            MemoryCache memCache = MemoryCache.getInstance("A");
            memCache.addEntry("key1", "value1");
            memCache.addEntry("key2", "value2");
            memCache.addEntry("key3", "value3");
            memCache.addEntry("key4", "value4");
            memCache.addEntry("key5", "value5");

            memCache.setMaxEntryCount(3);

            assert memCache.getEntryOrNull("key1") == null;
            assert memCache.getEntryOrNull("key2") == null;
            assert memCache.getEntryOrNull("key3") != null;
            assert memCache.getEntryOrNull("key4") != null;
            assert memCache.getEntryOrNull("key5") != null;

            memCache.addEntry("key6", "value6");

            assert memCache.getEntryOrNull("key3") == null;

            memCache.getEntryOrNull("key4");
            memCache.getEntryOrNull("key5");
            memCache.getEntryOrNull("key4");

            memCache.addEntry("key7", "value7");

            assert memCache.getEntryOrNull("key6") == null;

            memCache.addEntry("key5", "value5_updated");
            memCache.addEntry("key8", "value8"); // 8, 5, 7

            assert memCache.getEntryOrNull("key4") == null;

            assert memCache.getEntryOrNull("key5").equals("value5_updated");

            memCache.setEvictionPolicy(EvictionPolicy.FIRST_IN_FIRST_OUT);

            memCache.addEntry("key9", "value9");
            assert memCache.getEntryOrNull("key5") == null;

            memCache.addEntry("key10", "value10");
            assert memCache.getEntryOrNull("key7") == null;

            memCache.setMaxEntryCount(1);

            assert memCache.getEntryOrNull("key8") == null;
            assert memCache.getEntryOrNull("key9") == null;
            assert memCache.getEntryOrNull("key10").equals("value10"); // 10

            memCache.setMaxEntryCount(5);
            memCache.setEvictionPolicy(EvictionPolicy.LAST_IN_FIRST_OUT);

            memCache.addEntry("key11", "value11");
            memCache.addEntry("key12", "value12");
            memCache.addEntry("key13", "value13");
            memCache.addEntry("key14", "value14");

            assert memCache.getEntryOrNull("key10") != null;
            assert memCache.getEntryOrNull("key11") != null;
            assert memCache.getEntryOrNull("key12") != null;
            assert memCache.getEntryOrNull("key13") != null;
            assert memCache.getEntryOrNull("key14") != null;

            memCache.addEntry("key15", "value15");

            assert memCache.getEntryOrNull("key14") == null;

            assert memCache.getEntryOrNull("key13") != null;
            assert memCache.getEntryOrNull("key11") != null;
            assert memCache.getEntryOrNull("key12") != null;
            assert memCache.getEntryOrNull("key10") != null;
            assert memCache.getEntryOrNull("key15") != null;

            memCache.setEvictionPolicy(EvictionPolicy.LEAST_RECENTLY_USED);

            memCache.addEntry("key16", "value16");

            assert memCache.getEntryOrNull("key13") == null;
            assert memCache.getEntryOrNull("key10") != null;
            assert memCache.getEntryOrNull("key11") != null;
            assert memCache.getEntryOrNull("key12") != null;
            assert memCache.getEntryOrNull("key15") != null;
            assert memCache.getEntryOrNull("key16") != null;
        }
    }

    private static void testManyCaches() {
        MemoryCache.setMaxInstanceCount(1);
        MemoryCache memCacheA = MemoryCache.getInstance("A");
        MemoryCache.setMaxInstanceCount(Integer.MAX_VALUE);

        MemoryCache memCacheB = MemoryCache.getInstance("B");
        MemoryCache memCacheC = MemoryCache.getInstance("C");
        MemoryCache memCacheD = MemoryCache.getInstance("D");
        MemoryCache memCacheE = MemoryCache.getInstance("E");
        MemoryCache memCacheF = MemoryCache.getInstance("F");
        MemoryCache memCacheG = MemoryCache.getInstance("G");
        MemoryCache memCacheH = MemoryCache.getInstance("H");

        assert memCacheA == MemoryCache.getInstance("A");
        assert memCacheB == MemoryCache.getInstance("B");
        assert memCacheC == MemoryCache.getInstance("C");
        assert memCacheD == MemoryCache.getInstance("D");
        assert memCacheE == MemoryCache.getInstance("E");
        assert memCacheF == MemoryCache.getInstance("F");
        assert memCacheG == MemoryCache.getInstance("G");
        assert memCacheH == MemoryCache.getInstance("H");

        MemoryCache.setMaxInstanceCount(3);

        assert memCacheH == MemoryCache.getInstance("H");
        assert memCacheG == MemoryCache.getInstance("G");
        assert memCacheF == MemoryCache.getInstance("F");

        MemoryCache memCacheI = MemoryCache.getInstance("I");

        assert memCacheI == MemoryCache.getInstance("I");
        assert memCacheG == MemoryCache.getInstance("G");
        assert memCacheF == MemoryCache.getInstance("F");
    }

    private static void testManyEntry() {
        MemoryCache memCacheA = MemoryCache.getInstance("A");

        for (int i = 0; i < 10000; i++) {
            memCacheA.addEntry("hello" + i, "hello" + i);
            System.out.println("hello"+i);
        }

        memCacheA.setEvictionPolicy(EvictionPolicy.LAST_IN_FIRST_OUT);

        memCacheA.setMaxEntryCount(1);

        assert memCacheA.getEntryOrNull("hello0").equals("hello0");
    }
}
