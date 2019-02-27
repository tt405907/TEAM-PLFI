package plfi.plfi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import java.util.ArrayList;
public class gamePRINT extends View {

    public class Position {
        float x;
        float y;

        public float getX() {
            return x;
        }
        public float getY() {
            return y;
        }

        public Position(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
    public ArrayList<Position> tableauPositions;

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
        tableauPositions = new ArrayList<>();

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
        tableauPositions.add(new Position(xPos,yPos));
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

    public void reset(){
        tableauPositions.clear();
        path.reset();
        invalidate();
    }
    public ArrayList<Position> getTableau() {
        return tableauPositions;
    }
}
