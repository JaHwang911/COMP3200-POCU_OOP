package academy.pocu.comp2500.lab4;

import java.util.HashMap;

public class MemoryCache {
    private final String name;
    private int maxEntryCount;
    private MemoryCache previous;
    private MemoryCache next;
    private MemoryCache lruPrevious;
    private MemoryCache lruNext;
    private final HashMap<String, Entry> entry;
    private String firstEntryKey;
    private String lastEntryKey;
    private String lruFirstEntryKey;
    private String lruLastEntryKey;

    private static int maxInstanceCount = Integer.MAX_VALUE;
    private static EvictionPolicy evictionPolicy = EvictionPolicy.LEAST_RECENTLY_USED;
    private static final HashMap<String, MemoryCache> CACHES = new HashMap<>(256);
    private static String firstCacheName;
    private static String lastCacheName;
    private static String lruFirstCacheName;
    private static String lruLastCacheName;

    private MemoryCache(String name, MemoryCache lruNextOrNull, MemoryCache previous) {
        this.name = name;
        this.maxEntryCount = Integer.MAX_VALUE;
        this.lruNext = lruNextOrNull;
        this.previous = previous;
        this.entry = new HashMap<>();
    }

    public static MemoryCache getInstance(String name) {
        if (CACHES.containsKey(name)) {
            MemoryCache current = CACHES.get(name);
            MemoryCache previous = current.lruPrevious;
            MemoryCache next = current.lruNext;

            if (next == null) {
                assert lruLastCacheName.equals(current.name);

                lruLastCacheName = previous.name;
                previous.lruNext = null;
            } else {
                previous.lruNext = next;
                next.lruPrevious = previous;
            }

            MemoryCache firstCache = CACHES.get(lruFirstCacheName);

            current.lruPrevious = firstCache.lruPrevious;
            current.lruNext = firstCache;

            firstCache.lruPrevious = current;

            lruFirstCacheName = current.name;

            return CACHES.get(name);
        }

        if (CACHES.size() == maxInstanceCount) {
            removeCacheByEvictionPolicy();
        }

        if (CACHES.size() == 0) {
            CACHES.put(name, new MemoryCache(name,null, null));

            lruFirstCacheName = name;
            lruLastCacheName = name;

            firstCacheName = name;
            lastCacheName = name;

            return CACHES.get(name);
        }

        MemoryCache lruFirstCache = CACHES.get(lruFirstCacheName);
        MemoryCache lastOrderedCache = CACHES.get(lastCacheName);
        MemoryCache current = new MemoryCache(name, lruFirstCache, lastOrderedCache);

        CACHES.put(name, current);

        lruFirstCache.lruPrevious = current;
        lastOrderedCache.next = current;

        lruFirstCacheName = name;
        lastCacheName = name;

        return current;
    }

    public static void setMaxInstanceCount(int max) {
        assert (max != 0) : "Max instance count can never be zero";

        maxInstanceCount = max;

        while (CACHES.size() > maxInstanceCount) {
            removeCacheByEvictionPolicy();
        }
    }

    public void setEvictionPolicy(EvictionPolicy policyType) {
        evictionPolicy = policyType;
    }

    public void addEntry(String key, String value) {
        if (this.entry.containsKey(key)) {
            Entry current = this.entry.get(key);
            Entry prev = current.getLruPrevious();
            Entry next = current.getLruNext();

            current.setValue(value);

            if (this.entry.size() == 1) {
                return;
            }

            if (next == null) {
                assert this.lruLastEntryKey.equals(current.getKey());

                this.lruLastEntryKey = prev.getKey();
                prev.setLruNext(null);
                return;
            } else if (prev == null) {
                next.setLruPrevious(null);
                this.lruFirstEntryKey = next.getKey();
                return;
            }

            prev.setLruNext(next);
            next.setLruPrevious(prev);

            Entry firstEntry = this.entry.get(this.lruFirstEntryKey);

            current.setLruPrevious(null);
            current.setLruNext(firstEntry);
            firstEntry.setLruPrevious(current);
            this.lruFirstEntryKey = key;

            return;
        }

        if (this.entry.size() == maxEntryCount) {
            removeEntryByEvictionPolicy();
        }

        if (this.entry.size() == 0) {
            this.entry.put(key, new Entry(key, value, null, null));

            this.lruFirstEntryKey = key;
            this.lruLastEntryKey = key;

            this.firstEntryKey = key;
            this.lastEntryKey = key;

            return;
        }

        Entry lruFirstEntry = this.entry.get(this.lruFirstEntryKey);
        Entry lastOrderedEntry = this.entry.get(this.lastEntryKey);
        Entry current = new Entry(key, value, lastOrderedEntry, lruFirstEntry);

        this.entry.put(key, current);

        lruFirstEntry.setLruPrevious(current);
        lastOrderedEntry.setNext(current);

        this.lruFirstEntryKey = key;
        this.lastEntryKey = key;
    }

