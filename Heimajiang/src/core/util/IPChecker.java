package core.util;

public class IPChecker
{
  public static boolean ipRangCheck(String ipCheck, String ipStart, String ipEnd)
  {
    boolean result = false;
    long ipStartL = getIpNum(ipStart);
    long ipEndL = getIpNum(ipEnd);
    long ipCheckL = getIpNum(ipCheck);
    if (isInner(Long.valueOf(ipCheckL).longValue(), Long.valueOf(ipStartL).longValue(), Long.valueOf(ipEndL).longValue())) {
      result = true;
    } else {
      result = false;
    }
    return result;
  }
  
  private static long getIpNum(String ipAddress)
  {
    String[] ip = ipAddress.split("\\.");
    long a = Integer.parseInt(ip[0]);
    long b = Integer.parseInt(ip[1]);
    long c = Integer.parseInt(ip[2]);
    long d = Integer.parseInt(ip[3]);
    long ipNum = a * 256L * 256L * 256L + b * 256L * 256L + c * 256L + d;
    return ipNum;
  }
  
  private static boolean isInner(long userIp, long begin, long end)
  {
    return (userIp >= begin) && (userIp <= end);
  }
}
