package com.jcomm.whiteboard;

import java.util.*;

public class ThreeSum {

    HashSet<String> existingPaths = new HashSet<>();

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Map<Integer, Set<Integer>> firstPass = new HashMap<>();
        Set<Integer> cSeenValues = new HashSet<>();
        Set<Integer> iSeenValues = new HashSet<>();



        for(int x = 0; x < nums.length; x++) {
            if(firstPass.containsKey(nums[x])) {
                firstPass.get(nums[x]).add(x);
            }else {
                Set<Integer> indexes = new TreeSet<>();
                indexes.add(x);
                firstPass.put(nums[x], indexes);
            }
        }

        for(int i = 0; i < nums.length - 1; i++) {
            if(iSeenValues.contains(nums[i])) {
                continue;
            }

            for(int c = i +1; c < nums.length; c++) {
                int sum = (nums[i] + nums[c]) * -1;
                if(cSeenValues.contains(nums[c])) {
                    continue;
                }

                Set<Integer> indexes = firstPass.get(sum);
                boolean doCheck = indexes != null;
                if(doCheck && indexes.size() == 2) {
                    doCheck = !(indexes.contains(i) && indexes.contains(c));
                } else if(doCheck && indexes.size() == 1) {
                    doCheck = !indexes.contains(i) && !indexes.contains(c);
                }

                var list = List.of(sum, nums[i], nums[c]);
                if(doCheck && !alreadyExist(list)) {
                    results.add(list);
                }
                cSeenValues.add(nums[c]);
            }
            cSeenValues.clear();
            iSeenValues.add(nums[i]);
        }
        return results;
    }

    public boolean alreadyExist(List<Integer> nums) {
        String sb = "";
        for (Integer i : nums.stream().sorted().toList()) {
            sb = sb.concat(i.toString());
        }
        if (existingPaths.contains(sb)) {
            return true;
        }
        existingPaths.add(sb);
        return false;
    }


    public static void main(String[] args) {
        ThreeSum sum = new ThreeSum();
        sum.threeSum(new int[]{0, 0, 0});
    }
}
