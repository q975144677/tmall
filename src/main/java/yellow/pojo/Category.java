package yellow.pojo;

import java.util.List;

public class Category {
    private Integer id;

    private String name;
    
    private List<Product> products;
    
    private List<List<Product>> productsByRows;
    
    

    public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<List<Product>> getProductsByRows() {
		return productsByRows;
	}

	public void setProductsByRows(List<List<Product>> productsByRows) {
		this.productsByRows = productsByRows;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}