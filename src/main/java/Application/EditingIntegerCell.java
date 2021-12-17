package Application;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.input.KeyCode;

public class EditingIntegerCell<T> extends TableCell<T,Integer>{

    private TextField textField;


    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.requestFocus();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getString());
        setGraphic(null);
    }


    @Override
    public void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private String getString() {
        return getItem() == null ? "" : String.valueOf(getItem());
    }

    private void createTextField(){

    	textField = new TextField();
  	  
	    textField.setText(getString());
	
	    textField.setOnAction(evt -> {
	        if(textField.getText() != null && !textField.getText().isEmpty()){
	            commitEdit(Integer.parseInt(textField.getText()));
	        }
	    });
	
	    textField.setOnKeyPressed((ke) -> {
	        if (ke.getCode().equals(KeyCode.ESCAPE)) {
	            cancelEdit();
	        }
	    });
	    textField.textProperty().addListener((observable, oldValue, newValue) -> {
	    	if (!newValue.matches("\\d0*")) {
	    		textField.setText(newValue.replaceAll("[^\\d0-9]", ""));
	        }
	    });
	    textField.setAlignment(Pos.CENTER_LEFT);
	    this.setAlignment(Pos.CENTER_LEFT);
    }
    @Override
	public void commitEdit(Integer item) {
	    if (isEditing()) {
	        super.commitEdit(item);
	    } else {
	        final TableView<T> table = getTableView();
	        if (table != null) {
	            TablePosition<T, Integer> position = new TablePosition<T,Integer>(getTableView(),
	                    getTableRow().getIndex(), getTableColumn());
	            CellEditEvent<T,Integer> editEvent = new CellEditEvent<T,Integer>(table, position,
	                    TableColumn.editCommitEvent(), item);
	            Event.fireEvent(getTableColumn(), editEvent);
	        }
	        updateItem(item, false);
	        if (table != null) {
	            table.edit(-1, null);
	        }
	        
	    }
	}
}