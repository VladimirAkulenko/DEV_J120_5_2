/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev_j120_5_2;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class FileReader {
    public static List<String> fileReader(Path path) throws IOException, NullPointerException, AccessDeniedException {
        path = Objects.requireNonNull(path, "The file can't be null.");
        List<String> list = Files.readAllLines(path);
        list = delete(list);
        list.replaceAll(String::trim);
        return list;
    }

    private static List<String> delete(List<String> list){
        StringBuilder sb = new StringBuilder();
        for (char character : list.get(0).toCharArray()) {
            if (character != '\uFEFF')
                sb.append(character);
        }
        list.remove(0);
        list.add(0, sb.toString());
        return list;
    }
}
