//package Question7;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class SocialMediaAppSwing {
//    private JFrame frame;
//    private JPanel panel;
//    private Map<String, User> users = new HashMap<>();
//    private User currentUser;
//
//    public SocialMediaAppSwing() {
//        frame = new JFrame("Social Media App");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 200);
//        frame.setLocationRelativeTo(null);
//
//        panel = new JPanel(new GridLayout(3, 1, 10, 10));
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        JLabel welcomeLabel = new JLabel("Welcome to Social Media App");
//        JButton loginButton = new JButton("Login");
//
//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showLoginScreen();
//            }
//        });
//
//        panel.add(welcomeLabel);
//        panel.add(loginButton);
//
//        frame.add(panel);
//        frame.setVisible(true);
//    }
//
//    private void showLoginScreen() {
//        frame.getContentPane().removeAll();
//        frame.repaint();
//
//        JPanel loginPanel = new JPanel(new GridLayout(4, 1, 10, 10));
//        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        JLabel usernameLabel = new JLabel("Username:");
//        JTextField usernameField = new JTextField(20);
//
//        JButton loginButton = new JButton("Login");
//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String username = usernameField.getText();
//                if (users.containsKey(username)) {
//                    currentUser = users.get(username);
//                    showUserDashboard();
//                }
//            }
//        });
//
//        loginPanel.add(usernameLabel);
//        loginPanel.add(usernameField);
//        loginPanel.add(loginButton);
//
//        frame.add(loginPanel);
//        frame.revalidate();
//    }
//
//    private void showUserDashboard() {
//        // Implement user dashboard UI
//    }
//
//    private class User {
//        private String username;
//        private List<User> following;
//        private List<Post> posts;
//
//        public User(String username) {
//            this.username = username;
//            this.following = new ArrayList<>();
//            this.posts = new ArrayList<>();
//        }
//
//        public void follow(User user) {
//            following.add(user);
//        }
//
//        public void unfollow(User user) {
//            following.remove(user);
//        }
//
//        public void addPost(Post post) {
//            posts.add(post);
//        }
//
//        // Implement other methods as needed
//    }
//
//    private class Post {
//        private String content;
//        private User author;
//
//        public Post(String content, User author) {
//            this.content = content;
//            this.author = author;
//        }
//
//        // Implement other methods as needed
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new SocialMediaAppSwing();
//            }
//        });
//    }
//}
