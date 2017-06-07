package zhuwentao.com.zwtwitmass.network.common;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import zhuwentao.com.zwtwitmass.network.callback.ProgressListener;

/**
 * 扩展ResponseBody，支持回传下载进度
 */
public class ProgressResponseBody extends ResponseBody {

    private final ResponseBody responseBody;
    private final ProgressListener listener;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody,
                                ProgressListener listener) {
        super();
        this.responseBody = responseBody;
        this.listener = listener;
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public BufferedSource source() {
        if (null == bufferedSource) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);

                // 增加当前读取的字节数，如果读取完成了bytesRead会返回-1
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;

                // 回调，如果contentLength()不知道长度，会返回-1
                listener.onProgress(totalBytesRead,
                        responseBody.contentLength(), bytesRead == -1);
                return bytesRead;
            }
        };
    }

}
