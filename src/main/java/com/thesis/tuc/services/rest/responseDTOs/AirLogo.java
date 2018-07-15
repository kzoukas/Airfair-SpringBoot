package com.thesis.tuc.services.rest.responseDTOs;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

    @Document(collection = "airlogos")
    public class AirLogo {
        @Id
        private String id;
        private String companyName;
        private String airLogoSrc;


        public AirLogo(String id, String companyName,
                           String airLogoSrc ){

            this.companyName = companyName;
            this.airLogoSrc = airLogoSrc;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getAirLogoSrc() {
            return airLogoSrc;
        }

        public void setAirLogoSrc(String airLogoSrc) {
            this.airLogoSrc = airLogoSrc;
        }
    }
