package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    @FXML
    TextField keyField;

    @FXML
    TextArea inputTextArea;

    @FXML
    TextArea outputTextArea;

    private char[][] messageArray;
    private char[] chars;
    private String message;
    private List<Integer> key = new ArrayList<>();

    public void encodeActionButton() {
        start(true);
    }

    public void decodeActionButton() {
        start(false);
    }

    private void start(boolean button){
        message = inputTextArea.getText();
        String keyText = keyField.getText() + ' ';
        key = Arrays.stream(keyText.trim().split("\\s+")).map(Integer::valueOf).collect(Collectors.toList());
        String output = action(button);
        outputTextArea.setText(output);
    }

    private String action(boolean button){
        int cols = key.size();
        int rows = (int) Math.ceil((message.length()/cols));

        messageArray = new char[cols][rows];
        for(int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                messageArray[i][j] = ' ';
            }
        }

        chars = message.toCharArray();
        StringBuilder result = new StringBuilder();
        int counter = 0;

        if(button){
            for(int i = 0; i < rows && chars.length != 0; i++){
                for(int j = 0; j < cols; j++){
                    messageArray[j][i] = chars[counter++];
                }
            }
            for(int i = 0; i < key.size(); i++){
                result.append(messageArray[key.indexOf(i+1)]);
            }

        }else {
            for(int i = 0; i < cols && chars.length != 0; i++){
                for(int j = 0; j < rows; j++){
                    messageArray[i][j] = chars[counter++];
                }
            }
            for (int i = 0; i < rows; i++) {
                for (int index : key) {
                    result.append(messageArray[index-1][i]);
                }
            }
        }
        return result.toString();
    }
}

