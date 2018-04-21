package processor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LembreteTextProcessor {

    private String text;

    public LembreteTextProcessor(String text) {
        this.text = text;
    }

    public static LembreteTextProcessor fromTextCopied(String text) {
        return new LembreteTextProcessor(text);
    }

    public List<String> processToList() {
        List<String> lembreteList = new ArrayList<>();

        Pattern pattern = Pattern.compile("(?<=Lembrete\\n).*?\\n", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            lembreteList.add(matcher.group());
        }

        return lembreteList;
    }

}
