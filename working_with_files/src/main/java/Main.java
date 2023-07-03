import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        FileProcessor fileProcessor = new FileProcessor();

        fileProcessor.writeToFile("text.txt", "piogjrpogjweogkew[fpkawf[p" +
                ",g[" +
                "eormwoejgjpsoegpowewjg[" +
                "owrjg[wejwg" +
                "[wewg" +
                "wewng" +
                "piwnewgwpegnwewegnwewg" +
                "weg" +
                "weng" +
                "ms" +
                "om" +
                "egnweg" +
                "orgwoejgq3jt" +
                "fjqwe" +
                "g0weg" +
                "0j4hh" +
                "[kaehs,[dvsdmbd[o" +
                "nmsb" +
                "ew" +
                "[rohm" +
                "e[ohm" +
                "[onmdn" +
                "[etkj");

        System.out.println(fileProcessor.readFromFile("text.txt"));

        fileProcessor.analyzeFile("text.txt", false);
        System.out.println(fileProcessor.searchInFile("text.txt", "ewojmgnrkmv"));
        fileProcessor.convertToJson("wpifmdspomf[" +
                "mWEeg[omewp'ewompenepgmespo", "ekifnwgnewgpgpwejgwejwefm", "epfiewpgewgpojwefpoewg");
        fileProcessor.serializeObjectToJson("textout.json", "spogm[g");
    }
}