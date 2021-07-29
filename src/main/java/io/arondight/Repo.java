/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.arondight;

import com.google.gson.Gson;
import io.arondight.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class Repo {
    static final String URL_TEMPLATE = "https://api.github.com/repos/:org/:repo/git/trees/:branch?recursive=1";
    static final String RAW_TEMPLATE = "https://raw.githubusercontent.com/:org/:repo/:branch/:file";

    public static void main(String[] args) throws IOException {
//        Map<String, String> map = getRawByFilesMap("nats-io", "jsm.go", "main", "schema_source/jetstream/api/v1", false);
//        Map<String, String> map = getRawByFilesMap("nats-io", "jsm.go", "main", "schema_source/jetstream/", true);
    }

    public static Map<String, String> getRawByFilesMap(String org, String repo, String branch, String path, boolean recursive) throws IOException {
        String url = URL_TEMPLATE.replace(":org", org).replace(":repo", repo).replace(":branch", branch);
        String json = FileUtils.readUrl(url);

        String prefix = path.replaceAll("\\Q\\\\E", "/");
        if (!prefix.endsWith("/")) {
            prefix = prefix + "/";
        }
        int preLen = prefix.length();

        Map<String, String> results = new HashMap<>();

        Gson gson = new Gson();
        Map map = gson.fromJson(json, Map.class);
        ArrayList trees = (ArrayList)map.get("tree");
        for (Object tree : trees) {
            String file = (String)((Map)tree).get("path");
            if (file.startsWith(prefix)) {
                String type = (String)((Map)tree).get("type");
                if ("blob".equals(type)) {
                    boolean keep = recursive;
                    if (!recursive) {
                        String fPart = file.substring(preLen);
                        int anotherSlash = fPart.indexOf("/");
                        if (anotherSlash == -1) {
                            keep = true;
                        }
                    }
                    if (keep) {
                        String raw = RAW_TEMPLATE.replace(":org", org).replace(":repo", repo).replace(":branch", branch).replace(":file", file);
                        results.put(file, raw);
                    }
                }
            }
        }

        return results;
    }
}