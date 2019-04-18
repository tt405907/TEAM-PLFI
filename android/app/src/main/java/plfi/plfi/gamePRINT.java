package plfi.plfi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import commun.Point;


import java.util.ArrayList;
import java.util.jar.Attributes;

public class gamePRINT extends View {
    ArrayList<Point> points;
    Paint paint;
    Path path;
    public gamePRINT(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        points = new ArrayList<>();

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawPath(path,paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float xPos = event.getX();
        float yPos = event.getY();

        points.add(new Point(xPos,yPos));
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xPos,yPos);
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos,yPos);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;

        }
        invalidate();
        return true;
    }



    public ArrayList<Point> getPoints() {
        return points;
    }

    public void reset(){
        this.points.clear();
        path.reset();
        invalidate();
    }
}
