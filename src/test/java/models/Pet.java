package models;

public class Pet {
    private long id;
    private String name;
    private String status;

    
    public Pet() {}

    
    public Pet(String name, String status) {
        this.name = name;
        this.status = status;
    }

   
    public long getId() {
        return id;
    }

    public void setId(long  id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
