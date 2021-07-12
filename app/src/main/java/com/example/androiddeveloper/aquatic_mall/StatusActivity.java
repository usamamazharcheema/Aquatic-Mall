package com.example.androiddeveloper.aquatic_mall;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.example.androiddeveloper.aquatic_mall.glideProgressBar.DelayBitmapTransformation;
import com.example.androiddeveloper.aquatic_mall.glideProgressBar.LoggingListener;
import com.example.androiddeveloper.aquatic_mall.glideProgressBar.ProgressTarget;

import java.util.ArrayList;
import java.util.Locale;

public class StatusActivity extends Activity implements StoryStatusView.UserInteractionListener {
    public static final String STATUS_RESOURCES_KEY = "statusStoriesResources";
    public static final String STATUS_DURATION_KEY = "statusStoriesDuration";
    public static final String STATUS_Video_DURATION_KEY = "statusvideoStoriesDuration";
    public static final String ids_key = "statusStoriesDuration";
    public int seekTime;
    public static final String STATUS_DURATIONS_ARRAY_KEY = "statusStoriesDurations";
    public static final String IS_IMMERSIVE_KEY = "isImmersive";
    public static final String IS_CACHING_ENABLED_KEY = "isCaching";
    public static String MAX_PROGRESS1;
    public static final String IS_TEXT_PROGRESS_ENABLED_KEY = "isText";
    private static StoryStatusView storyStatusView;
    private ImageView image;
    private VideoView video;
    private int counter = 0;
    private ArrayList<String> statusResources;
    private ArrayList<String> durationResources;
        private long[] statusResourcesDuration;
    private long statusDuration= 3500L;
    private boolean isImmersive = true;
    private boolean isCaching = true;
    private static boolean isTextEnabled = true;
    ProgressBar imageProgressBar;
    private ProgressTarget<String, Bitmap> target;
    @SuppressLint("WrongViewCast")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);





        statusResources = getIntent().getStringArrayListExtra(STATUS_RESOURCES_KEY);
        durationResources = getIntent().getStringArrayListExtra(STATUS_Video_DURATION_KEY);
//        idsres= getIntent().getStringArrayListExtra(ids_key);
        statusDuration = getIntent().getLongExtra(STATUS_DURATION_KEY, 3500L);
        statusResourcesDuration = getIntent().getLongArrayExtra(STATUS_DURATIONS_ARRAY_KEY);
        isImmersive = getIntent().getBooleanExtra(IS_IMMERSIVE_KEY, true);
        isCaching = getIntent().getBooleanExtra(IS_CACHING_ENABLED_KEY, true);
        isTextEnabled = getIntent().getBooleanExtra(IS_TEXT_PROGRESS_ENABLED_KEY, true);

         imageProgressBar= findViewById(R.id.imageProgressBar);
        TextView textView = findViewById(R.id.textView);
        image = findViewById(R.id.image);
        video = findViewById(R.id.video);

        storyStatusView = findViewById(R.id.storiesStatus);
        storyStatusView.setStoriesCount(statusResources.size());
        storyStatusView.setStoryDuration(statusDuration);
        // or
        // statusView.setStoriesCountWithDurations(statusResourcesDuration);
        storyStatusView.setUserInteractionListener(this);
        storyStatusView.playStories();
        target = new MyProgressTarget<>(new BitmapImageViewTarget(image),imageProgressBar, textView);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storyStatusView.skip();
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storyStatusView.skip();
            }
        });


        if (statusResources.isEmpty()) {
           finish();
           Toast.makeText(StatusActivity.this, "check internet connection", Toast.LENGTH_SHORT).show();
        }

        else {
            storyStatusView.pause();
            target.setModel(statusResources.get(counter));
            if (statusResources.get(counter).endsWith(".jpg")) {
                image.setVisibility(View.VISIBLE);
                video.setVisibility(View.INVISIBLE);
//                imageProgressBar.setVisibility(View.VISIBLE);
                Glide.with(image.getContext())
                        .load(target.getModel())
                        .asBitmap()
                        .crossFade()
                        .skipMemoryCache(!isCaching)
                        .diskCacheStrategy(isCaching ? DiskCacheStrategy.ALL : DiskCacheStrategy.NONE)
                        .transform(new CenterCrop(image.getContext()), new DelayBitmapTransformation(1000))
                        .listener(new LoggingListener<String, Bitmap>())
                        .into(target);
//                storyStatusView.setStoryDuration(statusDuration);
//                Toast.makeText(StatusActivity.this, "usama", Toast.LENGTH_SHORT).show();

            } else if (statusResources.get(counter).toString().endsWith(".3gp")) {
//                if (String.valueOf(idsres.get(counter)).equals(String.valueOf(counter))) {
//                    storyStatusView.setAnimation(null);
                    image.setVisibility(View.INVISIBLE);
                    video.setVisibility(View.VISIBLE);
//                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//            retriever.setDataSource(statusResources.get(counter), new HashMap<String, String>());
//           MAX_PROGRESS1 =retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//            long timeInMillisec = Long.parseLong(MAX_PROGRESS1);
//            retriever.release();
//                Toast.makeText(StatusActivity.this, (int) timeInMillisec,Toast.LENGTH_SHORT).show();

//                    Toast.makeText(StatusActivity.this, String.valueOf(idsres.get(counter)), Toast.LENGTH_SHORT).show();
                    video.setVideoPath(statusResources.get(counter));
                storyStatusView.setStoryDuration(Long.parseLong(durationResources.get(counter)));
//                imageProgressBar.setVisibility(View.VISIBLE);
                    video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            video.start();
//                        imageProgressBar.setVisibility(View.INVISIBLE);
                        }
                    });

                    final MediaPlayer.OnInfoListener onInfoToPlayStateListener = new MediaPlayer.OnInfoListener() {

                        @Override
                        public boolean onInfo(MediaPlayer mp, int what, int extra) {
                            switch (what) {
                                case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START: {
//                                    Toast.makeText(StatusActivity.this, "usama", Toast.LENGTH_SHORT).show();
                                    storyStatusView.start();
                                    storyStatusView.setVisibility(View.VISIBLE);
//                                storyStatusView.setStoryDuration(3000L);
//                                storyStatusView.setStoryDuration(30000L);

//                                mProgressBar.setVisibility(View.GONE);
                                    return true;
                                }
                                case MediaPlayer.MEDIA_INFO_BUFFERING_START: {


//                                mProgressBar.setVisibility(View.VISIBLE);
                                    return true;
                                }
                                case MediaPlayer.MEDIA_INFO_BUFFERING_END: {
//                                mProgressBar.setVisibility(View.VISIBLE);
                                    return true;
                                }
                            }
                            return false;
                        }

                    };

                    video.setOnInfoListener(onInfoToPlayStateListener);
