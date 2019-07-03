package com.whc.nio2;

import java.nio.file.*;

/**
 * 监控文件系统的变化
 *
 * @Author Administrator
 * @Date 2019/7/3 21:10
 */
public class WatchServiceTest {
    public static void main(String[] args) throws Exception {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Paths.get("C:/").register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);

        while (true){
            // 获取一个文件变化事件
            WatchKey watchKey = watchService.take();
            for (WatchEvent<?> event : watchKey.pollEvents()){
                System.out.println(event.context() + "文件发生了" + event.kind() + "事件!");
            }
            // 重设
            boolean valid = watchKey.reset();
            if (!valid){
                break;
            }
        }
    }
}
