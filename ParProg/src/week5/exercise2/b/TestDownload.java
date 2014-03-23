package week5.exercise2.b;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class TestDownload {
    private static final String[] links = new String[] {
            "http://www.google.com", "http://www.bing.com",
            "http://www.yahoo.com", "http://www.microsoft.com",
            "http://www.oracle.com"    };

    public static void main(String[] args) throws IOException,
            InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        WebDownload downloader = new WebDownload();
        Map<String, DownloadCallbackHandler<String>> handlerList = new HashMap<String, DownloadCallbackHandler<String>>(
                links.length);
        for (int i = 0; i < links.length; i++) {
            String link = links[i];
            long start = System.currentTimeMillis();
            handlerList.put(link, (result) -> System.out.println(String.format(
                    "%s downloaded (%d characters) in %d ms", link,
                    Integer.valueOf(result.length()),
                    Long.valueOf(System.currentTimeMillis() - start))));
            downloader.asyncDownloadUrl(link, handlerList.get(link));

        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("total time: %d ms", endTime
                - startTime));
    }
}
