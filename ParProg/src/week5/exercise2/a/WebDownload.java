package week5.exercise2.a;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebDownload {
    private ExecutorService threadPool = Executors.newCachedThreadPool();

    public void finished() {
        threadPool.shutdown();
    }

    public String downloadUrl(final String link) throws IOException {
        URL url = new URL(link);
        StringBuffer stringBuffer = new StringBuffer();
        try (Reader reader = new InputStreamReader(url.openStream())) {
            int i;
            while ((i = reader.read()) >= 0) {
                stringBuffer.append((char) i);
            }
        }
        return stringBuffer.toString();
    }

    public Future<String> asyncDownloadUrl(final String aLink) {
        Future<String> asynchDownload = threadPool
                .submit(() -> downloadUrl(aLink));
        return asynchDownload;
    }
}
