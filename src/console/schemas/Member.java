package console.schemas;

public class Member {
	private int memberId;
    private String name;
    private String contactNumber;
    private String email;
    private String membershipType;

    // Getters
    public int getMemberId() {
        return memberId;
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

    public String getMembershipType() {
        return membershipType;
    }

    // Setters
    public void setMemberId(int memberId) {
        this.memberId = memberId;
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

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }
}
