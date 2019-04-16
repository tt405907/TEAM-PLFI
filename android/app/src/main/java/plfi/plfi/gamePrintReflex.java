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

public class gamePrintReflex extends View {
    ArrayList<Point> points;
    Paint paint;
    Path path;
    Connexion connexion;
    Boolean isStarted;
    public gamePrintReflex(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        points = new ArrayList<>();
        isStarted=false;

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawPath(path,paint);

    }
    public void setIsStarted(Boolean b){
        isStarted = b;
    }

    public boolean getIsstarted(){
        return isStarted;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
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
                if(isStarted ==  true ){
                   reflexActionUp(connexion);
                }
                break;
            default:
                return false;

        }
        invalidate();
        return true;
    }

    public void reflexActionUp(Connexion c){
        try {
            c.sendForme(points,"formereflex");
        }catch (Exception e){

            e.getStackTrace();
        }
        this.reset();
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