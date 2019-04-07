package com.transico.codezero.transico.SystemHelper;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

public class BottomNavigationBehaviour<V extends View> extends
        CoordinatorLayout.Behavior<V> {



    @Override
       public boolean  onStartNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout, V child, View directTargetChild, View target, int axes, int type){
           return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
        }

    @Override
    public void onNestedPreScroll (CoordinatorLayout coordinatorLayout,
                                   V child,
                                   View target,
                                   int dx,
                                   int dy,
                                   int[] consumed,
                                   int type) {

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        child.setTranslationY(Math.max(0f, Math.min(child.getHeight(), child.getTranslationY() + dy)));
    }


}
