package klam.lightupyoursmile;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

import util.Point;

/**
 * Created by Administrator on 2014/12/8.
 */
public class BeatsView extends View {

    public BeatsView(Context context) {
        super(context);
        init();
    }

    public BeatsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BeatsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    Point point;
    void init(){
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction() & MotionEvent.ACTION_MASK;
                if(action == MotionEvent.ACTION_DOWN){
                    point = new Point((int)event.getX(),(int )event.getY());
                    Log.i("smile","touch:(" + point.x + "," + point.y+")");
                    invalidate();
                }
                return true;
            }
        });
    }

    Paint mPaint = new Paint();
    int alpha = 255;
    ValueAnimator valueAnimator = ValueAnimator.ofInt(255,0);
    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.CYAN);
        mPaint.setAlpha(alpha);
        if(point != null) {
            canvas.drawCircle(point.x, point.y, 200f, mPaint);
            Log.i("smile","onDraw:(" + alpha +")");
        }
        if(!valueAnimator.isRunning()) {
            valueAnimator.setDuration(2000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    alpha = (Integer) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.start();
        }
    }

    public class Circle {

        public int alpha;
        public int rgb;

        public Circle(float radius, int alpha) {
            this.alpha = alpha;
            // rgb = randInt(0, 0xffdddddd);
            rgb = Color.rgb(randInt(0, 200), randInt(0, 200), randInt(0, 200));
        }

        public Circle(float cx, float cy, float radius, int alpha) {
            this( radius, alpha);
        }

        public int attenuate() {
            alpha = decrease();
//			if (alpha < 0) {
//				alpha = 0;
//				drawList.remove(this);
//			}
            return alpha;
        }

        private int decrease() {
            return alpha - 50;
        }

//        public Circle pass() {
//            int tmp = decrease();
//            if (tmp <= 0) {
//                return null;
//            }
//            return new Circle(radius + InitRadius, tmp);
//        }
    }

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
