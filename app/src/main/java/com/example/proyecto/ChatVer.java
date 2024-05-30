package com.example.proyecto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.example.proyecto.model.AdapterMensajes;
import com.example.proyecto.model.MensajeEnviar;
import com.example.proyecto.model.MensajeRecibir;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
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
    private static final int PHOTO_SEND = 1;
    private static final int PHOTO_PERFIL = 2;
    private String fotoPerfilCadena;

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

        // Retrieve passed arguments from the Activity or previous Fragment
        Bundle args = getArguments();
        String nombreUsuario = args != null ? args.getString("nombreUsuario") : "Unknown";
        String imageUrl = args != null ? args.getString("imageUrl") : "";

        nombre.setText(nombreUsuario);
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(this).load(imageUrl).into(fotoPerfil);
            fotoPerfilCadena = imageUrl;
        } else {
            // Set a default or placeholder image if no imageUrl is provided
            Glide.with(this).load(R.drawable.user_circle).into(fotoPerfil); // Ensure you have a default_profile drawable
        }

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("/");//Sala de chat (nombre)
        storage = FirebaseStorage.getInstance();

        adapter = new AdapterMensajes(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMensajes.setLayoutManager(layoutManager);
        rvMensajes.setAdapter(adapter);

        setupButtons();

        databaseReference.addChildEventListener(new ChildEventListener() {
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
        });

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
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Selecciona una foto"), requestCode);
    }

    private void setScrollbar() {
        rvMensajes.scrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                handleImageUpload(requestCode, uri);
            }
        }
    }

    private void handleImageUpload(int requestCode, Uri uri) {
        StorageReference photoRef = storageReference.child(uri.getLastPathSegment());
        photoRef.putFile(uri).addOnSuccessListener(taskSnapshot -> taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(uriResult -> {
            String imageUrl = uriResult.toString();
            if (requestCode == PHOTO_SEND) {
                sendMessage("Sent a photo", imageUrl, "2");
            } else if (requestCode == PHOTO_PERFIL) {
                updateProfileImage(imageUrl);
            }
        }));
    }

    private void sendMessage(String text, String imageUrl, String messageType) {
        MensajeEnviar message = new MensajeEnviar(text, imageUrl, nombre.getText().toString(), fotoPerfilCadena, messageType, ServerValue.TIMESTAMP);
        databaseReference.push().setValue(message);
    }

    private void updateProfileImage(String imageUrl) {
        fotoPerfilCadena = imageUrl;
        Glide.with(this).load(imageUrl).into(fotoPerfil);
        sendMessage("Updated profile picture", imageUrl, "2");
    }
}