    public String getEntryOrNull(String key) {
        if (this.entry.containsKey(key)) {
            Entry current = this.entry.get(key);
            Entry prev = current.getLruPrevious();
            Entry next = current.getLruNext();
            Entry firstEntry = this.entry.get(this.lruFirstEntryKey);

            if (this.entry.size() == 1) {
                return current.getValue();
            }

            if (next == null) {
                prev.setLruNext(null);
                this.lruLastEntryKey = prev.getKey();
            } else {
                prev.setLruNext(next);
                next.setLruPrevious(prev);
            }

            current.setLruPrevious(null);
            current.setLruNext(firstEntry);
            firstEntry.setLruPrevious(current);

            this.lruFirstEntryKey = key;

            return current.getValue();
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

    public static void clear() {
        CACHES.clear();
    }

    private static void removeCacheByEvictionPolicy() {
        MemoryCache removedCache;
        MemoryCache prev;
        MemoryCache next;

        switch (evictionPolicy) {
            case LEAST_RECENTLY_USED:
                removedCache = CACHES.remove(lruLastCacheName);

                lruLastCacheName = removedCache.lruPrevious.name;
                CACHES.get(lruLastCacheName).lruNext = null;

                prev = removedCache.previous;
                next = removedCache.next;

                if (prev == null) {
                    firstCacheName = next.name;
                    next.previous = null;
                    break;
                } else if (next == null) {
                    lastCacheName = prev.name;
                    prev.next = null;
                    break;
                }

                prev.next = next;
                next.previous = prev;

                break;
            case FIRST_IN_FIRST_OUT:
                removedCache = CACHES.remove(firstCacheName);
                firstCacheName = removedCache.next.name;
                break;
            case LAST_IN_FIRST_OUT:
                removedCache = CACHES.remove(lastCacheName);
                lastCacheName = removedCache.previous.name;
                break;
            default:
                assert false : "Unknown eviction policy type";
                break;
        }
    }

    private void removeEntryByEvictionPolicy() {
        Entry removedEntry;
        Entry prev;
        Entry next;

        switch (evictionPolicy) {
            case LEAST_RECENTLY_USED:
                removedEntry = this.entry.remove(this.lruLastEntryKey);
                lruLastEntryKey = removedEntry.getLruPrevious().getKey();
                this.entry.get(lruLastEntryKey).setLruNext(null);

                prev = removedEntry.getPrevious();
                next = removedEntry.getNext();

                if (this.entry.size() == 0) {
                    this.firstEntryKey = null;
                    this.lastEntryKey = null;
                    this.lruFirstEntryKey = null;
                    this.lruLastEntryKey = null;
                    break;
                }

                if (prev == null) {
                    this.firstEntryKey = next.getKey();
                    next.setPrevious(null);
                    break;
                } else if (next == null) {
                    this.lastEntryKey = prev.getKey();
                    prev.setNext(null);
                    break;
                }

                prev.setNext(next);
                next.setPrevious(prev);

                break;
            case FIRST_IN_FIRST_OUT:
                removedEntry = this.entry.remove(this.firstEntryKey);
                this.firstEntryKey = removedEntry.getNext().getKey();

                prev = removedEntry.getLruPrevious();
                next = removedEntry.getLruNext();

                if (this.entry.size() == 0) {
                    this.firstEntryKey = null;
                    this.lastEntryKey = null;
                    this.lruFirstEntryKey = null;
                    this.lruLastEntryKey = null;
                    break;
                }

                if (prev == null) {
                    this.lruFirstEntryKey = next.getKey();
                    next.setLruPrevious(null);
                    break;
                } else if (next == null) {
                    this.lruLastEntryKey = prev.getKey();
                    prev.setLruNext(null);
                    break;
                }

                prev.setLruNext(next);
                next.setLruPrevious(prev);

                break;
            case LAST_IN_FIRST_OUT:
                removedEntry = this.entry.remove(this.lastEntryKey);
                this.lastEntryKey = removedEntry.getPrevious().getKey();

                prev = removedEntry.getLruPrevious();
                next = removedEntry.getLruNext();

                if (this.entry.size() == 0) {
                    this.firstEntryKey = null;
                    this.lastEntryKey = null;
                    this.lruFirstEntryKey = null;
                    this.lruLastEntryKey = null;
                    break;
                }

                if (prev == null) {
                    this.lruFirstEntryKey = next.getKey();
                    next.setLruPrevious(null);
                    break;
                } else if (next == null) {
                    this.lruLastEntryKey = prev.getKey();
                    prev.setLruNext(null);
                    break;
                }

                prev.setLruNext(next);
                next.setLruPrevious(prev);

                break;
            default:
                assert false : "Unknown eviction policy type";
                break;
        }
    }
}
