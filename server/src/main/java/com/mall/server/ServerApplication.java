package com.mall.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 商城系统-注册中心
 *
 * @author Daoyuan
 * @version 1.0.0
 */
@EnableEurekaServer
@SpringBootApplication
public class ServerApplication {

    private static final Map<String, String> ENV_MAP = new TreeMap<>();

    static {
        ENV_MAP.put("s1", "slave1");
        ENV_MAP.put("s2", "slave2");
        ENV_MAP.put("s3", "slave3");
    }

    /**
     * 最大错误次数
     */
    private static final int ERROR_COUNT = 5;

    public static void main(String[] args) {
        int errorCount = 0;
        while (errorCount < ERROR_COUNT) {
            System.out.print("请选择运行环境" + ENV_MAP.keySet() + ": ");
            String profiles = new Scanner(System.in).nextLine();
            if (ENV_MAP.containsKey(profiles)) {
                profiles = ENV_MAP.get(profiles);
            } else {
                System.out.println("请输入正确的运行环境呦~~~");
                errorCount++;
                continue;
            }
            new SpringApplicationBuilder(ServerApplication.class).profiles(profiles).run(args);
        }
        System.exit(0);
    }
}
