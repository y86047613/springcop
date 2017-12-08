package core.util;

import java.io.PrintStream;
import java.util.Random;

public class TestPassword
{
  public static char[] getChar()
  {
    char[] passwordLit = new char[62];
    char fword = 'A';
    char mword = 'a';
    char bword = '0';
    for (int i = 0; i < 62; i++) {
      if (i < 26)
      {
        passwordLit[i] = fword;
        fword = (char)(fword + '\001');
      }
      else if (i < 52)
      {
        passwordLit[i] = mword;
        mword = (char)(mword + '\001');
      }
      else
      {
        passwordLit[i] = bword;
        bword = (char)(bword + '\001');
      }
    }
    return passwordLit;
  }
  
  public static String getPassWord()
  {
    char[] r = getChar();
    Random rr = new Random();
    char[] pw = new char[6];
    String p = "";
    for (int i = 0; i < pw.length; i++)
    {
      int num = rr.nextInt(62);
      pw[i] = r[num];
      p = p + pw[i];
    }
    return p;
  }
  
  public static void main(String[] args)
  {
    System.out.println(getPassWord());
  }
}
