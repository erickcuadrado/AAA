package com.example.cuadrado.frontend.api.a.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cuadrado on 15/11/2017.
 */

public class Post {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("hash_password")
        @Expose
        private String hashPassword;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("addres")
        @Expose
        private String addres;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("url")
        @Expose
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHashPassword() {
            return hashPassword;
        }

        public void setHashPassword(String hashPassword) {
            this.hashPassword = hashPassword;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddres() {
            return addres;
        }

        public void setAddres(String addres) {
            this.addres = addres;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }
