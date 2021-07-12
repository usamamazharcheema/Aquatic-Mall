package com.example.androiddeveloper.aquatic_mall;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahuljanagouda on 29/09/17.
 */

public class StoryStatusView extends LinearLayout {
    private static final int MAX_PROGRESS = 100;
    private static final int SPACE_BETWEEN_PROGRESS_BARS = 5;
    private final List<ProgressBar> progressBars = new ArrayList<>();
    private final List<ObjectAnimator> animators = new ArrayList<>();
    private int storiesCount = -1;
    private int current = 0;
    private UserInteractionListener userInteractionListener;
    boolean isReverse;
    private long mAnimationTime;
    boolean isComplete;

    public interface UserInteractionListener {
        void onNext();

        void onPrev();

        void onComplete();
    }

    public StoryStatusView(Context context) {
        super(context);
    }

    public StoryStatusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StoryStatusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StoryStatusView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void bindViews() {
        removeAllViews();

        for (int i = 0; i < storiesCount; i++) {
            final ProgressBar p = createProgressBar();
            p.setMax(MAX_PROGRESS);
            progressBars.add(p);
            addView(p);
            if ((i + 1) < storiesCount) {
                addView(createSpace());
            }
        }
    }

    private ProgressBar createProgressBar() {
        ProgressBar p = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleHorizontal);
        p.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        p.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.progress_bg));
        return p;
    }

    private View createSpace() {
        View v = new View(getContext());
        v.setLayoutParams(new LayoutParams(SPACE_BETWEEN_PROGRESS_BARS, LayoutParams.WRAP_CONTENT));
        return v;
    }


    public void setStoriesCount(int storiesCount) {
        this.storiesCount = storiesCount;
        bindViews();
    }


    public void setUserInteractionListener(UserInteractionListener userInteractionListener) {
        this.userInteractionListener = userInteractionListener;
    }


    public void skip() {
        if (isComplete) return;
        if (progressBars.isEmpty()) {

        }
           else
        {
            ProgressBar p = progressBars.get(current);
            p.setProgress(p.getMax());
            animators.get(current).cancel();
        }
    }
    public void start() {
        if (isComplete) return;
        if (progressBars.isEmpty()) {

        }
        else {
            ProgressBar p = progressBars.get(current);
            p.setProgress(p.getMax());
            animators.get(current).start();
        }
    }
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public void setStopStories(int value)
//    {
//        try {
//            try {
//                animators.get(value).setDuration(100000000000L).pause();
//            } catch (Exception e) {
//            }            animators.get(value).start();
//        } catch (Exception e) {
//        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void pause() {
        if (isComplete) return;
        if (progressBars.isEmpty()) {
//            ProgressBar p = progressBars.get(current);
//            p.setProgress(p.getProgress());
//            animators.get(current).pause();
        }
        else
        {
            ProgressBar p =progressBars.get(current);
            p.setProgress(p.getProgress());
            animators.get(current).pause();

        }
    }
    public void stopAnimation() {
        if (animators != null) {
            if (progressBars.isEmpty()) {
//            ProgressBar p = progressBars.get(current);
//            p.setProgress(p.getProgress());
//            animators.get(current).pause();
            } else {
                ProgressBar p = progressBars.get(current);
                p.setProgress(p.getProgress());
                mAnimationTime = animators.get(current).getCurrentPlayTime();
//            animators.get(current).removeAllListeners();
//                animators.get(current).setStartDelay(90000000L);
//            animators.get(current).cancel();
            }
        }
    }
//        public void stop() {
//            if (isComplete) return;
////            if (!progressBars.isEmpty()) {
//
//                ProgressBar p = progressBars.get(current);
//                p.setProgress(p.getProgress());
//                animators.get(current).setDuration(30000000000L);
//            }
//        else {
//
//        }
//    }
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    public void stop() {
//        if (isComplete) return;
//        if (!progressBars.isEmpty()) {
//
//            ProgressBar p = progressBars.get(current);
//            p.setProgress(p.getProgress());
//            animators.get(current).setStartDelay(300000000L);
//        }
////        else {
////
////        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void resume() {
        if (isComplete) return;
    if (progressBars.isEmpty()){

    }
    else {
    ProgressBar p = progressBars.get(current);
    p.setProgress(p.getProgress());
    animators.get(current).resume();
    }
//    else {
//
    }
    public void restart() {
        if (isComplete) return;
        if (progressBars.isEmpty()){

        }
        else {
            ProgressBar p = progressBars.get(current);
            p.setProgress(p.getProgress());
            animators.get(current).setCurrentPlayTime(mAnimationTime);
//            animators.get(current).start();

        }
//    else {
//
    }
//    }


    public void reverse() {
        if (isComplete) return;
        ProgressBar p = progressBars.get(current);
        p.setProgress(0);
        isReverse = true;
        animators.get(current).cancel();
        if (0 <= (current - 1)) {
            p = progressBars.get(current - 1);
            p.setProgress(0);
            animators.get(--current).start();
        } else {
            animators.get(current).start();
        }
    }


    public void setStoryDuration(long duration) {
        animators.clear();
        for (int i = 0; i < progressBars.size(); i++) {
            animators.add(createAnimator(i, duration));
        }
    }


    public void setStoriesCountWithDurations(@NonNull long[] durations) {
        storiesCount = durations.length;
        bindViews();
//        animators.clear();
        for (int i = 0; i < progressBars.size(); i++) {
            animators.add(createAnimator(i, durations[i]));
        }
    }
    public void playStories() {
        if (animators.isEmpty()){
//            destroy();
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (animators.isEmpty()){
////                        Toast.makeText(getContext(),"Check internet connection",Toast.LENGTH_SHORT).show();
////                        Intent intent=new Intent(getContext(),StatusActivity.class);
////                        getContext().startActivity(intent);
//
//                    }
//                    else {
//                        animators.get(0).start();
//                    }
////                    try {
//
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
//                }
//            }, 10000);


        }
        else {

            try {
                animators.get(0).start();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Need to call when Activity or Fragment destroy
     */
    public void destroy() {
        for (ObjectAnimator a : animators) {
            a.removeAllListeners();
            a.cancel();
        }
    }

    private ObjectAnimator createAnimator(final int index, long duration) {
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBars.get(index), "progress", MAX_PROGRESS);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(duration);
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                current = index;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (isReverse) {
                    isReverse = false;
                    if (userInteractionListener != null) userInteractionListener.onPrev();
                    return;
                }
                int next = current + 1;
                if (next <= (animators.size() - 1)) {
                    if (userInteractionListener != null) userInteractionListener.onNext();
                    animators.get(next).start();
                } else {
                    isComplete = true;
                    if (userInteractionListener != null) userInteractionListener.onComplete();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return animation;
    }
}
