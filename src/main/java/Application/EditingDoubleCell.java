package Application;

import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Pattern;

public class EditingDoubleCell<T> extends TableCell<T,Double>{

    private TextField textField;
    private TextFormatter<Double> textFormatter ;

    private Pattern partialInputPattern = Pattern.compile(
            "[-+]?[,0-9]*(\\.[0-9]*)?");

    private DecimalFormat df ;

    public EditingDoubleCell() {
        df = (DecimalFormat) NumberFormat.getInstance(Locale.ENGLISH);
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

    }

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
        setText(df.format(getItem()));
        setGraphic(null);
    }


    @Override
    public void updateItem(Double item, boolean empty) {
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
        return getItem() == null ? "" : df.format(getItem());
    }

    private void createTextField(){

        textField = new TextField();

        StringConverter<Double> converter = new StringConverter<Double>() {

            @Override
            public String toString(Double number) {
                return df.format(number);
            }

            @Override
            public Double fromString(String string) {
                try {
                    double value = df.parse(string).doubleValue() ;
                    return value;
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0.0 ;
                }
            }

        };

        textFormatter = new TextFormatter<>(converter,  0.0, c -> {
            if (partialInputPattern.matcher(c.getControlNewText()).matches()) {
                return c ;
            } else {
                return null ;
            }
        }) ;

        // add filter to allow for typing only integer
        textField.setTextFormatter( textFormatter);
        textField.setOnKeyPressed((ke) -> {
	        if (ke.getCode().equals(KeyCode.ESCAPE)) {
	            cancelEdit();
	        }
	    });
        textField.setText( getString() );

        textField.setMinWidth( this.getWidth() - this.getGraphicTextGap() * 2 );

        // commit on Enter
        textFormatter.valueProperty().addListener((obs, oldValue, newValue) -> {
            commitEdit(newValue);
        });
    }
}