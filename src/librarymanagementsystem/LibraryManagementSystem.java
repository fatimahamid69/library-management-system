package librarymanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.event.*;




class Book {
    private int bookId;
    private String title;
    private String author;
    private int year;
    private int readCount; 

    public Book(int bookId, String title, String author, int year,int readCount) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
        this.readCount = readCount;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }
    public void setBook(String book) {
        this.title = book;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public int getReadCount() {
        return readCount;
    }

    public void incrementReadCount() {
        readCount++;
    }
}

public class LibraryManagementSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel welcomePanel;
    private JPanel bookPanel;
    private JPanel addBookPanel;
    private JPanel editBookPanel;
    private DefaultTableModel tableModel;
    private JTable bookTable;
    private List<Book> books;

    public LibraryManagementSystem() {
        frame = new JFrame("Fatima's Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        books = new ArrayList<>();
        books.add(new Book(1, "Trump And Drama", "Author 1", 2020,50));
        books.add(new Book(2, "Imran Khan Vs Establishment", "Author 2", 2019,25));
        books.add(new Book(3, "Behind the Closed Doors", "Author 3", 2018,100));

        createWelcomePanel();
        createBookPanel();
        createAddBookPanel();
        createEditBookPanel();

        cardPanel.add(welcomePanel, "welcomePanel");
        cardPanel.add(bookPanel, "bookPanel");
        cardPanel.add(addBookPanel, "addBookPanel");
        cardPanel.add(editBookPanel, "editBookPanel");

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    public void createWelcomePanel() {
     welcomePanel = new JPanel() {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Load the background image (use an absolute path)
        ImageIcon backgroundImage = new ImageIcon("bg.jpg");
        // Draw the background image
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
    }
};

// Set a preferred size for the welcomePanel
welcomePanel.setPreferredSize(new Dimension(800, 600)); // Adjust the size as needed

welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.PAGE_AXIS));

        welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.PAGE_AXIS));
        JLabel welcomeLabel = new JLabel("Welcome to Fatima's Library");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton viewBooksButton = new JButton("View Books");
        viewBooksButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         viewBooksButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            viewBooksButton.setBackground(Color.LIGHT_GRAY); // Change background color on hover
        }

        @Override
        public void mouseExited(MouseEvent e) {
            viewBooksButton.setBackground(UIManager.getColor("Button.background")); // Restore the default background color
        }
    });

        JButton addBooksButton = new JButton("Add Books");
        addBooksButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBooksButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            addBooksButton.setBackground(Color.WHITE); // Change background color on hover
        }

        @Override
        public void mouseExited(MouseEvent e) {
            addBooksButton.setBackground(UIManager.getColor("Button.background")); // Restore the default background color
        }
    });
        JButton editBooksButton = new JButton("Edit Books");
        editBooksButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        viewBooksButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            editBooksButton.setBackground(Color.LIGHT_GRAY); // Change background color on hover
        }

        @Override
        public void mouseExited(MouseEvent e) {
            editBooksButton.setBackground(UIManager.getColor("Button.background")); // Restore the default background color
        }
    });
        
        
        JPanel deleteButtonPanel = new JPanel();
        deleteButtonPanel.setLayout(new BoxLayout(deleteButtonPanel, BoxLayout.LINE_AXIS));
        deleteButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        
        JButton deleteBookButton = new JButton("Delete Book");
        
        deleteBookButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            deleteBookButton.setBackground(Color.WHITE); // Change background color on hover
        }

        @Override
        public void mouseExited(MouseEvent e) {
            deleteBookButton.setBackground(UIManager.getColor("Button.background")); // Restore the default background color
        }
    });
        
        JButton readBookButton = new JButton("Read Book");
        readBookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        readBookButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            readBookButton.setBackground(Color.LIGHT_GRAY); // Change background color on hover
        }

        @Override
        public void mouseExited(MouseEvent e) {
            readBookButton.setBackground(UIManager.getColor("Button.background")); // Restore the default background color
        }
    });

        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "bookPanel");
            }
        });

        addBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "addBookPanel");
            }
        });

        editBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "editBookPanel");
            }
        });
        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookNameToDelete = JOptionPane.showInputDialog(frame, "Enter the name of the book to delete:");
                if (bookNameToDelete != null) {
                    boolean bookFound = false;
                    // Search for the book by name and remove it from the list
                    for (Book book : books) {
                        if (book.getTitle().equalsIgnoreCase(bookNameToDelete)) {
                            books.remove(book);
                            bookFound = true;
                            // Exit the loop after the first match (assuming book names are unique)
                            break;
                        }
                    }

                    // Refresh the book table with updated data
                    refreshBookTable();

                    if (bookFound) {
                        // Show a confirmation message
                        JOptionPane.showMessageDialog(frame, "The book has been deleted successfully.", "Book Deleted", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Show an error message if the book is not found
                        JOptionPane.showMessageDialog(frame, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
            deleteButtonPanel.add(deleteBookButton);
            readBookButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String bookNameToRead = JOptionPane.showInputDialog(frame, "Enter the name of the book to read:");
        if (bookNameToRead != null) {
            // Search for the book by name
            Book bookToRead = null;
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(bookNameToRead)) {
                    bookToRead = book;
                    break;
                }
            }

            if (bookToRead != null) {
                // Read the book text from a file
                String bookText = readBookTextFromFile(bookToRead.getTitle());
                // Display the book text to the user
                displayBookText(bookToRead.getTitle(), bookText);
                // Increment the read count for the book
                bookToRead.incrementReadCount();
            } else {
                // Show an error message if the book is not found
                JOptionPane.showMessageDialog(frame, "Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});
            
            
            
            
            
           JButton hotpicksButton = new JButton("Hotpicks");
    hotpicksButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    hotpicksButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Find the most read book
            Book mostReadBook = findMostReadBook();

            if (mostReadBook != null) {
                // Display a message with the most read book's title
               /* String hotpicksMessage = "Hotpicks For You\nMost Read Book: " + mostReadBook.getTitle();
                JOptionPane.showMessageDialog(frame, hotpicksMessage, "Hotpicks", JOptionPane.INFORMATION_MESSAGE);*/

                // Create and display the bar chart
                createHotpicksChart();
            } else {
                // Handle the case where there are no read books
                JOptionPane.showMessageDialog(frame, "No books have been read yet.", "Hotpicks", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });



        welcomePanel.add(Box.createVerticalGlue());
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(viewBooksButton);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(addBooksButton);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(editBooksButton);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(deleteButtonPanel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(readBookButton);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomePanel.add(hotpicksButton);
        welcomePanel.add(Box.createVerticalGlue());
        
    }
private String readBookTextFromFile(String bookTitle) {
   String resourcePath = bookTitle + ".txt";


    try {
        InputStream inputStream = getClass().getResourceAsStream(resourcePath);

        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder bookText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                bookText.append(line).append("\n");
            }
            reader.close();
            return bookText.toString();
        } else {
            return "Error: Book text not available.";
        }
    } catch (IOException e) {
        e.printStackTrace();
        return "Error: Book text not available.";
    }
}

private void displayBookText(String bookTitle, String bookText) {
    JFrame bookFrame = new JFrame(bookTitle);
    JTextArea textArea = new JTextArea(bookText);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    textArea.setCaretPosition(0); // Scroll to the top of the text
    textArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(textArea);

    // Create an "Exit Reading" button and add it to the bookFrame
    JButton exitReadingButton = new JButton("Exit Reading");
    exitReadingButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showConfirmDialog(bookFrame, "Do you want to exit reading?", "Exit Reading", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                bookFrame.dispose(); // Close the reading frame and return to the welcome page
            }
        }
    });

    bookFrame.add(scrollPane, BorderLayout.CENTER);
    bookFrame.add(exitReadingButton, BorderLayout.SOUTH);

    bookFrame.setSize(600, 400);
    bookFrame.setVisible(true);
}

    public void createBookPanel() {
        bookPanel = new JPanel(new BorderLayout());
        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "welcomePanel");
            }
        });

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Author", "Year"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells uneditable by default
            }
        };

        bookTable = new JTable(tableModel);
        bookTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        bookTable.getColumnModel().getColumn(1).setPreferredWidth(250);
        bookTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        bookTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(bookTable);

        bookPanel.add(scrollPane, BorderLayout.CENTER);
        bookPanel.add(goBackButton, BorderLayout.SOUTH);

        // Populate the table with existing books
        for (Book book : books) {
            tableModel.addRow(new Object[]{book.getBookId(), book.getTitle(), book.getAuthor(), book.getYear()});
        }
    }

    public void createAddBookPanel() {
        addBookPanel = new JPanel(new BorderLayout());
        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "welcomePanel");
            }
        });

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();
        JButton addButton = new JButton("Add Book");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                int year = Integer.parseInt(yearField.getText());

                // Add the new book to the list
                int newBookId = books.size() + 1;
                Book newBook = new Book(newBookId, title, author, year,5);
                books.add(newBook);

                // Add the book to the table
                tableModel.addRow(new Object[]{newBookId, title, author, year});

                // Clear the input fields
                titleField.setText("");
                authorField.setText("");
                yearField.setText("");

                // Show a confirmation message
                JOptionPane.showMessageDialog(frame, "Your book has been added successfully.", "Book Added", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Author:"));
        formPanel.add(authorField);
        formPanel.add(new JLabel("Year:"));
        formPanel.add(yearField);
        formPanel.add(new JLabel("")); // Empty label for spacing
        formPanel.add(addButton);

        addBookPanel.add(formPanel, BorderLayout.CENTER);
        addBookPanel.add(goBackButton, BorderLayout.SOUTH);
    }

    public void createEditBookPanel() {
        editBookPanel = new JPanel(new BorderLayout());
        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "welcomePanel");
            }
        });

        JPanel editFormPanel = new JPanel(new GridLayout(5, 2));
        JTextField bookIdField = new JTextField();
        JTextField newBookField = new JTextField();
        JTextField newAuthorField = new JTextField();
        JTextField newYearField = new JTextField();
        JButton applyChangesButton = new JButton("Apply Changes");

        applyChangesButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int editBookId = Integer.parseInt(bookIdField.getText());
            int newYear = -1; // Initialize to a value that cannot be used
            String newAuthor = null;
            String newBook = null;

            // Find the book to edit
            for (Book book : books) {
                if (book.getBookId() == editBookId) {
                    if (!newBookField.getText().isEmpty()) {
                        newBook = newBookField.getText();
                        book.setBook(newBook);
                    }
                    if (!newAuthorField.getText().isEmpty()) {
                        newAuthor = newAuthorField.getText();
                        book.setAuthor(newAuthor);
                    }
                    if (!newYearField.getText().isEmpty()) {
                        newYear = Integer.parseInt(newYearField.getText());
                        book.setYear(newYear);
                    }
                }
            }

            // Refresh the table with updated data
            refreshBookTable();

            // Show a confirmation message
            String message = "Book details have been updated:";
            if (newBook != null) {
                message += "\n- Title: " + newBook;
            }
            if (newAuthor != null) {
                message += "\n- Author: " + newAuthor;
            }
            if (newYear != -1) {
                message += "\n- Year: " + newYear;
            }
            JOptionPane.showMessageDialog(frame, message, "Book Updated", JOptionPane.INFORMATION_MESSAGE);

            // Clear the input fields
            bookIdField.setText("");
            newBookField.setText("");
            newAuthorField.setText("");
            newYearField.setText("");
        }
    });

        editFormPanel.add(new JLabel("Book ID to Edit:"));
        editFormPanel.add(bookIdField);
         editFormPanel.add(new JLabel("New Book Name:"));
        editFormPanel.add(newBookField);
        editFormPanel.add(new JLabel("New Author Name:"));
        editFormPanel.add(newAuthorField);
        editFormPanel.add(new JLabel("New Year:"));
        editFormPanel.add(newYearField);
        editFormPanel.add(new JLabel("")); // Empty label for spacing
        editFormPanel.add(applyChangesButton);

        editBookPanel.add(editFormPanel, BorderLayout.CENTER);
        editBookPanel.add(goBackButton, BorderLayout.SOUTH);
        
    }
