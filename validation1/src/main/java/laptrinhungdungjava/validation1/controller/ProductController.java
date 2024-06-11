package laptrinhungdungjava.validation1.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import laptrinhungdungjava.validation1.models.Product;
import laptrinhungdungjava.validation1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "products/create";
    }

    @PostMapping("/create")
    public String create(@Valid Product newProduct,
                         BindingResult result,
                         Model model,
                         @RequestParam("imageProduct") MultipartFile imageProduct) {
        if (result.hasErrors()) {
            model.addAttribute("product", newProduct);
            return "products/create";
        }

        if (imageProduct != null && !imageProduct.isEmpty()) {
            try {
                File saveFile = new ClassPathResource("static/images").getFile();
                String newImageFileName = UUID.randomUUID() + ".png";
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + newImageFileName);
                Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                newProduct.setImage(newImageFileName);
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý lỗi tải lên ảnh
            }
        }

        productService.add(newProduct);
        return "redirect:/products";
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("productlist", productService.getAll());
        return "products/index";
    }
    @PostMapping("/edit")
public String edit(@Valid Product editProduct, @RequestParam MultipartFile imageProduct, BindingResult result, Model model )
    {
        if(result.hasErrors()) {
            model.addAttribute("product", editProduct);
            return "products/edit";
        }
        if (imageProduct != null && !imageProduct.isEmpty()) {
            try {
                File saveFile = new ClassPathResource("static/images").getFile();
                String newImageFileName = UUID.randomUUID() + ".png";
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + newImageFileName);
                Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
               
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý lỗi tải lên ảnh
            }
        }
    productService.edit(editProduct);
        return "redirect:/products";
    }

}
