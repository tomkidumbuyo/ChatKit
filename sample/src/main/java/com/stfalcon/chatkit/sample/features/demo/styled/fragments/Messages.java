package com.stfalcon.chatkit.sample.features.demo.styled.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;
import com.stfalcon.chatkit.sample.R;
import com.stfalcon.chatkit.sample.common.data.fixtures.DialogsFixtures;
import com.stfalcon.chatkit.sample.common.data.model.Dialog;
import com.stfalcon.chatkit.sample.features.demo.styled.StyledDialogsActivity;
import com.stfalcon.chatkit.sample.features.demo.styled.StyledMessagesActivity;
import com.stfalcon.chatkit.utils.DateFormatter;

import java.util.Date;


public class Messages extends Fragment {

    private DialogsList dialogsList;
    private StyledDialogsActivity parentActivity;
    private DialogsListAdapter dialogsAdapter;


    public Messages() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // parentActivity = ((StyledDialogsActivity)getActivity());

        dialogsList = (DialogsList) getActivity().findViewById(R.id.dialogsList);
        initAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }


    public void onDialogClick(Dialog dialog) {
        StyledMessagesActivity.open(parentActivity);
    }




    private void initAdapter() {

        dialogsAdapter = parentActivity.initAdapter();

        dialogsList.setAdapter(dialogsAdapter);

    }


    public void setParent(StyledDialogsActivity styledDialogsActivity) {
        parentActivity = styledDialogsActivity;
    }
}
