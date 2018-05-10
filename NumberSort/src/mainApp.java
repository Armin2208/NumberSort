import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class mainApp {
    private JButton generateNumberButton;
    private JPanel panel1;
    private JComboBox<String> sortTypeComboBox;
    private JTextArea ConsoleOutputTextArea;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JButton startSortButton;
    private JLabel label4;
    private JButton resetButton;
    private JButton fromList;
    private JTextArea input;

    private static int arrayNumber = 0;
    private static int[] numbers;
    private static int sortType = 0;

    private mainApp() {
        comboBoxHandler();
        uiHandler(0);

        generateNumberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateNumber();
                uiHandler(1);
            }
        });
        sortTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selected = sortTypeComboBox.getSelectedItem();
                switch (selected.toString()) {
                    case "Bubble Sort":
                        sortType = 0;
                        break;
                    case "Selection Sort":
                        sortType = 1;
                        break;
                    case "Insertion Sort":
                        sortType = 2;
                        break;
                }
                System.out.println("Selected sort type: " + sortType);
            }
        });
        startSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sortType == 0) bubbleSort();
                if(sortType == 1) selectionSort();
                if (sortType == 2) insertionSort();
                uiHandler(2);
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrayNumber = 0;
                ConsoleOutputTextArea.append(System.lineSeparator());
                uiHandler(0);
            }
        });
        fromList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputFromList();
                uiHandler(1);
            }
        });
    }

    private void generateNumber(){
        numbers = new int[30];
        ConsoleOutputTextArea.append("Order: ");
        for(int i = 0; i < 30; i++){
            int generatedNumber = (int) (Math.random() * 100 + 1);
            numbers[arrayNumber] = generatedNumber;
            arrayNumber = arrayNumber+1;
            ConsoleOutputTextArea.append(generatedNumber + ", ");
        }
        ConsoleOutputTextArea.append(System.lineSeparator());
    }

    private void inputFromList(){
        ConsoleOutputTextArea.append("Order: ");
        String[] lines = input.getText().split("\\n");
        String tmp;
        int[] numbero = new int[lines.length + 1];
        numbers = new int[lines.length];
        arrayNumber = lines.length;
        for (int i = 0; i <lines.length ; i++) {
            try {
                numbero[i] = Integer.parseInt(lines[i]);
            } catch(Throwable e) {
                numbero[i] = 0;
            }
            numbers = numbero;
        }
        for (int i = 0; i < lines.length; i++) {
            ConsoleOutputTextArea.append(String.valueOf(numbers[i]));
            ConsoleOutputTextArea.append(", ");
        }
        ConsoleOutputTextArea.append((System.lineSeparator()));
        /*for(int i = 0; i < input.getLineCount(); i++){
            try {
                int generatedNumber = Integer.parseInt(input.get;
            } catch (ParseException e) {

            };

            numbers[arrayNumber] = generatedNumber;
            arrayNumber = arrayNumber+1;
            ConsoleOutputTextArea.append(generatedNumber + ", ");
        }
        ConsoleOutputTextArea.append(System.lineSeparator());
        //String s[] = input.getText().split("\\r?\\n");
        //int[] numbero = new int[s.length];
        String iput = input.getText();
        String[] ipt = iput.split("\n");
        System.out.println(ipt.length);
        //System.out.println(numbero.length);
        System.out.print(ipt);
        for (int i = 0; i < s.length; i++) {
            //numbers[i] = Integer.getInteger(s[i]);
            System.out.println(s[i]);
        }
        ConsoleOutputTextArea.append(numbero + ", ");
        */
    }

    //just GUI things
    private void comboBoxHandler(){
        sortTypeComboBox.addItem("Bubble Sort");
        sortTypeComboBox.addItem("Selection Sort");
        sortTypeComboBox.addItem("Insertion Sort");
    }
    private void uiHandler(int uiState){
        if(uiState == 0){
            generateNumberButton.setEnabled(true);
            fromList.setEnabled(true);
            sortTypeComboBox.setEnabled(false);
            startSortButton.setEnabled(false);
            resetButton.setEnabled(false);
        }else if (uiState == 1){
            generateNumberButton.setEnabled(false);
            fromList.setEnabled(false);
            sortTypeComboBox.setEnabled(true);
            startSortButton.setEnabled(true);
        }else if (uiState == 2){
            sortTypeComboBox.setEnabled(false);
            startSortButton.setEnabled(false);
            resetButton.setEnabled(true);
        }
    }

    private void printToTextArea(){
        for(int y = 0; y < arrayNumber; y++){
            ConsoleOutputTextArea.append(String.valueOf(numbers[y]) + ", ");
        }
        ConsoleOutputTextArea.append(System.lineSeparator());
    }

    //all 3 sort algorithms
    private void bubbleSort(){
        for(int i = 0; i < arrayNumber; i++){
            for (int j = 0; j < arrayNumber - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
            ConsoleOutputTextArea.append("After run " + i + ": ");
            printToTextArea();
        }
    }
    private void selectionSort(){
        for(int i = 0; i < arrayNumber; i++) {
            int small = numbers[i];
            int pos = i;

            for (int j = i + 1; j < arrayNumber; j++) {
                if (numbers[j] < small) {
                    small = numbers[j];
                    pos = j;
                }
            }
            int temp = numbers[pos];
            numbers[pos] = numbers[i];
            numbers[i] = temp;

            ConsoleOutputTextArea.append("After run " + i +": ");
            printToTextArea();
        }
    }
    private void insertionSort(){
        for(int i = 1; i < arrayNumber; i++) {
            int j = i - 1;
            int temp = numbers[i];

            while (j >= 0 && temp < numbers[j]) {
                numbers[j + 1] = numbers[j];
                j--;
            }
            numbers[j + 1] = temp;

            ConsoleOutputTextArea.append("Nach Durchlauf " + i +": ");
            printToTextArea();
        }
    }

    public static void main(String[] args){
        //create GUI
        JFrame mainWindow = new JFrame("Number Sort by Armin Osaj");
        mainWindow.setContentPane(new mainApp().panel1);
        mainWindow.pack();
        mainWindow.setSize(1000,600);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
    }
}
