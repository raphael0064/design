package util;

/**
 * 工具类
 */
public class Funtions {
	public static long ipv4ToUInt(String ipstr) {
		String[] ipstra = ipstr.split("[.]");
		long ip = 0;
		for (int i = 0; i < ipstra.length; ++i) {
			ip |= (Long.parseLong(ipstra[i]) << (8 * (ipstra.length - 1 - i)));
		}
		return ip;
	}
}