//                int duration = video.getCurrentPosition();
//                Toast.makeText(StatusActivity.this, duration,Toast.LENGTH_SHORT).show();


//              storyStatusView.pause();

//                 video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                     @Override
//                     public void onPrepared(MediaPlayer mp) {
//
//                         storyStatusView.resume();
//                     }
//                 });


//                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//            retriever.setDataSource("https://cpecintel.com/aquatic/news/php/imges/inam.3gp", new HashMap<String, String>());
//           MAX_PROGRESS1 =retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//            long timeInMillisec = Long.parseLong(MAX_PROGRESS1);
//            retriever.release();
//                storyStatusView.setStoryDuration(timeInMillisec);
//                video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                    @Override
//                    public void onPrepared(MediaPlayer mp) {
//                       long a= video.getDuration();
//                        Toast.makeText(StatusActivity.this, "mazhar", Toast.LENGTH_SHORT).show();
//                    }
//                });


                }
            }
//        }
//        }
        // bind reverse view
        findViewById(R.id.reverse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storyStatusView.reverse();
            }
        });

        // bind skip view
        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storyStatusView.skip();
            }
        });

        findViewById(R.id.actions).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {

                    storyStatusView.pause();
                    storyStatusView.stopAnimation();
                    video.pause();
                   seekTime = video.getCurrentPosition();

                } else {
                    storyStatusView.resume();
                    storyStatusView.restart();

                    video.seekTo(seekTime);
                    video.start();
//                    video.resume();


                }
                return true;
            }
        });
    }





    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onNext() {

        storyStatusView.pause();
        ++counter;
        target.setModel(statusResources.get(counter));

        if (statusResources.get(counter).endsWith(".jpg")) {
            image.setVisibility(View.VISIBLE);
            video.setVisibility(View.INVISIBLE);
//            imageProgressBar.setVisibility(View.VISIBLE);
            storyStatusView.setStoryDuration(3500L);
            if(!this.isFinishing()){
            Glide.with(image.getContext())
                    .load(target.getModel())
                    .asBitmap()
                    .crossFade()
                    .skipMemoryCache(!isCaching)
                    .diskCacheStrategy(isCaching ? DiskCacheStrategy.ALL : DiskCacheStrategy.NONE)
                    .transform(new CenterCrop(image.getContext()), new DelayBitmapTransformation(1000))
                    .listener(new LoggingListener<String, Bitmap>())
                    .into(target);
        }} else if (statusResources.get(counter).endsWith(".3gp")) {
//            storyStatusView.stop();
//            if (String.valueOf(idsres.get(counter)).equals(String.valueOf(counter))) {
//                storyStatusView.setAnimation(null);
                image.setVisibility(View.INVISIBLE);
                video.setVisibility(View.VISIBLE);
//            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//            retriever.setDataSource(statusResources.get(counter), new HashMap<String, String>());
//            MAX_PROGRESS1 =retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//            long timeInMillisec = Long.parseLong(MAX_PROGRESS1);

//            Toast.makeText(StatusActivity.this, MAX_PROGRESS1,Toast.LENGTH_SHORT).show();
                video.setVideoPath(statusResources.get(counter));
//                Toast.makeText(StatusActivity.this, String.valueOf(counter), Toast.LENGTH_SHORT).show();
                storyStatusView.setStoryDuration(Long.parseLong(durationResources.get(counter)));
                video.start();
//            retriever.release();
                final MediaPlayer.OnInfoListener onInfoToPlayStateListener = new MediaPlayer.OnInfoListener() {

                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        switch (what) {
                            case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START: {

//                            Toast.makeText(StatusActivity.this, "usama", Toast.LENGTH_SHORT).show();
//                                mProgressBar.setVisibility(View.GONE);


                                storyStatusView.start();
                                storyStatusView.setVisibility(View.VISIBLE);
//                            storyStatusView.setStoryDuration(3000L);

                                return true;
                            }
                            case MediaPlayer.MEDIA_INFO_BUFFERING_START: {
//                            Toast.makeText(StatusActivity.this, "mazhar", Toast.LENGTH_SHORT).show();

//                                mProgressBar.setVisibility(View.VISIBLE);
                                return true;
                            }
                            case MediaPlayer.MEDIA_INFO_BUFFERING_END: {
//                                mProgressBar.setVisibility(View.VISIBLE);
                                return true;
                            }
                        }
                        return false;
                    }

                };

                video.setOnInfoListener(onInfoToPlayStateListener);
//            if (video.isPlaying()){
//                Toast.makeText(StatusActivity.this, "usama", Toast.LENGTH_SHORT).show();
//            }

//            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//            retriever.setDataSource("https://cpecintel.com/aquatic/news/php/imges/inam.3gp", new HashMap<String, String>());
//           MAX_PROGRESS1 =retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
////            long timeInMillisec = Long.parseLong(time);
//            retriever.release();
//            int ab=Integer.parseInt(time);
//            String duration=convertMillieToHMmSs(timeInMillisec);
//            int ab=Integer.parseInt(duration);
//            Toast.makeText(StatusActivity.this, MAX_PROGRESS1, Toast.LENGTH_SHORT).show();


            }
        }
