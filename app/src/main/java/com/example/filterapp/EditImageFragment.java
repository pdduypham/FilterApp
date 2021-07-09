package com.example.filterapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import com.example.filterapp.Interface.EditImageFragmentListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditImageFragment extends BottomSheetDialogFragment implements SeekBar.OnSeekBarChangeListener {

    private EditImageFragmentListener listener;
    SeekBar seek_brightness, seek_constraint, seek_saturation;
    static EditImageFragment instance;

    public static EditImageFragment getInstance() {
        if (instance == null) {
            instance = new EditImageFragment();
        }
        return instance;
    }

    public void setListener(EditImageFragmentListener listener) {
        this.listener = listener;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditImageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditImageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditImageFragment newInstance(String param1, String param2) {
        EditImageFragment fragment = new EditImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_image, container, false);

        seek_brightness = view.findViewById(R.id.seekBar_brightness);
        seek_constraint = view.findViewById(R.id.seekBar_constraint);
        seek_saturation = view.findViewById(R.id.seekBar_saturation);

        seek_brightness.setMax(200);
        seek_brightness.setProgress(100);

        seek_constraint.setMax(20);
        seek_brightness.setProgress(0);

        seek_saturation.setMax(30);
        seek_saturation.setProgress(10);

        seek_brightness.setOnSeekBarChangeListener(this);
        seek_constraint.setOnSeekBarChangeListener(this);
        seek_saturation.setOnSeekBarChangeListener(this);

        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (listener != null) {
            if (seekBar.getId() == R.id.seekBar_brightness) {
                listener.onBrightnessChanged(progress - 100);
            } else if (seekBar.getId() == R.id.seekBar_constraint) {
                progress += 10;
                float value = .10f * progress;
                listener.onConstraintChanged(value);
            } else if (seekBar.getId() == R.id.seekBar_saturation) {
                float value = .10f * progress;
                listener.onSaturationChanged(value);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (listener != null) {
            listener.onEditStarted();
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (listener != null) {
            listener.onEditCompleted();
        }
    }

    public void resetControls() {
        seek_brightness.setProgress(100);
        seek_constraint.setProgress(0);
        seek_saturation.setProgress(10);
    }
}