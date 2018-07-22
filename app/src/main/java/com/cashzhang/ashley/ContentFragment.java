package com.cashzhang.ashley;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.InputStream;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangchi on 2018/3/30.
 */

public class ContentFragment extends Fragment {

    private final static String TAG = "ashley-rss";
    private String title = null;
    private String time = null;
    private String url = null;
    private String content = null;

    Bundle bundle;

//    @BindView(R.id.c_title)
//    TextView mTitle;
//    @BindView(R.id.c_content)
//    TextView mContent;

    TextView mTitle;
    TextView mContent;

    Activity activity;

    public static ContentFragment newInstance() {
        ContentFragment contentFragment = new ContentFragment();
        return contentFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "ContentFragment onCreateView: ");
        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.content_page, container, false);
//        ButterKnife.bind(this, layout);

        mTitle = (TextView) layout.findViewById(R.id.c_title);
        mContent = (TextView) layout.findViewById(R.id.c_content);


        return layout;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "ContentFragment onCreate: ");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint() -> isVisibleToUser: " + isVisibleToUser);
        if (isVisibleToUser)
            loadData(MainActivity.getBundle());
    }

    public void loadData(Bundle tmpBundle) {
        try {
            bundle = tmpBundle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bundle != null) {
            title = bundle.getString("title");
            time = bundle.getString("time");
            url = bundle.getString("url");
            content = bundle.getString("content");
        }

        if (mTitle != null && mContent != null && title != null) {
            mTitle.setText(title);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Spanned sp = Html.fromHtml(content, new Html.ImageGetter() {
                        @Override
                        public Drawable getDrawable(String source) {
                            InputStream is = null;
                            try {
                                is = (InputStream) new URL(source).getContent();

                                Drawable d = Drawable.createFromStream(is, "src");
                                DisplayMetrics dm = getActivity().getResources().getDisplayMetrics();
                                int dwidth = dm.widthPixels - 50;

                                float dheight = (float) d.getIntrinsicHeight() * (float) dwidth / (float) d.getIntrinsicWidth();
                                int dh = (int) (dheight - 0.5);
                                int wid = dwidth;
                                int hei = dh;
                                //fix blank line bewteen text and image
                                int radio = (int) (hei * 0.25);
                                d.setBounds(0, 0 - radio, wid, hei - radio);

                                is.close();
                                return d;
                            } catch (Exception e) {
                                return null;
                            }
                        }
                    }, null);
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mContent.setText(sp);
                                mContent.setMovementMethod(LinkMovementMethod.getInstance());
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "ContentFragment onCreateOptionsMenu: ");
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.content_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.open_brower:
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            case android.R.id.home:
                getActivity().onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*public class PicassoImageGetter implements Html.ImageGetter {

        private AppTextView textView = null;

        public PicassoImageGetter() {

        }

        public PicassoImageGetter(AppTextView target) {
            textView = target;
        }

        @Override
        public Drawable getDrawable(String source) {
            BitmapDrawablePlaceHolder drawable = new BitmapDrawablePlaceHolder();
            Picasso.with(App.get())
                    .load(source)
                    .placeholder(R.mipmap.rss_symbol)
                    .into(drawable);
            return drawable;
        }

        private class BitmapDrawablePlaceHolder extends BitmapDrawable implements Target {

            protected Drawable drawable;

            @Override
            public void draw(final Canvas canvas) {
                if (drawable != null) {
                    drawable.draw(canvas);
                }
            }

            public void setDrawable(Drawable drawable) {
                this.drawable = drawable;
                int width = drawable.getIntrinsicWidth();
                int height = drawable.getIntrinsicHeight();
                drawable.setBounds(0, 0, width, height);
                setBounds(0, 0, width, height);
                if (textView != null) {
                    textView.setText(textView.getText());
                }
            }

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                setDrawable(new BitmapDrawable(App.get().getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }

        }
    }*/

}

