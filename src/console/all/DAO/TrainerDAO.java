package console.all.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import console.dbConnection.DBConnection;
import console.schemas.Trainer;

public class TrainerDAO {
	 public void addTrainer(Trainer trainer) throws SQLException {
	        String query = "INSERT INTO Trainer (name, contact_number, email, speciality) VALUES (?, ?, ?, ?)";
	        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, trainer.getName());
	            stmt.setString(2, trainer.getContactNumber());
	            stmt.setString(3, trainer.getEmail());
	            stmt.setString(4, trainer.getSpeciality());
	            stmt.executeUpdate();
	        }
	    }

	    public Trainer getTrainer(int trainerId) throws SQLException {
	        String query = "SELECT * FROM Trainer WHERE trainer_id = ?";
	        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setInt(1, trainerId);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                Trainer trainer = new Trainer();
	                trainer.setTrainerId(rs.getInt("trainer_id"));
	                trainer.setName(rs.getString("name"));
	                trainer.setContactNumber(rs.getString("contact_number"));
	                trainer.setEmail(rs.getString("email"));
	                trainer.setSpeciality(rs.getString("speciality"));
	                return trainer;
	            }
	        }
	        return null;
	    }

	    public void updateTrainer(Trainer trainer) throws SQLException {
	        String query = "UPDATE Trainer SET name = ?, contact_number = ?, email = ?, speciality = ? WHERE trainer_id = ?";
	        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, trainer.getName());
	            stmt.setString(2, trainer.getContactNumber());
	            stmt.setString(3, trainer.getEmail());
	            stmt.setString(4, trainer.getSpeciality());
	            stmt.setInt(5, trainer.getTrainerId());
	            stmt.executeUpdate();
	        }
	    }

	    public void deleteTrainer(int trainerId) throws SQLException {
	        String query = "DELETE FROM Trainer WHERE trainer_id = ?";
	        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setInt(1, trainerId);
	            stmt.executeUpdate();
	        }
	    }
	    
	    public List<Trainer> getAllTrainers() throws SQLException {
	        String query = "SELECT * FROM Trainer";
	        List<Trainer> trainers = new ArrayList<>();
	        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                Trainer trainer = new Trainer();
	                trainer.setTrainerId(rs.getInt("trainer_id"));
	                trainer.setName(rs.getString("name"));
	                trainer.setContactNumber(rs.getString("contact_number"));
	                trainer.setEmail(rs.getString("email"));
	                trainer.setSpeciality(rs.getString("speciality"));
	                trainers.add(trainer);
	            }
	        }
	        return trainers;
	    }
}
