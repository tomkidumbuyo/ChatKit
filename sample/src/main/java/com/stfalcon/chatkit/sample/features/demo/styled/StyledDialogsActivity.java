package com.stfalcon.chatkit.sample.features.demo.styled;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;
import com.stfalcon.chatkit.sample.R;
import com.stfalcon.chatkit.sample.common.data.fixtures.DialogsFixtures;
import com.stfalcon.chatkit.sample.common.data.model.Dialog;
import com.stfalcon.chatkit.sample.features.demo.DemoDialogsActivity;
import com.stfalcon.chatkit.sample.features.demo.styled.fragments.Calls;
import com.stfalcon.chatkit.sample.features.demo.styled.fragments.Messages;
import com.stfalcon.chatkit.sample.features.demo.styled.fragments.Status;
import com.stfalcon.chatkit.sample.utils.AppUtils;
import com.stfalcon.chatkit.utils.DateFormatter;

import java.util.Date;

public class StyledDialogsActivity extends AppCompatActivity
        implements DialogsListAdapter.OnDialogClickListener<Dialog>,
        DialogsListAdapter.OnDialogLongClickListener<Dialog>,
        DateFormatter.Formatter {

    public static void open(Context context) {
        context.startActivity(new Intent(context, StyledDialogsActivity.class));
    }

    private static final String TAG = "StyledDialogsActivity";

    protected ImageLoader imageLoader;
    protected DialogsListAdapter<Dialog> dialogsAdapter;

    private DialogsList dialogsList;
    private SectionsPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    public DialogsListAdapter mDialogsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styled_dialogs);

        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url, Object payload) {
                Picasso.with(StyledDialogsActivity.this).load(url).into(imageView);
            }
        };


        Log.d(TAG,"Oncreate started");

        mSectionPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


//        dialogsList = (DialogsList) findViewById(R.id.dialogsList);
//        initAdapter();
    }

    public void onDialogLongClick(Dialog dialog) {
        AppUtils.showToast(
                this,
                dialog.getDialogName(),
                false);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Calls(),"CALLS");

        Messages messageFragment = new Messages();
        messageFragment.setParent(this);

        adapter.addFragment(messageFragment,"MESSAGES");


        adapter.addFragment(new Status(),"CALLS");
    }



    private void initAdapter() {

        dialogsAdapter = new DialogsListAdapter<>(imageLoader);
        dialogsAdapter.setItems(DialogsFixtures.getDialogs());

        dialogsAdapter.setOnDialogClickListener(this);
        dialogsAdapter.setOnDialogLongClickListener(this);
        dialogsAdapter.setDatesFormatter(this);

        dialogsList.setAdapter(dialogsAdapter);

    }


    @Override
    public void onDialogClick(Dialog dialog) {

    }

    public String format(Date date) {
        if (DateFormatter.isToday(date)) {
            return DateFormatter.format(date, DateFormatter.Template.TIME);
        } else if (DateFormatter.isYesterday(date)) {
            return getString(R.string.date_header_yesterday);
        } else if (DateFormatter.isCurrentYear(date)) {
            return DateFormatter.format(date, DateFormatter.Template.STRING_DAY_MONTH);
        } else {
            return DateFormatter.format(date, DateFormatter.Template.STRING_DAY_MONTH_YEAR);
        }
    }

}
