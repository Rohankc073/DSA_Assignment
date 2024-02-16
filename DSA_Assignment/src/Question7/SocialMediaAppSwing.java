package Question7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class SocialMediaAppSwing {
    private JFrame frame;
    private JPanel panel;
    private Map<String, User> users = new HashMap<>();
    private User currentUser;

    public SocialMediaAppSwing() {
        frame = new JFrame("Social Media App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome to Social Media App");
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginScreen();
            }
        });

        panel.add(welcomeLabel);
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void showLoginScreen() {
        frame.getContentPane().removeAll();
        frame.repaint();

        JPanel loginPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (users.containsKey(username)) {
                    currentUser = users.get(username);
                    showUserDashboard();
                } else {
                    JOptionPane.showMessageDialog(frame, "User does not exist. Please register first.");
                }
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (!users.containsKey(username)) {
                    User newUser = new User(username);
                    users.put(username, newUser);
                    currentUser = newUser;
                    showUserDashboard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Username already exists. Please choose another one.");
                }
            }
        });

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        frame.add(loginPanel);
        frame.revalidate();
    }

    private void showUserDashboard() {
        frame.getContentPane().removeAll();
        frame.repaint();

        JPanel dashboardPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome, " + currentUser.getUsername() + "!");
        JButton postButton = new JButton("Create Post");
        JButton logoutButton = new JButton("Logout");

        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String postContent = JOptionPane.showInputDialog(frame, "Enter your post:");
                if (postContent != null && !postContent.isEmpty()) {
                    Post newPost = new Post(postContent, currentUser);
                    currentUser.addPost(newPost);
                    JOptionPane.showMessageDialog(frame, "Post created successfully.");
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = null;
                showLoginScreen();
            }
        });

        dashboardPanel.add(welcomeLabel);
        dashboardPanel.add(postButton);
        dashboardPanel.add(logoutButton);

        frame.add(dashboardPanel);
        frame.revalidate();
    }

    private class User {
        private String username;
        private List<User> following;
        private List<Post> posts;

        public User(String username) {
            this.username = username;
            this.following = new ArrayList<>();
            this.posts = new ArrayList<>();
        }

        public void follow(User user) {
            following.add(user);
        }

        public void unfollow(User user) {
            following.remove(user);
        }

        public void addPost(Post post) {
            posts.add(post);
        }

        public String getUsername() {
            return username;
        }

        public List<User> getFollowing() {
            return following;
        }

        public List<Post> getPosts() {
            return posts;
        }
    }

    private class Post {
        private String content;
        private User author;

        public Post(String content, User author) {
            this.content = content;
            this.author = author;
        }

        public User getAuthor() {
            return author;
        }

        public String getContent() {
            return content;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SocialMediaAppSwing();
            }
        });
    }
}
