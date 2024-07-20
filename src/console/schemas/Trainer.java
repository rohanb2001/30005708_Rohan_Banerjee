package console.schemas;

public class Trainer {
	private int trainerId;
    private String name;
    private String contactNumber;
    private String email;
    private String speciality;

    // Getters
    public int getTrainerId() {
        return trainerId;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getSpeciality() {
        return speciality;
    }

    // Setters
    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
