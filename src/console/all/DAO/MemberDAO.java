package console.all.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import console.dbConnection.DBConnection;
import console.schemas.Member;

public class MemberDAO {
	public void addMember(Member member) throws SQLException {
        String query = "INSERT INTO Member (name, contact_number, email, membership_type) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getContactNumber());
            stmt.setString(3, member.getEmail());
            stmt.setString(4, member.getMembershipType());
            stmt.executeUpdate();
        }
    }

    public Member getMember(int memberId) throws SQLException {
        String query = "SELECT * FROM Member WHERE member_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setContactNumber(rs.getString("contact_number"));
                member.setEmail(rs.getString("email"));
                member.setMembershipType(rs.getString("membership_type"));
                return member;
            }
        }
        return null;
    }

    public void updateMember(Member member) throws SQLException {
        String query = "UPDATE Member SET name = ?, contact_number = ?, email = ?, membership_type = ? WHERE member_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getContactNumber());
            stmt.setString(3, member.getEmail());
            stmt.setString(4, member.getMembershipType());
            stmt.setInt(5, member.getMemberId());
            stmt.executeUpdate();
        }
    }

    public void deleteMember(int memberId) throws SQLException {
        String query = "DELETE FROM Member WHERE member_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberId);
            stmt.executeUpdate();
        }
    }
    
    public List<Member> getAllMembers() throws SQLException {
        String query = "SELECT * FROM Member";
        List<Member> members = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setContactNumber(rs.getString("contact_number"));
                member.setEmail(rs.getString("email"));
                member.setMembershipType(rs.getString("membership_type"));
                members.add(member);
            }
        }
        return members;
    }
}
