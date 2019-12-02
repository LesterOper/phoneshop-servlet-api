package com.es.phoneshop.web.security;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapDosProtectionService implements DosProtectionService {

    private final static int MAX_REQUEST_COUNT = 100;
    private static DosProtectionService dosProtectionService;

    private Map<String, Integer> requestCountMap = new ConcurrentHashMap<>();

    private MapDosProtectionService() {

    }

    public static DosProtectionService getInstance() {
        if (dosProtectionService == null) {
            synchronized (MapDosProtectionService.class) {
                if (dosProtectionService == null) {
                    dosProtectionService = new MapDosProtectionService();
                }
            }
        }
        return dosProtectionService;
    }

    @Override
    public boolean allowed(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        Integer count = requestCountMap.get(ip);
        if (count == null) {
            count = 0;
        } else if (count > MAX_REQUEST_COUNT) {
            return false;
        }
        requestCountMap.put(ip, count+1);
        return true;
    }
}
