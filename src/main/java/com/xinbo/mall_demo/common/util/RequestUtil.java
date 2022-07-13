package com.xinbo.mall_demo.common.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.lionsoul.ip2region.xdb.Searcher;
/**
 * 请求工具类
 * @author Xinbo
 */
public class RequestUtil {

    public static String getRequestIp(HttpServletRequest request) {
        //通过HTTP代理服务器转发时添加
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            // 从本地访问时根据网卡取本机配置的IP
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inetAddress.getHostAddress();
            }
        }
        // 通过多个代理转发的情况，第一个IP为客户端真实IP，多个IP会按照','分割
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }


    public static String getCityInfo(String ip){
        //db
        String dbPath = RequestUtil.class.getResource("/ip2region.xdb").getPath();
        File file = new File(dbPath);
        if ( file.exists() == false ) {
            System.out.println("Error: Invalid ip2region.xdb file");
        }

        // 提前从 xdb 文件中加载出来 VectorIndex 数据，然后全局缓存
//        Searcher searcher = SearchWithVectorIndex(dbPath);
        // 预先加载整个 ip2region.xdb 的数据到内存
        Searcher searcher = SearchWithBuffer(dbPath);

        // 3、查询
        try {
            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
            return region;
        } catch (Exception e) {
            System.out.printf("failed to search(%s): %s\n", ip, e);
        }
        return null;
    }

    public static String getCityInfo(HttpServletRequest request) {
        return getCityInfo(getRequestIp(request));
    }


    private static Searcher SearchWithVectorIndex(String dbPath) {
        // 1、从 dbPath 中预先加载 VectorIndex 缓存，并且把这个得到的数据作为全局变量，后续反复使用。
        byte[] vIndex;
        try {
            vIndex = Searcher.loadVectorIndexFromFile(dbPath);
        } catch (Exception e) {
            System.out.printf("failed to load vector index from `%s`: %s\n", dbPath, e);
            return null;
        }

        // 2、使用全局的 vIndex 创建带 VectorIndex 缓存的查询对象。
        Searcher searcher;
        try {
            searcher = Searcher.newWithVectorIndex(dbPath, vIndex);
            return searcher;
        } catch (Exception e) {
            System.out.printf("failed to create vectorIndex cached searcher with `%s`: %s\n", dbPath, e);
            return null;
        }
        // 备注：每个线程需要单独创建一个独立的 Searcher 对象，但是都共享全局的制度 vIndex 缓存
    }

    private static Searcher SearchWithBuffer(String dbPath) {
        // 1、从 dbPath 加载整个 xdb 到内存。
        byte[] cBuff;
        try {
            cBuff = Searcher.loadContentFromFile(dbPath);
        } catch (Exception e) {
            System.out.printf("failed to load content from `%s`: %s\n", dbPath, e);
            return null;
        }

        // 2、使用上述的 cBuff 创建一个完全基于内存的查询对象。
        Searcher searcher;
        try {
            searcher = Searcher.newWithBuffer(cBuff);
            return searcher;
        } catch (Exception e) {
            System.out.printf("failed to create content cached searcher: %s\n", e);
            return null;
        }
        // 备注：并发使用，用整个 xdb 数据缓存创建的查询对象可以安全的用于并发，也就是你可以把这个 searcher 对象做成全局对象去跨线程访问。
    }
}
