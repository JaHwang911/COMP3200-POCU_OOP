package academy.pocu.comp2500.lab4;

import java.util.ArrayList;
import java.util.HashMap;

/*
    개체 마다 자기 순서를 가지고 있고 만약 어떤 일을 하면 그 개체의 순서는 1로 바꾸고
    0 부터 그 개체의 원래 순서의 값 만큼 for 문 돌면서 값을 순선의 값을 1씩 올려 줌
*/
public class MemoryCache {
    private final String diskName;
    private final HashMap<String, String> entry;
    private int priority;

    private static int numInstance;
    private static int maxInstance = Integer.MAX_VALUE;
    private static EvictionPolicy evictionPolicy = EvictionPolicy.LEAST_RECENTLY_USED;
    private static ArrayList<MemoryCache> caches;

    private MemoryCache(String name) {
        this.diskName = name;
        this.entry = new HashMap<>(256);
        this.priority = 0;
        ++numInstance;
    }

    public String getDiskName() {
        return this.diskName;
    }

    public static MemoryCache getInstance(String name) {
        for (MemoryCache cache : caches) {
            if (cache.getDiskName().equals(name)) {
                return cache;
            }
        }

        if (numInstance == maxInstance) {
            // Delete instance by eviction policy
            switch (evictionPolicy) {
                case FIRST_IN_FIRST_OUT:
                    caches.remove(0);
                    break;
                case LAST_IN_FIRST_OUT:
                    caches.remove(maxInstance - 1);
                    break;
            }
        }

        MemoryCache retInstance = new MemoryCache(name);
        caches.add(retInstance);

        return retInstance;
    }

    public void setInstanceCount(int max) {
        assert max != 0 : "Max instance count can never be zero";

        maxInstance = max;
    }

    public void setEvictionPolicy(EvictionPolicy policyType) {
        evictionPolicy = policyType;
    }

    public void clear() {
        caches = null;
        numInstance = 0;
    }

    private void setPriority(int priority) {
        this.priority = priority;
    }

    private int getPriority() {
        return this.priority;
    }

    private void fixPriority(int priorityOfInstance) {
        for (MemoryCache cache : caches) {
            int priority = cache.getPriority();

            if (priority < priorityOfInstance) {
                cache.setPriority(priority + 1);
            }
        }
    }
}
