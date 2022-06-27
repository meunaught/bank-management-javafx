package com.projectyobank.controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.projectyobank.database.dbcontroller;
import com.projectyobank.models.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class StatementController extends Controller implements Initializable {
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXTreeTableView<myCustomer> treeTableView;
    @FXML
    private JFXTreeTableColumn<myCustomer, String> userNameColumn;
    @FXML
    private JFXTreeTableColumn<myCustomer, String> accTypeColumn;
    @FXML
    private JFXTreeTableColumn<myCustomer, String> accColumn;
    @FXML
    private JFXTreeTableColumn<myCustomer, String> prevbalanceColumn;
    @FXML
    private JFXTreeTableColumn<myCustomer, String> balanceColumn;
    @FXML
    private JFXTreeTableColumn<myCustomer, String> DateColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BankerName.setText(dbcontroller.getInstance().getBanker().getUsername());
        Designation.setText(dbcontroller.getInstance().getBanker().getDesignation());
        setupReadOnlyTableView();
    }

    //TreeTable Stuff
    static final class myCustomer extends RecursiveTreeObject<myCustomer> {
        final StringProperty[] list = new StringProperty[6];

        myCustomer(Customer customer) {
            this.list[0] = new SimpleStringProperty(customer.getUsername());
            this.list[1] = new SimpleStringProperty((customer.getAccount().getTransaction().getType()));
            this.list[2] = new SimpleStringProperty(Long.toString(customer.getAccount().getNumber()));
            this.list[3] = new SimpleStringProperty(customer.getAccount().getTransaction().getPrevBal());
            this.list[4] = new SimpleStringProperty(customer.getAccount().getTransaction().getCurrBal());
            this.list[5] = new SimpleStringProperty(customer.getAccount().getTransaction().getDate());
        }
        StringProperty customerID() {
            return list[0];
        }
        StringProperty acctype() {
            return list[1];
        }
        StringProperty acc() {
            return list[2];
        }
        StringProperty balance() {
            return list[3];
        }
        StringProperty phone() {
            return list[4];
        }
        StringProperty status() {
            return list[5];
        }
    }

    private ObservableList<myCustomer> genCell() {
        final ObservableList<myCustomer> data = FXCollections.observableArrayList();
        var ccList = dbcontroller.getInstance().transArrayList();
        for(Customer cc : ccList) {
            data.add(new myCustomer(cc));
        }
        return data;
    }

    private <T> void setupCellValueFactory(JFXTreeTableColumn<myCustomer, T> column, Function<myCustomer, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<myCustomer, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }

    private ChangeListener<String> setupSearchField(final JFXTreeTableView<myCustomer> tableView) {
        return (o, oldVal, newVal) ->
                tableView.setPredicate(myCustomerProp -> {
                    final myCustomer customer = myCustomerProp.getValue();
                    boolean match = false;
                    for(int i = 0; i < 6; ++i) {
                        match = match || customer.list[i].get().contains(newVal);
                    }
                    return match;
                });
    }

    private void setupReadOnlyTableView() {
        setupCellValueFactory(userNameColumn, myCustomer::customerID);
        setupCellValueFactory(accTypeColumn, myCustomer::acctype);
        setupCellValueFactory(accColumn, myCustomer::acc);
        setupCellValueFactory(prevbalanceColumn, myCustomer::balance);
        setupCellValueFactory(balanceColumn, myCustomer::phone);
        setupCellValueFactory(DateColumn, myCustomer::status);
        ObservableList<myCustomer> data = genCell();
        treeTableView.setRoot(new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren));
        treeTableView.setShowRoot(false);
        searchField.textProperty().addListener(setupSearchField(treeTableView));
    }
}
