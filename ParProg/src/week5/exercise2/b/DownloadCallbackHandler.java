package week5.exercise2.b;

@FunctionalInterface
public interface DownloadCallbackHandler<T> {
    void handleResult(T result);
}
