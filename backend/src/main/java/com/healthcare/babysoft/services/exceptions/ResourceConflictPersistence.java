package com.healthcare.babysoft.services.exceptions;

import javax.persistence.EntityExistsException;
import java.io.Serializable;

public class ResourceConflictPersistence extends EntityExistsException {
    private static final long serialVersionUID = 1L;

    String cpf;
    public ResourceConflictPersistence(String msg) {
        super(msg);
    }
}
