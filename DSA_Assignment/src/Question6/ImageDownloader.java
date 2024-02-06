package Question6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageDownloader extends JFrame {

    private JTextField urlField;
    private JButton downloadButton;
    private JTextArea logArea;
    private ExecutorService executorService;
    private boolean downloading;

    public ImageDownloader() {
        setTitle("Image Downloader");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        urlField = new JTextField(20);
        downloadButton = new JButton("Download");
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDownload();
            }
        });
        inputPanel.add(urlField);
        inputPanel.add(downloadButton);

        logArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(logArea);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        executorService = Executors.newFixedThreadPool(5); // Using a fixed-size thread pool with 5 threads
    }

    private void startDownload() {
        String url = urlField.getText();
        if (url.isEmpty()) {
            logMessage("Please enter a URL.");
            return;
        }

        if (downloading) {
            logMessage("Already downloading. Please wait.");
            return;
        }

        downloading = true;
        logMessage("Downloading images from: " + url);

        executorService.execute(new ImageDownloadTask(url));
    }

    private void logMessage(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                logArea.append(message + "\n");
            }
        });
    }

    private class ImageDownloadTask implements Runnable {

        private String url;

        public ImageDownloadTask(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            try {
                // Download image from the URL
                URL imageUrl = new URL(url);
                InputStream inputStream = imageUrl.openStream();
                String fileName = url.substring(url.lastIndexOf('/') + 1);

                // Specify desktop directory path
                String desktopPath = System.getProperty("user.home") + "/Desktop/";
                String filePath = desktopPath + fileName;

                // Create FileOutputStream with desktop file path
                FileOutputStream outputStream = new FileOutputStream(filePath);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream.close();

                logMessage("Image downloaded: " + fileName);
            } catch (IOException e) {
                logMessage("Error downloading image: " + e.getMessage());
            } finally {
                downloading = false;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ImageDownloader().setVisible(true);
            }
        });
    }
}
