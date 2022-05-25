package academy.pocu.comp2500.lab4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MemoryCache {
    private final String diskName;
    private int maxEntryCount;
    private final HashMap<String, String> entry;
    private final LinkedList<String> entryRecentlyUsed;
    private final LinkedList<String> entryAdded;

    private static int maxInstanceCount = Integer.MAX_VALUE;
    private static EvictionPolicy evictionPolicy = EvictionPolicy.LEAST_RECENTLY_USED;
    private static final HashMap<String, MemoryCache> caches = new HashMap<>(256);
    private static final LinkedList<String> cachesRecentlyUsed = new LinkedList<>();
    private static final LinkedList<String> cachesAdded = new LinkedList<>();

    private MemoryCache(String name) {
        this.diskName = name;
        this.maxEntryCount = Integer.MAX_VALUE;
        this.entry = new HashMap<>(256);
        this.entryRecentlyUsed = new LinkedList<>();
        this.entryAdded = new LinkedList<>();
    }

    public String getDiskName() {
        return this.diskName;
    }

    public static MemoryCache getInstance(String name) {
        if (caches.containsKey(name)) {
            cachesRecentlyUsed.remove(name);
            cachesRecentlyUsed.addFirst(name);

            assert caches.size() == cachesRecentlyUsed.size();
            return caches.get(name);
        }

        if (caches.size() == maxInstanceCount) {
            removeCacheByEvictionPolicy();
        }

        caches.put(name, new MemoryCache(name));
        cachesRecentlyUsed.addFirst(name);
        cachesAdded.addFirst(name);

        assert caches.size() == cachesRecentlyUsed.size();
        assert caches.size() == cachesAdded.size();

        return caches.get(name);
    }

    public static void setMaxInstanceCount(int max) {
        assert (max != 0) : "Max instance count can never be zero";

        maxInstanceCount = max;

        while (caches.size() > maxInstanceCount) {
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

    public void clear() {
        caches.clear();
        cachesRecentlyUsed.clear();
    }

    private static void removeCacheByEvictionPolicy() {
        String removedCacheName = null;

        switch (evictionPolicy) {
            case LEAST_RECENTLY_USED:
                removedCacheName = cachesRecentlyUsed.getLast();
                break;
            case FIRST_IN_FIRST_OUT:
                removedCacheName = cachesAdded.getLast();
                break;
            case LAST_IN_FIRST_OUT:
                removedCacheName = cachesAdded.getFirst();
                break;
            default:
                assert false : "Unknown eviction policy type";
                break;
        }

        assert removedCacheName != null : "Cache priority zero... wrong priority add or delete";

        MemoryCache removedCache = caches.remove(removedCacheName);
        removedCache = null;
        cachesRecentlyUsed.remove(removedCacheName);
        cachesAdded.remove(removedCacheName);
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
