package egovframework.itman.member.web;

import javax.servlet.http.HttpServletRequest;

public class EgovItmanIpUtil {
	  public static String getClientIP(HttpServletRequest request) {
	        String ip = null;

	        // 프록시 헤더에서 추출 (우선순위 순)
	        String[] headerNames = {
	            "X-Forwarded-For",
	            "Proxy-Client-IP",
	            "WL-Proxy-Client-IP",
	            "HTTP_CLIENT_IP",
	            "HTTP_X_FORWARDED_FOR"
	        };

	        for (String header : headerNames) {
	            ip = request.getHeader(header);
	            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
	                // 여러 프록시를 거쳤다면 가장 앞의 IP가 클라이언트 IP
	                if (ip.contains(",")) {
	                    ip = ip.split(",")[0].trim();
	                }
	                break;
	            }
	        }

	        // 프록시 헤더에서 못 찾았을 경우 기본 IP
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getRemoteAddr();
	        }

	        // IPv6 loopback → IPv4로 변환
	        if ("0:0:0:0:0:0:0:1".equals(ip) || "::1".equals(ip)) {
	            ip = "127.0.0.1";
	        }

	        return ip;
	    }
}
