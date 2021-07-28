package io.arondight.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class FileUtils {

    public static String readUrl(String urlString) throws Exception {
        return readInputStream(new URL(urlString).openStream());
    }

    public static String readFile(String fileSpec) throws Exception {
        return readInputStream(new FileInputStream(fileSpec));
    }

    public static String readInputStream(InputStream is) throws Exception {
        BufferedReader reader = null;
        try {
            InputStreamReader isr = new InputStreamReader(is);
            reader = new BufferedReader(isr);
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
