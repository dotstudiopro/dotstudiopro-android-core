package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.CommentItem;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Admin on 05-12-2014.
 */
public class CommentAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<CommentItem> commentItemList;

    public CommentAdapter(Activity activity, List<CommentItem> commentItemList) {
        this.activity = activity;
        this.commentItemList = commentItemList;
    }

    @Override
    public int getCount() {
        return commentItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
            convertView = inflater.inflate(R.layout.comment_row, null);

        SimpleDraweeView userImage = (SimpleDraweeView) convertView.findViewById(R.id.userImage);
        TextView username = (TextView) convertView.findViewById(R.id.username);
        TextView userComment = (TextView) convertView.findViewById(R.id.userComment);
        TextView commentDate = (TextView) convertView.findViewById(R.id.commentDate);

        username.setTypeface(FontsConstants.tfBold);
        userComment.setTypeface(FontsConstants.tfRegular);
        commentDate.setTypeface(FontsConstants.tfBold);

        CommentItem commentItem = commentItemList.get(position);
        Uri uri = Uri.parse(commentItem.getUserImage());
        userImage.setImageURI(uri);
        username.setText(commentItem.getUsername());
        userComment.setText(commentItem.getComment());
        commentDate.setText(commentItem.getCommentDate());

        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.BLACK);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#101010"));
        }

        return convertView;
    }
}
