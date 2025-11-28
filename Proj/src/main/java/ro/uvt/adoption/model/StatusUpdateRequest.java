package ro.uvt.adoption.model;

import jakarta.validation.constraints.NotNull;

public class StatusUpdateRequest {
    @NotNull
    private AnimalStatus status;

    public AnimalStatus getStatus() {
        return status;
    }

    public void setStatus(AnimalStatus status) {
        this.status = status;
    }
}
