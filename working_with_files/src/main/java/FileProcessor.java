import java.io.*;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileInterfeces.ConvertorToJson;
import fileInterfeces.FileAnalyzer;
import fileInterfeces.FileReader;
import fileInterfeces.FileWriter;
import fileInterfeces.FileSearcher;
import fileInterfeces.FileSerializeDeserialize;

public class FileProcessor implements FileReader, FileWriter, FileSearcher, FileAnalyzer, FileSerializeDeserialize, ConvertorToJson {

    @Override
    public String readFromFile(String linkToFile) {
        File file = new File(linkToFile);
        return readFromFile(file);
    }

    public String readFromFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeToFile(String linkToFile, String... text) {
        writeToFile(linkToFile, Arrays.asList(text));
    }

    public void writeToFile(String linkToFile, List<String> textLines) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new java.io.FileWriter(linkToFile))) {
            for (String line : textLines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean searchInFile(String linkToFile, String text) {
        return readFromFile(linkToFile).contains(text);
    }

    @Override
    public void analyzeFile(String linkToFile, boolean writeToFile) {
        String line = readFromFile(linkToFile);
        int numberOfWords = line.split("\\s+").length;
        int numberOfSymbols = analyzeFile("\\p{Punct}", line);
        int numberOfNumbers = line.split("[0-9]").length;
        if (writeToFile) {
            String outputFile = "text.txt";
            writeToFile(outputFile, String.valueOf(numberOfWords), String.valueOf(numberOfSymbols),
                    String.valueOf(numberOfNumbers));
            System.out.println("Results saved to " + outputFile);
        } else {
            System.out.println("numberOfWords: " + numberOfWords
                    + "\nnumberOfSymbols: " + numberOfSymbols
                    + "\nnumberOfNumbers: " + numberOfNumbers);
        }
    }

    private int analyzeFile(String typeToChecking, String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (String.valueOf(text.charAt(i)).matches(typeToChecking)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void serializeObjectToJson(String linkToFile, Object obj) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(linkToFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    @Override
    public Object deserializeJsonToObject(String linkToFile) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(linkToFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        return objectInputStream.readObject();
    }

    @Override
    public void convertToJson(String... text) throws IOException {
        convertToJson(Arrays.asList(text));
    }

    public void convertToJson(List<String> textLines) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("textOut.txt"), textLines);
    }

 }