public Book findMostReadBook() {
    Book mostReadBook = null;
    int maxReadCount = 0;

    for (Book book : books) {
        if (book.getReadCount() > maxReadCount) {
            maxReadCount = book.getReadCount();
            mostReadBook = book;
        }
    }

    return mostReadBook;
}

    public void refreshBookTable() {
        // Clear the table
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }

        // Repopulate the table with updated book data
        for (Book book : books) {
            tableModel.addRow(new Object[]{book.getBookId(), book.getTitle(), book.getAuthor(), book.getYear()});
        }
    }
    public void createHotpicksChart() {
        JFrame chartFrame = new JFrame("Hotpicks Chart");
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.setSize(800, 600);

        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;

                int barWidth = 50;
                int spacing = 200;
                int x = 50;
                int maxHeight = 400;

                for (Book book : books) {
                    int popularity = book.getReadCount();
                    int barHeight = (int) ((double) popularity / getMaxPopularity() * maxHeight);
                    g2.setColor(Color.RED);
                    g2.fillRect(x, 500 - barHeight, barWidth, barHeight);
                    g2.setColor(Color.BLACK);
                    g2.drawRect(x, 500 - barHeight, barWidth, barHeight);

                    g2.drawString(book.getTitle(), x, 520);
                    x += barWidth + spacing;
                }
            }
        };

        chartFrame.add(chartPanel);
        chartFrame.setVisible(true);
    }

    public int getMaxPopularity() {
        int maxPopularity = 0;

        for (Book book : books) {
            maxPopularity = Math.max(maxPopularity, book.getReadCount());
        }

        return maxPopularity;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryManagementSystem();
            }
        });
    }
}