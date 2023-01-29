package com.example.web.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "additional_user_info")
public class AdditionalInfoEntity extends BaseEntity {

    @Column(name = "id_front_picture_path", nullable = false)
    private String idFrontPicturePath;

    @Column(name = "id_back_picture_path", nullable = false)
    private String idBackPicturePath;

    @Column(name = "license_document_path", nullable = false)
    private String licenseDocumentPath;

    @Column(name = "credential_number", nullable = false, unique = true)
    private String credentialNumber;

    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @Column(name = "unique_company_identifier", nullable = false, unique = true)
    private String uniqueCompanyIdentifier;

    //TODO should I set names in that class or different one
}
