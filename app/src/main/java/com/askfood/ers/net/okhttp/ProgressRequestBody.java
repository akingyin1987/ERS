/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.askfood.ers.net.okhttp;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 *  带进度条上传数据
 * @ Description:
 * @ Author king
 * @ Date 2017/9/13 11:19
 * @ Version V1.0
 */

public class ProgressRequestBody    extends RequestBody {
  /**
   * ProgressListener interface
   */
  public interface ProgressListener {
    void onProgress(long total, long progress);
  }
  /**
   * Wrapper RequestBody
   */
  private RequestBody mRequestBody;

  private ProgressListener mProgressListener;

  private BufferedSink mBufferedSink;

  public ProgressRequestBody(RequestBody requestBody) {
    this(requestBody, null);
  }

  public ProgressRequestBody(RequestBody requestBody, ProgressListener progressListener) {
    mRequestBody = requestBody;
    mProgressListener = progressListener;
  }

  @Override
  public long contentLength() throws IOException {
    return mRequestBody.contentLength();
  }

  @Override
  public MediaType contentType() {
    return mRequestBody.contentType();
  }

  @Override
  public void writeTo(BufferedSink sink) throws IOException {

    if (mBufferedSink == null) {
      mBufferedSink = Okio.buffer(wrapperSink(sink));
    }

    mRequestBody.writeTo(mBufferedSink);
    mBufferedSink.flush();
  }


  private Sink wrapperSink(Sink sink) {
    return new ForwardingSink(sink) {

      long mWrited = 0;
      long mTotal = 0;

      @Override
      public void write(Buffer source, long byteCount) throws IOException {
        super.write(source, byteCount);
        if (mTotal == 0) {
           mTotal = contentLength();
        }

        mWrited += byteCount;
        if (mProgressListener != null) {
          mProgressListener.onProgress(mTotal, mWrited);
        }
      }
    };
  }
}
