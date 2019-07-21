package com.whc.file;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Administrator
 * @date 2019/7/15 21:41
 */
public class MemoryAnalyze {
    public static void main(String[] arqUEgs) {
        List<String> list = new ArrayList<>();
        while(true){
            list.add(UUID.randomUUID().toString());
        }
    }
}
