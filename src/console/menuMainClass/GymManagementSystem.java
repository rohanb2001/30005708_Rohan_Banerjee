package console.menuMainClass;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import console.all.DAO.ClassScheduleDAO;
import console.all.DAO.MemberDAO;
import console.all.DAO.TrainerDAO;
import console.schemas.ClassSchedule;
import console.schemas.Member;
import console.schemas.Trainer;

public class GymManagementSystem {
//	Initial Step ->
//	Create your own database schemas for Member , Trainer and Class-Schedule
//	Then check these codes and run accordingly.
	
    private static Scanner scanner = new Scanner(System.in);
    private static MemberDAO memberDAO = new MemberDAO();
    private static TrainerDAO trainerDAO = new TrainerDAO();
    private static ClassScheduleDAO classScheduleDAO = new ClassScheduleDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Member Management");
            System.out.println("2. Trainer Management");
            System.out.println("3. Class Schedule Management");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    memberManagement();
                    break;
                case 2:
                    trainerManagement();
                    break;
                case 3:
                    classScheduleManagement();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void memberManagement() {
        System.out.println("1. Register new member");
        System.out.println("2. View member details");
        System.out.println("3. Update member information");
        System.out.println("4. Delete member");
        System.out.println("5. Display all members");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                registerMember();
                break;
            case 2:
                viewMemberDetails();
                break;
            case 3:
                updateMember();
                break;
            case 4:
                deleteMember();
                break;
            case 5:
                displayAllMembers();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void registerMember() {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter contact number: ");
        String contactNumber = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter membership type: ");
        String membershipType = scanner.next();
        Member member = new Member();
        member.setName(name);
        member.setContactNumber(contactNumber);
        member.setEmail(email);
        member.setMembershipType(membershipType);
        try {
            memberDAO.addMember(member);
            System.out.println("Member registered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewMemberDetails() {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        try {
            Member member = memberDAO.getMember(memberId);
            if (member != null) {
                System.out.println("Name: " + member.getName());
                System.out.println("Contact Number: " + member.getContactNumber());
                System.out.println("Email: " + member.getEmail());
                System.out.println("Membership Type: " + member.getMembershipType());
            } else {
                System.out.println("Member not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateMember() {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        try {
            Member member = memberDAO.getMember(memberId);
            if (member != null) {
                System.out.print("Enter new name: ");
                String name = scanner.next();
                System.out.print("Enter new contact number: ");
                String contactNumber = scanner.next();
                System.out.print("Enter new email: ");
                String email = scanner.next();
                System.out.print("Enter new membership type: ");
                String membershipType = scanner.next();
                member.setName(name);
                member.setContactNumber(contactNumber);
                member.setEmail(email);
                member.setMembershipType(membershipType);
                memberDAO.updateMember(member);
                System.out.println("Member information updated successfully.");
            } else {
                System.out.println("Member not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteMember() {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        try {
            memberDAO.deleteMember(memberId);
            System.out.println("Member deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayAllMembers() {
        try {
            List<Member> members = memberDAO.getAllMembers();
            if (members.isEmpty()) {
                System.out.println("No members found.");
            } else {
                for (Member member : members) {
                    System.out.println("Member ID: " + member.getMemberId());
                    System.out.println("Name: " + member.getName());
                    System.out.println("Contact Number: " + member.getContactNumber());
                    System.out.println("Email: " + member.getEmail());
                    System.out.println("Membership Type: " + member.getMembershipType());
                    System.out.println("------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void trainerManagement() {
        System.out.println("1. Add new trainer");
        System.out.println("2. View trainer details");
        System.out.println("3. Update trainer information");
        System.out.println("4. Delete trainer");
        System.out.println("5. Display all trainers");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addTrainer();
                break;
            case 2:
                viewTrainerDetails();
                break;
            case 3:
                updateTrainer();
                break;
            case 4:
                deleteTrainer();
                break;
            case 5:
                displayAllTrainers();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void addTrainer() {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter contact number: ");
        String contactNumber = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter speciality: ");
        String speciality = scanner.next();
        Trainer trainer = new Trainer();
        trainer.setName(name);
        trainer.setContactNumber(contactNumber);
        trainer.setEmail(email);
        trainer.setSpeciality(speciality);
        try {
            trainerDAO.addTrainer(trainer);
            System.out.println("Trainer added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewTrainerDetails() {
        System.out.print("Enter trainer ID: ");
        int trainerId = scanner.nextInt();
        try {
            Trainer trainer = trainerDAO.getTrainer(trainerId);
            if (trainer != null) {
                System.out.println("Name: " + trainer.getName());
                System.out.println("Contact Number: " + trainer.getContactNumber());
                System.out.println("Email: " + trainer.getEmail());
                System.out.println("Speciality: " + trainer.getSpeciality());
            } else {
                System.out.println("Trainer not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateTrainer() {
        System.out.print("Enter trainer ID: ");
        int trainerId = scanner.nextInt();
        try {
            Trainer trainer = trainerDAO.getTrainer(trainerId);
            if (trainer != null) {
                System.out.print("Enter new name: ");
                String name = scanner.next();
                System.out.print("Enter new contact number: ");
                String contactNumber = scanner.next();
                System.out.print("Enter new email: ");
                String email = scanner.next();
                System.out.print("Enter new speciality: ");
                String speciality = scanner.next();
                trainer.setName(name);
                trainer.setContactNumber(contactNumber);
                trainer.setEmail(email);
                trainer.setSpeciality(speciality);
                trainerDAO.updateTrainer(trainer);
                System.out.println("Trainer information updated successfully.");
            } else {
                System.out.println("Trainer not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteTrainer() {
        System.out.print("Enter trainer ID: ");
        int trainerId = scanner.nextInt();
        try {
            trainerDAO.deleteTrainer(trainerId);
            System.out.println("Trainer deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayAllTrainers() {
        try {
            List<Trainer> trainers = trainerDAO.getAllTrainers();
            if (trainers.isEmpty()) {
                System.out.println("No trainers found.");
            } else {
                for (Trainer trainer : trainers) {
                    System.out.println("Trainer ID: " + trainer.getTrainerId());
                    System.out.println("Name: " + trainer.getName());
                    System.out.println("Contact Number: " + trainer.getContactNumber());
                    System.out.println("Email: " + trainer.getEmail());
                    System.out.println("Speciality: " + trainer.getSpeciality());
                    System.out.println("------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void classScheduleManagement() {
        System.out.println("1. Create new class schedule");
        System.out.println("2. View class schedules");
        System.out.println("3. Update class information");
        System.out.println("4. Cancel class");
        System.out.println("5. Display all class schedules");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                createClassSchedule();
                break;
            case 2:
                viewClassSchedules();
                break;
            case 3:
                updateClassSchedule();
                break;
            case 4:
                cancelClass();
                break;
            case 5:
                displayAllClassSchedules();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void createClassSchedule() {
        System.out.print("Enter class name: ");
        String className = scanner.next();
        System.out.print("Enter trainer ID: ");
        int trainerId = scanner.nextInt();
        System.out.print("Enter day of the week: ");
        String dayOfWeek = scanner.next();
        System.out.print("Enter start time (HH:MM:SS): ");
        String startTime = scanner.next();
        System.out.print("Enter end time (HH:MM:SS): ");
        String endTime = scanner.next();
        ClassSchedule schedule = new ClassSchedule();
        schedule.setClassName(className);
        schedule.setTrainerId(trainerId);
        schedule.setDayOfWeek(dayOfWeek);
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        try {
            classScheduleDAO.addClassSchedule(schedule);
            System.out.println("Class schedule created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewClassSchedules() {
        try {
            List<ClassSchedule> schedules = classScheduleDAO.getAllClassSchedules();
            if (schedules.isEmpty()) {
                System.out.println("No class schedules found.");
            } else {
                for (ClassSchedule schedule : schedules) {
                    System.out.println("Schedule ID: " + schedule.getScheduleId());
                    System.out.println("Class Name: " + schedule.getClassName());
                    System.out.println("Trainer ID: " + schedule.getTrainerId());
                    System.out.println("Day of the Week: " + schedule.getDayOfWeek());
                    System.out.println("Start Time: " + schedule.getStartTime());
                    System.out.println("End Time: " + schedule.getEndTime());
                    System.out.println("------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateClassSchedule() {
        System.out.print("Enter schedule ID: ");
        int scheduleId = scanner.nextInt();
        try {
            ClassSchedule schedule = classScheduleDAO.getClassSchedule(scheduleId);
            if (schedule != null) {
                System.out.print("Enter new class name: ");
                String className = scanner.next();
                System.out.print("Enter new trainer ID: ");
                int trainerId = scanner.nextInt();
                System.out.print("Enter new day of the week: ");
                String dayOfWeek = scanner.next();
                System.out.print("Enter new start time (HH:MM:SS): ");
                String startTime = scanner.next();
                System.out.print("Enter new end time (HH:MM:SS): ");
                String endTime = scanner.next();
                schedule.setClassName(className);
                schedule.setTrainerId(trainerId);
                schedule.setDayOfWeek(dayOfWeek);
                schedule.setStartTime(startTime);
                schedule.setEndTime(endTime);
                classScheduleDAO.updateClassSchedule(schedule);
                System.out.println("Class schedule updated successfully.");
            } else {
                System.out.println("Schedule not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void cancelClass() {
        System.out.print("Enter schedule ID: ");
        int scheduleId = scanner.nextInt();
        try {
            classScheduleDAO.deleteClassSchedule(scheduleId);
            System.out.println("Class schedule canceled successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayAllClassSchedules() {
        try {
            List<ClassSchedule> schedules = classScheduleDAO.getAllClassSchedules();
            if (schedules.isEmpty()) {
                System.out.println("No class schedules found.");
            } else {
                for (ClassSchedule schedule : schedules) {
                    System.out.println("Schedule ID: " + schedule.getScheduleId());
                    System.out.println("Class Name: " + schedule.getClassName());
                    System.out.println("Trainer ID: " + schedule.getTrainerId());
                    System.out.println("Day of the Week: " + schedule.getDayOfWeek());
                    System.out.println("Start Time: " + schedule.getStartTime());
                    System.out.println("End Time: " + schedule.getEndTime());
                    System.out.println("------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

