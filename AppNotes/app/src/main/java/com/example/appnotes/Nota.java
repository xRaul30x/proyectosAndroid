package com.example.appnotes;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

public class Nota extends View implements View.OnTouchListener{


    public Nota(Context context) {

        super(context);
        this.setOnTouchListener(this);


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {



        invalidate();
        return true;
    }
}
