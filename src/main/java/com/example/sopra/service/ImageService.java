package com.example.sopra.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {
    private static final String IMAGE_DIR = "src/main/resources/static/images/";

    public List<String> getImageNames() {
        File dir = new File(IMAGE_DIR);
        return Arrays.stream(dir.listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }
}