//    }
//    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onPrev() {

        if (counter - 1 < 0) return;
        storyStatusView.pause();
        --counter;
        target.setModel(statusResources.get(counter));
        if (statusResources.get(counter).endsWith(".jpg")) {
            image.setVisibility(View.VISIBLE);
            video.setVisibility(View.INVISIBLE);
//            imageProgressBar.setVisibility(View.VISIBLE);
            storyStatusView.setStoryDuration(3500L);
            Glide.with(image.getContext())
                    .load(target.getModel())
                    .asBitmap()
                    .crossFade()
                    .skipMemoryCache(!isCaching)
                    .diskCacheStrategy(isCaching ? DiskCacheStrategy.ALL : DiskCacheStrategy.NONE)
                    .transform(new CenterCrop(image.getContext()), new DelayBitmapTransformation(1000))
                    .listener(new LoggingListener<String, Bitmap>())
                    .into(target);
        } else if (statusResources.get(counter).endsWith(".3gp")) {
//           if (String.valueOf(idsres.get(counter)).equals(String.valueOf(counter))) {
                image.setVisibility(View.INVISIBLE);
                video.setVisibility(View.VISIBLE);
//            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//            retriever.setDataSource(statusResources.get(counter), new HashMap<String, String>());
//            MAX_PROGRESS1 =retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//            long timeInMillisec = Long.parseLong(MAX_PROGRESS1);
//            retriever.release();
                video.setVideoPath(statusResources.get(counter));
                video.start();
//                Toast.makeText(StatusActivity.this, (int) timeInMillisec,Toast.LENGTH_SHORT).show();
            storyStatusView.setStoryDuration(Long.parseLong(durationResources.get(counter)));
                final MediaPlayer.OnInfoListener onInfoToPlayStateListener = new MediaPlayer.OnInfoListener() {

                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        switch (what) {
                            case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START: {

//                            Toast.makeText(StatusActivity.this, "usama", Toast.LENGTH_SHORT).show();
//                                mProgressBar.setVisibility(View.GONE);


                                storyStatusView.start();
                                storyStatusView.setVisibility(View.VISIBLE);
//                            storyStatusView.setStoryDuration(3000L);

                                return true;
                            }
                            case MediaPlayer.MEDIA_INFO_BUFFERING_START: {
//                            Toast.makeText(StatusActivity.this, "mazhar", Toast.LENGTH_SHORT).show();

//                                mProgressBar.setVisibility(View.VISIBLE);
                                return true;
                            }
                            case MediaPlayer.MEDIA_INFO_BUFFERING_END: {
//                                mProgressBar.setVisibility(View.VISIBLE);
                                return true;
                            }
                        }
                        return false;
                    }

                };

                video.setOnInfoListener(onInfoToPlayStateListener);
            }
        }
