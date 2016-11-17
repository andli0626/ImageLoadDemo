package andli.com.imageloaddemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

public class MainActivity extends AppCompatActivity {

    ImageLoader mImageLoader;

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.imageView);

        mImageLoader = ImageLoader.getInstance();

//        mImageLoader.displayImage("http://od7nv5gvk.bkt.clouddn.com/2016-11-17-0eb30f2442a7d9334f268ca9a84bd11372f00159.jpg", mImageView);

        DisplayImageOptions mOptions = new DisplayImageOptions.Builder()
                // 设置图片在下载期间显示的图片
                .showImageOnLoading(R.mipmap.ic_launcher)
                // 设置图片Uri为空或是错误的时候显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                // 设置图片加载/解码过程中错误时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)
                // 设置下载的图片是否缓存在内存中
                .cacheInMemory(true)
                // 设置是否缓存在SD卡中
                .cacheOnDisk(true)
                // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .considerExifParams(true)
                // 设置图片的缩放类型
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                // 设置图片的解码类型
                .bitmapConfig(Bitmap.Config.ARGB_4444)
                // 设置Bitmap的配置选项
                // .decodingOptions(null)
                // 设置图片在下载前是否重置，复位
                .resetViewBeforeLoading(true)
                // 是否设置为圆角,弧度为多少
                .displayer(new RoundedBitmapDisplayer(50))
                // 是否图片加载好后渐入的动画时间
                .displayer(new FadeInBitmapDisplayer(100))
                .build();
        mImageLoader.displayImage("http://od7nv5gvk.bkt.clouddn.com/2016-11-17-0eb30f2442a7d9334f268ca9a84bd11372f00159.jpg", mImageView,mOptions);
    }
}
