package laptrinhungdungjava.validation1.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    private int id;
    @NotBlank(message = "ten san pham khong duoc de trong")
    private String name;
    @Length(min = 0, max = 50, message = "ten hinh anh khong qua 50 ky tuc")
    private String image;
    @NotNull(message = "gia san pham khong duoc de trong")
    @Min(value = 1, message = "gia san pham khong duoc nho hon 1")
    @Max(value = 999999999, message = "gia san pham khong duoc lon hon 999999999")
    private long price;
}
