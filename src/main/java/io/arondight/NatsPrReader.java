/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.arondight;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("rawtypes")
public class NatsPrReader {

    public static final String GITHUB_WORKFLOW = ".github/workflow/";
    public static final String EXAMPLES = "src/examples/java/io/nats/examples/";
    public static final String TEST_JAVA = "src/test/java/io/nats/";
    public static final String TEST_RES = "src/test/resources/";
    public static final String MAIN = "src/main/java/io/nats/";

    public static void main(String[] args) throws IOException {
        List<String> list = PullRequest.getFileList("nats-io", "nats.java", 501);

        String lastGroup = "no-match";

        for (String f : list) {
            String display = f;
            String curGroup = "";
            int at = f.indexOf("/");
            if (at == -1) {
                curGroup = "root";
            }
            else if (f.startsWith(GITHUB_WORKFLOW)){
                curGroup = "actions";
                display = f.substring(GITHUB_WORKFLOW.length());
            }
            else if (f.startsWith(EXAMPLES)){
                curGroup = "examples";
                display = f.substring(EXAMPLES.length());
            }
            else if (f.startsWith(TEST_JAVA)){
                curGroup = "test java";
                display = f.substring(TEST_JAVA.length());
            }
            else if (f.startsWith(TEST_RES)){
                curGroup = "test resources";
                display = f.substring(TEST_RES.length());
            }
            else { // MAIN
                display = f.substring(MAIN.length());
                at = display.lastIndexOf("/");
                curGroup = display.substring(0, at);
                display = display.substring(at + 1);
            }
            if (!curGroup.equals(lastGroup)) {
                System.out.println(curGroup);
                lastGroup = curGroup;
            }
            System.out.println("    " + display);
        }
    }
}