package ra.business.entity;

public class Catalog {
    private int catalogId;  // Kiểu int
    private String catalogName;
    private String descriptions;

    public Catalog() {
    }

    public Catalog(int catalogId, String catalogName, String descriptions) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }


    @Override
    public String toString() {
        return "Catalog ID: " + catalogId + ", Catalog Name: " + catalogName + ", Descriptions: " + descriptions;
    }

}
