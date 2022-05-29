public class WordHandler {
    public static boolean areNeighbors (int x, int y, int length, String[] words){
        for (int i = 0; i < length; ++i)
            if (words[x].contains(String.valueOf(words[y].charAt(i))))
                return true;
        return false;
    }



    public static String getRandomWordFromAlphabet ( char[] alphabet, int length, int alphabetSize){
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < length; ++j) {
            char c = alphabet[(int) (Math.random() * (alphabetSize - 1))];
            s.append(c);
        }
        return s.toString();
    }

    public static boolean haveACommonLetter(String s1, String s2)
    {
        for (int i = 0; i < s1.length(); i++)
            if(s2.contains(String.valueOf(s1.charAt(i))))
                return true;

        return false;
    }

    public static void findMaxSubset(int n, String[] words){

        int currentLength;
        int startIndex;
        int maxLength = 0;
        int maxStartIndex = 0;
        String nextWord, antWord;
        String firstWord = words[0];

        for (int i = 0; i < n; i++){
            nextWord = words[i];
            currentLength = 0;
            startIndex = 0;
            antWord = firstWord;

            for (int j = i; j < n; j++){
                if(haveACommonLetter(nextWord, antWord)){
                    currentLength++;
                    antWord = nextWord;
                    nextWord = words[j];
                }
            }
            if(maxLength < currentLength){
                maxLength = currentLength;
                maxStartIndex = startIndex;
            }
        }

        if(maxLength >= 3) {
            System.out.println("Maximum length is: " + maxLength
                    + ", starting index: " + maxStartIndex);
            for(int i = 0; i < maxLength; i++){
                System.out.print(words[maxStartIndex + i] + " ");
            }
        }
        else {
            System.out.println("No correct subset found.");
        }
    }
}
