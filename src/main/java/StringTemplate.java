import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串模版替换
 */
public class StringTemplate {

    private static final String PATTERN = "\\{(.+?)}";

    private static final String FORMAT = "{%s}";

    public static void main(String[] args) {
        Map<String, String> map = Map.of("time", "2021-08-25", "nick", "nick-nick", "uid", "gfd7893r2");
        String template = "经过系统检测判定，您在 {time} 的对局中举报的用户 {nick}（ID：{uid}）无违规行为";
        final String s = fillTemplate(template, map);
        System.out.println(s);
    }

    private static String fillTemplate(String template, Map<String, String> map) {
        final Pattern pattern = Pattern.compile(PATTERN);
        final Matcher matcher = pattern.matcher(template);
        final StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = map.get(key);
            matcher.appendReplacement(sb, value == null ? String.format(FORMAT, key) : value);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
