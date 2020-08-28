package com.lmaye.ms.core.utils;

import com.lmaye.ms.core.context.ResultCode;
import com.lmaye.ms.core.exception.CoreException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * -- 线程安全的Ip工具类
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/7/2 23:19 星期四
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IpUtils {
    private static final String DEFAULT_FILL = "0";

    /**
     * Ip地址数据索搜，基于内存的数据源线程安全，否则线程不安全
     */
    private static final DbSearcher SEARCHER;

    static {
        Resource resource = new ClassPathResource("ip2region.db");
        try (InputStream is = resource.getInputStream()) {
            byte[] bytes = IOUtils.toByteArray(is);
            DbConfig config = new DbConfig();
            SEARCHER = new DbSearcher(config, bytes);
        } catch (Exception e) {
            throw new CoreException(ResultCode.IP_DATA_INIT_FAILED, e);
        }
    }

    /**
     * 获取ip地址
     *
     * @param ip ip
     * @return Ip地址
     */
    public static String getRegion(String ip) {
        try {
            DataBlock block = SEARCHER.memorySearch(ip);
            return block.getRegion();
        } catch (IOException e) {
            throw new CoreException(ResultCode.GET_IP_ADDRESS_FAILED, e);
        }
    }

    /**
     * 获取ip地址
     *
     * @param ip ip
     * @return Ip地址
     */
    public static String[] getRegions(String ip) {
        String region = getRegion(ip);
        if (!StringUtils.isEmpty(region)) {
            String[] regions = region.split("\\|");
            int length = regions.length;
            // 替换所有的0为上一级
            for (int i = 0; i < length; i++) {
                if (i >= 1) {
                    if (StringUtils.isEmpty(regions[i]) || Objects.equals(DEFAULT_FILL, regions[i])) {
                        regions[i] = regions[i - 1];
                    }
                }
            }
            // 替换所有的0为下一级
            for (int i = length - 1; i >= 0; i--) {
                if (i < length - 1) {
                    if (StringUtils.isEmpty(regions[i]) || Objects.equals(DEFAULT_FILL, regions[i])) {
                        regions[i] = regions[i + 1];
                    }
                }
            }
            return regions;
        } else {
            throw new CoreException(ResultCode.GET_IP_ADDRESS_FAILED);
        }
    }
}
