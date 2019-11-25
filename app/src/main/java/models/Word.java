package models;

public class Word {
    private int wordId;
    private String word;
    private String meaning;

    public Word(int wordId,String word, String meaning){
        this.wordId = wordId;
        this.word = word;
        this.meaning = meaning;
    }

    public int getWordId() {return wordId;}

    public void setWordId(int wordId) { this.wordId = wordId;}

    public String getWord() {return word;}

    public void setWord(String word) {this.word = word;}

    public String getMeaning() {return meaning;}

    public void setMeaning(String meaning) {this.meaning = meaning;}
}
