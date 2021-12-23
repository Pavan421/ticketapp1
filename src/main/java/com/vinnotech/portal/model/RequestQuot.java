package com.vinnotech.portal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RequestQuot {
    @Id
    @Column(name = "id")
    private Long id;

    private String serviceName;
    private String name;
    private String email;
    private String phoneNumber;
    private String phoneExtension;
    private String subject;
    private String message;
}
