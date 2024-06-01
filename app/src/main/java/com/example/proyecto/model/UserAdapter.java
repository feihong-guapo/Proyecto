package com.example.proyecto.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.proyecto.R;
import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsuarioViewHolder> {
    private Context context;
    private List<User> listaUsuarios;
    private List<User> fullListaUsuarios;
    private OnUserClickListener listener;

    public UserAdapter(Context context, List<User> listaUsuarios, OnUserClickListener listener) {
        this.context = context;
        this.listaUsuarios = new ArrayList<>(listaUsuarios);
        this.fullListaUsuarios = new ArrayList<>(listaUsuarios);
        this.listener = listener;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuarios, parent, false);
        return new UsuarioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        User usuario = listaUsuarios.get(position);
        holder.nombreTextView.setText(usuario.getNombre());
        if (usuario.getRutaImg() != null && !usuario.getRutaImg().isEmpty()) {
            Glide.with(context).load(usuario.getRutaImg()).into(holder.imagenPerfil);
        }
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;
        ImageView imagenPerfil;

        public UsuarioViewHolder(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreInicio);
            imagenPerfil = itemView.findViewById(R.id.fotoPerfilInicio); // Assuming images are used

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onUserClicked(listaUsuarios.get(position));
                }
            });
        }
    }

    public interface OnUserClickListener {
        void onUserClicked(User user);
    }
    public void setListaUsuarios(List<User> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
        notifyDataSetChanged();
    }
    public void filter(String text) {
        listaUsuarios.clear();
        if (text.isEmpty()) {
            listaUsuarios.addAll(fullListaUsuarios);
        } else {
            text = text.toLowerCase();
            for (User user : fullListaUsuarios) {
                if (user.getNombre().toLowerCase().contains(text)) {
                    listaUsuarios.add(user);
                }
            }
        }
        notifyDataSetChanged();
    }
}
