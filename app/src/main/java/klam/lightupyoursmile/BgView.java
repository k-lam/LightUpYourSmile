package klam.lightupyoursmile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2014/12/8.
 */
public class BgView extends View {

    public BgView(Context context) {
        super(context);
    }

    public BgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    Bitmap mBitmap;
    void init(){

    }

    Paint mPaint;
    int width;
    int height;
    void setBitmap(int r_id){
        mPaint = new Paint();
        width = getResources().getDisplayMetrics().widthPixels;
        BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds = true;
        Bitmap bitmapTmp = BitmapFactory.decodeResource(getResources(),r_id);
        height = (int)(width * (bitmapTmp.getHeight() / (float)bitmapTmp.getWidth()));
        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
//        canvas.scale((float)bitmapTmp.getWidth() / bitmap.getWidth(),(float)bitmapTmp.getHeight() / bitmap.getHeight());
        canvas.scale((float)bitmap.getWidth() / bitmapTmp.getWidth(),(float)bitmap.getHeight() / bitmapTmp.getHeight());
        canvas.drawBitmap(bitmapTmp,0,0,null);
        //float ratio = width / (float)options.outWidth;
//      Bitmap bitmap = Bitmap.createScaledBitmap(bitmapTmp,width,(int)(width * (bitmapTmp.getHeight() / (float)bitmapTmp.getWidth())),true);
//        bitmapTmp.recycle();
        bitmapTmp.recycle();
        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP));
        ViewGroup.LayoutParams params = getLayoutParams();
        if(null == params){
            params = new ViewGroup.LayoutParams(width,height);
        }else {
            params.height = height; params.width = width;
        }
        setLayoutParams(params);
    }
    Rect rect;
    @Override
    protected void onDraw(Canvas canvas) {
        if(null == rect){
            rect = new Rect(0,0,width,height);
        }
        canvas.drawColor(Color.BLACK);
        canvas.drawRect(rect,mPaint);
    }
}
