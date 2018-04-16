class Element {

    private int itemId;
    int getItemId() {
        return itemId;
    }

    private int groupId;
    int getGroupId() {
        return groupId;
    }

    Element(int itemId, int groupId) {
        this.itemId = itemId;
        this.groupId = groupId;
    }
}