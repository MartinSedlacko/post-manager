package com.example.assignment.model.external.jsonplaceholder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonPlaceholderAddress {

        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private JsonPlaceholderGeo geo;
}
