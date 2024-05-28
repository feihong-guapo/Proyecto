package com.example.proyecto;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.model.ChatVer;
import com.example.proyecto.model.User;
import com.example.proyecto.model.UserAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment implements UserAdapter.OnUserClickListener {
    private RecyclerView recyclerView;
    private UserAdapter usuarioAdapter;
    private List<User> listaUsuarios;
    private List<User> fullListaUsuarios; // Full list for filtering

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        listaUsuarios = new ArrayList<>();
        fullListaUsuarios = new ArrayList<>();
        addFakeUsers();

        recyclerView = view.findViewById(R.id.chatRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        usuarioAdapter = new UserAdapter(getContext(), listaUsuarios, this);
        recyclerView.setAdapter(usuarioAdapter);

        FloatingActionButton fab = view.findViewById(R.id.fab_new_chat);
        fab.setOnClickListener(view1 -> showSearchDialog());

        return view;
    }

    private void addFakeUsers() {
        // Add users to the list
        listaUsuarios.add(new User("Sophia Martinez", "https://example.com/image1.png"));
        // Add more users...
        fullListaUsuarios.addAll(listaUsuarios); // Keep a full copy for filtering
    }

    @Override
    public void onUserClicked(User user) {
        ChatVer chatDetailFragment = new ChatVer();
        Bundle args = new Bundle();
        args.putString("userId", Integer.toString(0));  // Assuming User has an ID field or similar
        chatDetailFragment.setArguments(args);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragment, chatDetailFragment)  // Assuming there is a container in your layout
                .addToBackStack(null)  // Adds the transaction to the back stack
                .commit();
    }

    private void showSearchDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialogs_search, null);
        EditText searchEditText = dialogView.findViewById(R.id.searchEditText);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                usuarioAdapter.filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        dialog.show();
    }
}
