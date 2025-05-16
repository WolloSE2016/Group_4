
package exam.exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;

public class Exam extends JFrame {
    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin";
    private static final Path STUD_FILE = Paths.get("students.txt");
    private static final Path STAFF_FILE = Paths.get("staffs.txt");
    private static final Path Q_FILE = Paths.get("questions.txt");
    private static final Path RESULTS_FILE = Paths.get("results.txt");
    private static final Path EXAM_TAKEN_FILE = Paths.get("exam_taken.txt");

    private CardLayout cards = new CardLayout();
    private JPanel mainPanel = new JPanel(cards);
    private Vector<Question> questions = new Vector<>();
    private Map<String, String> answers = new HashMap<>();
    private int currentIndex = 0;
    private Instant startTime;
    private int timeLimit = 30;
    private String currentUser = "";
    private javax.swing.Timer examTimer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Exam().setVisible(true));
    }

    public Exam() {
        super("Exam System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        mainPanel.add(createLoginPanel(), "login");
        mainPanel.add(createAdminPanel(), "admin");
        mainPanel.add(createStaffPanel(), "staff");
        mainPanel.add(createStudentPanel(), "student");
        mainPanel.add(createExamPanel(), "exam");

        add(mainPanel);
        cards.show(mainPanel, "login");
    }

    private JPanel createLoginPanel() {
        JPanel p = new JPanel(null);
        JLabel userLbl = new JLabel("Username:"), passLbl = new JLabel("Password:"), roleLbl = new JLabel("Role:");
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JComboBox<String> roleBox = new JComboBox<>(new String[]{"student", "staff", "admin"});
        JButton loginBtn = new JButton("Login");

        userLbl.setBounds(100, 50, 80, 25);
        userField.setBounds(180, 50, 150, 25);
        passLbl.setBounds(100, 90, 80, 25);
        passField.setBounds(180, 90, 150, 25);
        roleLbl.setBounds(100, 130, 80, 25);
        roleBox.setBounds(180, 130, 150, 25);
        loginBtn.setBounds(200, 180, 100, 30);

        loginBtn.addActionListener(e -> {
            String u = userField.getText().trim();
            String pw = new String(passField.getPassword());
            String r = (String) roleBox.getSelectedItem();
            if (!authenticate(u, pw, r)) {
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            currentUser = u;
            switch (r) {
                case "admin": cards.show(mainPanel, "admin"); break;
                case "staff": cards.show(mainPanel, "staff"); break;
                default: cards.show(mainPanel, "student");
            }
        });

        p.add(userLbl); p.add(userField);
        p.add(passLbl); p.add(passField);
        p.add(roleLbl); p.add(roleBox);
        p.add(loginBtn);
        return p;
    }

   private JPanel createAdminPanel() {
    JPanel p = new JPanel(new GridLayout(0, 1, 5, 5));
    p.add(new JLabel("Admin Dashboard", SwingConstants.CENTER));

    JButton addBtn = new JButton("Add User");
    JButton delStudentBtn = new JButton("Delete Student");
    JButton delStaffBtn = new JButton("Delete Staff");
    JButton viewResBtn = new JButton("View Student Scores");
    JButton viewAllStudentsBtn = new JButton("View All Students");
    JButton viewAllStaffBtn = new JButton("View All Staff");
    JButton viewStudentPasswordsBtn = new JButton("View Student Passwords");
    JButton viewStaffPasswordsBtn = new JButton("View Staff Passwords");
    JButton logoutBtn = new JButton("Logout");

addBtn.addActionListener(a -> addUser());
    delStudentBtn.addActionListener(a -> deleteStudent());
    delStaffBtn.addActionListener(a -> deleteStaff());
    viewResBtn.addActionListener(a -> showList("Student Scores", formatResults()));
    viewAllStudentsBtn.addActionListener(a -> showList("All Students", formatStudentList()));
    viewAllStaffBtn.addActionListener(a -> showList("All Staff", formatStaffList()));
    viewStudentPasswordsBtn.addActionListener(a -> showList("Student Passwords", formatStudentPasswords()));
    viewStaffPasswordsBtn.addActionListener(a -> showList("Staff Passwords", formatStaffPasswords()));
    logoutBtn.addActionListener(a -> cards.show(mainPanel, "login"));

    p.add(addBtn);
    p.add(delStudentBtn);
    p.add(delStaffBtn);
    p.add(viewResBtn);
    p.add(viewAllStudentsBtn);
    p.add(viewAllStaffBtn);
    p.add(viewStudentPasswordsBtn);
    p.add(viewStaffPasswordsBtn);
    p.add(logoutBtn);
    return p;
}





   private JPanel createStaffPanel() {
    JPanel p = new JPanel(new GridLayout(0, 1, 5, 5));
    p.add(new JLabel("Staff Dashboard", SwingConstants.CENTER));

    JButton viewBtn = new JButton("View Student Scores");
    JButton timeBtn = new JButton("Set Exam Time");
    JButton qBtn = new JButton("Add Question");
    JButton delQBtn = new JButton("Delete Question");
    JButton openExamBtn = new JButton("Open Exam");
    JButton closeExamBtn = new JButton("Close Exam");
    JButton logoutBtn = new JButton("Logout");

    viewBtn.addActionListener(a -> showList("Student Scores", formatResults()));
    timeBtn.addActionListener(a -> {
        String t = JOptionPane.showInputDialog(this, "Time (minutes):");
        try { timeLimit = Integer.parseInt(t); } catch (Exception ignored) {}
    });
    qBtn.addActionListener(a -> addQuestions());
    delQBtn.addActionListener(a -> deleteQuestion());
    openExamBtn.addActionListener(a -> openExam());
    closeExamBtn.addActionListener(a -> closeExam());
    logoutBtn.addActionListener(a -> cards.show(mainPanel, "login"));

    p.add(viewBtn);
    p.add(timeBtn);
    p.add(qBtn);
    p.add(delQBtn);
    p.add(openExamBtn);
    p.add(closeExamBtn);
    p.add(logoutBtn);
    return p;
}

    private JPanel createStudentPanel() {
        JPanel p = new JPanel(new GridLayout(0,1,5,5));
        p.add(new JLabel("Student Dashboard", SwingConstants.CENTER));

        JButton takeBtn = new JButton("Take Exam");
        JButton viewBtn = new JButton("View Past Score");
        JButton logoutBtn = new JButton("Logout");

        takeBtn.addActionListener(a -> startStudentExam());
        viewBtn.addActionListener(a -> showPastScore());
        logoutBtn.addActionListener(a -> cards.show(mainPanel, "login"));

        p.add(takeBtn); p.add(viewBtn); p.add(logoutBtn);
        return p;
    }

    private JPanel createExamPanel() {
        JPanel p = new JPanel(null);
        JLabel qLbl = new JLabel();
        p.add(qLbl);
        qLbl.setBounds(50, 50, 500, 25);

        Vector<JRadioButton> opts = new Vector<>();
        ButtonGroup bg = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            JRadioButton rb = new JRadioButton();
            opts.add(rb);
            bg.add(rb);
            p.add(rb);
            rb.setBounds(50, 100 + i * 30, 500, 25);
        }

        JButton prev = new JButton("Prev"), next = new JButton("Next"), finish = new JButton("Finish");
        p.add(prev); p.add(next); p.add(finish);
        prev.setBounds(50, 300, 80, 30);
        next.setBounds(140, 300, 80, 30);
        finish.setBounds(230, 300, 80, 30);

        JLabel timeLbl = new JLabel(); p.add(timeLbl); timeLbl.setBounds(500, 10, 80, 25);


ActionListener nav = e -> {
            for (JRadioButton rb : opts)
                if (rb.isSelected()) answers.put(questions.get(currentIndex).id, rb.getText());
            Object s = e.getSource();
            if (s == prev && currentIndex > 0) currentIndex--;
            else if (s == next && currentIndex < questions.size() - 1) currentIndex++;
            else if (s == finish) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to finish the exam?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    stopExamTimer();
                    showReview();
                }
                return;
            }
            showQuestion();
        };
        prev.addActionListener(nav);
        next.addActionListener(nav);
        finish.addActionListener(nav);

        // TIMER WITH "TIME IS UP"
        examTimer = new javax.swing.Timer(1000, ev -> {
            long elapsed = Duration.between(startTime, Instant.now()).toMinutes();
            long remaining = timeLimit - elapsed;
            timeLbl.setText(remaining + "m");
            if (remaining <= 0) {
                stopExamTimer();
                JOptionPane.showMessageDialog(this, "Time is up!");
                showReview();
            }
        });

        return p;
    }

    private void stopExamTimer() {
        if (examTimer != null && examTimer.isRunning()) examTimer.stop();
    }

    private Vector<String> formatResults() {
        Vector<String> list = new Vector<>();
        for (String ln : readAll(RESULTS_FILE)) {
            String[] p = ln.split(",");
            if (p.length >= 2) list.add("Student: " + p[0] + " - Score: " + p[1] + "%");
        }
        return list;
    }

    private void startStudentExam() {
    if (readExamStatus().equals("closed")) {
        JOptionPane.showMessageDialog(this, "The exam is currently closed. Please wait for it to open.");
        return;
    }

    if (hasTakenExam(currentUser)) {
        JOptionPane.showMessageDialog(this, "You have already taken the exam.");
        return;
    }
    
    loadQuestions();
    startTime = Instant.now();
    currentIndex = 0;
    answers.clear();
    showQuestion();
    cards.show(mainPanel, "exam");
    examTimer.start();
}

    
    private Vector<String> formatStaffList() {
    Vector<String> list = new Vector<>();
    for (String ln : readAll(STAFF_FILE)) {
        String[] parts = ln.split(",");
        if (parts.length >= 1) list.add("Username: " + parts[0]);
    }
    return list;
}
     
    private static final Path EXAM_STATUS_FILE = Paths.get("exam_status.txt");

