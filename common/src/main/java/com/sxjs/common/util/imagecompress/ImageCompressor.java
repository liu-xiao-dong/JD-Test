package com.sxjs.common.util.imagecompress;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;


/**
 * Created on : June 18, 2016
 */
public class ImageCompressor {
    private static volatile ImageCompressor INSTANCE;
    private Context context;
    //max width and height values of the compressed image is taken as 612x816
    private float maxWidth = 1280.0f;
    private float maxHeight = 1280.0f;
    private Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
    private Bitmap.Config bitmapConfig = Bitmap.Config.ARGB_8888;
    private int quality = 70;
    private String destinationDirectoryPath;
    private String fileNamePrefix;
    private String fileName;


    private ImageCompressor(Context context) {
        this.context = context.getApplicationContext();
        destinationDirectoryPath = context.getExternalCacheDir().getAbsolutePath() + File.pathSeparator + FileUtil.FILES_PATH;
    }

    public static ImageCompressor getDefault(Context context) {
        if (INSTANCE == null) {
            synchronized (ImageCompressor.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ImageCompressor(context);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 默认在UI线程执行 请在子线程执行此方法
     * @param file
     * @return
     */
    public File compressToFile(File file) {
        return ImageUtil.compressImage(context, Uri.fromFile(file), maxWidth, maxHeight,
            compressFormat, bitmapConfig, quality, destinationDirectoryPath,
            fileNamePrefix, fileName);
    }

    /**
     * 默认在UI线程执行 请在子线程执行此方法
     * @param file
     * @return
     */
    public Bitmap compressToBitmap(File file) {
        return ImageUtil.getScaledBitmap(context, Uri.fromFile(file), maxWidth, maxHeight, bitmapConfig);
    }

    /**
     * 使用defer操作符 待订阅后再开始压缩任务
     * @param file
     * @return
     */
    public Observable<File> compressToFileAsObservable(final File file) {
        return Observable.defer(new Callable<ObservableSource<? extends File>>() {
            @Override
            public Observable<File> call() {
                return Observable.just(compressToFile(file));
            }
        });
    }

    public Observable<Bitmap> compressToBitmapAsObservable(final File file) {
        return Observable.defer(new Callable<ObservableSource<? extends Bitmap>>() {
            @Override
            public Observable<Bitmap> call() {
                return Observable.just(compressToBitmap(file));
            }
        });
    }

    public static class Builder {
        private ImageCompressor imageCompressor;

        public Builder(Context context) {
            imageCompressor = new ImageCompressor(context);
        }

        public Builder setMaxWidth(float maxWidth) {
            imageCompressor.maxWidth = maxWidth;
            return this;
        }

        public Builder setMaxHeight(float maxHeight) {
            imageCompressor.maxHeight = maxHeight;
            return this;
        }

        public Builder setCompressFormat(Bitmap.CompressFormat compressFormat) {
            imageCompressor.compressFormat = compressFormat;
            return this;
        }

        public Builder setBitmapConfig(Bitmap.Config bitmapConfig) {
            imageCompressor.bitmapConfig = bitmapConfig;
            return this;
        }

        public Builder setQuality(int quality) {
            imageCompressor.quality = quality;
            return this;
        }

        public Builder setDestinationDirectoryPath(String destinationDirectoryPath) {
            imageCompressor.destinationDirectoryPath = destinationDirectoryPath;
            return this;
        }

        public Builder setFileNamePrefix(String prefix) {
            imageCompressor.fileNamePrefix = prefix;
            return this;
        }

        public Builder setFileName(String fileName) {
            imageCompressor.fileName = fileName;
            return this;
        }

        public ImageCompressor build() {
            return imageCompressor;
        }
    }
}
