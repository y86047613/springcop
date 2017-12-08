package core.util;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

public class HtmlUtils
{
  public static String omitString(String strText, int KeepLen)
  {
    String strOmit = "... ";
    if (strText == null) {
      return "";
    }
    if ((strText + strOmit).length() <= KeepLen) {
      return strText;
    }
    try
    {
      return strText.substring(0, KeepLen - 2) + strOmit;
    }
    catch (Exception e) {}
    return strText;
  }
  
  public static String removeHTML(String input, int length)
  {
    if ((input == null) || (input.trim().equals(""))) {
      return "";
    }
    String str = input.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?>", "");
    str = str.replaceAll("&nbsp;", "");
    if (length > 0) {
      str = omitString(str, length);
    }
    return str;
  }
  
  public static String replaceHtmlCode(String content)
  {
    if (StringUtils.isEmpty(content)) {
      return "";
    }
    String[] eventKeywords = { "onmouseover", "onmouseout", "onmousedown", "onmouseup", "onmousemove", "onclick", "ondblclick", "onkeypress", "onkeydown", "onkeyup", "ondragstart", "onerrorupdate", "onhelp", 
      "onreadystatechange", "onrowenter", "onrowexit", "onselectstart", "onload", "onunload", "onbeforeunload", "onblur", "onerror", "onfocus", "onresize", "onscroll", "oncontextmenu" };
    content = StringUtils.replaceChars(content, "<script", "&ltscript");
    content = StringUtils.replace(content, "</script", "&lt/script");
    content = StringUtils.replace(content, "<marquee", "&ltmarquee");
    content = StringUtils.replace(content, "</marquee", "&lt/marquee");
    for (int i = 0; i < eventKeywords.length; i++) {
      content = StringUtils.replace(content, eventKeywords[i], "_" + eventKeywords[i]);
    }
    return content;
  }
  
  public static String htmltoText(String inputString)
  {
    if (StringUtils.isBlank(inputString)) {
      return null;
    }
    String htmlStr = inputString;
    String textStr = "";
    try
    {
      String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
      String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
      String regEx_html = "<[^>]+>";
      String patternStr = "\\s+";
      
      Pattern p_script = Pattern.compile(regEx_script, 2);
      Matcher m_script = p_script.matcher(htmlStr);
      htmlStr = m_script.replaceAll("");
      
      Pattern p_style = Pattern.compile(regEx_style, 2);
      Matcher m_style = p_style.matcher(htmlStr);
      htmlStr = m_style.replaceAll("");
      
      Pattern p_html = Pattern.compile(regEx_html, 2);
      Matcher m_html = p_html.matcher(htmlStr);
      htmlStr = m_html.replaceAll("");
      
      textStr = htmlStr;
    }
    catch (Exception e)
    {
      System.err.println("Html2Text: " + e.getMessage());
    }
    return textStr;
  }
}
