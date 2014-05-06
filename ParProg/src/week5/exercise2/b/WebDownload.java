package week5.exercise2.b;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class WebDownload {
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

    public void asyncDownloadUrl(final String aLink,
            DownloadCallbackHandler<String> aCallbackHandler)
            throws IOException {
        String result = downloadUrl(aLink);
        aCallbackHandler.handleResult(result);
    }
}
