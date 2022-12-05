public class NoSavingNoAccumulation extends PatternJPanelFirstLevel {

    public NoSavingNoAccumulation() {
        this.add(getAddNewSavingMoney());
        this.add(getAddSavingMoney());
        this.add(getAddNewAccumulationMoney());
        this.add(getAddAccumulation());
    }
}
