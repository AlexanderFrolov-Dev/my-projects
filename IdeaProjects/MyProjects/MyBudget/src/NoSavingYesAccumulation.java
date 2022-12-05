import javax.swing.*;

public class NoSavingYesAccumulation extends PatternJPanelFirstLevel {

    public NoSavingYesAccumulation() {
        this.add(getAddNewSavingMoney());
        this.add(getAddSavingMoney());
        this.add(getSelectAccumulationLabel());
        this.add(getSelectAccumulation());
    }
}
