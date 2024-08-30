package com.demo.hazeltest.server1.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileInfo {
    String fileName;
    String filePath;

    @Override
    public String toString() {
        return "FileInfo{" +
                "fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
