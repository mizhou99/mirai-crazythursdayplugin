package datas;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static java.nio.file.Files.newBufferedReader;

public class DataManager {
    public static void createCrazy() throws IOException {
        Path path = Paths.get("data/mi.yxz.mirai-CrazyThursdayPlugin");
        Files.createDirectories(path);
        File file = new File("data/mi.yxz.mirai-CrazyThursdayPlugin/thursday.txt");
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print("请用你自己的段子填满这里(π_π)");
        }
    }
    public static boolean checkCrazyFile(){
        File root = new File("data/mi.yxz.mirai-CrazyThursdayPlugin/thursday.txt");
        return root.exists();
    }
    public static String sendCrazy(File file) throws IOException{
        Random random = new Random();
        String fileName = file.getPath();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int x = 0;
        while (bufferedReader.readLine()!=null) {
            x=x+1;
        }
        int readLine = random.nextInt(x)+1;
        return readPointLine(fileName,readLine);
    }
    public static String readPointLine(String fileName,int readLine){
        Charset charset = Charset.forName("GBK");
        /*
        newBufferReader的默认编码是UTF_8,要改成GBK
         */
        String line;
        try(BufferedReader bfR = newBufferedReader(Paths.get(fileName),charset)){
            int i=0;
            while((line=bfR.readLine())!=null){
                i=i+1;
                if(i==readLine){
                    return line;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
