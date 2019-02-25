package cordiscorp.com.fleek.pandora;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.View;
import android.view.animation.TranslateAnimation;

import cordiscorp.com.fleek.R;


/**
 * Created by Ibikunle Adeoluwa on 12/21/2018.
 */
public class NavigationManager {

    /**
     * @param activity
     * @param activityClass
     */
    public void loadActivity(Activity activity, Class activityClass) {
        Intent i = new Intent(activity, activityClass);
        activity.startActivity(i);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        activity.finish();
    }

    /**
     * @param activity
     * @param fragmentClass
     */
    public void loadFragment(Activity activity, Fragment fragmentClass) {
        FragmentManager manager = activity.getFragmentManager();
        manager.beginTransaction()
                .replace(R.id.content, fragmentClass)
                .commit();
    }


    /**
     * @param view
     */
    public void slideViewUpFromBottom(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    /**
     * @param view
     */
    public void slideViewDownFromTop(View view) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    /**
     * @param view
     */
    public void slideViewLeftFromRight(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                view.getWidth(),                 // fromXDelta
                0,                 // toXDelta
                0,  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    /**
     * @param view
     */
    public void slideViewRightFromLeft(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                view.getWidth(),                 // toXDelta
                0,  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

}
