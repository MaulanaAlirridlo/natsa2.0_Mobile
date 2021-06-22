package com.natsa.natsa20_mobile.model.auth.login;

public class LoginRespone {
    private User user;
    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public class User {
        private int id;
        private String name;
        private String email;
        private String email_verified_at;
        private int current_team_id;
        private String profile_photo_path;
        private String created_at;
        private String updated_at;
        private String role;
        private String username;
        private String ktp;
        private String profile_photo_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail_verified_at() {
            return email_verified_at;
        }

        public void setEmail_verified_at(String email_verified_at) {
            this.email_verified_at = email_verified_at;
        }

        public int getCurrent_team_id() {
            return current_team_id;
        }

        public void setCurrent_team_id(int current_team_id) {
            this.current_team_id = current_team_id;
        }

        public String getProfile_photo_path() {
            return profile_photo_path;
        }

        public void setProfile_photo_path(String profile_photo_path) {
            this.profile_photo_path = profile_photo_path;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getKtp() {
            return ktp;
        }

        public void setKtp(String ktp) {
            this.ktp = ktp;
        }

        public String getProfile_photo_url() {
            return profile_photo_url;
        }

        public void setProfile_photo_url(String profile_photo_url) {
            this.profile_photo_url = profile_photo_url;
        }
    }
}
