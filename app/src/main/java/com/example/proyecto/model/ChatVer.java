package com.example.proyecto.model;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyecto.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatVer extends Fragment {

    private CircleImageView fotoPerfil;
    private TextView nombre;
    private RecyclerView rvMensajes;
    private EditText txtMensaje;
    private Button btnEnviar;
    private AdapterMensajes adapter;
    private ImageButton btnEnviarFoto;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private ChildEventListener childEventListener;
    private static final int PHOTO_SEND = 1;
    private static final int PHOTO_PERFIL = 2;
    private String fotoPerfilCadena;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_ver, container, false);

        fotoPerfil = view.findViewById(R.id.fotoPerfil);
        nombre = view.findViewById(R.id.nombre);
        rvMensajes = view.findViewById(R.id.rvMensajes);
        txtMensaje = view.findViewById(R.id.txtMensaje);
        btnEnviar = view.findViewById(R.id.btnEnviar);
        btnEnviarFoto = view.findViewById(R.id.btnEnviarFoto);
        fotoPerfilCadena = "";

        // Obtiene el ID del admin del Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            user = (User) bundle.getSerializable("usuario");
        }
        String adminId = user != null ? user.getAdminId() : "";
        Log.d("Oskitar", "ID del admin: " + adminId);
        String userId = user != null ? String.valueOf(user.getUser_id()) : "";
        Log.d("Oskitar", "ID del usuario: " + userId);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chats").child("chat1").child("messages");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("chat_images");

        adapter = new AdapterMensajes(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMensajes.setLayoutManager(layoutManager);
        rvMensajes.setAdapter(adapter);

        // Realiza una consulta para encontrar la conversación que contiene al usuario y al admin correspondiente
        DatabaseReference chatsRef = FirebaseDatabase.getInstance().getReference("chats");
        Query query = chatsRef.orderByChild("participants/user" + userId).equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot chatSnapshot : dataSnapshot.getChildren()) {
                    if (chatSnapshot.child("participants/admin" + adminId).getValue(Boolean.class) != null) {
                        DatabaseReference chatsRef = FirebaseDatabase.getInstance().getReference("chats");
                        String chatId = "chat" + userId + "_" + adminId;
                        // Actualiza la referencia de la base de datos para apuntar a los mensajes de la conversación
                        databaseReference = database.getReference("chats").child(chatId).child("messages");
                        // Carga los mensajes de esta conversación
                        attachDatabaseReadListener();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Maneja errores aquí
            }
        });

        setupButtons();

        return view;
    }

    private void setupButtons() {
        btnEnviar.setOnClickListener(view -> {
            String messageText = txtMensaje.getText().toString().trim();
            if (!messageText.isEmpty() && messageText.length() <= 140) {
                MensajeEnviar message = new MensajeEnviar(messageText, nombre.getText().toString(), fotoPerfilCadena, "1", ServerValue.TIMESTAMP);
                databaseReference.push().setValue(message);
                txtMensaje.setText("");
            }
        });

        btnEnviarFoto.setOnClickListener(view -> selectImage(PHOTO_SEND));
        fotoPerfil.setOnClickListener(view -> selectImage(PHOTO_PERFIL));
    }

    private void selectImage(int requestCode) {
        // Implementa la lógica para seleccionar una imagen
    }

    private void attachDatabaseReadListener() {
        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    MensajeRecibir message = dataSnapshot.getValue(MensajeRecibir.class);
                    adapter.addMensaje(message);
                    setScrollbar();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {}

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
            databaseReference.addChildEventListener(childEventListener);
        }
    }

    private void detachDatabaseReadListener() {
        if (childEventListener != null) {
            databaseReference.removeEventListener(childEventListener);
            childEventListener = null;
        }
    }

    private void setScrollbar() {
        rvMensajes.scrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachDatabaseReadListener();
    }
}
