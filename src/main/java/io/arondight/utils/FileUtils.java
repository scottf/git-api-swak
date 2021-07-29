package io.arondight.utils;

import java.io.*;
import java.net.URL;

public class FileUtils {

    public static String readUrl(String urlString) throws IOException {
        return readInputStream(new URL(urlString).openStream());
    }

    public static String readFile(String fileSpec) throws IOException {
        return readInputStream(new FileInputStream(fileSpec));
    }

    public static String readInputStream(InputStream is) throws IOException {
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
