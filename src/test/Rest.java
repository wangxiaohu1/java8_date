package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Rest {
    
    private static final String[] PATHS = new String[]{
                                                      
                             "D:\\Ykse_resource\\projects\\projects_01\\basedata\\basedata-web\\src\\main\\java\\cn\\com\\ykse\\basedata\\web\\controller\\rest"                         
    };
    
    private static final String ANNO = "@RequestMapping";
    
    public static void main(String[] args) {
        for(String path:PATHS){
            File folder = new File(path);
            readFolder(folder);
            
        }
        
    }
    
    private static void readFolder(File folder){
        try {
            
            File[] files = folder.listFiles();
            if(files != null){
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    if(file.isDirectory()){
                        readFolder(file);
                        continue;
                    }
                    
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    
                    String pathPrefix = null;
                    String line = null;
                    while((line = reader.readLine()) != null){
                        if(!line.contains(ANNO)){
                            continue;
                        }
                        
                        String value = getValue(line);
                        if(pathPrefix == null){
                            pathPrefix = value;
                            continue;
                        }
                        String finalValue = (pathPrefix + value).replaceAll("///", "/").replaceAll("//", "/");
                        System.out.println(finalValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String getValue(String line){
        line = line.trim();
        line = line.replaceAll("@RequestMapping", "");
        line = line.replaceAll("\"", "");
        line = line.replaceAll("\\(", "");
        line = line.replaceAll("\\)", "");
        line = line.replaceAll(" ", "");
        line = line.replaceAll("=", "");
        line = line.replaceAll("value", "");
        line = line.replaceAll("method", "");
        line = line.replaceAll("RequestMethod\\.GET", "");
        line = line.replaceAll("RequestMethod\\.POST", "");
        line = line.replaceAll(",", "");
        line = line.replaceAll("produces", "");
        line = line.replaceAll("application/json", "");
        line = line.replaceAll(";", "");
        line = line.replaceAll("charset", "");
        line = line.replaceAll("UTF-8", "");
        line = line.replaceAll("\\{", "");
        line = line.replaceAll("\\}", "");
        line = "/" + line + "/";
        return line;
        
    }
    
}
