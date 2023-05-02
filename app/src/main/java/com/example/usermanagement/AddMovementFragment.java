package com.example.usermanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddMovementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMovementFragment extends Fragment {
    private EditText etType,etTime;
    private Button btnAdd;
    private FirebaseSarvices fbs;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddMovementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMovementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMovementFragment newInstance(String param1, String param2) {
        AddMovementFragment fragment = new AddMovementFragment();
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
        return inflater.inflate(R.layout.fragment_add_movement, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    private void init() {
        fbs=FirebaseSarvices.getInstance();
        etType=getView().findViewById(R.id.etTypeAddFragment);
        etTime=getView().findViewById(R.id.etTimeAddFragment);
        btnAdd=getView().findViewById(R.id.btnAddAddFragment);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFirestore();
            }
        });

    }

    private void addToFirestore() {
        String type,time;
        type=etType.getText().toString();
        time=etTime.getText().toString();

        if (type.trim().isEmpty() || time.trim().isEmpty() )
        {
            Toast.makeText(getActivity(), "some  data missing or incorrect!", Toast.LENGTH_SHORT).show();
            return;
        }
        int time1 = Integer.parseInt(time);

        Movement movement=new Movement(type,time1);

        fbs.getFire().collection("movements").add(movement).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // TODO: goto playlist fragment

                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frameLayoutMain,new PlayListFragment());
                    ft.commit();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
}