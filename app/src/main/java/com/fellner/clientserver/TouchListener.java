package com.fellner.clientserver;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Thomas Fellner on 28.02.2015.
 */

public class TouchListener implements View.OnTouchListener {
    private MainActivity ma;

    public TouchListener(MainActivity ma){
        this.ma = ma;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        RelativeLayout drag = (RelativeLayout) ma.findViewById(R.id.drag);
        RelativeLayout circle = (RelativeLayout) ma.findViewById(R.id.circle);

        final int x = (int) event.getRawX() - (int)circle.getX();
        final int y = (int) event.getRawY() - (int)circle.getY();

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:

                ServerThread.write[0] = 0;
                ServerThread.write[1] = 0;

                drag.setX(circle.getWidth()/2 - drag.getWidth() / 2);
                drag.setY(circle.getHeight()/2 - drag.getHeight() / 2);

                break;
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int r = circle.getWidth() / 2;

                int calcX;
                int calcY;
                if((Math.pow(x - r, 2)) + Math.pow(y - r, 2) < Math.pow(r, 2)) {
                    float middleX = x - drag.getWidth() / 2;
                    float middleY = y - drag.getHeight() / 2;

                    drag.setX(middleX);
                    drag.setY(middleY);

                    calcX = x;
                    calcY = y;
                }else{
                    double a = 180 - (Math.toDegrees( Math.atan2(x - r, r - y) )) % 360.0;
                    float newX = (float) (Math.sin(Math.toRadians(a)) * r) + r - drag.getWidth() / 2;
                    float newY = (float) (Math.cos(Math.toRadians(a)) * r) + r - drag.getHeight() / 2;

                    drag.setX(newX);
                    drag.setY(newY);

                    calcX = (int)newX + drag.getHeight() / 2;
                    calcY = (int)newY + drag.getHeight() / 2;
                }

                byte sendX = (byte) (((calcX-circle.getWidth()/2)*100)/(r-1));
                byte sendY = (byte) (((calcY-circle.getHeight()/2)*100)/(r-1));

                ServerThread.write[1] = sendX;
                ServerThread.write[0] = sendY;

                break;
        }

        return true;
    }

}
