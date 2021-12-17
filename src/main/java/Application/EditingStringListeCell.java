package Application;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;


public class EditingStringListeCell extends ListCell<String> {
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
	        	commitEdit(textField.getText());
	        }
	    });
	
	    textField.setOnKeyPressed((ke) -> {
	        if (ke.getCode().equals(KeyCode.ESCAPE)) {
	            cancelEdit();
	        }
	    });
	    textField.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.isEmpty() && !newValue.matches("\\sa-zA-Z0-9*._/() "))
	    		textField.setText(newValue.replaceAll("[^\\sa-zA-Z0-9 ._/()]", "").toUpperCase());
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
	        super.commitEdit(item.toUpperCase());
	    } else {
	        final ListView<String> liste = getListView();
	        if (liste != null) {
	            liste.getItems().set(getIndex(), getItem());
	        }
	        updateItem(item, false);
	        if (liste != null) {
	            liste.edit(-1);
	        }
	        
	    }
	}
}
