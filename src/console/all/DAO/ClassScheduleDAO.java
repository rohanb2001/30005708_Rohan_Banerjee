package console.all.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import console.dbConnection.DBConnection;
import console.schemas.ClassSchedule;

public class ClassScheduleDAO {
	public void addClassSchedule(ClassSchedule schedule) throws SQLException {
        String query = "INSERT INTO ClassSchedule (class_name, trainer_id, day_of_week, start_time, end_time) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, schedule.getClassName());
            stmt.setInt(2, schedule.getTrainerId());
            stmt.setString(3, schedule.getDayOfWeek());
            stmt.setString(4, schedule.getStartTime());
            stmt.setString(5, schedule.getEndTime());
            stmt.executeUpdate();
        }
    }

    public ClassSchedule getClassSchedule(int scheduleId) throws SQLException {
        String query = "SELECT * FROM ClassSchedule WHERE schedule_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, scheduleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ClassSchedule schedule = new ClassSchedule();
                schedule.setScheduleId(rs.getInt("schedule_id"));
                schedule.setClassName(rs.getString("class_name"));
                schedule.setTrainerId(rs.getInt("trainer_id"));
                schedule.setDayOfWeek(rs.getString("day_of_week"));
                schedule.setStartTime(rs.getString("start_time"));
                schedule.setEndTime(rs.getString("end_time"));
                return schedule;
            }
        }
        return null;
    }

    public void updateClassSchedule(ClassSchedule schedule) throws SQLException {
        String query = "UPDATE ClassSchedule SET class_name = ?, trainer_id = ?, day_of_week = ?, start_time = ?, end_time = ? WHERE schedule_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, schedule.getClassName());
            stmt.setInt(2, schedule.getTrainerId());
            stmt.setString(3, schedule.getDayOfWeek());
            stmt.setString(4, schedule.getStartTime());
            stmt.setString(5, schedule.getEndTime());
            stmt.setInt(6, schedule.getScheduleId());
            stmt.executeUpdate();
        }
    }

    public void deleteClassSchedule(int scheduleId) throws SQLException {
        String query = "DELETE FROM ClassSchedule WHERE schedule_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, scheduleId);
            stmt.executeUpdate();
        }
    }
    
    public List<ClassSchedule> getAllClassSchedules() throws SQLException {
        String query = "SELECT * FROM ClassSchedule";
        List<ClassSchedule> schedules = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ClassSchedule schedule = new ClassSchedule();
                schedule.setScheduleId(rs.getInt("schedule_id"));
                schedule.setClassName(rs.getString("class_name"));
                schedule.setTrainerId(rs.getInt("trainer_id"));
                schedule.setDayOfWeek(rs.getString("day_of_week"));
                schedule.setStartTime(rs.getString("start_time"));
                schedule.setEndTime(rs.getString("end_time"));
                schedules.add(schedule);
            }
        }
        return schedules;
    }
}
