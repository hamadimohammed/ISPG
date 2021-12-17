package Application;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.input.KeyCode;


public class EditingStringCell<T> extends TableCell<T, String> {
	private TextField textField;
	
	@Override
	public void startEdit() {
	    if(editableProperty().get()){
	        if (!isEmpty()) {
	            super.startEdit();
	            createTextField();
	            setText(null);
	            setGraphic(textField);
	            textField.requestFocus();
	        }
	    }
	}
	
	@Override
	public void cancelEdit() {
	    super.cancelEdit();
	    setText(getItem() != null ? getItem().toString() : null);
	    setGraphic(null);
	}
	
	@Override
	public void updateItem(String item, boolean empty) {
	    super.updateItem(item, empty);
	    if (empty) {
	        setText(null);
	        setGraphic(null);
	    } else {
	        if (isEditing()) {
	            if (textField != null) {
	                textField.setText(getString());
	                textField.selectAll();
	            }
	            setText(null);
	            setGraphic(textField);
	        } else {
	            setText(getString());
	            setGraphic(null);
	        }
	    }
	}
	
	private void createTextField() {
	    textField = new TextField();
	  
	    textField.setText(getString());
	
	    textField.setOnAction(evt -> {
	        if(textField.getText() != null && !textField.getText().isEmpty()){
	            //NumberStringConverter nsc = new NumberStringConverter();
	            //Number n = nsc.fromString(textField.getText());
	            commitEdit(textField.getText());
	        }
	    });
	
	    textField.setOnKeyPressed((ke) -> {
	        if (ke.getCode().equals(KeyCode.ESCAPE)) {
	            cancelEdit();
	        }
	    });
	    textField.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.isEmpty() && !newValue.matches("\\sa-zA-Z0-9 *._/()�-"))
	    		textField.setText(newValue.replaceAll("[^\\sa-zA-Z0-9 *._/()�-]", "").toUpperCase());
	    });
	    textField.setAlignment(Pos.CENTER_LEFT);
	    this.setAlignment(Pos.CENTER_LEFT);
	}
	
	private String getString() {
	    return getItem() == null ? "" : getItem();
	}

	@Override
	public void commitEdit(String item) {
	    if (isEditing()) {
	        super.commitEdit(item);
	    } else {
	        final TableView<T> table = getTableView();
	        if (table != null) {
	            TablePosition<T, String> position = new TablePosition<T,String>(getTableView(),
	                    getTableRow().getIndex(), getTableColumn());
	            CellEditEvent<T,String> editEvent = new CellEditEvent<T,String>(table, position,
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
