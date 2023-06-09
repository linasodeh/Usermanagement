package com.example.usermanagement;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayListFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Movement> movementArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    //ProgressDialog progressDialog;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlayListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayListFragment newInstance(String param1, String param2) {
        PlayListFragment fragment = new PlayListFragment();
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
        return inflater.inflate(R.layout.fragment_play_list, container, false);
    }

        @Override
        public void onStart() {
            super.onStart();
            //progressDialog = new ProgressDialog(getActivity());
            //progressDialog.setCancelable(false);
            //progressDialog.setMessage("Fetching Data....");
            //progressDialog.show();
            recyclerView = getView().findViewById(R.id.rvMovementsPlayList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

            db = FirebaseFirestore.getInstance();
            movementArrayList = new ArrayList<Movement>();
            myAdapter = new MyAdapter(this.getActivity(), movementArrayList);
            recyclerView.setAdapter(myAdapter);

            EventChangeListener();
        }

    private void EventChangeListener() {

        db.collection("movements").orderBy("type", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error!=null){
                            //if(progressDialog.isShowing())
                                //progressDialog.dismiss();
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges())
                        {
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                movementArrayList.add(dc.getDocument().toObject(Movement.class));
                            }
                            flipData(movementArrayList);
                            myAdapter.notifyDataSetChanged();
                        }

                        recyclerView.setAdapter(myAdapter);


                    }
                });
    }

    public void flipData(ArrayList<Movement> movements)
    {
        int size = movementArrayList.size();
        for (int i = 0; i < size / 2; i++) {
            Movement temp = movementArrayList.get(i);
            movementArrayList.set(i, movementArrayList.get(size - i - 1));
            movementArrayList.set(size - i - 1, temp);
        }
    }
}