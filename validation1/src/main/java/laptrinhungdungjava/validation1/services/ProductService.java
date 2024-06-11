package laptrinhungdungjava.validation1.services;

import laptrinhungdungjava.validation1.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> productList = new ArrayList<>();

    public ProductService() {
        // Khởi tạo danh sách sản phẩm ở đây nếu cần
        // Ví dụ:
        // this.productList.add(new Product(1, "san pham 1", "1.jpg", 29312));
        // this.productList.add(new Product(2, "san pham 2", "2.jpg", 124246));
    }

    public void add(Product product) {
        productList.add(product);
    }

    public List<Product> getAll() {
        return productList;
    }

    public Product get(int id) {
        return productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void edit(Product editedProduct) {
        Product find = productList.get(editedProduct.getId());
        if(find != null) {
            find.setName(editedProduct.getName());
            find.setImage(editedProduct.getImage());
            find.setPrice(editedProduct.getPrice());
        }
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == editedProduct.getId()) {
                productList.set(i, editedProduct);
                return;
            }
        }
    }

    public void delete(int id) {
        productList.removeIf(p -> p.getId() == id);
    }

    public Product search(String keyword) {
        return productList.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
