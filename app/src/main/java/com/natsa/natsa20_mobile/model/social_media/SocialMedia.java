package com.natsa.natsa20_mobile.model.social_media;

public class SocialMedia {
        Integer id;
        String sosmed;
        String icon_path;
        String created_at;
        String updated_at;

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getSosmed() {
                return sosmed;
        }

        public void setSosmed(String sosmed) {
                this.sosmed = sosmed;
        }

        public String getIcon_path() {
                return icon_path;
        }

        public void setIcon_path(String icon_path) {
                this.icon_path = icon_path;
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
}
