package academy.pocu.comp2500.lab4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MemoryCache {
    private final String diskName;
    private final HashMap<String, String> entry;

    private static int numInstance;
    private static int maxInstance = Integer.MAX_VALUE;
    private static EvictionPolicy evictionPolicy = EvictionPolicy.LEAST_RECENTLY_USED;
    private static final HashMap<String, MemoryCache> caches = new HashMap<>(256);
    private static final LinkedList<String> cachesPriority = new LinkedList<>();

    private MemoryCache(String name) {
        this.diskName = name;
        this.entry = new HashMap<>(256);
        ++numInstance;
    }

    public String getDiskName() {
        return this.diskName;
    }

    public static MemoryCache getInstance(String name) {
        if (caches.get(name) != null) {
            if (evictionPolicy == EvictionPolicy.LEAST_RECENTLY_USED) {
                cachesPriority.remove(name);
                cachesPriority.add(name);
            }

            return caches.get(name);
        }

        if (numInstance == maxInstance) {
            String tmpName = null;

            switch (evictionPolicy) {
                case FIRST_IN_FIRST_OUT:
                case LEAST_RECENTLY_USED:
                    tmpName = cachesPriority.getFirst();
                    break;
                case LAST_IN_FIRST_OUT:
                    tmpName = cachesPriority.getLast();
                    break;
                default:
                    assert false : "Unknown eviction policy type";
                    break;
            }

            assert tmpName != null : "Cache priority zero... wrong priority add or delete";

            caches.remove(tmpName);
            cachesPriority.remove(tmpName);
        }

        caches.put(name, new MemoryCache(name));
        cachesPriority.add(name);

        assert caches.size() == cachesPriority.size() : "";

        return caches.get(name);
    }

    public void setInstanceCount(int max) {
        assert max != 0 : "Max instance count can never be zero";

        maxInstance = max;
    }

    public void setEvictionPolicy(EvictionPolicy policyType) {
        evictionPolicy = policyType;
    }

    public void clear() {
        caches.clear();
        cachesPriority.clear();

        numInstance = 0;
    }
}
