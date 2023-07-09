package com.example.Platforme_Simulation.service.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.io.IOException;

public class FileUtil {

    public static void createDirectoryIfNotExists(String directoryPath) {

        File directory = new File(directoryPath);

        if (!directory.exists()) {

            directory.mkdirs();

        }

    }

    public static void saveFile(MultipartFile file, String directoryPath, String fileName) throws IOException {

        String filePath = directoryPath + fileName;

        File dest = new File(filePath);

        file.transferTo(dest);

    }

}
