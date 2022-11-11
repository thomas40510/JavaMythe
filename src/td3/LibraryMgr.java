package td3;
import td3.Documents.*;

import java.util.ArrayList;

public class LibraryMgr {
    /*
    A simple Library Manager
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static class Log{
        public void e(String message){
            System.out.println(ANSI_RED + "[Log/E]: " + message + ANSI_RESET);
        }
        public void i(String message){
            System.out.println("[Log/I]: " + message);
        }
        public void d(String message){
            System.out.println(ANSI_BLUE + "[Log/D]: " + message + ANSI_RESET);
        }
    }
    static Log Log = new Log();

    public static class Library{
        private final int size;
        private final ArrayList<Document> books;

        public Library(int size){
            this.size = size;
            this.books = new ArrayList<Document>();
        }

        public void showDocuments(){
            for(Document doc : books){
                System.out.println(doc);
            }
        }

        public Document getDoc(int i){
            try{
                return books.get(i);
            } catch(IndexOutOfBoundsException e){
                Log.i("Index out of bounds. Document does not exist.");
                return null;
            }
        }

        public ArrayList<Document> getDocuments(){
            return this.books;
        }

        public boolean addDocument(Document doc){
            if(books.size() < size){
                books.add(doc);
                return true;
            } else {
                Log.e("Library is full. Document not added.");
                return false;
            }
        }

        public boolean delDocument(Document doc){
            if(books.contains(doc)){
                books.remove(doc);
                return true;
            } else {
                Log.i("Document does not exist in library.");
                return false;
            }
        }

        public void showAuthors(){
            for(Document doc : books){
                if(doc instanceof Book){
                    System.out.println(((Book) doc).getAuthor());
                }
            }
        }
    }


    public static void main(String[] args){
        Library lib = new Library(10);
        Book b1 = new Book(1, "The Lord of the Rings", "J.R.R. Tolkien", 1000);
        Book b2 = new Book(2, "The Hobbit", "J.R.R. Tolkien", 500);
        Novel n1 = new Novel(3, "The Lord of the Rings", "J.R.R. Tolkien", 1000, Novel.littPrize.Goncourt);
        Novel n2 = new Novel(4, "The Hobbit", "J.R.R. Tolkien", 500, null);
        Magazine m1 = new Magazine(5, "Vice", 12, 2021);
        Magazine m2 = new Magazine(6, "Vice", 11, 2021);
        Dictionary d1 = new Dictionary(7, "English-French Dictionary", "English");
        lib.addDocument(b1);
        lib.addDocument(b2);
        lib.addDocument(n1);
        lib.addDocument(n2);
        lib.addDocument(m1);
        lib.addDocument(m2);
        lib.addDocument(d1);
        lib.showDocuments();
        System.out.println("Document at index 2: " + lib.getDoc(2));
        System.out.println("Document at index 10: " + lib.getDoc(10));
        lib.delDocument(b1);
        lib.showDocuments();
        lib.showAuthors();
    }
}


