/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.arondight;

import com.google.gson.Gson;
import io.arondight.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class PullRequest {
    static final String URL_TEMPLATE = "https://api.github.com/repos/:org/:repo/pulls/:pr/files?per_page=100";

    public static void main(String[] args) throws IOException {
        List<String> list = getFileList("nats-io", "nats.java", 501);
    }

    public static List<String> getFileList(String org, String repo, int pr) throws IOException {
        String url = URL_TEMPLATE.replace(":org", org).replace(":repo", repo).replace(":pr", "" + pr);
        String json = FileUtils.readUrl(url);
        Gson gson = new Gson();
        ArrayList list = gson.fromJson(json, ArrayList.class);

        List<String> results = new ArrayList<>();
        for (Object file : list) {
            results.add((String)((Map)file).get("filename"));
        }

        return results;
    }
}
