package com.example.service_best_practice;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/11/2.
 */

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    private static final int TYPE_SUCCESS = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSED = 2;
    private static final int TYPE_CANCELED = 3;

    private DownloadListener listener;

    private boolean isCanceled = false;
    private boolean isPause = false;
    private int lastProgress;

    public DownloadTask(DownloadListener downloadListener) {
        listener = downloadListener;
    }

    //处理后台具体下载逻辑
    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;

        try {
            long downloadLength = 0;//记录已经下载过文件的长度
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));//解析 url 文件名
            String directory = Environment.getExternalStoragePublicDirectory(Environment.
                    DIRECTORY_DOWNLOADS).getPath();//sd卡 DownLoad 目录
            file = new File(directory + fileName);
            if (file.exists()) {
                //如果已经存在读取已下载的字节数
                downloadLength = file.length();
            }
            long contentLength = getContentLength(downloadUrl);//获取文件总长度
            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if (contentLength == downloadLength) {
                //已下载字节和文件总字节相等，说明已经下载完成了
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    //断点下载，指定从哪个字节开始下载
                    //告诉服务器 我想要从哪个字节开始下载
                    .addHeader("RANGE", "bytes=" + downloadLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();

            if (response != null) {
                is = response.body().byteStream();//使用文件流方式读取
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadLength);//跳过已经下载的字节
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPause) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);

                        int progress = (int) ((total + downloadLength) * 100 / contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return TYPE_FAILED;
    }

    //在界面上更新当前下载进度
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        //和上次下载进度 比 有变化调用接口
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    //用于通知最终的下载结果
    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer) {
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
        }
    }

    void pauseDownload() {
        isPause = true;
    }

    void cancelDownload() {
        isCanceled = true;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(downloadUrl).build();
        Response response = client.newCall(request).execute();

        if (response != null && response.isSuccessful()) {
            long conentLength = response.body().contentLength();
            response.body().close();
            return conentLength;
        }
        return 0;
    }


}