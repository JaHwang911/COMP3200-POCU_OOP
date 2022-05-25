package academy.pocu.comp2500.lab4;

import java.util.HashMap;
import java.util.LinkedList;

public class MemoryCache {
    private int maxEntryCount;
    private final HashMap<String, String> entry;
    private final LinkedList<String> entryRecentlyUsed;
    private final LinkedList<String> entryAdded;

    private static int maxInstanceCount = Integer.MAX_VALUE;
    private static EvictionPolicy evictionPolicy = EvictionPolicy.LEAST_RECENTLY_USED;
    private static final HashMap<String, MemoryCache> CACHES = new HashMap<>(256);
    private static final LinkedList<String> CACHES_RECENTLY_USED = new LinkedList<>();
    private static final LinkedList<String> CACHES_ADDED = new LinkedList<>();

    private MemoryCache(String name) {
        this.maxEntryCount = Integer.MAX_VALUE;
        this.entry = new HashMap<>(256);
        this.entryRecentlyUsed = new LinkedList<>();
        this.entryAdded = new LinkedList<>();
    }

    public static MemoryCache getInstance(String name) {
        if (CACHES.containsKey(name)) {
            CACHES_RECENTLY_USED.remove(name);
            CACHES_RECENTLY_USED.addFirst(name);

            assert CACHES.size() == CACHES_RECENTLY_USED.size();
            return CACHES.get(name);
        }

        if (CACHES.size() == maxInstanceCount) {
            removeCacheByEvictionPolicy();
        }

        CACHES.put(name, new MemoryCache(name));
        CACHES_RECENTLY_USED.addFirst(name);
        CACHES_ADDED.addFirst(name);

        assert CACHES.size() == CACHES_RECENTLY_USED.size();
        assert CACHES.size() == CACHES_ADDED.size();

        return CACHES.get(name);
    }

    public static void setMaxInstanceCount(int max) {
        assert (max != 0) : "Max instance count can never be zero";

        maxInstanceCount = max;

        while (CACHES.size() > maxInstanceCount) {
            removeCacheByEvictionPolicy();
        }
    }

    public void addEntry(String key, String value) {
        if (this.entry.containsKey(key)) {
            this.entryRecentlyUsed.remove(key);
            this.entryRecentlyUsed.addFirst(key);

            this.entry.replace(key, value);
            return;
        }

        if (this.entry.size() == maxEntryCount) {
            removeEntryByEvictionPolicy();
        }

        this.entry.put(key, value);
        this.entryRecentlyUsed.addFirst(key);
        this.entryAdded.addFirst(key);

        assert this.entry.size() == this.entryRecentlyUsed.size();
        assert this.entry.size() == this.entryAdded.size();
    }

    public String getEntryOrNull(String key) {
        if (this.entry.containsKey(key)) {
            this.entryRecentlyUsed.remove(key);
            this.entryRecentlyUsed.addFirst(key);

            return this.entry.get(key);
        }

        return null;
    }

    public void setMaxEntryCount(int max) {
        assert (max != 0) : "Max entry count can never be zero";
        this.maxEntryCount = max;

        while (this.entry.size() > this.maxEntryCount) {
            removeEntryByEvictionPolicy();
        }
    }

    public static void setEvictionPolicy(EvictionPolicy policyType) {
        evictionPolicy = policyType;
    }

    public static void clear() {
        CACHES.clear();
        CACHES_RECENTLY_USED.clear();
        CACHES_ADDED.clear();
    }

    private static void removeCacheByEvictionPolicy() {
        String removedCacheName = null;

        switch (evictionPolicy) {
            case LEAST_RECENTLY_USED:
                removedCacheName = CACHES_RECENTLY_USED.getLast();
                break;
            case FIRST_IN_FIRST_OUT:
                removedCacheName = CACHES_ADDED.getLast();
                break;
            case LAST_IN_FIRST_OUT:
                removedCacheName = CACHES_ADDED.getFirst();
                break;
            default:
                assert false : "Unknown eviction policy type";
                break;
        }

        assert removedCacheName != null : "Cache priority zero... wrong priority add or delete";

        MemoryCache removedCache = CACHES.remove(removedCacheName);
        removedCache = null;
        CACHES_RECENTLY_USED.remove(removedCacheName);
        CACHES_ADDED.remove(removedCacheName);
    }

    private void removeEntryByEvictionPolicy() {
        String removedEntry = null;

        switch (evictionPolicy) {
            case LEAST_RECENTLY_USED:
                removedEntry = this.entryRecentlyUsed.getLast();
                break;
            case FIRST_IN_FIRST_OUT:
                removedEntry = this.entryAdded.getLast();
                break;
            case LAST_IN_FIRST_OUT:
                removedEntry = this.entryAdded.getFirst();
                break;
            default:
                assert false : "Unknown eviction policy type";
                break;
        }

        assert (removedEntry != null) : "Entry priority zero... wrong priority add or delete";

        this.entry.remove(removedEntry);
        this.entryRecentlyUsed.remove(removedEntry);
        this.entryAdded.remove(removedEntry);

        assert this.entry.size() == this.entryRecentlyUsed.size();
        assert this.entry.size() == this.entryAdded.size();
    }
}
