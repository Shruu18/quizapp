public class User {

    private int userId;
    private String name;
    private String department;

    public User(int userId, String name, String department) {
        this.userId = userId;
        this.name = name;
        this.department = department;
    }

    // Getter methods
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    // Optional: Setters (if needed later)
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}