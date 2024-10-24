package Complejidad;

import java.util.HashMap;
import java.util.Map;

public class EJ1v2 {
    public static int longestSubsequence(int[] arr) {
        Map<Integer, Integer> counts = new HashMap<>();
        int maxCount = 0;

        for (int num : arr) {  // O(n)
            counts.put(num, counts.getOrDefault(num, 0) + 1); // O(1)

            maxCount = Math.max(maxCount, counts.get(num)); // O(1) 
        }

        return maxCount; // O(n)
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 1, 1, 1};
        int longest = longestSubsequence(arr);  
        System.out.println("La longitud de la subsecuencia más larga es: " + longest);
    }
}

