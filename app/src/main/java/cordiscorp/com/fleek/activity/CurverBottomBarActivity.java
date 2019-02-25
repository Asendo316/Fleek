package cordiscorp.com.fleek.activity;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sdsmdg.harjot.vectormaster.VectorMasterView;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

import cordiscorp.com.fleek.R;
import cordiscorp.com.fleek.utils.CurvedBottomNavigationView;

public class CurverBottomBarActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    CurvedBottomNavigationView bottomNavigationView;
    VectorMasterView fab, fab1, fab2;
    RelativeLayout lin_id;
    PathModel outline;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curved_bottom_bar);

        bottomNavigationView = (CurvedBottomNavigationView) findViewById(R.id.curvedBottomNavigationView);
        fab = (VectorMasterView) findViewById(R.id.fab);
        fab1 = (VectorMasterView) findViewById(R.id.fab1);
        fab2 = (VectorMasterView) findViewById(R.id.fab2);

        lin_id = (RelativeLayout) findViewById(R.id.lin_id);
        bottomNavigationView.inflateMenu(R.menu.main_menu);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                draw(6);
                lin_id.setX(bottomNavigationView.mFirstCurveControlPoint1.x);
                fab2.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);
                fab1.setVisibility(View.GONE);
                drawAnimation(fab2);
                break;
            case R.id.home:
                Toast.makeText(this, "Click home", Toast.LENGTH_SHORT).show();
                draw(2);
                lin_id.setX(bottomNavigationView.mFirstCurveControlPoint1.x);
                fab1.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);
                fab2.setVisibility(View.GONE);
                drawAnimation(fab1);
                break;
            case R.id.account:
                Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();
                draw();
                lin_id.setX(bottomNavigationView.mFirstCurveControlPoint1.x);
                fab2.setVisibility(View.GONE);
                fab1.setVisibility(View.GONE);
                fab.setVisibility(View.VISIBLE);
                drawAnimation(fab);
                break;
        }
        return true;
    }


    private void drawAnimation(final VectorMasterView fab) {
        outline = fab.getPathModelByName("outline");
        outline.setStrokeColor(Color.WHITE);
        outline.setTrimPathEnd(0.0f);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                outline.setTrimPathEnd((Float) valueAnimator.getAnimatedValue());
                fab.update();
            }
        });
        valueAnimator.start();
    }

    private void draw() {
        // get width and height of navigation bar
        // Navigation bar bounds (width & height)
        //mNavigationBarHeight = getHeight();
        //mNavigationBarWidth = getWidth();
        // the coordinates (x,y) of the start point before curve
        bottomNavigationView.mFirstCurveStartPoint.set((bottomNavigationView.mNavigationBarWidth * 10 / 12) - (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) - (bottomNavigationView.CURVE_CIRCLE_RADIUS / 3), 0);
        // the coordinates (x,y) of the end point after curve
        bottomNavigationView.mFirstCurveEndPoint.set(bottomNavigationView.mNavigationBarWidth * 10 / 12, bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 4));
        // same thing for the second curve
        bottomNavigationView.mSecondCurveStartPoint = bottomNavigationView.mFirstCurveEndPoint;
        bottomNavigationView.mSecondCurveEndPoint.set((bottomNavigationView.mNavigationBarWidth * 10 / 12) + (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 3), 0);

        // the coordinates (x,y)  of the 1st control point on a cubic curve
        bottomNavigationView.mFirstCurveControlPoint1.set(bottomNavigationView.mFirstCurveStartPoint.x + bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 4), bottomNavigationView.mFirstCurveStartPoint.y);
        // the coordinates (x,y)  of the 2nd control point on a cubic curve
        bottomNavigationView.mFirstCurveControlPoint2.set(bottomNavigationView.mFirstCurveEndPoint.x - (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) + bottomNavigationView.CURVE_CIRCLE_RADIUS, bottomNavigationView.mFirstCurveEndPoint.y);

        bottomNavigationView.mSecondCurveControlPoint1.set(bottomNavigationView.mSecondCurveStartPoint.x + (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) - bottomNavigationView.CURVE_CIRCLE_RADIUS, bottomNavigationView.mSecondCurveStartPoint.y);
        bottomNavigationView.mSecondCurveControlPoint2.set(bottomNavigationView.mSecondCurveEndPoint.x - (bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 4)), bottomNavigationView.mSecondCurveEndPoint.y);

    }

    private void draw(int i) {
        // get width and height of navigation bar
        // Navigation bar bounds (width & height)
        //mNavigationBarHeight = getHeight();
        //mNavigationBarWidth = getWidth();
        // the coordinates (x,y) of the start point before curve
        bottomNavigationView.mFirstCurveStartPoint.set((bottomNavigationView.mNavigationBarWidth / i) - (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) - (bottomNavigationView.CURVE_CIRCLE_RADIUS / 3), 0);
        // the coordinates (x,y) of the end point after curve
        bottomNavigationView.mFirstCurveEndPoint.set(bottomNavigationView.mNavigationBarWidth / i, bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 4));
        // same thing for the second curve
        bottomNavigationView.mSecondCurveStartPoint = bottomNavigationView.mFirstCurveEndPoint;
        bottomNavigationView.mSecondCurveEndPoint.set((bottomNavigationView.mNavigationBarWidth / i) + (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 3), 0);

        // the coordinates (x,y)  of the 1st control point on a cubic curve
        bottomNavigationView.mFirstCurveControlPoint1.set(bottomNavigationView.mFirstCurveStartPoint.x + bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 4), bottomNavigationView.mFirstCurveStartPoint.y);
        // the coordinates (x,y)  of the 2nd control point on a cubic curve
        bottomNavigationView.mFirstCurveControlPoint2.set(bottomNavigationView.mFirstCurveEndPoint.x - (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) + bottomNavigationView.CURVE_CIRCLE_RADIUS, bottomNavigationView.mFirstCurveEndPoint.y);

        bottomNavigationView.mSecondCurveControlPoint1.set(bottomNavigationView.mSecondCurveStartPoint.x + (bottomNavigationView.CURVE_CIRCLE_RADIUS * 2) - bottomNavigationView.CURVE_CIRCLE_RADIUS, bottomNavigationView.mSecondCurveStartPoint.y);
        bottomNavigationView.mSecondCurveControlPoint2.set(bottomNavigationView.mSecondCurveEndPoint.x - (bottomNavigationView.CURVE_CIRCLE_RADIUS + (bottomNavigationView.CURVE_CIRCLE_RADIUS / 4)), bottomNavigationView.mSecondCurveEndPoint.y);
    }
}
