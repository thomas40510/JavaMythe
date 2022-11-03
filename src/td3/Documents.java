package td3;

/*
 Implements Documents used in LibraryMgr
 */

public class Documents {
    public static class Document{
        private final int regNum;
        private final String title;

        public Document(int regNum, String title) {
            this.regNum = regNum;
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public int getRegNum() {
            return this.regNum;
        }

        public String toString(){
            return "Document: " + title + " (nbr " + regNum + ")";
        }
    }

    public static class Book extends Document{
        private final String author;
        private final int nbPages;

        public Book(int regNum, String title, String author, int nbPages) {
            super(regNum, title);
            this.author = author;
            this.nbPages = nbPages;
        }

        public String getAuthor() {
            return this.author;
        }

        public int getNbPages() {
            return this.nbPages;
        }

        public String toString(){
            return "Book: " + getTitle() + " (nbr " + getRegNum() + ") by " + author + " (" + nbPages + " pages)";
        }
    }

    public static class Dictionary extends Document{
        private final String language;

        public Dictionary(int regNum, String title, String language) {
            super(regNum, title);
            this.language = language;
        }

        public String getLanguage() {
            return this.language;
        }

        public String toString(){
            return "Dictionary: " + getTitle() + " (nbr " + getRegNum() + ") in " + language;
        }
    }

    public static class Magazine extends Document{
        private final int month;
        private final int year;

        public Magazine(int regNum, String title, int month, int year) {
            super(regNum, title);
            this.month = month;
            this.year = year;
        }

        public int getMonth() {
            return this.month;
        }

        public int getYear() {
            return this.year;
        }

        public String toString(){
            return "Magazine: " + getTitle() + " (nbr " + getRegNum() + ") of " + month + "/" + year;
        }
    }

    public static class Manual extends Book{
        enum Level {Elementary, MiddleSchool, HighSchool};
        private final Level level;

        public Manual(int regNum, String title, String author, int nbPages, Level level) {
            super(regNum, title, author, nbPages);
            this.level = level;
        }

        public String getLevel(){
            return level.toString();
        }

        public String toString(){
            return "Manual: " + getTitle() + " (nbr " + getRegNum() + ") by " + this.getAuthor() + " (" + this.getNbPages() + " pages) for " + level;
        }
    }

    public static class Novel extends Book{
        enum littPrize {Goncourt, Renaudot, Femina, Interallié, Médicis};
        private littPrize prize;

        public Novel(int regNum, String title, String author, int nbPages, littPrize prize) {
            super(regNum, title, author, nbPages);
            this.prize = prize;
        }

        public String getPrize(){
            return prize.toString();
        }

        public void setPrize(littPrize prize){
            this.prize = prize;
        }

        public String toString(){
            return "Novel: " + getTitle() + " (nbr " + getRegNum() + ") by " + this.getAuthor() + " (" + this.getNbPages() + " pages) - " + prize;
        }
    }

}
