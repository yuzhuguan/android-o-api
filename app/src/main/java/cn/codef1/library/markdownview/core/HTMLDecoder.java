package cn.codef1.library.markdownview.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by CoderF1 on 2017/11/9.
 */
public class HTMLDecoder {
    public static String decode(String html) {
        TextEditor ed = new TextEditor(html);
        Pattern p1 = Pattern.compile("&#(\\d+);");
        ed.replaceAll(p1, new Replacement() {
            public String replacement(Matcher m) {
                String charDecimal = m.group(1);
                char ch = (char) Integer.parseInt(charDecimal);
                return Character.toString(ch);
            }
        });

        Pattern p2 = Pattern.compile("&#x([0-9a-fA-F]+);");
        ed.replaceAll(p2, new Replacement() {
            public String replacement(Matcher m) {
                String charHex = m.group(1);
                char ch = (char) Integer.parseInt(charHex, 16);
                return Character.toString(ch);
            }
        });

        return ed.toString();
    }
}