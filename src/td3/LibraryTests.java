package td3;

import org.junit.jupiter.api.Test;
import td3.Documents.Document;
import td3.Documents.Novel;
import td3.LibraryMgr.Library;

public class LibraryTests {
    @Test
    public void testCreate(){
        Library lib = new Library(3);
        assert lib.getDocuments().size() == 0;
    }

    @Test
    public void testAddDocument(){
        Library lib = new Library(3);
        Document doc = new Document(123, "test");
        assert lib.addDocument(doc);
        assert lib.getDocuments().size() == 1;
        assert lib.getDocuments().get(0).getRegNum() == 123;
        assert lib.getDoc(0).getTitle().equals("test");
    }

    @Test
    public void testAddDocumentFull(){
        Library lib = new Library(1);
        Document doc = new Document(345, "test");
        assert lib.addDocument(doc);
        assert lib.getDocuments().size() == 1;
        assert lib.getDocuments().get(0).getRegNum() == 345;
        assert lib.getDoc(0).getTitle().equals("test");
        Document doc2 = new Document(456, "test2");
        assert !lib.addDocument(doc2);
        assert lib.getDocuments().size() == 1;
    }

    @Test
    public void testDelDocument(){
        Library lib = new Library(3);
        Document doc = new Document(123, "test");
        assert lib.addDocument(doc);
        assert lib.getDocuments().size() == 1;
        assert lib.getDocuments().get(0).getRegNum() == 123;
        assert lib.getDoc(0).getTitle().equals("test");
        assert lib.delDocument(doc);
        assert lib.getDocuments().size() == 0;
    }

    @Test
    public void testDelDocumentNotFound(){
        Library lib = new Library(3);
        Document doc = new Document(123, "test");
        assert lib.addDocument(doc);
        assert lib.getDocuments().size() == 1;
        assert lib.getDocuments().get(0).getRegNum() == 123;
        assert lib.getDoc(0).getTitle().equals("test");
        Document doc2 = new Document(456, "test2");
        assert !lib.delDocument(doc2);
        assert lib.getDocuments().size() == 1;
    }

    @Test
    public void testGetDoc(){
        Library lib = new Library(3);
        Document doc = new Document(123, "test");
        assert lib.addDocument(doc);
        assert lib.getDocuments().size() == 1;
        assert lib.getDocuments().get(0).getRegNum() == 123;
        assert lib.getDoc(0).getTitle().equals("test");
        assert lib.getDoc(0) == doc;
    }

    @Test
    public void testGetDocNotFound(){
        Library lib = new Library(3);
        Document doc = new Document(123, "test");
        assert lib.addDocument(doc);
        assert lib.getDocuments().size() == 1;
        assert lib.getDocuments().get(0).getRegNum() == 123;
        assert lib.getDoc(0).getTitle().equals("test");
        assert lib.getDoc(1) == null;
    }

    @Test
    public void testAddNovel(){
        Library lib = new Library(3);
        Novel nov = new Novel(123, "test", "author", 40, Novel.littPrize.Goncourt);
        assert lib.addDocument(nov);
        assert lib.getDoc(0).getClass() == Novel.class;
        assert lib.getDocuments().size() == 1;
        assert lib.getDocuments().get(0).getRegNum() == 123;
        assert lib.getDoc(0).getTitle().equals("test");
        assert ((Novel) lib.getDoc(0)).getPrize().equals("Goncourt");
        assert ((Novel) lib.getDoc(0)).getNbPages() == 40;
        assert lib.getDoc(0) == nov;
        assert ((Novel)lib.getDoc(0)).getAuthor().equals("author");
    }

}
