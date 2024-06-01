package com.example.proyecto;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.model.ChatVer;
import com.example.proyecto.model.User;
import com.example.proyecto.model.UserAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment implements UserAdapter.OnUserClickListener {
    private RecyclerView recyclerView;
    private UserAdapter usuarioAdapter;
    private List<User> listaUsuarios;
    private List<User> fullListaUsuarios; // Full list for filtering

    private DatabaseReference usersRef;
    private String currentUserId;
    private User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            user = (User) bundle.getSerializable("usuario");
        }
        listaUsuarios = new ArrayList<>();
        fullListaUsuarios = new ArrayList<>();

        recyclerView = view.findViewById(R.id.chatRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        usuarioAdapter = new UserAdapter(getContext(), listaUsuarios, this);
        recyclerView.setAdapter(usuarioAdapter);

        FloatingActionButton fab = view.findViewById(R.id.fab_new_chat);
        fab.setOnClickListener(view1 -> showSearchDialog());
        usersRef = FirebaseDatabase.getInstance().getReference("chats");
        currentUserId = String.valueOf(user.getUser_id());
        if (currentUserId != null) {
            loadChatsForCurrentUser();
        }
        return view;
    }


    private void loadChatsForCurrentUser() {
        Query query = usersRef.orderByChild("participants/user" + user.getUser_id()).equalTo(true);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaUsuarios.clear();
                for (DataSnapshot chatSnapshot : dataSnapshot.getChildren()) {
                    // Iterar sobre cada chat
                    String chatId = chatSnapshot.getKey();
                    // Obtener los participantes de la conversación
                    DataSnapshot participantsSnapshot = chatSnapshot.child("participants");
                    for (DataSnapshot participantSnapshot : participantsSnapshot.getChildren()) {
                        String participantKey = participantSnapshot.getKey();
                        // Verificar si el participante es un admin (comienza con "admin")
                        if (participantKey.startsWith("admin")) {
                            // Obtener el ID del admin
                            String adminId = participantKey.substring(5); // Excluir "admin" del ID
                            // Crear un usuario con el ID del admin y una imagen de perfil predeterminada
                            User adminUser = new User(adminId, "https://example.com/image1.png"); // Reemplaza "" con la URL de la imagen del perfil del admin si está disponible
                            listaUsuarios.add(adminUser);
                        }
                    }
                }
                usuarioAdapter.notifyDataSetChanged();

                // Agregar mensajes de registro para verificar los datos recuperados
                Log.d("ChatFragment", "Número de conversaciones cargadas: " + listaUsuarios.size());
                for (User user : listaUsuarios) {
                    Log.d("ChatFragment", "ID del admin: " + user.getNombre());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Maneja errores aquí
            }
        });
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
