import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class IndexGenerator{
        private File file;
    
        public IndexGenerator(String filepath){
            this.file = new File(filepath);
        }
    
        public void readExc(Set<String> excludedWords) throws IOException{
            BufferedReader excReader = new BufferedReader(new FileReader(file));
            String line;
            while((line = excReader.readLine())!=null){
                String[] words = line.split("\\s+");
                for(String word : words){
                    excludedWords.add(word.toLowerCase());
                }
            }
            excReader.close();
        }
    
        public void read(int filenum, Map<String, String> Indexofword, Set<String> excludedWords) throws IOException{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String data;
            while((data = reader.readLine()) != null){
    
                String[] words = data.split("\\s+");
    
                for(String word : words){
                    word = word.replaceAll("[^A-Za-z']+", "");
                    word = word.toLowerCase();
                    System.out.println(word);
                    if(excludedWords.contains(word) || word.equals("")){
                        continue;
                    }
                    if(!Indexofword.containsKey(word)){
                        Indexofword.put(word, ""+filenum);
                    }else{
                        String pages = Indexofword.get(word);
                        if(!pages.contains(""+filenum)){
                            pages += ","+filenum;
                            Indexofword.put(word,pages);
                        }
                    }
                }
            }
            reader.close();
        }
    
        public void write(Map<String, String> Indexofword) throws IOException{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("Word : Page Numbers");
            writer.newLine();
            writer.write("-------------------");
            writer.newLine();
    
            TreeMap<String, String> sorted = new TreeMap<>();
            sorted.putAll(Indexofword);
            for(Map.Entry<String, String> entry : sorted.entrySet()){
                writer.write(entry.getKey()+": "+entry.getValue());
                writer.newLine();
            }
    
            writer.close();
        }
    }
