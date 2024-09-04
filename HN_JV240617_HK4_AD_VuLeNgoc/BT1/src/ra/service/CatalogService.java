package ra.service;

import ra.business.entity.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements IGenericService<Catalog, Integer> {
    private List<Catalog> catalogs = new ArrayList<>();

    @Override
    public List<Catalog> getAll() {
        return catalogs;
    }

    @Override
    public void save(Catalog catalog) {
        try {
            catalogs.add(catalog);
            System.out.println("Danh mục đã được thêm thành công.");
        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @Override
    public Catalog findById(Integer catalogId) {
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogId() == catalogId) {
                return catalog;
            }
        }
        System.out.println("Không tìm thấy danh mục với mã: " + catalogId);
        return null;
    }

    @Override
    public void delete(Integer catalogId) {
        try {
            Catalog catalogToRemove = findById(catalogId);
            if (catalogToRemove != null) {
                catalogs.remove(catalogToRemove);
                System.out.println("Danh mục đã được xóa.");
            }
        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra khi xóa danh mục: " + e.getMessage());
        }
    }

    public void updateCatalogName(Integer catalogId, String newName) {
        Catalog catalog = findById(catalogId);
        if (catalog != null) {
            try {
                catalog.setCatalogName(newName);
                System.out.println("Tên danh mục đã được cập nhật.");
            } catch (IllegalArgumentException e) {
                System.out.println("Tên danh mục không hợp lệ: " + e.getMessage());
            }
        }
    }
}
