package com.zaman.sengkhunlim.bouncingball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import java.util.Formatter;

public class BouncingBall extends View {

    private int xMax, yMax;
    private int xMin = 0;
    private int yMin = 70;
    private float ballSize = 100;
    private int minBallSize = 50;
    private int maxBallSize = 400;
    private float ballPosX = 50;
    private float ballPosY = 100;
    private float velocityX = 10;
    private float velocityY = 10;
    private long speed = 10;
    private StringBuilder statusMsg = new StringBuilder();
    private Formatter formatter = new Formatter( statusMsg );

    public BouncingBall(Context context, AttributeSet attributeSet ) {
        super( context, attributeSet );
        setFocusable( true );
        requestFocus();
    }

    @Override
    protected void onSizeChanged( int w, int h, int oldW, int oldH ) {
        super.onSizeChanged( w, h, oldW, oldH );

        xMax = w;
        yMax = h;

    }

    @Override
    protected void onDraw( Canvas canvas ) {
        super.onDraw(canvas);

        // Create paint
        Paint paint = new Paint();
        paint.setStyle( Paint.Style.FILL );
        paint.setColor( Color.GREEN );

        // Draw a ball
        RectF ball = new RectF(
                ballPosX,
                ballPosY,
                ballPosX + ballSize,
                ballPosY + ballSize
        );
        canvas.drawOval( ball, paint );

        // Draw text
        paint.setColor( Color.BLUE );
        paint.setTextSize( 50 );
        canvas.drawText( statusMsg.toString(),20, 70, paint );

        // call updateVelocity()
        updateVelocity();

    }

    @Override
    public boolean onKeyUp( int keyCode, KeyEvent event ) {

        switch ( keyCode ) {

            case KeyEvent.KEYCODE_MINUS:
                if ( ballSize > minBallSize ) ballSize *= 90.0/100;
                break;

            case KeyEvent.KEYCODE_EQUALS:
                if ( ballSize < maxBallSize ) ballSize *= 1.1;
                break;

            case KeyEvent.KEYCODE_1:
                velocityY++;
                break;

            case KeyEvent.KEYCODE_2:
                velocityY--;
                break;

            case KeyEvent.KEYCODE_3:
                velocityX--;
                break;

            case KeyEvent.KEYCODE_4:
                velocityX++;
                break;

        }

        return true;

    }

    private void updateVelocity() {

        ballPosX += velocityX;
        ballPosY += velocityY;

        if ( ballPosX + ballSize >= xMax || ballPosX <= xMin ) {
            velocityX = -velocityX;
        }

        if ( ballPosY + ballSize >= yMax || ballPosY <= yMin ) {
            velocityY = -velocityY;
        }

        statusMsg.delete( 0, statusMsg.length() );
        formatter.format( "Ball Pos (%3.0f, %3.0f), speed( %2.0f, %2.0f ), Size( %2.0f )",
                ballPosX, ballPosY, velocityX, velocityY, ballSize );

    }

    public void doAnimation() {

        while ( true ) {

            try {

                Thread.sleep( speed );

            } catch ( InterruptedException e ) { break; }

            postInvalidate();

        } // End of while loop

    }

}
