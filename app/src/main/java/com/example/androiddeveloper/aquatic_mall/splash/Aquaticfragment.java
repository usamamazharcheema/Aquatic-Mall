package com.example.androiddeveloper.aquatic_mall.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider1;

/**
 * Created by ANDROID DEVELOPER on 16/02/2018.
 */

public class Aquaticfragment extends Fragment  implements Animation.AnimationListener
{
    private Animation  animMoveUp;
    private Animation  animMoveDown;

        public static Aquaticfragment newInstance(String movieTitle) {
        Aquaticfragment fragmentAction = new Aquaticfragment();
        Bundle args = new Bundle();
        args.putString(" ", movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.splash, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView title =  (ImageView) view.findViewById(R.id.title);
        animMoveUp = AnimationUtils.loadAnimation(getActivity(), R.anim.move_up);
        animMoveUp.setAnimationListener(this);

        animMoveDown = AnimationUtils.loadAnimation(getActivity(), R.anim.move_down);
        animMoveDown.setAnimationListener(this);
        title.startAnimation(animMoveUp);
    }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

}

