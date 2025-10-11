package com.qk.management.task;

import com.aliyun.oss.model.OSSObjectSummary;
import com.qk.management.service.UserService;
import com.qk.util.AliYunOSSOperators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/11 18:09
 * @Description:
 */
@Slf4j
@Component
public class AvatarClearTask {
    @Autowired
    private AliYunOSSOperators aliYunOSSOperators;
    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 0 2 * * ?")
    public void clearAvatar() throws Exception {
        List<String> allImage = userService.getAllImage();
        List<OSSObjectSummary> select = aliYunOSSOperators.select();
        List<String> invalidFiles = new ArrayList<>();
        Integer failureNum = 0;
        for (OSSObjectSummary summary : select) {
            if (!allImage.contains(summary.getKey())) {
                invalidFiles.add(summary.getKey());
            }
        }
        try {
            Integer count = aliYunOSSOperators.deleteBatch(invalidFiles);
            log.info("清理头像成功，共清理{}个文件", count);
        } catch (Exception e) {
            log.error("清理头像失败: {}", e.getMessage());
        }

        for (String invalidFile : invalidFiles) {
            if (aliYunOSSOperators.compare(invalidFile)){
                failureNum++;
            }
        }
        log.info("清理头像失败{}个文件", failureNum);
    }
}
