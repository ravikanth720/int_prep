import java.util.*;

class WordCount {

    class Frequency  implements Comparable<Frequency> {
        String word;
        int count;
        public Frequency(String word) {
            this.word = word;
            count = 1;
        }

        public void increment() {
            count++;
        }

        @Override
        public int compareTo(Frequency other) {
            int comp = other.count - this.count;
            return comp == 0 ? this.word.compareTo(other.word) : comp;
        }
    }

    public List<String> frequentWords(List<String> words, List<String> stopWords) {
        List<String> result = new ArrayList<String>();
        Map<String, Frequency> map = new HashMap<String, Frequency>();

        for (String word: words) {
            if (map.containsKey(word)) {
                map.get(word).increment();
            } else {
                map.put(word, new Frequency(word));
            }
        }

        List<Frequency> wordList = new ArrayList<Frequency>(map.values());
        Collections.sort(wordList); 

        for (Frequency wc: wordList) {
            result.add(wc.word);
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> wl = Arrays.asList(new String[]{"a", "b", "c", "d", "a", "c", "z", "a", "a"});
        List<String> sw = Arrays.asList(new String[]{"d", "z", "y"});
        WordCount wc = new WordCount();
        System.out.println(wc.frequentWords(wl, sw));
    }
}