// Open the exam
private void openExam() {
    writeExamStatus("open");
    JOptionPane.showMessageDialog(this, "Exam is now open. Students can take the exam.");
}

// Close the exam
private void closeExam() {
    writeExamStatus("closed");
    JOptionPane.showMessageDialog(this, "Exam is now closed. Students can no longer take the exam.");
}

// Write the exam status to a file (open or closed)
private void writeExamStatus(String status) {
    try {
        Files.write(EXAM_STATUS_FILE, Collections.singletonList(status), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


// Read the exam status from the file
private String readExamStatus() {
    try {
        return Files.readAllLines(EXAM_STATUS_FILE).get(0);
    } catch (IOException | IndexOutOfBoundsException e) {
        return "closed"; // Default to closed if the file is missing or empty
    }
}
     
    private Vector<String> formatStudentPasswords() {
    Vector<String> list = new Vector<>();
    try {
        Files.lines(STUD_FILE).forEach(line -> {
            String[] data = line.split(",");
            if (data.length == 2) {
                list.add("Student: " + data[0] + " - Password: " + data[1]);
            }
        });
    } catch (IOException e) {
        e.printStackTrace();
    }
    return list;
}
    
    private Vector<String> formatStaffPasswords() {
    Vector<String> list = new Vector<>();
    try {
        Files.lines(STAFF_FILE).forEach(line -> {
            String[] data = line.split(",");
            if (data.length == 2) {
                list.add("Staff: " + data[0] + " - Password: " + data[1]);
            }
        });
    } catch (IOException e) {
        e.printStackTrace();
    }
    return list;
}

    
    private void deleteStaff() {
    // Read the list of staff from the file
    Vector<String> staffLines = readAll(STAFF_FILE);
    Vector<String> displayList = new Vector<>();

    // Display staff usernames
    for (int i = 0; i < staffLines.size(); i++) {
        String[] parts = staffLines.get(i).split(",");
        if (parts.length >= 1) {
            displayList.add((i + 1) + ". " + parts[0]); // Display username
        }
    }

    // Ask the admin to select a staff member to delete
    String selectedStaff = (String) JOptionPane.showInputDialog(this, "Select staff to delete:", "Delete Staff",
            JOptionPane.PLAIN_MESSAGE, null, displayList.toArray(), null);

    if (selectedStaff != null) {
        // Get the index of the staff to delete
        int selectedIndex = Integer.parseInt(selectedStaff.split("\\.")[0]) - 1;
        staffLines.remove(selectedIndex); // Remove the selected staff

        // Write the updated list back to the file
        writeAll(STAFF_FILE, staffLines);
        JOptionPane.showMessageDialog(this, "Staff member deleted.");
    }
}

    
    private Vector<String> formatStudentList() {
    Vector<String> list = new Vector<>();
    for (String ln : readAll(STUD_FILE)) {
        String[] parts = ln.split(",");
        if (parts.length >= 1) list.add("Username: " + parts[0]);
    }
    return list;
}
     
    
    
    private void showPastScore() {
        for (String ln : readAll(RESULTS_FILE)) {
            if (ln.startsWith(currentUser + ",")) {
                String[] p = ln.split(",");
                JOptionPane.showMessageDialog(this, "Your score: " + p[1] + "%");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "No score found.");
    }

    private void showList(String title, Vector<String> items) {
        JOptionPane.showMessageDialog(this, String.join("\n", items), title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteQuestion() {
        Vector<String> qLines = readAll(Q_FILE);
        Vector<String> display = new Vector<>();
        for (int i = 0; i < qLines.size(); i++) {
            String line = qLines.get(i);
            String[] parts = line.split("\\|");
            display.add((i+1) + ". " + parts[0]);
        }
        String sel = (String) JOptionPane.showInputDialog(this, "Select question to delete:", "Delete Question",
                JOptionPane.PLAIN_MESSAGE, null, display.toArray(), null);
        if (sel != null) {
            int idx = Integer.parseInt(sel.split("\\.")[0]) - 1;
            qLines.remove(idx);
            writeAll(Q_FILE, qLines);
            JOptionPane.showMessageDialog(this, "Question deleted.");
        }
    }


private void loadQuestions() {
        questions.clear();
        for (String ln : readAll(Q_FILE)) {
            questions.add(Question.fromLine(ln));
        }
    }
    
    
    
    private void showQuestion() {
        JPanel p = (JPanel) mainPanel.getComponent(4);
        JLabel lbl = (JLabel) p.getComponent(0);
        Question q = questions.get(currentIndex);
        lbl.setText((currentIndex + 1) + ". " + q.text);
        String[] os = q.options.split(",");
        for (int i = 0; i < 4; i++) {
            JRadioButton rb = (JRadioButton) p.getComponent(i + 1);
            rb.setText(i < os.length ? os[i] : "");
            rb.setSelected(q.answer.equals(answers.getOrDefault(q.id, "")));
        }
    }
     
    private void showReview() {
    int correct = 0;
    StringBuilder sb = new StringBuilder();
    boolean examClosed = readExamStatus().equals("closed") || Duration.between(startTime, Instant.now()).toMinutes() >= timeLimit;
    
    for (Question q : questions) {
        String a = answers.getOrDefault(q.id, "");
        if (q.answer.equals(a)) correct++;
        sb.append(q.text).append("\nYour: ").append(a).append(" | Ans: ")
          .append(examClosed ? q.answer : "Not revealed yet").append("\n\n");
    }
    
    int score = questions.isEmpty() ? 0 : (correct * 100 / questions.size());
    appendLine(RESULTS_FILE, currentUser + "," + score);
    appendLine(EXAM_TAKEN_FILE, currentUser);
    JOptionPane.showMessageDialog(this, sb + "Score: " + score + "%", "Review", JOptionPane.INFORMATION_MESSAGE);
    cards.show(mainPanel, "student");
}


    private boolean authenticate(String u, String p, String r) {
        if ("admin".equals(r)) return ADMIN_USER.equals(u) && ADMIN_PASS.equals(p);
        Path f = "staff".equals(r) ? STAFF_FILE : STUD_FILE;
        for (String ln : readAll(f)) {
            String[] a = ln.split(",");
            if (a[0].equals(u) && a[1].equals(p)) return true;
        }
        return false;
    }

    private boolean hasTakenExam(String u) {
        return readAll(EXAM_TAKEN_FILE).contains(u);
    }

    private void addUser() {
        String u = JOptionPane.showInputDialog(this, "Username:");
        String pw = JOptionPane.showInputDialog(this, "Password:");
        String r = JOptionPane.showInputDialog(this, "Role (student/staff):");
        if (u != null && pw != null && r != null)
            appendLine("staff".equals(r) ? STAFF_FILE : STUD_FILE, u + "," + pw);
    }
    
    

    
    private void deleteStudent() {
        String u = JOptionPane.showInputDialog(this, "Username to delete:");
        if (u != null) {
            Vector<String> v = readAll(STUD_FILE);
            v.removeIf(ln -> ln.split(",")[0].equals(u));
            writeAll(STUD_FILE, v);
        }
    }

    private void addQuestions() {
        String n = JOptionPane.showInputDialog(this, "Number of questions:");
        try {
            int c = Integer.parseInt(n);
            for (int i = 0; i < c; i++) {
                String t = JOptionPane.showInputDialog(this, "Question text:");
                String o = JOptionPane.showInputDialog(this, "Options (comma sep):");
                String a = JOptionPane.showInputDialog(this, "Correct ans:");
                appendLine(Q_FILE, t + "|" + o + "|" + a);
            }
        } catch (Exception ignored) {}
    }

    private static Vector<String> readAll(Path p) {
        try { return new Vector<>(Files.readAllLines(p)); } catch (IOException e) { return new Vector<>(); }
    }

    private static void writeAll(Path p, Vector<String> v) {
        try { Files.write(p, v); } catch (IOException ignored) {}
    }

    private static void appendLine(Path p, String l) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(p.toFile(), true))) {
            bw.write(l); bw.newLine();
        } catch (IOException ignored) {}
    }


static class Question {
        String id, text, options, answer;
        Question(String i, String t, String o, String a) {
            id = i; text = t; options = o; answer = a;
        }
        static Question fromLine(String l) {
            String[] p = l.split("\\|");
            return new Question(UUID.randomUUID().toString(), p[0], p[1], p[2]);
        }
    }
}