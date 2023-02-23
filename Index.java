import java.io.IOException;
import java.util.*;

public class Index{ 
    static Map<String, String> Indexofword = new HashMap<>();
    static Set<String> excludedWords = new HashSet<>();
    public static void main(String[] args) {
        try{
            IndexGenerator excobj = new IndexGenerator("exclude-words.txt");
            excobj.readExc(excludedWords);
            IndexGenerator pageobj1 = new IndexGenerator("page1.txt");
            pageobj1.read(1, Indexofword, excludedWords);
            IndexGenerator pageobj2 = new IndexGenerator("page2.txt");
            pageobj2.read(2, Indexofword, excludedWords);
            IndexGenerator pageobj3 = new IndexGenerator("page3.txt");
            pageobj3.read(3, Indexofword, excludedWords);
            IndexGenerator writeobj = new IndexGenerator("index.txt");
            writeobj.write(Indexofword);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}