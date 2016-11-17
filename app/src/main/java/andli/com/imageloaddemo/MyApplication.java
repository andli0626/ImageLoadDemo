package andli.com.imageloaddemo;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * author lilin
 * create at 16/11/17 下午6:08
 **/
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

//        1、ImageLoaderConfiguration必须配置并且全局化的初始化这个配置ImageLoader.getInstance().init(config); 否则会出现错误提示
//        2、ImageLoader是根据ImageView的height，width确定图片的宽高。
//        3、如果经常出现OOM（官方的建议）
//        ①减少配置之中线程池的大小，(.threadPoolSize).推荐1-5；
//        ②使用.bitmapConfig(Bitmap.config.RGB_565)代替ARGB_8888;
//        ③使用.imageScaleType(ImageScaleType.IN_SAMPLE_INT)或者 try.imageScaleType(ImageScaleType.EXACTLY)；
//        ④避免使用RoundedBitmapDisplayer.他会创建新的ARGB_8888格式的Bitmap对象；
//        ⑤使用.memoryCache(new WeakMemoryCache())，不要使用.cacheInMemory();


        /** 全局配置ImageLoaderConfiguration **/

        // 获取缓存文件
        File cacheDir = StorageUtils.getCacheDirectory(this);

        // 设置自定义缓存的目录
        cacheDir = StorageUtils.getOwnCacheDirectory(this, "imageloader/Cache");

        // 初始化ImageLoaderConfiguration
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480, 800)                  //设置缓存图片的默认尺寸,一般取设备的屏幕尺寸
                .diskCacheExtraOptions(480, 800, null)
                .threadPoolSize(3)                                  // 线程池内加载的数量,default = 3
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))   //自定义内存的缓存策略
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13)                      // default
                .diskCache(new UnlimitedDiskCache(cacheDir))        // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)                            //缓存的文件数量
                .diskCache(new UnlimitedDiskCache(cacheDir))        //自定义缓存路径
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())// default
                .imageDownloader(new BaseImageDownloader(this))// default
                .imageDecoder(new BaseImageDecoder(true))// default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())// default
                .writeDebugLogs()
                .build();
        // 初始化配置
        ImageLoader.getInstance().init(config);
    }
}
