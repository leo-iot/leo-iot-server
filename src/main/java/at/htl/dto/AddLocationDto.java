package at.htl.dto;

public class AddLocationDto {
    public String name;
    public Long parentLocationId;

    public AddLocationDto(String name, Long parentLocationId) {
        this.name = name;
        this.parentLocationId = parentLocationId;
    }

    public AddLocationDto(String name) {
        this.name = name;
    }

    public AddLocationDto() {
    }
}
