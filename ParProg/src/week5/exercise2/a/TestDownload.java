package week5.exercise2.a;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import week5.exercise2.a.WebDownload;

public class TestDownload {
    private static final String[] links = new String[] {
            "http://www.google.com", "http://www.bing.com",
            "http://www.yahoo.com", "http://www.microsoft.com",
            "http://www.oracle.com"    };

    public static void main(String[] args) throws IOException,
            InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        WebDownload downloader = new WebDownload();
        Map<String, Future<String>> downloadResults = new HashMap<String, Future<String>>();
        for (int i = 0; i < links.length; i++) {
            String link = links[i];
            downloadResults.put(link, downloader.asyncDownloadUrl(link));
        }
        for (Map.Entry<String, Future<String>> result : downloadResults
                .entrySet()) {
            System.out.println(String.format("%s downloaded (%d characters)",
                    result.getKey(), result.getValue().get().length()));
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("total time: %d ms", endTime
                - startTime));
        downloader.finished();
    }
}
