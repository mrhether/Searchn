package com.markhetherington.searchn.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.markhetherington.searchn.R;
import com.markhetherington.searchn.network.model.Shot;

import java.util.List;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;

public class ShotsAdapter extends RecyclerView.Adapter<ShotsAdapter.ShotViewHolder> {

    List<Shot> mShots;

    public ShotsAdapter(@NonNull List<Shot> shots) {
        mShots = shots;
    }

    @Override
    public ShotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, parent, false);
        ShotViewHolder shotViewHolder = new ShotViewHolder((CardView) view);
        return shotViewHolder;
    }

    @Override
    public void onBindViewHolder(final ShotViewHolder holder, int position) {
        Shot shot = mShots.get(position);

        holder.mTitleTextView.setText(shot.getTitle());
        if (shot.getDescription() != null) {
            holder.mSubTextView.setVisibility(View.VISIBLE);
            Spanned text = Html.fromHtml(shot.getDescription());
            holder.mSubTextView.setText(text);
        } else {
            holder.mSubTextView.setVisibility(View.GONE);
        }

        Glide.with(holder.mImageView.getContext())
                .load(shot.getImages().getNormal())
                .crossFade(1000)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mShots.size();
    }

    public void appendShots(List<Shot> shots) {
        int size = mShots.size();
        mShots.addAll(shots);
        notifyItemRangeInserted(size, mShots.size());
    }

    static class ShotViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image)
        ImageView mImageView;
        @Bind(R.id.text)
        TextView mTitleTextView;
        @Bind(R.id.text2)
        TextView mSubTextView;

        CardView mItemView;

        @BindColor(R.color.Pink)
        int mPink;


        public ShotViewHolder(CardView itemView) {
            super(itemView);
            mItemView = itemView;
            ButterKnife.bind(this, itemView);
            mSubTextView.setMovementMethod(LinkMovementMethod.getInstance());
            mSubTextView.setLinkTextColor(mPink);
        }
    }

}
