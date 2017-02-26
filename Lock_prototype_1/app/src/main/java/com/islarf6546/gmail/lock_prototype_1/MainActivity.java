package com.islarf6546.gmail.lock_prototype_1;


import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels/10;
        final int width = displayMetrics.widthPixels/10;
        final int freedom = (int) (GeometryMath.round((double) ((height < width) ? height : width), 10) * 1.5);



        final DrawView d = (DrawView) findViewById(R.id.drawArea);


        Button sub = (Button) findViewById(R.id.submit_button);
        sub.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("Freedom: " + freedom);

                        d.displayStrokes(freedom);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {
        //getMenuInflater().inflate(R.menu.activity_write_on_screen, menu);
        return true;
    }


}