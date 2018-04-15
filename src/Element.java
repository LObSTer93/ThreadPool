class Element {
    private int itemId;

    private int groupId;
    public int getGroupId() {
        return groupId;
    }

    Element(int itemId, int groupId) {
        this.itemId = itemId;
        this.groupId = groupId;
    }
}