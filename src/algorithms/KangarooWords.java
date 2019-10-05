package algorithms;

import java.util.*;

/**
 * Find kangaroo score
 *
 * [animosity, encourage]
 * ["encourage:urge,boost,inspire", "animosity:hate"]
 * ["animosity:amity,like"]
 *
 *
 */
public class KangarooWords {
    public static int findKangarooScore(List<String> words, List<String> wordsToSynonyms, List<String> wordsToAntonyms) {

        int score = 0;
        Set<String> cousins = new HashSet<>();
        Map<String, Set<String>> synonyms = stringToMapUtil(wordsToSynonyms);
        Map<String, Set<String>> antonyms = stringToMapUtil(wordsToAntonyms);

        for (int i=0; i<words.size(); i++) {
            score += findScore(words.get(i), synonyms.getOrDefault(words.get(i), new HashSet<>()), cousins) + findScore(words.get(i), antonyms.getOrDefault(words.get(i), new HashSet<>()), cousins);
        }

        return score;
    }

    public static int findScore(String word, Set<String> associations, Set<String> cousins) {
        int score = 0;
        for (String w2: associations) {
            if (w2.length() > word.length()) return 0;

            int currScore = findRecursiveScore(word, w2, 0, 0);
            if (currScore == 1) {
                if (!cousins.add(w2)) {
                    score++;
                }
            }
            score += currScore;
        }
        return score;
    }

    public static int findRecursiveScore(String w1, String w2, int i, int j) {
        if (j >= w2.length()) return 1;
        if (i >= w1.length()) return 0;

        if (w1.charAt(i) == w2.charAt(j)) {
            return Math.max(findRecursiveScore(w1, w2, i+1, j+1), findRecursiveScore(w1, w2, i+1, j));
        }

        return findRecursiveScore(w1, w2, i+1, j);
    }

    private static Map<String, Set<String>> stringToMapUtil(List<String> words) {
        Map<String, Set<String>> map = new HashMap<>();
        for (String ws: words) {
            String[] parts = ws.split(":");
            map.put(parts[0], new HashSet<>(Arrays.asList(parts[1].split(","))));
        }


        return map;
    }

    public static void main(String[] args) {
        int score = findKangarooScore(Arrays.asList("animosity", "encourage"), Arrays.asList("encourage:urge,boost,inspire", "animosity:hate"), Arrays.asList("animosity:amity,like"));

        System.out.println(score);
    }
}
