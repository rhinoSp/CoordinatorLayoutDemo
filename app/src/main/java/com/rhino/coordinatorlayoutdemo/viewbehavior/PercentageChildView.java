package com.rhino.coordinatorlayoutdemo.viewbehavior;



public interface PercentageChildView {

    /**
     * This will be called on behavior updated.
     * @param behavior Changing behavior object
     * @param progress 0.0 to 1.0 progress of behavior animation
     */
    void onPercentageBehaviorChange(PercentageViewBehavior behavior, float progress);
}
