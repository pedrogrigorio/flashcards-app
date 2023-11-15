package com.example.flashcards_app.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.flashcards_app.R;
import com.example.flashcards_app.models.Animator;
import com.example.flashcards_app.models.Review;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {

    private List<Review> reviews = new ArrayList<>();
    private final Context context;
    private  LinearLayoutManager linearLayoutManager;


    public ReviewAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_new_review_item, parent, false);
        return new ReviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        Review currentReview = reviews.get(position);
        holder.frontTextCard.setText(currentReview.getFrontText());
        holder.backTextCard.setText(currentReview.getBackText());

        Animator animator = new Animator(context,
                (AnimatorSet) AnimatorInflater.loadAnimator(this.context, R.animator.front_animator_anticlockwise),
                (AnimatorSet) AnimatorInflater.loadAnimator(this.context, R.animator.back_animator_anticlockwise),
                (AnimatorSet) AnimatorInflater.loadAnimator(this.context, R.animator.front_animator_clockwise),
                (AnimatorSet) AnimatorInflater.loadAnimator(this.context, R.animator.back_animator_clockwise),
                holder.frontCard, holder.backCard
        );

        holder.frontCard.setOnClickListener(v -> animator.makeAnimationRight());
        holder.backCard.setOnClickListener(v -> animator.makeAnimationLeft());

    }
    @Override
    public void onViewRecycled(@NonNull ReviewHolder holder) {
        super.onViewRecycled(holder);
        holder.frontCard.clearAnimation();
        holder.backCard.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return this.reviews.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    public static class ReviewHolder extends RecyclerView.ViewHolder {
        private View frontCard;
        private View backCard;
        private TextView frontTextCard;
        private TextView backTextCard;
        public ReviewHolder(@NonNull View itemView) {
            super(itemView);

            frontTextCard = itemView.findViewById(R.id.frontCardReviewText);
            backTextCard  = itemView.findViewById(R.id.backCardReviewText);
            frontCard     = itemView.findViewById(R.id.frontCardView);
            backCard      = itemView.findViewById(R.id.backCardView);
        }

        public String getTextCard() {
            return (String) this.frontTextCard.getText();
        }
    }

}
