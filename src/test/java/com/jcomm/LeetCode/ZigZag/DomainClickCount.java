package com.jcomm.LeetCode.ZigZag;

import java.util.HashMap;
import java.util.Map;

public class DomainClickCount {

    public static void main(String[] args) {
        String [] counts = {"900,google.com",
        "60,mail.yahoo.com",
        "10,mobile.sports.yahoo.com",
        "40,sports.yahoo.com",
        "300,yahoo.com",
        "10,stackoverflow.com",
        "2,en.wikipedia.org",
        "1,es.wikipedia.org",
        "1,mobile.sports"};

        System.out.println(DomainClickCount.countUrls(counts));
    }


    /**
     *
     *
     *
     *
     *
     * @param urls
     * @return
     */
//    public static Map<String,Integer> countUrls(String [] urls){
//        Map<String, Integer> m =  new HashMap<>();
//
//        for(String entry : urls){
//            String [] entries = entry.split(",");
//            Integer count = Integer.parseInt(entries[0]);
//            String domain = entries[1];
//            String [] domainEntries = domain.split("\\.");
//            StringBuilder domainBuilder = new StringBuilder();
//            for(int idx = domainEntries.length-1; idx >=0; idx--){
//                String domainEntry = domainEntries[idx];
//                StringBuilder t = new StringBuilder();
//                if(idx == domainEntries.length-1){
//                    domainBuilder = t.append(domainEntry).append(domainBuilder);
//
//                }else{
//                    domainBuilder = t.append(domainEntry).append(".").append(domainBuilder);
//                }
//                m.computeIfPresent(domainBuilder.toString(), (k,v) -> v + count);
//                m.putIfAbsent(domainBuilder.toString(), count);
//            }
//        }
//        return m;
//    }




    public static Map<String, Integer> countUrls(String [] urls){
        Map<String, Integer> entries = new HashMap<>();
        for(int idx = 0; idx < urls.length; idx++){
            String [] arr= urls[idx].split(",");
            Integer count = Integer.parseInt(arr[0]);
            String url = arr[1];
            String [] tempUrls = url.split("\\.");
            String urlBuilder = "";
            for(int x = 0;  x < tempUrls.length;  x++){
                if(x == 0){
                    urlBuilder  =  urlBuilder.concat(tempUrls[x]);
                    continue;
                }
                urlBuilder =  urlBuilder.concat(".").concat(tempUrls[x]);
                entries.computeIfPresent(urlBuilder, (k,y) -> y + count);
                entries.putIfAbsent(urlBuilder, count);
                entries.computeIfPresent(tempUrls[x], (k,y) -> y + count);
                entries.putIfAbsent(tempUrls[x], count);
            }
        }

        return entries;
    }
}
