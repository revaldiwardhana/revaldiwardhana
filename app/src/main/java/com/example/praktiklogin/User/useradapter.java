package com.example.praktiklogin.User;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.praktiklogin.Model.Modeluser;
import com.example.praktiklogin.R;

import java.util.List;

public class useradapter extends RecyclerView.Adapter<useradapter.UserViewHolder>{
    private static final String TAG = useradapter.class.getSimpleName();
    private List<Modeluser> listUsers;
    public useradapter(List<Modeluser> listUsers) {
        this.listUsers = listUsers;
    }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_user, parent, false);
        return new UserViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(listUsers.get(position));
    }
    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView daftarnama;
        private TextView daftaralamat;
        private TextView daftarnohp;
        public UserViewHolder(View view) {
            super(view);
            daftarnama = view.findViewById(R.id.daftarnama);
            daftaralamat = view.findViewById(R.id.daftaralamat);
            daftarnohp = view.findViewById(R.id.daftarnohp);
        }

        public void bind(Modeluser modelUser) {
            daftarnama.setText(modelUser.getUsername());
            daftaralamat.setText(modelUser.getAlamat());
            daftarnohp.setText(modelUser.getNomor());
        }
    }
}