//    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (isImmersive && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (hasFocus) {
                getWindow().getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }

    @Override
    public void onComplete() {
        finish();
    }
    public static String convertMillieToHMmSs(long millie) {
        long seconds = (millie / 1000);
        long second = seconds % 60;
        long minute = (seconds / 60) % 60;
        long hour = (seconds / (60 * 60)) % 24;

        String result = "";
        if (hour > 0) {
            return String.format("%02d:%02d:%02d", hour, minute, second);
        }
        else {
            return String.format("%02d:%02d" , minute, second);
        }

    }

    @Override
    protected void onDestroy() {
        // Very important !
        storyStatusView.destroy();
        super.onDestroy();
    }

    /**
     * Demonstrates 3 different ways of showing the progress:
     * <ul>
     * <li>Update a full fledged progress bar</li>
     * <li>Update a text view to display size/percentage</li>
     * <li>Update the placeholder via Drawable.level</li>
     * </ul>
     * This last one is tricky: the placeholder that Glide sets can be used as a progress drawable
     * without any extra Views in the view hierarchy if it supports levels via <code>usesLevel="true"</code>
     * or <code>level-list</code>.
     *
     * @param <Z> automatically match any real Glide target so it can be used flexibly without reimplementing.
     */
    @SuppressLint("SetTextI18n") // text set only for debugging
    private static class MyProgressTarget<Z> extends ProgressTarget<String, Z> {
        private final TextView text;
        private ProgressBar progress;

        public MyProgressTarget(Target<Z> target, ProgressBar progress, TextView text) {
            super(target);
            this.progress = progress;
            this.text = text;
        }

        @Override
        public float getGranualityPercentage() {
            return 0.1f; // this matches the format string for #text below
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onConnecting() {
            progress.setIndeterminate(true);
            progress.setVisibility(View.VISIBLE);

            if (isTextEnabled) {
                text.setVisibility(View.VISIBLE);
                text.setText("connecting");
            } else {
                text.setVisibility(View.INVISIBLE);
            }
            storyStatusView.pause();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onDownloading(long bytesRead, long expectedLength) {
            progress.setIndeterminate(false);
            progress.setProgress((int) (100 * bytesRead / expectedLength));

            if (isTextEnabled) {
                text.setVisibility(View.VISIBLE);
                text.setText(String.format(Locale.ROOT, "downloading %.2f/%.2f MB %.1f%%",
                        bytesRead / 1e6, expectedLength / 1e6, 100f * bytesRead / expectedLength));
            } else {
                text.setVisibility(View.INVISIBLE);
            }


            storyStatusView.pause();

        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onDownloaded() {
            progress.setIndeterminate(true);
            if (isTextEnabled) {
                text.setVisibility(View.VISIBLE);
                text.setText("decoding and transforming");
            } else {
                text.setVisibility(View.INVISIBLE);
            }


            storyStatusView.pause();
        }

        @Override
        protected void onDelivered() {
            progress.setVisibility(View.INVISIBLE);
            text.setVisibility(View.INVISIBLE);
            storyStatusView.resume();
        }
    }
}
