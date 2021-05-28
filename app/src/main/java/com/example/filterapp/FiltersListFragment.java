package com.example.filterapp;

import android.graphics.Bitmap;
import android.net.wifi.aware.WifiAwareManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filterapp.Adapter.ThumbnailAdapter;
import com.example.filterapp.Interface.FiltersListFragmentListener;
import com.example.filterapp.Utils.BitmapUtils;
import com.example.filterapp.Utils.SpacesItemDecoration;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.zomato.photofilters.FilterPack;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.utils.ThumbnailItem;
import com.zomato.photofilters.utils.ThumbnailsManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FiltersListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FiltersListFragment extends BottomSheetDialogFragment implements FiltersListFragmentListener {

    RecyclerView recyclerView;
    ThumbnailAdapter thumbnailAdapter;
    List<ThumbnailItem> thumbnailItemList;

    FiltersListFragmentListener listener;

    static FiltersListFragment instance;

    static Bitmap bitmap;

    public static FiltersListFragment getInstance(Bitmap bitmapSave){
        bitmap = bitmapSave;

        if (instance==null){
            instance = new FiltersListFragment();
        }
        return instance;
    }

    public void setListener(FiltersListFragmentListener listener) {
        this.listener = listener;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FiltersListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FiltersListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FiltersListFragment newInstance(String param1, String param2) {
        FiltersListFragment fragment = new FiltersListFragment();
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
        View view =  inflater.inflate(R.layout.fragment_filters_list, container, false);

        thumbnailItemList = new ArrayList<>();
        thumbnailAdapter = new ThumbnailAdapter(getActivity(),thumbnailItemList,this);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,getResources().getDisplayMetrics());
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));
        recyclerView.setAdapter(thumbnailAdapter);

        displayThumbnail(bitmap);

        return view;
    }

    public void displayThumbnail(Bitmap bitmap) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Bitmap thumbImg;
                if (bitmap == null){
                    thumbImg = BitmapUtils.getBitmapFromAssets(getActivity(),MainActivity.pictureName,100,100);
                }else {
                    thumbImg = Bitmap.createScaledBitmap(bitmap,100,100,false);
                }

                if (thumbImg == null){
                    return;
                }

                ThumbnailsManager.clearThumbs();
                thumbnailItemList.clear();

                //Add normal bitmap first
                ThumbnailItem thumbnailItem = new ThumbnailItem();
                thumbnailItem.image = thumbImg;
                thumbnailItem.filterName = "Normal";
                ThumbnailsManager.addThumb(thumbnailItem);

                List<Filter> filters = FilterPack.getFilterPack(getActivity());

                for (Filter filter : filters){
                    ThumbnailItem tI = new ThumbnailItem();
                    tI.image = thumbImg;
                    tI.filter = filter;
                    tI.filterName = filter.getName();
                    ThumbnailsManager.addThumb(tI);
                }

                thumbnailItemList.addAll(ThumbnailsManager.processThumbs(getActivity()));

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        thumbnailAdapter.notifyDataSetChanged();
                    }
                });
            }
        };
        new Thread(r).start();

    }

    @Override
    public void onFilterSelected(Filter filter) {
        if (listener!=null){
            listener.onFilterSelected(filter);
        }
    }
}