package com.example.electivecompilation;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class DragandDrop extends Fragment {
    private ImageView batman, superman, ironman, wolverine, dropHere;
    private TextView status, heroName;
    private ConstraintLayout constraintLayout;
    private Animation animateImage, animateText;
    private Drawable drawable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dragand_drop, container, false);
        initializeViews(view);
        setupDragListeners();
        return view;
    }

    private void initializeViews(View view) {
        // Initialize animations
        animateText = AnimationUtils.loadAnimation(requireContext(), R.anim.my_animation);
        animateImage = AnimationUtils.loadAnimation(requireContext(), R.anim.blink);

        // Initialize views
        batman = view.findViewById(R.id.ivBatman);
        superman = view.findViewById(R.id.ivSuperman);
        wolverine = view.findViewById(R.id.ivWolverine);
        ironman = view.findViewById(R.id.ivIronman);
        dropHere = view.findViewById(R.id.ivHero);
        status = view.findViewById(R.id.tvStatus);
        heroName = view.findViewById(R.id.tvNameOfHero);
        constraintLayout = view.findViewById(R.id.main);
    }

    private void setupDragListeners() {
        // Set up touch listeners for all hero images
        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData clipData = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(clipData, shadowBuilder, view, 0);
                    view.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        };

        // Apply touch listener to all hero images
        batman.setOnTouchListener(touchListener);
        superman.setOnTouchListener(touchListener);
        ironman.setOnTouchListener(touchListener);
        wolverine.setOnTouchListener(touchListener);

        // Set up drop zone
        dropHere.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        status.setText("Dragging Started");
                        return true;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        status.setText("Drag Entered");

                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:
                        status.setText("Drag Exited");
                        v.setBackground(null); // Remove highlight
                        return true;

                    case DragEvent.ACTION_DROP:
                        status.setText("Dropped");
                        View view = (View) event.getLocalState();

                        // Handle the drop
                        if (view instanceof ImageView) {
                            ImageView droppedImage = (ImageView) view;
                            dropHere.setImageDrawable(droppedImage.getDrawable());
                            dropHere.startAnimation(animateImage);

                            // Update hero name based on the dropped image
                            String heroName = "";
                            if (view.getId() == R.id.ivBatman) heroName = "BATMAN";
                            else if (view.getId() == R.id.ivSuperman) heroName = "SUPERMAN";
                            else if (view.getId() == R.id.ivIronman) heroName = "IRONMAN";
                            else if (view.getId() == R.id.ivWolverine) heroName = "WOLVERINE";

                            DragandDrop.this.heroName.setText(heroName);
                            DragandDrop.this.heroName.startAnimation(animateText);
                        }

                        view.setVisibility(View.VISIBLE);
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        status.setText("Drag Ended");
                        View draggedView = (View) event.getLocalState();
                        draggedView.setVisibility(View.VISIBLE);
                        v.setBackground(null); // Remove highlight
                        return true;

                    default:
                        break;
                }
                return false;
            }
        });
    }
}