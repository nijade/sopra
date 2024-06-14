package com.example.sopra.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service-Klasse zur Verwaltung von Bilddateien.
 */
@Service
public class ImageService {
    private static final String IMAGE_DIR = "src/main/resources/static/images/";

    /**
     * Gibt die Namen aller Bilddateien im Verzeichnis zurück.
     *
     * @return List<String> die Liste der Bilddateinamen
     */
    public List<String> getImageNames() {
        File dir = new File(IMAGE_DIR);
        return Arrays.stream(dir.listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }
}
